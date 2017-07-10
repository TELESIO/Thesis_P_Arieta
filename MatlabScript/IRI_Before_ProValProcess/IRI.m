%Scelta del file da leggere
[file,folder]=uigetfile('*.csv','Select the file');
fic=fullfile(folder,file);

%lettura del file
[AccelerometroX,AccelerometroY,AccelerometroZ ,GravityX ,GravityY ,GravityZ, AccelerometroLineareX, AccelerometroLineareY, AccelerometroLineareZ, CampoMagneticoX ,CampoMagneticoY ,CampoMagneticoZ ,AZIMUTH_OrientamentoZ ,PITCH_OrientamentoX ,ROLL_OrientamentoY ,Livello_Sonoro ,Latitudine ,Longitudine,Altitudine_m ,Altitudine_google ,Velocita_KMH ,Precisione ,Orientamento ,Satelliti ,Tempo_ms,Data ]= CSV_File_Reader(fic,',',1, inf);


[LinearAccelerometerX, LinearAccelerometerY, LinearAccelerometerZ] = reorientedAcceleration(AccelerometroLineareX, AccelerometroLineareY, AccelerometroLineareZ, PITCH_OrientamentoX, ROLL_OrientamentoY);
figure
plot(Tempo_ms, LinearAccelerometerZ)
title('Reoriented')
xlabel('time')
ylabel('Acceleration')


%Ridefinizione della latitudine e della longitudine in segmenti piccoli, e
%calcolo della distance percorsa.
[Lat, Lon] = redefineLatitudeAndLongitude(Latitudine, Longitudine);
[Distance, DistancePro] = DistanceCalculator(Lat, Lon);

T(1,1) = 0.01;
for index=2:numel(Tempo_ms)
T(index,1) = T(index-1) + 0.01;
end


%rimozione del rumore di fondo generato dal motore
Acceleration = removeEngineRumors(LinearAccelerometerZ);
%rimozione dei punti con velocità nulla
Acceleration2 = removeNoVelocityComponents(Acceleration, Velocita_KMH);

%Low-Pass  moving average filter
Acceleration3 = windowingFilter(Acceleration2, 2);
Acceleration3 = windowingFilter(Acceleration3, 2);

Acceleration4 = Filters(Acceleration3,0.3);
figure
plot(Distance, Acceleration4)
title('After Filtering')


%Integrazione dell'accelerazione per ottenere la velocità
v = cumtrapz(T,Acceleration4);
v_fit = Filters(v,0.15);



%integrazione della velocità per ottenere lo spostamento
d = cumtrapz(T, v_fit);
d = -d;

max_distance = max(DistancePro)/2;
if(max_distance<1)
    max_distance = 1;
end
%Raccolta dei punti all'interno di una distanza specifica.
[verticalDisplacement, DistanceProgress, DistancesCOv, NewLatitudine, NewLongitudine] = handleIntoSpecificDistance(d, DistancePro, Lat, Lon, 1);

figure
plot(Distance, d)
title('Displacement')
xlabel('Distance')
ylabel('Displacement')



figure
subplot(3,1,1)
plot(Distance, Acceleration4)
subplot(3,1,2)
plot(Distance, v_fit)
subplot(3,1,3)
plot(Distance, d)


 

    figure
    plot(DistanceProgress, verticalDisplacement);
    title('Risultato Finale')
    xlabel('Distance')
    ylabel('Displacement')

    
    %Data parsing
a = Data{1,1}(1:10);
t = datetime(Data,'InputFormat','yyyy-MM-dd HH:mm:ss:SSS');
DateString = datestr(t(1:numel(verticalDisplacement)));
M = table(verticalDisplacement,NewLatitudine,NewLongitudine,DateString,DistanceProgress);

%Scelta del nome file e della cartella in cui salvare
[FileName,PathName] = uiputfile('*.csv','SaveFile');
fic=fullfile(PathName,FileName);


%salvataggio
writetable(M,fic,'delimiter',',');

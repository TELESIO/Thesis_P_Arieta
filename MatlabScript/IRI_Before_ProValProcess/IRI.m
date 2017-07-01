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

%rimozione dei punti con velocità nulla
Acceleration = removeNoVelocityComponents(LinearAccelerometerZ, Velocita_KMH);

%rimozione del rumore di fondo generato dal motore
Acceleration2 = removeEngineRumors(Acceleration);


figure
subplot(3,1,1)
plot(Distance, AccelerometroLineareZ)
subplot(3,1,2)
plot(Distance, Acceleration)
subplot(3,1,3)
plot(Distance, Acceleration2)


%Low-Pass  moving average filter
Acceleration3 = windowingFilter(Acceleration2, 5);

%low-pass FIR-based filters
Acceleration4 = Cheby2Order(100, Acceleration3);

figure
subplot(2,1,1)
plot(Distance, Acceleration2)
subplot(2,1,2)
plot(Distance, Acceleration4)


figure
plot(Distance, Acceleration4)
title('After Filtering')


%Integrazione dell'accelerazione per ottenere la velocità
v = cumtrapz(Acceleration4);
v = v * 0.01;

figure
plot(Distance, v)
title('Velocity')
xlabel('Distance')
ylabel('Velocity')

%low-pass FIR-based filters
v_fit = Cheby2Order(100, v);



%integrazione della velocità per ottenere lo spostamento
d = cumtrapz(v_fit);
d = d * 0.01;


%Raccolta dei punti all'interno di una distanza specifica.
[verticalDisplacement, DistanceProgress, DistancesCOv, NewLatitudine, NewLongitudine] = handleIntoSpecificDistance(d, DistancePro, Lat, Lon, 5);

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


    if(mean(verticalDisplacement) < 0)

        verticalDisplacement = verticalDisplacement - mean(verticalDisplacement);
    end
 

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

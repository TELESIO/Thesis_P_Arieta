
%selezione del file da processare
[file,folder]=uigetfile('*.csv','Select the file');
fic=fullfile(folder,file);

%lettura del file
[AccelerometroX,AccelerometroY,AccelerometroZ ,GravityX ,GravityY ,GravityZ, AccelerometroLineareX, AccelerometroLineareY, AccelerometroLineareZ, CampoMagneticoX ,CampoMagneticoY ,CampoMagneticoZ ,AZIMUTH_OrientamentoZ ,PITCH_OrientamentoX ,ROLL_OrientamentoY ,Livello_Sonoro ,Latitudine ,Longitudine,Altitudine_m ,Altitudine_google ,Velocita_KMH ,Precisione ,Orientamento ,Satelliti ,Tempo_ms,Data ]= CSV_File_Reader(fic,',',1, inf);

figure
plot(Tempo_ms, AccelerometroLineareZ)
title('Raw Accelerometer')
xlabel('time')
ylabel('Acceleration')

%ridefinizione della latitudine e longitudine in segmenti piccoli
[Lat, Lon] = redefineLatitudeAndLongitude(Latitudine, Longitudine);
[LinearAccelerometerX, LinearAccelerometerY, LinearAccelerometerZ] = reorientedAcceleration(AccelerometroLineareX, AccelerometroLineareY, AccelerometroLineareZ, PITCH_OrientamentoX, ROLL_OrientamentoY);
figure
plot(Tempo_ms, LinearAccelerometerZ)
title('Reoriented')
xlabel('time')
ylabel('Acceleration')


%rimozione del rumore di fondo generato dal motore
Acceleration = removeEngineRumors(LinearAccelerometerZ);
figure
plot(Tempo_ms, Acceleration)
title('Accelerometer without Engine Rumors')
xlabel('time')
ylabel('Acceleration')


%rimozione dei punti con velocità nulla
Acceleration2 = removeNoVelocityComponents(Acceleration, Velocita_KMH);
figure
plot(Tempo_ms, Acceleration2)
title('Accelerometer without velocity')
xlabel('time')
ylabel('Acceleration')


%rimozione di tutti i valori al di sotto di una specifica soglia
Acceleration3 = ResetsValuesBelowSpecificThreshold(Acceleration2);
figure
plot(Tempo_ms, Acceleration3)
title('Accelerometer without values under specific th')
xlabel('time')
ylabel('Acceleration')


figure
subplot(2,2,1)
plot(Tempo_ms, LinearAccelerometerZ);
subplot(2,2,2)
plot(Tempo_ms, Acceleration)
subplot(2,2,3)
plot(Tempo_ms, Acceleration2)
subplot(2,2,4)
plot(Tempo_ms, Acceleration3)

%vengono presi i punti critici 
[Critical, NewLatitudine, NewLongitudine] = takeCriticalPoint(Acceleration3, Lat, Lon );

%vengono riprocessate la latitudine e la longitudine per raggruppare i
%punti in distanze specifiche
[Lat, Lon] = redefineLatitudeAndLongitude(NewLatitudine, NewLongitudine);
[Distance, DistancePro] = DistanceCalculator(Lat, Lon);
figure
plot(Distance, Critical)
title('Critical Point')
xlabel('Distance')
ylabel('Acceleration')

%raggruppamento di tutti i punti i vicini in meno di 5mt tra di loro.
[cr, dt, df, nwlat, nwlon] = handleIntoSpecificDistance(Critical, DistancePro, NewLatitudine, NewLongitudine,5);

%data parsing
a = Data{1,1}(1:10);
t = datetime(Data,'InputFormat','yyyy-MM-dd HH:mm:ss:SSS');
DateString = datestr(t(1:numel(cr)));
M = table(cr,nwlat,nwlon,DateString);

%scelta della cartella e del nome del file
[FileName,PathName] = uiputfile('*.csv','SaveFile');
fic=fullfile(PathName,FileName);

%salvataggio del file processato.
writetable(M,fic,'delimiter',',');
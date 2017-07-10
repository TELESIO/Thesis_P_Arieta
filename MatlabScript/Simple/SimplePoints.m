
%Selezione del file da leggere
[file,folder]=uigetfile('*.csv','Select the file');
fic=fullfile(folder,file);


%Lettura del file
[AccelerometroX,AccelerometroY,AccelerometroZ ,GravityX ,GravityY ,GravityZ, AccelerometroLineareX, AccelerometroLineareY, AccelerometroLineareZ, CampoMagneticoX ,CampoMagneticoY ,CampoMagneticoZ ,AZIMUTH_OrientamentoZ ,PITCH_OrientamentoX ,ROLL_OrientamentoY ,Livello_Sonoro ,Latitudine ,Longitudine,Altitudine_m ,Altitudine_google ,Velocita_KMH ,Precisione ,Orientamento ,Satelliti ,Tempo_ms,Data ]= CSV_File_Reader(fic,',',1, inf);

figure
plot(Tempo_ms, AccelerometroLineareZ)
title('Raw Accelerometer')
xlabel('time')
ylabel('Acceleration')


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


%rimozione del rumore di fondo generato dal motore
Acceleration = removeEngineRumors(LinearAccelerometerZ);
figure
plot(Tempo_ms, Acceleration2)
title('Accelerometer without Engine Rumors')
xlabel('time')
ylabel('Acceleration')



%rimozione dei punti con velocità nulla
Acceleration2 = removeNoVelocityComponents(Acceleration, Velocita_KMH);
figure
plot(Tempo_ms, Acceleration)
title('Accelerometer without velocity')
xlabel('time')
ylabel('Acceleration')

%Raccolta dei punti all'interno di una distanza specifica.
[Acceleration3, DistanceProgress, DistancesCOv, NewLatitudine, NewLongitudine] = handleIntoSpecificDistance(Acceleration, DistancePro, Lat, Lon, 10);
figure
plot(DistanceProgress, Acceleration3)
title('Accelerometer Point Result')
xlabel('Distance')
ylabel('Acceleration')


%data parsign
a = Data{1,1}(1:10);
t = datetime(Data,'InputFormat','yyyy-MM-dd HH:mm:ss:SSS');
DateString = datestr(t(1:numel(Acceleration3)));
M = table(Acceleration3,NewLatitudine,NewLongitudine,DateString);

%Selezione della cartella 
[FileName,PathName] = uiputfile('*.csv','SaveFile');
fic=fullfile(PathName,FileName);

writetable(M,fic,'delimiter',',');
clear all
clc
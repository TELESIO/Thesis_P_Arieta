%Questo script viene utilizzato per leggere interamente il contenuto del
%file .csv. Bisogna specificare all'interno dello script stesso, il nome
%del file da leggere presente nella Working Directory.

file_Name = 'VeryFast.csv';
 
type_Of_Delimiter = [',' , ';'];

%Scelgo il delimitatore opportuno con cui sono suddivisi i vari valori per
%riga del file.
delimiter = type_Of_Delimiter(1);

fileID = fopen(file_Name);

start_Row = 2;
%Viene definito il formato specifico dei tipi di valori presenti nelle
%colonne del file
data_File_Format='%f%f%f%f%f%f%f%f%f%f%f%f%f%f%f%f%f%f%f%s%s%f%s';

%Salvo i dati del file specificandone le varie impostazioni di lettura.
data_File = textscan(fileID,data_File_Format,'Delimiter',delimiter,'HeaderLines',start_Row-1,'EmptyValue', NaN,'ReturnOnError',false);

%Chiudo il file.
fclose(fileID);

%Creo gli Array contenenti i dati.
AccelerometroX = data_File{1};
AccelerometroY = data_File{2};
AccelerometroZ = data_File{3};
GravityX = data_File{4};
GravityY = data_File{5};
GravityZ = data_File{6};
CampoMagneticoX = data_File{7};
CampoMagneticoY = data_File{8};
CampoMagneticoZ = data_File{9};
AZIMUTH_OrientamentoZ = data_File{10};
PITCH_OrientamentoX = data_File{11};
ROLL_OrientamentoY = data_File{12};
Livello_Sonoro = data_File{13};
Latitudine = data_File{14};
Longitudine = data_File{15};
Altitudine_m = data_File{16};
Altitudine_google = data_File{17};
Velocita_KMH = data_File{18};
Precisione = data_File{19};
Orientamento = data_File{20};
Satelliti = data_File{21};
Tempo_ms = data_File{22};
Data = data_File{23};

clearvars data_File file_Name delimiter fileID data_File_Format type_Of_Delimiter start_Row

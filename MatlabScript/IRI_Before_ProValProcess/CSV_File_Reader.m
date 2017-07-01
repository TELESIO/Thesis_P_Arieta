%Questa Funzione serve a leggere il contenuto del File .csv dei dati
%registrati dallo smartphone con l'app Andro Sensor. Ciascuna lettura è
%raggruppata all'intero di Vettori, nel momento in cui essa viene chiamata
%all'interno della command window di matlab bisognerà passare come
%parametri il nome del file presente all'interno della working directory,
%il delimitatore che differenzia le varie letture sulla riga, la riga da
%cui si vuole iniziare la lettura, e la riga in cui la lettura deve
%terminare, in modo tale da consentire la lettura di uno specifico range di valori.
%N.B se si vuole leggere tutto il file bisogna indicare come riga finale il valore inf. 

%Quando la funzione viene chiamata all'interno della command window per
%ottenere i vettori su cui lavorare nel workspace bisognerà appunto
%utilizzare il formato: [AccelerometroX,AccelerometroY,AccelerometroZ ,GravityX ,GravityY ,GravityZ ,CampoMagneticoX ,CampoMagneticoY ,CampoMagneticoZ ,AZIMUTH_OrientamentoZ ,PITCH_OrientamentoX ,ROLL_OrientamentoY ,Livello_Sonoro ,Latitudine ,Longitudine,Altitudine_m ,Altitudine_google ,Velocita_KMH ,Precisione ,Orientamento ,Satelliti ,Tempo_ms,Data ]= CSV_File_Reader('NomeFile.csv','Delimitatore',StartRow,EndRow);

function [AccelerometroX,AccelerometroY,AccelerometroZ ,GravityX ,GravityY ,GravityZ, AccelerometroLineareX, AccelerometroLineareY, AccelerometroLineareZ, CampoMagneticoX ,CampoMagneticoY ,CampoMagneticoZ ,AZIMUTH_OrientamentoZ ,PITCH_OrientamentoX ,ROLL_OrientamentoY ,Livello_Sonoro ,Latitudine ,Longitudine,Altitudine_m ,Altitudine_google ,Velocita_KMH ,Precisione ,Orientamento ,Satelliti ,Tempo_ms,Data ]= CSV_File_Reader(file_Name,delimiter,start_Row,end_Row)


if(end_Row ~= inf)
   end_Row = end_Row-start_Row+1;
end





%Apro il file mediante la funzione fopen~~
fileID = fopen(file_Name);

%Viene definito il formato specifico dei tipi di valori presenti nelle
%colonne del file
data_File_Format='%f%f%f%f%f%f%f%f%f%f%f%f%f%f%f%f%f%f%f%s%f%f%s%s%f%s';

%Salvo i dati del file specificandone le varie impostazioni di lettura.
data_File = textscan(fileID,data_File_Format,end_Row,'Delimiter',delimiter,'HeaderLines',start_Row,'EmptyValue', NaN,'ReturnOnError',false);

%Chiudo il file.
fclose(fileID);

%Creo gli Array contenenti i dati.
AccelerometroX = data_File{1};
AccelerometroY = data_File{2};
AccelerometroZ = data_File{3};
GravityX = data_File{4};
GravityY = data_File{5};
GravityZ = data_File{6};
AccelerometroLineareX = data_File{7};
AccelerometroLineareY = data_File{8};
AccelerometroLineareZ = data_File{9};
CampoMagneticoX = data_File{10};
CampoMagneticoY = data_File{11};
CampoMagneticoZ = data_File{12};
AZIMUTH_OrientamentoZ = data_File{13};
PITCH_OrientamentoX = data_File{14};
ROLL_OrientamentoY = data_File{15};
Livello_Sonoro = data_File{16};
Latitudine = data_File{17};
Longitudine = data_File{18};
Altitudine_m = data_File{19};
Altitudine_google = data_File{20};
Velocita_KMH = data_File{21};
Precisione = data_File{22};
Orientamento = data_File{23};
Satelliti = data_File{24};
Tempo_ms = data_File{25};
Data = data_File{26};

clearvars data_File file_Name delimiter fileID data_File_Format

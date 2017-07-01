[file,folder]=uigetfile('*.xlsx','Select the file obtained from Proval');
fic=fullfile(folder,file);
%% Read data
data=xlsread(fic);

%% Allocate imported array to column variable names
StartDistancem = data(:,1);
StopDistancem = data(:,2);
CenterIRImkm = data(:,4);

%% Clear temporary variables
clearvars data raw file folder fic;

%% Initialize variables.
[file,folder]=uigetfile('*.csv','Select the file pass to ProVal');
filename=fullfile(folder,file);

delimiter = ',';
startRow = 2;

%% Format string for each line of text:
%   column1: double (%f)
%	column2: double (%f)
%   column3: double (%f)
%	column4: datetimes (%{dd-MMM-yyyy HH:mm:ss}D)
%   column5: double (%f)
formatSpec = '%f%f%f%{dd-MMM-yyyy HH:mm:ss}D%f%[^\n\r]';

%% Open the text file.
fileID = fopen(filename,'r');

%% Read columns of data according to format string.
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter, 'HeaderLines' ,startRow-1, 'ReturnOnError', false);

%% Close the text file.
fclose(fileID);

%% Post processing for unimportable data.
% No unimportable data rules were applied during the import, so no post
% processing code is included. To generate code which works for
% unimportable data, select unimportable cells in a file and regenerate the
% script.

%% Allocate imported array to column variable names
NewLatitudine1 = dataArray{:, 2};
NewLongitudine1 = dataArray{:, 3};
DateString1 = dataArray{:, 4};
DistanceProgress1 = dataArray{:, 5};

%% Clear temporary variables
clearvars filename delimiter startRow formatSpec fileID dataArray ans;
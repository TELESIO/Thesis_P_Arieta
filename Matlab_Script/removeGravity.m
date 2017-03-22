function[AccelerometerY_WithouthGravity] = removeGravity(RawAccelerometerY, Time)

%%Questa Funzione viene utilizzata per rimuovere dal segnale di Accelerazione
%%Verticale la gravità tenendo conto del filtro consigliato da Android al
%%link:
%% https://developer.android.com/reference/android/hardware/SensorEvent.html#values in merit
%%



num_elements = numel(Time);



%%Alpha dev'essere un valore compreso sempre tra zero e uno e viene
%%calcolato in relazione alle registrazioni dei vari tempi.
alpha = zeros(num_elements,1);
for index = 1:numel(Time)-1
    alpha(index) = Time(index)/(Time(index) + (Time(index+1)-Time(index)));
end


%%definisco l'ultimo valore del vettore colonna.
alpha(numel(alpha), 1 ) = Time(numel(alpha))/(Time(numel(alpha)) + (Time(numel(alpha))-Time(numel(alpha)-1)));



%%In questo caso considero la gravita  costante ad ogni misurazione
gravity_constant = 9.81;
gravity = zeros(num_elements,1);
for index = 1:numel(RawAccelerometerY)
    gravity(index, 1) = alpha(index)*gravity_constant + (1-alpha(index))*RawAccelerometerY(index);
end


%%Oppure la gravita va considerata ad ogni misurazione facendo riferimento
%%alla precedente?
% gravity = zeros(num_elements,1);
%   gravity(1,1) = alpha(1)*9.81 + (1-alpha(1))*RawAccelerometerY(1);
%  for index = 2:numel(RawAccelerometerY)
%      gravity(index, 1) = alpha(index)*gravity(index-1) + (1-alpha(index))*RawAccelerometerY(index);
%  end


%% A questo punto posso calcolare i valori dei dati Accelerometrici senza la Gravita'.
AccelerometerY_WithouthGravity = zeros(num_elements,1);
%linear acceleration = Raw_AccelerometerY - Gravity
for index = 1:numel(gravity)
    AccelerometerY_WithouthGravity(index,1) =  RawAccelerometerY(index) - gravity(index);
end



%%Per vedere su un grafico la differenza tra i dati originali e i dati
%%senza gravita' decomentarre questa parte.

% plot (Time, RawAccelerometerY,Time,AccelerometerY_WithouthGravity)
% legend('Acc G','Acc no G')
% title('Acceleration With G vs Acceleration No G')
% xlabel('Time')
% ylabel('Vertical Acceleration')

clearvars gravity_constant gravity  alpha index num_elements;

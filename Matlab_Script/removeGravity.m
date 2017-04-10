function[vertical_Acceleration_Without_Gravity] = removeGravity(vertical_Acceleration, Time)

%Questa Funzione viene utilizzata per rimuovere dal segnale di Accelerazione
%Verticale la gravità tenendo conto del filtro passa basso, 
%%consigliato su Android Developer al link:
% https://developer.android.com/reference/android/hardware/SensorEvent.html#values in merita
%Per rendere le registrazioni dei dati accelerometrici di dispositivi
%Android privi della componente g.

num_elements = numel(Time);

%%Alpha dev'essere un valore compreso sempre tra zero e uno e viene
%%calcolato in relazione alle registrazioni dei vari tempi.

alpha = zeros(num_elements,1);

for index = 1:numel(Time)-1
    alpha(index) = Time(index)/(Time(index) + (Time(index+1)-Time(index)));
end

%%definisco l'ultimo valore del vettore colonna.
alpha(numel(alpha), 1 ) = Time(numel(alpha))/(Time(numel(alpha)) + (Time(numel(alpha))-Time(numel(alpha)-1)));

%%A questo punto possiamo definire la componente di gravità alla
%%registrazione i-esima che successivamente verrà sottratto al i-esimo
%%valore di accelerazione.

%Si parte dalla condizione in cui il primo valore di gravità è zero, in
%modo tale che potremmo calcolare il valore al passo i-esimo facendo
%riferimento al valore del passo precedente (i-1).


gravity = zeros(num_elements,1);
  gravity(1,1) = alpha(1)*0 + (1-alpha(1))*vertical_Acceleration(1);
 for index = 2:numel(vertical_Acceleration)
     gravity(index, 1) = alpha(index)*gravity(index-1) + (1-alpha(index))*vertical_Acceleration(index);
 end


% A questo punto posso calcolare i valori dei dati Accelerometrici senza la Gravita'.
vertical_Acceleration_Without_Gravity = zeros(num_elements,1);
for index = 1:numel(gravity)
    vertical_Acceleration_Without_Gravity(index,1) =  vertical_Acceleration(index) - gravity(index);
end


%%Per vedere su un grafico la differenza tra i dati originali e i dati
%%senza gravita' decomentarre questa parte.

% plot (Time, vertical_Acceleration,Time,vertical_Acceleration_Without_Gravity)
% legend('Acc G','Acc no G')
% title('Acceleration With G vs Acceleration No G')
% xlabel('Time')
% ylabel('Vertical Acceleration')

clearvars gravity_constant gravity  alpha index num_elements;

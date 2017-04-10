function[vertical_Acceleration_Smoothed] = movingAverage(vertical_Acceleration, window_size)

%%Questa funzione, applica un filtro a media mobile sui dati di
%%accelerazione verticale che riceve in ingresso, la finestra su cui
%%verrà applicata la media tra i dati sarà relativamente piccola in modo
%%tale da non distorcere il segnale.
vertical_Acceleration_Smoothed = tsmovavg(vertical_Acceleration,'s',window_size,1);



%%Per non avere problemi durante la fase d'integrazione, vengono azzerati l'1% dei
%%valor totali, ovvero lo 0.5% iniziale e finale.
%%Questo perchè i primi dati si riferiscono principalmente all'accensione della macchina, e
%%quindi alle prime vibrazioni che il motore applicherà sulla vettura, e ci
%%troveremo dei picchi molto alti, mentre i finali si riferiscono alla fase
%%di chiusura, quindi passiamo da una fase di vibrazioni dovute sempre
%%all'autovettura accessa, ad una fase in cui risultano non esserci
%%vibrazioni.
to_Reset = floor((length(vertical_Acceleration)/50));
for index = (1:to_Reset)
    vertical_Acceleration_Smoothed(index,1)=0;
end
startPoint = floor(length(vertical_Acceleration) - to_Reset - 1);

for index = (startPoint : length(vertical_Acceleration))
    vertical_Acceleration_Smoothed(index,1)=0;
end

%%Per vedere la differenza tra i dati originali e i dati a cui e' stato
%%applicato un filtro di media mobile decommentare.

% plot (Tempo_ms, AccelerometroY,Tempo_ms,AccY_MovAverage);
% legend('Acc No Mv','Acc Mv');
% title('Acceleration vs Acceleration Mvg');
% xlabel('Time');
% ylabel('Vertical Acceleration');


clearvars index;


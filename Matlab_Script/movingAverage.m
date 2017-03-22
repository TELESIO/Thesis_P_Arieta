function[AccY_MovAverage] = movingAverage(AccelerometroY, window_size);

%%con l'opzione s indico che voglio applicare il filtro a media mobile
%%semplice. per applicare 

AccY_MovAverage = tsmovavg(AccelerometroY,'s',window_size,1);



%%la funzione tsmovag assegna ai valori compresi tra [1, window_size] NaN,
%%imposto tali valori a 0 per non avere problemi di calcolo
%%successivamente.
for index = (1:window_size)
    AccY_MovAverage(index,1)=0;
end


%%Per vedere la differenza tra i dati originali e i dati a cui e' stato
%%applicato un filtro di media mobile decommentare.

% plot (Time, AccelerometroY,Time,AccY_MovAverage);
% legend('Acc No Mv','Acc Mv');
% title('Acceleration vs Acceleration Mvg');
% xlabel('Time');
% ylabel('Vertical Acceleration');


clearvars index;


function[Min_Passband_Frequency, Max_Passband_Frequency, MinStpBndFrequency, Max_Stopband_Frequency] = ChebyFrequency(signal_amplitude, frequency)

%Questa funzione trova le frequenze che devono essere utilizzate per il
%filtro Passa Banda di Chebyshev, in particolar modo, si focalizza
%nell'individuare il Range in cui cadono i valori di Frequenza Massima,
%e inoltre di individuare i valori di frequenza entro il quale il segnale
%verrà smussato (StopBand), ma che comunque sia dovranno restare il più vicino
%possibile al range in cui la Frequenza passerà.


%%Per esempio se notiamo che il nostro segnale ha dei picchi di frequenze
%%alti intorno a 3Hz, e altri picchi di Frequenza alti (Anche la metà o poco meno del picco massimo)
%%che si propagano fino a 15Hz, allora il range passabanda sarà [3,15],
%%mentre il Range relativo allo StopBand potrà essere per esempio [2,16].
%%Se definiamo quindi per esempio il range PassBand (Y), e il range
%%StopBand (Z), ciò che deve risultare fondamentale è che Y dev'essere
%%contenuto in Z, e non il contrario.

%%Nell'individuare i range si effettuano questi passi:
%%1. Viene individuato il picco massimo dell'amplitudine del segnale, e
%%viene presa la corrispettiva frequenza, in corrsipondenza del picco,
%%vengono quindi impostati, la Min_Passband_Frequency, e la 
%%MinStpBndFrequency che sarà pari al valore intero della
%%Min_Passband_Frequency.

%%Successivamente per trovare gli altri segnali alti, verrano rimossi e impostati al valore 
%%medio del segnale di amplituine tutti i picchi che sono al di sopra della
%%metà del valore di amplitudine massimo precedentemente trovato

%%Dopo questa operazione verrano presi in considerazione soltanto i picchi
%%la cui altezza minima è almeno un terzo del picco massimo trovato
%%precedentemente.
%%Individuati i picchi di questo range verrà preso in considerazione 
%%quello la cui Frequenza sull'asse delle X è più grande rispetto a tutti
%%gli altri, e saranno impostati i valori, Max_Passband_Frequency = (frequency(max(xPosition2)));
%%Max_Passband_Frequency;
%%Max_Stopband_Frequency = floor(Max_Passband_Frequency)+1;


%%Nel caso in cui Min_Passband_Frequency > Max_Passband_Frequency;, perchè
%%il picco massimo assoluto si trovava in frequenze più alte rispetto ai
%%picchi relativi trovati precedentemente, i valori verranno invertiti.



% subplot(3,1,1)
% plot(dataXAxis, dataYAxis);
[pk,xPosition] = findpeaks(signal_amplitude,'NPeaks',1,'SortStr','descend');
Min_Passband_Frequency = (frequency(xPosition));
MinStpBndFrequency = floor(Min_Passband_Frequency);

% subplot(3,1,2)
% plot(dataXAxis, dataYAxis);
% hold on 
% plot(dataXAxis(xPosition), pk, 'or');
% hold off;

meanValue = mean(signal_amplitude);

cutOff = pk  / 1.5;
minPeakHeight = pk / 3.5;
tallPeakIndexes = signal_amplitude > cutOff; 
signal_amplitude(tallPeakIndexes) = meanValue;
clc

[~,xPosition2] = findpeaks(signal_amplitude,'SortStr','descend','MinPeakHeight',minPeakHeight);
Max_Passband_Frequency = (frequency(max(xPosition2)));
Max_Stopband_Frequency = floor(Max_Passband_Frequency)+1;


if(Min_Passband_Frequency > Max_Passband_Frequency)
    temp_pssbnd = Max_Passband_Frequency;
    temp_stpbnd = Max_Stopband_Frequency;
    Max_Stopband_Frequency = MinStpBndFrequency;
    Max_Passband_Frequency = Min_Passband_Frequency;
    MinStpBndFrequency = temp_stpbnd;
    Min_Passband_Frequency = temp_pssbnd;
end
% subplot(3,1,3)
% plot(dataXAxis, dataYAxis);
% hold on 
% plot(dataXAxis(xPosition2), pk2, 'or');
% hold off;
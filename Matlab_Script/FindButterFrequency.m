function[max_frequency] = FindButterFrequency(dataYAxis, dataXAxis)

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Questa funzione viene utilizzata prima di applicare il
% low-pass Butterworth filter, in quanto essa si occupa 
% di identificare la frequenza a cui corrsiponde
% il picco massimo dello spettro del segnale, relativo
% alla trasformata di Fourier.
% 
% Viene richiamata la funzione di Matlab per identificare il picco 
% più alto, e successivamente viene presa la frequenza sull'asse delle x
% in cui corrisponde il massimo assoluto.




[~,xPosition] = findpeaks(dataYAxis,'NPeaks',1,'SortStr','descend');
max_frequency = (dataXAxis(xPosition));

% figure(1)
% plot(dataXAxis, dataYAxis);
% hold on 
% plot(dataXAxis(xPosition), pk, 'or');
% hold off;
% 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%   - Lettura del File
%   - Rimozione Gravità.
% 	- Filtro Media Mobile (Con valore di finestra x relativamente piccolo, x € [2,4], altrimenti i dati vengono 	
% 				distorti troppo).
% 	- Rimozione DC offset (Tutte le componenti vicino alle frequenze di 0Hz)
% 	- Analisi di Fourier -> (Troviamo il Range di Frequenze Alte). CF.
% 	- Applicazione del Filtro di Chebyshev
% 	- Integrazione dell'accelerazione (cumtrpaz)
% 	- Analisi di Fourier sulla velocità -> (Troviamo il Range di Frequenze Alte). CF.
% 	- Applicazione del Filtro di Chebyshev
% 	- Integrazione della velocità per ottenere lo spostamento.
%   - Rappresentazione dei risultati.
 


CSV_Reader_File_Scr
AccelerometroZ = removeGravity(AccelerometroZ, Tempo_ms);
AccelerometroZ = movingAverage(AccelerometroZ,2);
Y = removeDC_Components(AccelerometroZ,217);
[out, freq] = FourierAnalysis(100, Y);
[minpfre, maxpfre, minstfre, maxstpfre] = ChebyFrequency(out,freq);
acc_refit = Cheby2Order(100,minpfre,maxpfre,minstfre,maxstpfre,Y,Tempo_ms);
v = cumtrapz(acc_refit);
[out, freq] = FourierAnalysis(100, v);
[minpfre, maxpfre, minstfre, maxstpfre] = ChebyFrequency(out,freq);
v_refit = Cheby2Order(100,minpfre,maxpfre,minstfre,maxstpfre,v,Tempo_ms);
d = cumtrapz(v_refit);

t = Tempo_ms;

figure(1)
plot(t,d_res);
title('Displacement Range [0,1]')
xlabel('Time(ms)');
ylabel('Displacement (m)');


figure(2)
title('Accelerometer: Raw vs Filt')
subplot(2,1,1)
plot(t,AccelerometroZ);
xlabel('Time(ms)');
ylabel('RawAcc (m/s^2)');
subplot(2,1,2)
plot(t,acc_refit);
xlabel('Time(ms)');
ylabel('First Fit (m/s^2)');

figure(3)
subplot(2,1,1)
plot(t,v);
xlabel('time');
ylabel('vel after int');
subplot(2,1,2)
plot(t,v_refit);
xlabel('time');
ylabel('second fit');


figure(4)
title('Acc - Vel - Disp');
subplot(3,1,1)
plot(t,acc_refit);
xlabel('Time (ms)');
ylabel('Vertical Acceleration (m/s^2)');
subplot(3,1,2)
plot(t,v_refit);
xlabel('Time (ms)');
ylabel('Velocity (m/s)');
subplot(3,1,3)
plot(t,d1);
xlabel('Time (ms)');
ylabel('Displacement (m)');
subplot(3,1,3)



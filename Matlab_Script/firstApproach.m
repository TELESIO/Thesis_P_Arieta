%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%   -Lettura del File
%   - Rimozione Gravità.
% 	- Filtro Media Mobile (Con valore di finestra x relativamente piccolo, x € [2,4], altrimenti i dati vengono 	
% 				distorti troppo).
% 	- Rimozione DC offset (Tutte le componenti vicino alle frequenze di 0Hz)
% 	- Analisi di Fourier -> (Troviamo il Range di Frequenze Alte). CF.
% 	- Applicazione del Filtro di Chebyshev
% 	- Analisi di Fourier -> (Troviamo il Range di Frequenze Alte). CF.
% 	- Applicazione del Butterworth Filter sulla frequenza più Alta trovata dall'analisi di Fourier sui dati 
% 	dell'accelerazione per smussare e non alterare il più possibile il segnale.
% 	- Integrazione dell'accelerazione (cumtrpaz )
% 	- Analisi di Fourier sulla velocità -> (Troviamo il Range di Frequenze Alte). CF.
% 	- Applicazione del Filtro di Chebyshev
% 	- Analisi di Fourier -> (Troviamo il Range di Frequenze Alte). CF.
% 	- Applicazione del Butterworth Filter sulla frequenza più Alta trovata dall'analisi di Fourier sui dati
% 	della velocità per smussare e non alterare il più possibile il segnale.
% 	- Integrazione della velocità per ottenere lo spostamento.
%   - Rappresentazione dei Risultati


CSV_Reader_File_Scr
AccelerometroZ = removeGravity(AccelerometroZ, Tempo_ms);
AccelerometroZ = movingAverage(AccelerometroZ,2);
Y = removeDC_Components(AccelerometroZ,217);
[out, freq] = FourierAnalysis(100, Y);
[minpfre, maxpfre, minstfre, maxstpfre] = ChebyFrequency(out,freq);
acc_fit = Cheby2Order(100,minpfre,maxpfre,minstfre,maxstpfre,Y,Tempo_ms);
[out, freq] = FourierAnalysis(100, acc_fit);
freq = FindButterFrequency(out,freq);
acc_refit = Butter(100,freq,acc_fit);
v = cumtrapz(acc_refit);
[out, freq] = FourierAnalysis(100, v);
[minpfre, maxpfre, minstfre, maxstpfre] = ChebyFrequency(out,freq);
v_fit = Cheby2Order(100,minpfre,maxpfre,minstfre,maxstpfre,v,Tempo_ms);
[out, freq] = FourierAnalysis(100, v_fit);
freq = FindButterFrequency(out,freq);
v_refit = Butter(100,freq,v_fit);
d = cumtrapz(v_refit);

t = Tempo_ms;

d = -d;

d1 = tsmovavg(d,'s',5,1);


d_max = max(d1);
d_res = d1 / d_max;
M = [d_res, Latitudine, Longitudine];
dlmwrite('DisplacementHTMLRepresentation/TestingDisplacement/file/sample.csv',M,'delimiter',',','precision','%.30f');

figure(1)
plot(t,d_res);
title('Displacement Range [0,1]')
xlabel('Time(ms)');
ylabel('Displacement (m)');


figure(2)
title('Accelerometer: Raw vs Filt')
subplot(3,1,1)
plot(t,AccelerometroZ);
xlabel('Time(ms)');
ylabel('RawAcc (m/s^2)');
subplot(3,1,2)
plot(t,acc_fit);
xlabel('Time(ms)');
ylabel('First Fit (m/s^2)');
subplot(3,1,3)
plot(Tempo_ms,acc_refit)
xlabel('Time');
ylabel('Second Fit (m/s^2)');



figure(3)
title('Vel: Int vs Filt');
subplot(3,1,1)
plot(t,v);
xlabel('Time(ms)');
ylabel('Vel (m/s)');
subplot(3,1,2)
plot(t,v_fit);
xlabel('Time');
ylabel('First Fit (m/s)');
subplot(3,1,3)
plot(t,v_refit);
xlabel('Time');
ylabel('Second Fit (m/s)');
subplot(3,1,3)

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



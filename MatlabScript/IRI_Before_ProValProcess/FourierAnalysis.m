function [dOut, f] = FourierAnalysis(Fs,dIn)

%%Questa Funzione effettua l'analisi di Fourier, sui dati ricevuti in
%%ingresso (dIn), ed ogni dato è registrato alla frequenza in Hz Fs.
%%(Sample Rate)

L = length(dIn);           % Length of signal 
f = Fs*(0:(L/2))/L;    %%Viene creato il dominio di Frequenza.                           
Y = dIn;
Y = fft(Y);             %%Viene applicata la trasformata di Fourier  


%%Definiamo lo Spetto del segnale:
%%Inizialmente viene definito lo spettro per intero. lungo tutta la
%%Frequenza
P2 = abs(Y/L);   
P1 = P2(1:floor(L/2)+1);
P1(2:end-1) = 2*P1(2:end-1);

%%Viene restituito lo spettro del segnale che cade nella metà delle
%%Frequenze di tutto il segnale. e.g 100Hz è tutto il segnale, viene
%%restiuito ciò che cade intorno ai 50Hz. 
%dOut = P2(1:L/2+1);

dOut = 20*log10(P1); 
plot(f,dOut) 
title('Single-Sided Amplitude Spectrum of Data(t)')
xlabel('f (Hz)')
ylabel('P1(db)')

%compute the PSD
periodogram(dIn,rectwin(length(dIn)),length(dIn),Fs)



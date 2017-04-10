function [signal_denoise] = Butter(sample_Rate, CutOffFrequency, signal_In)

%Questa funzione applica un 'low-pass Butterworth filter' del 2 ordine, 
%sul segnale in ingresso (signal_in), il cui compito è quello di ridurre 
%il rumore di fondo, smussando il segnale il più possibile, senza tuttavia
%alterarlo, in corrispondenza delle frequenze alte, diminuendo inoltre
%le frequenze basse.


Fs = sample_Rate;      %Frequenza di registrazione del segnale (Hz)
CF = CutOffFrequency;  %Frequenza di taglio del segnale in ingresso.
Nyq = (Fs/2);          %Viene definita la frequenza di Nyquist in relazione alla frequenza di registrazione del segnale in ingresso
Wn = (CF/Nyq);         %Viene definita la frequenza di taglio effettiva del segnale in relazione alla frequenza di Nyquist
[B, A]=butter(2,Wn,'low');   %Creazione del filtro
signal_denoise = filter(B,A,signal_In); %Applicazione del filtro sui dati in ingresso.

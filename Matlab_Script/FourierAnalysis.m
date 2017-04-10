function [dOut, f] = FourierAnalysis(Fs,dIn)

%%Questa Funzione effettua l'analisi di Fourier, sui dati ricevuti in
%%ingresso (dIn), ed ogni dato è registrato alla frequenza in Hz Fs.
%%(Sample Rate)

L = length(dIn);           % Length of signal 
dIn = dIn - mean(dIn);     %Bisogna rimuovere la media dai dati in ingresso, 
                           %%per cercare di rimuovere ulteriormente le DC-Components
                           %%ovvero le frequenze vicine a 0hz

f = Fs*(0:(L/2))/L;       %%Viene creato il dominio di Frequenza.                           
Y = fft(dIn);             %%Viene applicata la trasformata di Fourier  

%%Definiamo lo Spetto del segnale:

%%Inizialmente viene definito lo spettro per intero. lungo tutta la
%%Frequenza
P2 = abs(Y/L);           

%%Viene restituito lo spettro del segnale che cade nella metà delle
%%Frequenze di tutto il segnale. e.g 100Hz è tutto il segnale, viene
%%restiuito ciò che cade intorno ai 50Hz. 
dOut = P2(1:L/2+1);



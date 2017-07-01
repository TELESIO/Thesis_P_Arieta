
function [dOut] = Cheby2Order(SampleRate, dIn) 

Fs = SampleRate;
Fn = Fs/2;     % Nyquist Frequency
Wp = [1 48]/Fn;                                          % Passband Frequencies (Normalized)
Ws = [0.1 49]/Fn;                                        % Stopband Frequencies 
Rp = 10;                                                 % Passband Ripple (dB)
Rs = 50;                                                 % Stopband Ripple (dB)
[n,Ws] = cheb2ord(Wp,Ws,Rp,Rs);                          % Ordine del filtro
[c,b,a] = cheby2(n,Rs,Ws);                               % Vengono impostati i parametri del filtro
[sosbp,gbp] = zp2sos(c,b,a);                            

dOut = filtfilt(sosbp, gbp, dIn);  %Filtraggio dei dati.





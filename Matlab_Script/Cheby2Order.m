
function [dOut] = Cheby2Order(SampleRate, MinCutFreq, MaxCutFreq, MinStpFreq, MaxStopFreq, dIn,Tempo_ms) 

Fs = SampleRate;
Fn = Fs/2;                                              % Nyquist Frequency
Wp = [MinCutFreq MaxCutFreq]/Fn;                        % Passband Frequencies 
Ws = [MinStpFreq MaxStopFreq]/Fn;                       % Stopband Frequencies 
Rp = 10;                                                % Passband Ripple (dB)
Rs = 50;                                                % Stopband Ripple (dB)
[n,Ws] = cheb2ord(Wp,Ws,Rp,Rs);                         % Ordine del filtro
[c,b,a] = cheby2(n,Rs,Ws);                              % Vengono impostati i parametri del filtro
[sosbp,gbp] = zp2sos(c,b,a);                            

dOut = filtfilt(sosbp, gbp, dIn);  %Filtraggio dei dati.
% 
% figure(1)
% subplot(2,1,1)
% plot(Tempo_ms, dIn)
% xlabel('Time')
% ylabel('Signal')
% legend('Unfiltered Signal')
% subplot(2,1,2)
% plot(Tempo_ms, dOut)
% xlabel('Time')
% ylabel('signal')
% legend('Filtered Signal')



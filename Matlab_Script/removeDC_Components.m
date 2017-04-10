function[signal_No_Dc_Components] = removeDC_Components(signal, time_Lenght_of_signal)

% Questa funzione viene utilizzata per rimuovere le DC-Components utilizzando 
% un FIR filter, il DCBlocker di matlab, deve ricevere come parametro la lunghezza totale
% di registrazione del segnale in (s).
% successivamente verrà isolato il segnale originale dalle componenti DC.

dcblk = dsp.DCBlocker('Algorithm','FIR','Length',time_Lenght_of_signal);
signal_No_Dc_Components = step(dcblk,signal);
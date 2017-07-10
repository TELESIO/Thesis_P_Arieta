function[Signal_Denoise] = Filters(Signal_In,Wn)

Fs = 100;
Fn = Fs/2;
[B,A] = butter(1,Wn/Fn,'high');
Signal_Denoise = filtfilt(B,A,Signal_In); 

[B,A] = butter(2,35/Fn, 'low');
Signal_Denoise = filtfilt(B,A, Signal_Denoise);



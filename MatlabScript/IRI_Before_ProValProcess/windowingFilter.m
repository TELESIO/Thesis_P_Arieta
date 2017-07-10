function[Signal_Smoothed] = windowingFilter(Signal, window_size)

b = (1/window_size)*ones(1,window_size);
a=1;
Signal_Smoothed=filter(b,a,Signal(:,1));

clearvars b a;


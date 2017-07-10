function [Smoothed_Signal] = removeEngineRumors(Signal)

max = 0.4;
min =  -0.6;
window = 5;

Smoothed_Signal = zeros(length(Signal), 1);
for i = 1:numel(Signal)
    if(Signal(i,1) <= max && Signal(i,1) >= min && Signal(i,1) ~= 0)
       if(i+window<numel(Signal))
        means = rms(Signal(i:i+window));
       else
        means = rms(Signal(i:i-window));
       end   
    if(isnan(means))
      Smoothed_Signal(i,1) = 0;
    else
         Smoothed_Signal(i,1) = means;
    end
     
     else
        Smoothed_Signal(i,1) = Signal(i,1);
    end
end

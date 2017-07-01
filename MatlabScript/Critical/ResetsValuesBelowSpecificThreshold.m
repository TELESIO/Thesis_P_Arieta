function [Smoothed_Signal] = ResetsValuesBelowSpecificThreshold(Signal)

max = 2;
min =  -2;
Smoothed_Signal = zeros(length(Signal), 1);
for i = 1:numel(Signal)
    if(Signal(i,1) <= max && Signal(i,1) >= min)
          Smoothed_Signal(i,1) = 0;       
     else
        Smoothed_Signal(i,1) = Signal(i,1);
    end
end

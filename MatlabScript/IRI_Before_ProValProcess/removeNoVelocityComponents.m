function[Smoothed_Signal] = removeNoVelocityComponents(Signal, Velocity)

Smoothed_Signal = zeros(length(Signal),1);
window = 100;
for i=1:numel(Signal)
    
    if(Velocity(i,1) < 3)
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
 

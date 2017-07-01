function[Smoothed_Signal] = removeNoVelocityComponents(Signal, Velocity)

Smoothed_Signal = zeros(length(Signal),1);
for i=1:numel(Signal)
    if(Velocity(i,1) < 1)
      Smoothed_Signal(i,1) = 0; 
    else
        Smoothed_Signal(i,1) = Signal(i,1);
    end
end
 

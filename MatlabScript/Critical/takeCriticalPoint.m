function [Smoothed_Signal, Lat, Lon] = takeCriticalPoint(Signal, Latitudine, Longitudine)

Smoothed_Signal = zeros(1,1);
Lat = zeros(1,1);
Lon = zeros(1,1);
th = 3;
index = 1;
for i = 1:numel(Signal)
    if(abs(Signal(i,1))> th)
        Smoothed_Signal(index, 1) = Signal(i,1);
        Lat(index, 1) = Latitudine(i,1);
        Lon(index,1) = Longitudine(i,1);
        index = index + 1;
    end
end

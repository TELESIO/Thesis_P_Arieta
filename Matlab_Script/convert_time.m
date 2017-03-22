function [Time] = convert_time (Time)

for index = 1:numel(Time)
    Time(index,1) = Time(index,1)/1000;
end
return;
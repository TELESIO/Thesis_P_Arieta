
function [Time] = convert_time (Time)
%%%
%%questa funzione converte il tempo da ms a s, di tutti gli elementi del vettore.
for index = 1:numel(Time)
    Time(index,1) = Time(index,1)/1000;
end
return;
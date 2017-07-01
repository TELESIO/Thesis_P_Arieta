function[VerticalDisplacement, DistanceProgressive, DistanceDefined, NewLat, NewLon] = handleIntoSpecificDistance(VDis, DistancePro, Lat, Lon, max_distance)

VerticalDisplacement = zeros(1,1);
DistanceProgressive = zeros(1,1);
DistanceDefined = zeros(1,1);
NewLat = zeros(1,1);
NewLon = zeros(1,1);


array_index = 1;
start_index = 1;
d_covered = 0;

for i=1:numel(VDis)
    
    distance1 = DistancePro(i);
    d_covered = d_covered + distance1;

   
   
    
    
    if(floor(d_covered) == max_distance)
%        vertical_displacement_max = max((VDis(start_index:i)));
%        vertical_displacement_min = min((VDis(start_index:i)));
%        
%        if(abs(vertical_displacement_max) > abs(vertical_displacement_min))
%            vertical_displacement = vertical_displacement_max ;
%        else
%            vertical_displacement = abs(vertical_displacement_min);
%        end

       vertical_displacement = mean((VDis(start_index:i))); 
       NewLat(array_index, 1) = Lat(i,1);
       NewLon(array_index, 1) = Lon(i,1);
       VerticalDisplacement(array_index,1) = vertical_displacement ;
       DistanceDefined(array_index,1) = d_covered;
       
       if(array_index > 1)
           DistanceProgressive(array_index, 1) = DistanceProgressive(array_index-1, 1) + max_distance;
       else
           DistanceProgressive(array_index, 1) = max_distance;
       end
        
        start_index = i;
        array_index = array_index + 1;
        d_covered = distance1;
    elseif(d_covered > max_distance)
%        NewLat(array_index, 1) = Lat(i,1);
%        NewLon(array_index, 1) = Lon(i,1);
%        VerticalDisplacement(array_index,1) = VDis(i,1) ;
%        DistanceDefined(array_index,1) = max_distance;   
%        DistanceProgressive(array_index, 1) = max_distance;
%        start_index = i;
%        array_index = array_index + 1;
       d_covered = 0;
    end       
end
function [DistanceCovered, DistanceProgressive]  = DistanceCalculator (Latitudine, Longitudine)

DistanceCovered = zeros(1,1);
DistanceProgressive = zeros(1,1);
i = 1;

    for y = 1:numel(Longitudine) 
       latlon1 = [Latitudine(i,1), Longitudine(i,1)];
       latlon2 = [Latitudine(y,1), Longitudine(y,1)];
       distance = lldistkm(latlon1,latlon2) * 1000;
       
       if(distance ~= 0)
        
          if(i>1)
          DistanceCovered(i,1) =  DistanceCovered(i-1) + distance;
          else
              DistanceCovered(i,1) = distance;
          end
    
          DistanceProgressive(i,1) = distance;
          i = y;
       end
    end
    
    
    latlon3 = [Latitudine(i-1,1), Longitudine(i-1,1)];
    latlon4 = [Latitudine(i,1), Longitudine(i,1)];
    
    distance = lldistkm(latlon3,latlon4);
    DistanceCovered(i,1) =  DistanceCovered(i-1) + distance;
    DistanceProgressive(i,1) = distance;
    

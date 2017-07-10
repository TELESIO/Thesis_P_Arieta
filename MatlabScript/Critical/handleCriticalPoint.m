function[Critical, NewLat, NewLon] = handleCriticalPoint (Points, Latitudine, Longitudine);
Critical = zeros(1,1);
NewLat = zeros(1,1);
NewLon = zeros(1,1);

index = 1;
y = 2;
for i = 1:numel(Points)-2
       latlon1 = [Latitudine(i,1), Longitudine(i,1)];
       latlon2 = [Latitudine(y,1), Longitudine(y,1)];
       distance = lldistkm(latlon1,latlon2) * 1000;

       if(distance < 5)
          for index2 = y+1:numel(Points)-1
              latlon1 = [Latitudine(y,1), Longitudine(y,1)];
              latlon2 = [Latitudine(index2,1), Longitudine(index2,1)];
              distance = distance + lldistkm(latlon1,latlon2) * 1000;
             
            if(distance > 5) 
               newLatitudine = mean(Latitudine(i:index2));
               newLongitudine =  mean(Longitudine(i:index2));    
               Critical(index, 1) = mean(Points(i:index2));
               NewLat(index,1) = newLatitudine;
               NewLon(index,1) = newLongitudine;
               index = index +1;
            
               distance = 0;
               i = index2 + 1;
               y = i +1;
            else
               break;
            end
       
          end
       else
           y = i +1;
       end
end


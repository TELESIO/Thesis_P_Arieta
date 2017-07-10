clear all;
clc;
xlsIri;
IRIInitialFIleReader;

array_index = 1;
% while(index <numel(DistanceProgress1))
%     for index2 = 1: numel(StartDistancem)
%         if(DistanceProgress1(index,1) >= StartDistancem(index2, 1) && DistanceProgress1(index, 1)<= StopDistancem(index2, 1))
%             ValoriIri(array_index, 1) = CenterIRImkm(index2, 1);
%             Latitudine(array_index, 1) = NewLatitudine1(index, 1);
%             Longitudine(array_index, 1) = NewLongitudine1(index, 1);
%             Date(array_index, 1) = DateString1(index, 1);
%             array_index = array_index + 1;
%             break;
%         end
%     end   
%     index = index + 2;
% end
indice2 = 1;
indice = 1;

while (indice <= numel(StartDistancem))
    indice2 = 1;
while( indice2 <= numel(DistanceProgress1))
    if(DistanceProgress1(indice2, 1)>=StartDistancem(indice, 1) && DistanceProgress1(indice2, 1)<=StopDistancem(indice, 1))
        indice3 = indice2;
        if(indice3 > 1)
            Distanza = DistanceProgress1(indice3, 1) - DistanceProgress1(indice3 - 1, 1);
        else
            Distanza = DistanceProgress1(indice3, 1);
        end
        LatitudineNuova = NewLatitudine1(indice3, 1);
        LongitudineNuova = NewLongitudine1(indice3,1);
        
        while(Distanza < 10)
            indice3 = indice3 + 1;
            Distanza = Distanza + DistanceProgress1(indice3, 1) - DistanceProgress1(indice3 - 1, 1);
            LatitudineNuova = LatitudineNuova + NewLatitudine1(indice3, 1);
            LongitudineNuova = LongitudineNuova + NewLongitudine1(indice3,1);
          
            if(indice3 >= numel(DistanceProgress1))
                Distanza = 10;
            end
        end
           if(Distanza >= 10)  
             if(CenterIRImkm(indice, 1) < 2)
                ValoriIri(array_index, 1) = CenterIRImkm(indice, 1) * 2.3;
             elseif (CenterIRImkm(indice, 1) < 4)
                ValoriIri(array_index, 1) = CenterIRImkm(indice, 1) * 1.6;
             elseif (CenterIRImkm(indice, 1) < 8)
                ValoriIri(array_index, 1) = CenterIRImkm(indice, 1) * 1.4;
             else
                ValoriIri(array_index, 1) = CenterIRImkm(indice, 1) * 1.2;
             end
            Latitudine(array_index, 1) = LatitudineNuova / ((indice3 - indice2) + 1);
            Longitudine(array_index, 1) = LongitudineNuova / ((indice3 - indice2) + 1);
            Date(array_index, 1) = DateString1(indice, 1);
            array_index = array_index + 1;
            indice2 = indice3 + 1;
            Distanza = 0;
            LatitudineNuova = 0;
            LongitudineNuova = 0;
           end
    else
        indice2 = indice2 + 1;
    end
end
indice = indice + 1;
end



M = table(ValoriIri,Latitudine,Longitudine,Date);
[FileName,PathName] = uiputfile('*.csv','SaveFile');
fic=fullfile(PathName,FileName);

%        
%     M = [verticalDisplacement,NewLatitudine,NewLongitudine];
% dlmwrite(fic,M,'delimiter',',','precision','%.30f');
writetable(M,fic,'delimiter',',');
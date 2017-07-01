xlsIri;
IRIInitialFIleReader;

array_index = 1;
index = 1;
while(index <numel(DistanceProgress1))
    for index2 = 1: numel(StartDistancem)
        if(DistanceProgress1(index,1) >= StartDistancem(index2, 1) && DistanceProgress1(index, 1)<= StopDistancem(index2, 1))
            ValoriIri(array_index, 1) = CenterIRImkm(index2, 1);
            Latitudine(array_index, 1) = NewLatitudine1(index, 1);
            Longitudine(array_index, 1) = NewLongitudine1(index, 1);
            Date(array_index, 1) = DateString1(index, 1);
            array_index = array_index + 1;
            break;
        end
    end   
    index = index + 2;
end




M = table(ValoriIri,Latitudine,Longitudine,Date);
[FileName,PathName] = uiputfile('*.csv','SaveFile');
fic=fullfile(PathName,FileName);

%        
%     M = [verticalDisplacement,NewLatitudine,NewLongitudine];
% dlmwrite(fic,M,'delimiter',',','precision','%.30f');
writetable(M,fic,'delimiter',',');
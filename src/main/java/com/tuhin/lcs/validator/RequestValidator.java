package com.tuhin.lcs.validator;

import com.tuhin.lcs.domain.Request;
import com.tuhin.lcs.exception.BadLCSRequestException;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RequestValidator {

    public static void validateRequest(Request request){
        //validate - Request body is not empty
        if(Objects.isNull(request) ){
            throw new BadLCSRequestException("Request body can't be empty");
        }

        //validate - setOfStrings is not empty
        if(CollectionUtils.isEmpty(request.getSetOfStrings())){
            throw new BadLCSRequestException("Input string can't be empty");
        }

        //validate - setOfStrings must be unique
        List<String> inputs = request.getSetOfStrings().stream().map(t->t.value).collect(Collectors.toList());
        HashMap<String, Integer> hm = new HashMap<>();
        boolean hasDuplicate = false;
        // Store counts of all elements in map hm
        for (int i=0; i<inputs.size(); i++){
            // initializing value to 0, if key not found
            if(!hm.containsKey(inputs.get(i)))
                hm.put(inputs.get(i),0);

            hm.put(inputs.get(i), hm.get(inputs.get(i))+1);
        }
        for(String key: hm.keySet()){
            if(hm.get(key)>1){
                hasDuplicate = true;
            }
        }
        if(hasDuplicate){
            throw new BadLCSRequestException("Input string must be unique");
        }

    }
}

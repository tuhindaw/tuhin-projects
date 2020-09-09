package com.tuhin.lcs.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuhin.lcs.domain.Request;
import com.tuhin.lcs.exception.BadLCSRequestException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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

        //validate - input JSON format
        validateRequestJSONFormat(request);

        //validate - setOfStrings is not empty
        if(CollectionUtils.isEmpty(request.getSetOfStrings())){
            throw new BadLCSRequestException("Input string can't be empty");
        }
        List<String> inputs = request.getSetOfStrings().stream().map(t->t.value).collect(Collectors.toList());
        for(String s : inputs){
            if(StringUtils.isEmpty(s)){
                throw new BadLCSRequestException("Must provide all the input String values OR input child array JSON property is other then 'value' ");
            }
        }

        //validate - setOfStrings must be unique
        HashMap<String, Integer> hm = new HashMap<>();
        boolean hasDuplicate = false;
        // Store counts of all elements in map hm
        for (int i=0; i<inputs.size(); i++){
            // initializing value to 0, if key not found
            if(!hm.containsKey(inputs.get(i)))
                hm.put(inputs.get(i),0);

            hm.put(inputs.get(i), hm.get(inputs.get(i))+1);
        }
        String duplicateValue = "";
        for(String key: hm.keySet()){
            if(hm.get(key)>1){
                hasDuplicate = true;
                duplicateValue = key;
                break;
            }
        }
        if(hasDuplicate){
            throw new BadLCSRequestException("Input string must be unique but you have provided duplicate string => "+duplicateValue);
        }

    }

    public static void validateRequestJSONFormat(Request request) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(request);
            JsonNode node = objectMapper.readTree(json);
            if(!node.hasNonNull("setOfStrings")){
                throw new BadLCSRequestException("Input JSON format is not correct, json root property is other than 'setOfStrings' ");
            }

        } catch (JsonProcessingException ex) {
            throw new BadLCSRequestException("Input JSON format is not correct");
        }
    }
}

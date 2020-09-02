package com.tuhin.lcs.controller;

import com.tuhin.lcs.domain.Lcs;
import com.tuhin.lcs.domain.Request;
import com.tuhin.lcs.domain.Response;
import com.tuhin.lcs.validator.RequestValidator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Comcast coding assignment
 * Created by Tuhin Kumar Daw (tuhin.daw@gmail.com) on 09/01/2020
 */

@RestController
@RequestMapping("/api/substring/")
public class LCSController {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
                    value = "/lcs",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getLongestCommonSubstring(@RequestBody Request request) {
        RequestValidator.validateRequest(request);
        return findLongestSubstring(request);
    }

    private Response findLongestSubstring(Request request){
        List<String> inputs = request.getSetOfStrings().stream().map(t->t.value).collect(Collectors.toList());
        return createResponse(findstem(inputs));
    }

    // Method to find longest common substring) from the string array
    private String findstem(List<String> input)
    {
        int n = input.size();

        // Take first word from array as reference
        String s = input.get(0);
        int len = s.length();

        String res = "";

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {

                // generating all possible substrings
                String stem = s.substring(i, j);
                int k = 1;
                for (k = 1; k < n; k++)

                    // Check if the generated stem is common to all words
                    if (!input.get(k).contains(stem))
                        break;

                // If current substring is present in all strings and its length is greater
                // than current result
                if (k == n && res.length() < stem.length())
                    res = stem;
            }
        }

        return res;
    }

    private Response createResponse(String res){
        Response response = new Response();
        Set<Lcs> sets = new HashSet<>();
        Lcs lcs = new Lcs(res);
        sets.add(lcs);
        response.setLcs(sets);
        return response;
    }


}

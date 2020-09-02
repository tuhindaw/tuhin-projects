package com.tuhin.lcs.service;

import com.tuhin.lcs.domain.Lcs;
import com.tuhin.lcs.domain.Request;
import com.tuhin.lcs.domain.Response;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LCSServiceImpl implements LCSService{

    @Override
    public Response findLongestSubstring(Request request) {
        List<String> inputs = request.getSetOfStrings().stream().map(t->t.value).collect(Collectors.toList());
        return createResponse(findstem(inputs));
    }

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

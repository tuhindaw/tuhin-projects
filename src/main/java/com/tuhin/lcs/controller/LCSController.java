package com.tuhin.lcs.controller;

import com.tuhin.lcs.domain.Request;
import com.tuhin.lcs.domain.Response;
import com.tuhin.lcs.service.LCSService;
import com.tuhin.lcs.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Comcast coding assignment
 * Created by Tuhin Kumar Daw (tuhin.daw@gmail.com) on 09/01/2020
 */

@RestController
@RequestMapping("/api/substring/")
public class LCSController {

    @Autowired private LCSService lcsService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
                    value = "/lcs",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getLongestCommonSubstring(@RequestBody Request request) {
        RequestValidator.validateRequest(request);
        return lcsService.findLongestSubstring(request);
    }

}

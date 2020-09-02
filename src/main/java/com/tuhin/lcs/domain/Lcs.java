package com.tuhin.lcs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Lcs {

    public Lcs() {}
    @JsonProperty("value")
    public String value;

    public Lcs(String subString){
        this.value = subString;
    }

}

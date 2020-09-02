package com.tuhin.lcs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class Response {

    @JsonProperty("lcs")
    Set<Lcs> lcs;
    public Set<Lcs> getLcs() {
        return lcs;
    }
    public void setLcs(
            Set<Lcs> lcs) {
        this.lcs = lcs;
    }

}

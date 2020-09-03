package com.tuhin.lcs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class Request {
    @JsonProperty("setOfStrings")
    private Set<SetOfString> setOfStrings;
    public Set<SetOfString> getSetOfStrings() {
        return setOfStrings;
    }
    public void setSetOfStrings(
            Set<SetOfString> setOfStrings) {
        this.setOfStrings = setOfStrings;
    }

    public static class SetOfString {
        @JsonProperty("value")
        public String value;
    }

}

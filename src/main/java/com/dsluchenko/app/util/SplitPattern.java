package com.dsluchenko.app.util;

import java.util.regex.Pattern;

public enum SplitPattern {
    SPLIT_BY_DOT(Pattern.quote(".")),
    SPLIT_BY_SPACE(Pattern.quote(" ")),
    SPLIT_BY_COMMA(Pattern.quote(",")),
    SPLIT_BY_SELECT(Pattern.quote("SELECT")),
    SPLIT_BY_FROM(Pattern.quote("FROM"));

    private String regex;

    SplitPattern(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}

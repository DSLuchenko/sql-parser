package com.dsluchenko.app.util;

import java.util.regex.Pattern;

public enum SplitPattern {
    SPLIT_BY_DOT(Pattern.quote(".")),
    SPLIT_BY_SPACE(Pattern.quote(" ")),
    SPLIT_BY_COMMA(Pattern.quote(","));

    private String regex;

    SplitPattern(String regexp) {
        this.regex = regexp;
    }

    public String getRegex() {
        return regex;
    }
}

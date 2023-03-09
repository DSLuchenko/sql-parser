package com.dsluchenko.app;

import com.dsluchenko.app.core.QueryParser;

public class Main {
    public static void main(String[] args) {
        QueryParser.parse("SELECT b.id FROM book b");
    }
}
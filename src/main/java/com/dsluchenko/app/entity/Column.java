package com.dsluchenko.app.entity;

import java.util.Objects;

public class Column {
    private String sourceName;
    private String name;
    private String alias;

    public Column(String sourceName, String name, String alias) {
        this.sourceName = sourceName;
        this.name = name;
        this.alias = alias;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column column = (Column) o;
        return sourceName.equals(column.sourceName) && name.equals(column.name) && alias.equals(column.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceName, name, alias);
    }
}

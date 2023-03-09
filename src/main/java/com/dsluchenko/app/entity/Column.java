package com.dsluchenko.app.entity;

import java.util.Objects;

public class Column {
    private Table table;
    private String name;
    private String alias;

    public Column(Table table, String name, String alias) {
        this.table = table;
        this.name = name;
        this.alias = alias;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
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
        return table.equals(column.table) && name.equals(column.name) && alias.equals(column.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table, name, alias);
    }
}

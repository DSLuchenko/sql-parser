package com.dsluchenko.app.entity;

import java.util.List;

public class Query {
    private List<Column> columns;
    private List<Table> fromTables;
    private List<Join> joins;
    private List<WhereClause> whereClauses;
    private List<String> groupByColumns;
    private List<Sort> sortColumns;
    private Integer limit;
    private Integer offset;

    public Query() {
    }


    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Table> getTables() {
        return fromTables;
    }

    public void setTables(List<Table> fromTables) {
        this.fromTables = fromTables;
    }

    public List<Join> getJoins() {
        return joins;
    }

    public void setJoins(List<Join> joins) {
        this.joins = joins;
    }

    public List<WhereClause> getWhereClauses() {
        return whereClauses;
    }

    public void setWhereClauses(List<WhereClause> whereClauses) {
        this.whereClauses = whereClauses;
    }

    public List<String> getGroupByColumns() {
        return groupByColumns;
    }

    public void setGroupByColumns(List<String> groupByColumns) {
        this.groupByColumns = groupByColumns;
    }

    public List<Sort> getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(List<Sort> sortColumns) {
        this.sortColumns = sortColumns;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}

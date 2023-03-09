package com.dsluchenko.app.core;

import com.dsluchenko.app.entity.Column;
import com.dsluchenko.app.entity.Query;
import com.dsluchenko.app.entity.Table;
import com.dsluchenko.app.util.SplitPattern;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class QueryParser {
    public static Query parse(String strQuery) {
        Query query = new Query();
        String[] splitByFrom = strQuery.split(SplitPattern.SPLIT_BY_FROM.getRegex());

        //from block
        String trimmedSplitByFrom = splitByFrom[1].trim();
        query.setTables(fillListTable(trimmedSplitByFrom));

        //select block
        String selectBlock = splitByFrom[0];
        query.setColumns(fillListColumns(selectBlock, query.getTables()));
        return query;
    }

    public static List<Table> fillListTable(String tablesWithAliasFromTable) {
        String trimmedTablesNamesWithAlias = tablesWithAliasFromTable.split(SplitPattern.SPLIT_BY_FROM.getRegex())[1].trim();

        return trimmedTablesNamesWithAlias.contains(",")
                ? Arrays.stream(trimmedTablesNamesWithAlias.split(SplitPattern.SPLIT_BY_COMMA.getRegex()))
                .map(tableNameWithAlias -> fillTableByTableNameWithAlias(tableNameWithAlias))
                .collect(Collectors.toList())
                : List.of(fillTableByTableNameWithAlias(trimmedTablesNamesWithAlias));
    }

    public static List<Column> fillListColumns(String querySelectColumns, List<Table> tables) {
        String fullColumnNames = querySelectColumns.trim().split(SplitPattern.SPLIT_BY_SELECT.getRegex())[1].trim();

        return fullColumnNames.contains(",")
                ? Arrays.stream(fullColumnNames.split(SplitPattern.SPLIT_BY_COMMA.getRegex()))
                .map(fullColumnName -> fillColumnByColumnNameWithTableNameAndAlias(fullColumnName, tables))
                .collect(Collectors.toList())
                : List.of(fillColumnByColumnNameWithTableNameAndAlias(fullColumnNames, tables));
    }

    private static Table fillTableByTableNameWithAlias(String tableNameWithAlias) {
        String trimmedTableNameWithAlias = tableNameWithAlias.trim();
        String[] parsedTable = parseStringWithDotCommaSpace(trimmedTableNameWithAlias);
        String tableName = parsedTable[1];
        String tableAlias = parsedTable[2];
        return new Table(tableName, tableAlias);
    }

    private static Column fillColumnByColumnNameWithTableNameAndAlias(String columnNameWithTableNameAndAlias, List<Table> tables) {
        String trimmedColumnNameWithTableNameAndAlias = columnNameWithTableNameAndAlias.trim();
        String[] parsedTable = parseStringWithDotCommaSpace(trimmedColumnNameWithTableNameAndAlias);
        String tableName = parsedTable[0];
        String columnName = parsedTable[1];
        String tableAlias = parsedTable[2];

        if (tables.size() != 1) {
            Table table = tables.stream()
                    .filter(t -> t.getName().equals(tableName) || t.getAlias().equals(tableName))
                    .findAny().orElseThrow();
            return new Column(table, columnName, tableAlias);
        }

        Table table = tables.get(0);
        return new Column(table, columnName, tableAlias);

    }

    private static String[] parseStringWithDotCommaSpace(String str) {
        String stringBeforeDot = "", stringBetweenDotAndSpace = "", stringAfterSpace = "", stringAfterDot = str;
        if (str.contains(".")) {
            String[] splitColumnNameWithTableNameAndAlias = str.split(SplitPattern.SPLIT_BY_DOT.getRegex());
            stringBeforeDot = splitColumnNameWithTableNameAndAlias[0];
            stringAfterDot = splitColumnNameWithTableNameAndAlias[1];
        }
        if (stringAfterDot.contains(" ")) {
            String[] splitColumnNameWithAlias = stringAfterDot.split(SplitPattern.SPLIT_BY_SPACE.getRegex());
            stringBetweenDotAndSpace = splitColumnNameWithAlias[0];
            stringAfterSpace = splitColumnNameWithAlias[1];
        } else {
            stringBetweenDotAndSpace = str;
        }

        return new String[]{stringBeforeDot, stringBetweenDotAndSpace, stringAfterSpace};
    }

}

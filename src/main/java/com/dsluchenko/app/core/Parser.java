package com.dsluchenko.app.core;

import com.dsluchenko.app.entity.Column;
import com.dsluchenko.app.entity.Query;
import com.dsluchenko.app.entity.Table;
import com.dsluchenko.app.util.SplitPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Parser {
    //need refactor
    public static Query parse(String strQuery) {
        Query query = new Query();
        String[] splitByFrom = strQuery.split("FROM");

        //select block
        String selectBlock = splitByFrom[0];
        query.setColumns(fillListColumns(selectBlock));

        //from block
        String trimmedSplitByFrom = splitByFrom[1].trim();
        query.setFromSources(fillListTable("FROM" + trimmedSplitByFrom));

        return query;
    }

    public static List<Table> fillListTable(String tablesWithAliasFromTable) {
        if (!tablesWithAliasFromTable.contains("FROM")) {
            throw new IllegalArgumentException();
        }
        String trimmedTablesNamesWithAlias = tablesWithAliasFromTable.split("FROM")[1].trim();

        return trimmedTablesNamesWithAlias.contains(",")
                ? Arrays.stream(trimmedTablesNamesWithAlias.split(SplitPattern.SPLIT_BY_COMMA.getRegex()))
                .map(tableNameWithAlias -> fillTableByTableNameWithAlias(tableNameWithAlias))
                .collect(Collectors.toList())
                : List.of(fillTableByTableNameWithAlias(trimmedTablesNamesWithAlias));
    }

    private static Table fillTableByTableNameWithAlias(String tableNameWithAlias) {
        String trimmedTableNameWithAlias = tableNameWithAlias.trim();
        if (trimmedTableNameWithAlias.contains(" ")) {
            String[] tableNameAndAlias = trimmedTableNameWithAlias.split(SplitPattern.SPLIT_BY_SPACE.getRegex());
            return new Table(tableNameAndAlias[0], tableNameAndAlias[1]);
        } else {
            return new Table(trimmedTableNameWithAlias, "");
        }
    }


    public static List<Column> fillListColumns(String querySelectColumns) {
        if (!querySelectColumns.contains("SELECT")) {
            throw new IllegalArgumentException();
        }
        String fullColumnNames = querySelectColumns.trim().split("SELECT ")[1].trim();

        return fullColumnNames.contains(",")
                ? Arrays.stream(fullColumnNames.split(SplitPattern.SPLIT_BY_COMMA.getRegex()))
                .map(fullColumnName -> fillColumnByColumnNameWithTableNameAndAlias(fullColumnName))
                .collect(Collectors.toList())
                : List.of(fillColumnByColumnNameWithTableNameAndAlias(fullColumnNames));
    }

    private static Column fillColumnByColumnNameWithTableNameAndAlias(String columnNameWithTableNameAndAlias) {
        String columnSourceName = "", name = "", alias = "";
        String trimmedColumnNameWithTableNameAndAlias = columnNameWithTableNameAndAlias.trim();
        if (trimmedColumnNameWithTableNameAndAlias.contains(".")) {
            String[] splitColumnNameWithTableNameAndAlias = trimmedColumnNameWithTableNameAndAlias.split(SplitPattern.SPLIT_BY_DOT.getRegex());
            columnSourceName = splitColumnNameWithTableNameAndAlias[0];

            String columnNameWithAlias = splitColumnNameWithTableNameAndAlias[1];
            if (columnNameWithAlias.contains(" ")) {
                String[] splitColumnNameWithAlias = columnNameWithAlias.split(SplitPattern.SPLIT_BY_SPACE.getRegex());
                name = splitColumnNameWithAlias[0];
                alias = splitColumnNameWithAlias[1];
            }
        } else if (trimmedColumnNameWithTableNameAndAlias.contains(" ")) {
            String[] splitColumnNameWithAlias = trimmedColumnNameWithTableNameAndAlias.split(SplitPattern.SPLIT_BY_SPACE.getRegex());
            name = splitColumnNameWithAlias[0];
            alias = splitColumnNameWithAlias[1];
        } else {
            name = trimmedColumnNameWithTableNameAndAlias;
        }

        return new Column(columnSourceName, name, alias);
    }

}

package app.core;

import com.dsluchenko.app.core.QueryParser;
import com.dsluchenko.app.entity.Column;
import com.dsluchenko.app.entity.Table;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueryParserTest {
    @Test
    public void fillListColumns() {
        String selectAllColumns = "SELECT *";
        String selectOneColumnWithoutAliasAndWithoutSource = "SELECT id";
        String selectOneColumnWithAliasAndWithoutSource = "SELECT id i";
        String selectOneColumnWithAliasAndWithSource = "SELECT book.id i";
        String selectManyColumnsWithoutAliasAndWithoutSource = "SELECT id, author";
        String selectManyColumnsWithAliasAndWithoutSource = "SELECT id i, author a";
        String selectManyColumnsWithAliasAndWithSource = "SELECT book.id i, book.author a";
        String selectManyColumnsWithAliasAndWithManySource = "SELECT book.id b_id, author.id a_id";


        List<Column> expectedSelectAllColumns = List.of(new Column(new Table("book", ""), "*", ""));
        List<Column> expectedSelectOneColumnWithoutAliasAndWithoutSource = List.of(new Column(new Table("book", ""), "id", ""));
        List<Column> expectedSelectOneColumnWithAliasAndWithoutSource = List.of(new Column(new Table("book", ""), "id", "i"));
        List<Column> expectedSelectOneColumnWithAliasAndWithSource = List.of(new Column(new Table("book", ""), "id", "i"));
        List<Column> expectedSelectManyColumnsWithoutAliasAndWithoutSource = List.of(new Column(new Table("book", ""), "id", ""), new Column(new Table("book", ""), "author", ""));
        List<Column> expectedSelectManyColumnsWithAliasAndWithoutSource = List.of(new Column(new Table("book", ""), "id", "i"), new Column(new Table("book", ""), "author", "a"));
        List<Column> expectedSelectManyColumnsWithAliasAndWithSource = List.of(new Column(new Table("book", ""), "id", "i"), new Column(new Table("book", ""), "author", "a"));
        List<Column> expectedSelectManyColumnsWithAliasAndWithManySource = List.of(new Column(new Table("book", ""), "id", "b_id"), new Column(new Table("author", ""), "id", "a_id"));

        List<Column> actualSelectAllColumns = QueryParser.fillListColumns(selectAllColumns, List.of(new Table("book", "")));
        List<Column> actualSelectOneColumnWithoutAliasAndWithoutSource = QueryParser.fillListColumns(selectOneColumnWithoutAliasAndWithoutSource, List.of(new Table("book", "")));
        List<Column> actualSelectOneColumnWithAliasAndWithoutSource = QueryParser.fillListColumns(selectOneColumnWithAliasAndWithoutSource, List.of(new Table("book", "")));
        List<Column> actualSelectOneColumnWithAliasAndWithSource = QueryParser.fillListColumns(selectOneColumnWithAliasAndWithSource, List.of(new Table("book", "")));
        List<Column> actualSelectManyColumnsWithoutAliasAndWithoutSource = QueryParser.fillListColumns(selectManyColumnsWithoutAliasAndWithoutSource, List.of(new Table("book", "")));
        List<Column> actualSelectManyColumnsWithAliasAndWithoutSource = QueryParser.fillListColumns(selectManyColumnsWithAliasAndWithoutSource, List.of(new Table("book", "")));
        List<Column> actualSelectManyColumnsWithAliasAndWithSource = QueryParser.fillListColumns(selectManyColumnsWithAliasAndWithSource, List.of(new Table("book", "")));
        List<Column> actualSelectManyColumnsWithAliasAndWithManySource = QueryParser.fillListColumns(selectManyColumnsWithAliasAndWithManySource, List.of(new Table("book", ""), new Table("author", "")));

        assertEquals(expectedSelectAllColumns, actualSelectAllColumns);
        assertEquals(expectedSelectOneColumnWithoutAliasAndWithoutSource, actualSelectOneColumnWithoutAliasAndWithoutSource);
        assertEquals(expectedSelectOneColumnWithAliasAndWithoutSource, actualSelectOneColumnWithAliasAndWithoutSource);
        assertEquals(expectedSelectOneColumnWithAliasAndWithSource, actualSelectOneColumnWithAliasAndWithSource);
        assertEquals(expectedSelectManyColumnsWithoutAliasAndWithoutSource, actualSelectManyColumnsWithoutAliasAndWithoutSource);
        assertEquals(expectedSelectManyColumnsWithAliasAndWithoutSource, actualSelectManyColumnsWithAliasAndWithoutSource);
        assertEquals(expectedSelectManyColumnsWithAliasAndWithSource, actualSelectManyColumnsWithAliasAndWithSource);
        assertEquals(expectedSelectManyColumnsWithAliasAndWithManySource, actualSelectManyColumnsWithAliasAndWithManySource);

    }

    @Test
    public void fillListTable() {
        String fromOneTableNameWithoutAlias = "FROM book";
        String fromOneTableNameWithAlias = "FROM book b";
        String fromManyTableNameWithoutAlias = "FROM book, author";
        String fromManyTableNameWithAliasOnlyFirstTable = "FROM book b, author";
        String fromManyTableNameWithAlias = "FROM book b, author a";
        String fromManyTableNameWithAliasOnlySecondTable = "FROM book, author a";

        List<Table> expectedFromOneTableNameWithoutAlias = List.of(new Table("book", ""));
        List<Table> expectedFromOneTableNameWithAlias = List.of(new Table("book", "b"));
        List<Table> expectedFromManyTableNameWithoutAlias = List.of(new Table("book", ""), new Table("author", ""));
        List<Table> expectedFromManyTableNameWithAliasOnlyFirstTable = List.of(new Table("book", "b"), new Table("author", ""));
        List<Table> expectedFromManyTableNameWithAlias = List.of(new Table("book", "b"), new Table("author", "a"));
        List<Table> expectedFromManyTableNameWithAliasOnlySecondTable = List.of(new Table("book", ""), new Table("author", "a"));


        List<Table> actualFromOneTableNameWithoutAlias = QueryParser.fillListTable(fromOneTableNameWithoutAlias);
        List<Table> actualFromOneTableNameWithAlias = QueryParser.fillListTable(fromOneTableNameWithAlias);
        List<Table> actualFromManyTableNameWithoutAlias = QueryParser.fillListTable(fromManyTableNameWithoutAlias);
        List<Table> actualFromManyTableNameWithAliasOnlyFirstTable = QueryParser.fillListTable(fromManyTableNameWithAliasOnlyFirstTable);
        List<Table> actualFromManyTableNameWithAlias = QueryParser.fillListTable(fromManyTableNameWithAlias);
        List<Table> actualFromManyTableNameWithAliasOnlySecondTable = QueryParser.fillListTable(fromManyTableNameWithAliasOnlySecondTable);


        assertEquals(expectedFromOneTableNameWithoutAlias, actualFromOneTableNameWithoutAlias);
        assertEquals(expectedFromOneTableNameWithAlias, actualFromOneTableNameWithAlias);
        assertEquals(expectedFromManyTableNameWithoutAlias, actualFromManyTableNameWithoutAlias);
        assertEquals(expectedFromManyTableNameWithAliasOnlyFirstTable, actualFromManyTableNameWithAliasOnlyFirstTable);
        assertEquals(expectedFromManyTableNameWithAlias, actualFromManyTableNameWithAlias);
        assertEquals(expectedFromManyTableNameWithAliasOnlySecondTable, actualFromManyTableNameWithAliasOnlySecondTable);


    }
}

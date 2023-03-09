package app.core;

import com.dsluchenko.app.core.Parser;
import com.dsluchenko.app.entity.Column;
import com.dsluchenko.app.entity.Table;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
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


        List<Column> expectedSelectAllColumns = List.of(new Column("", "*", ""));
        List<Column> expectedSelectOneColumnWithoutAliasAndWithoutSource = List.of(new Column("", "id", ""));
        List<Column> expectedSelectOneColumnWithAliasAndWithoutSource = List.of(new Column("", "id", "i"));
        List<Column> expectedSelectOneColumnWithAliasAndWithSource = List.of(new Column("book", "id", "i"));
        List<Column> expectedSelectManyColumnsWithoutAliasAndWithoutSource = List.of(new Column("", "id", ""), new Column("", "author", ""));
        List<Column> expectedSelectManyColumnsWithAliasAndWithoutSource = List.of(new Column("", "id", "i"), new Column("", "author", "a"));
        List<Column> expectedSelectManyColumnsWithAliasAndWithSource = List.of(new Column("book", "id", "i"), new Column("book", "author", "a"));
        List<Column> expectedSelectManyColumnsWithAliasAndWithManySource = List.of(new Column("book", "id", "b_id"), new Column("author", "id", "a_id"));

        List<Column> actualSelectAllColumns = Parser.fillListColumns(selectAllColumns);
        List<Column> actualSelectOneColumnWithoutAliasAndWithoutSource = Parser.fillListColumns(selectOneColumnWithoutAliasAndWithoutSource);
        List<Column> actualSelectOneColumnWithAliasAndWithoutSource = Parser.fillListColumns(selectOneColumnWithAliasAndWithoutSource);
        List<Column> actualSelectOneColumnWithAliasAndWithSource = Parser.fillListColumns(selectOneColumnWithAliasAndWithSource);
        List<Column> actualSelectManyColumnsWithoutAliasAndWithoutSource = Parser.fillListColumns(selectManyColumnsWithoutAliasAndWithoutSource);
        List<Column> actualSelectManyColumnsWithAliasAndWithoutSource = Parser.fillListColumns(selectManyColumnsWithAliasAndWithoutSource);
        List<Column> actualSelectManyColumnsWithAliasAndWithSource = Parser.fillListColumns(selectManyColumnsWithAliasAndWithSource);
        List<Column> actualSelectManyColumnsWithAliasAndWithManySource = Parser.fillListColumns(selectManyColumnsWithAliasAndWithManySource);

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


        List<Table> actualFromOneTableNameWithoutAlias = Parser.fillListTable(fromOneTableNameWithoutAlias);
        List<Table> actualFromOneTableNameWithAlias = Parser.fillListTable(fromOneTableNameWithAlias);
        List<Table> actualFromManyTableNameWithoutAlias = Parser.fillListTable(fromManyTableNameWithoutAlias);
        List<Table> actualFromManyTableNameWithAliasOnlyFirstTable = Parser.fillListTable(fromManyTableNameWithAliasOnlyFirstTable);
        List<Table> actualFromManyTableNameWithAlias = Parser.fillListTable(fromManyTableNameWithAlias);
        List<Table> actualFromManyTableNameWithAliasOnlySecondTable = Parser.fillListTable(fromManyTableNameWithAliasOnlySecondTable);


        assertEquals(expectedFromOneTableNameWithoutAlias, actualFromOneTableNameWithoutAlias);
        assertEquals(expectedFromOneTableNameWithAlias, actualFromOneTableNameWithAlias);
        assertEquals(expectedFromManyTableNameWithoutAlias, actualFromManyTableNameWithoutAlias);
        assertEquals(expectedFromManyTableNameWithAliasOnlyFirstTable, actualFromManyTableNameWithAliasOnlyFirstTable);
        assertEquals(expectedFromManyTableNameWithAlias, actualFromManyTableNameWithAlias);
        assertEquals(expectedFromManyTableNameWithAliasOnlySecondTable, actualFromManyTableNameWithAliasOnlySecondTable);


    }
}

package com;

import com.column.*;
import com.column.datatype.Choice;
import com.column.datatype.DateAndTime;
import com.column.datatype.Number;
import com.column.datatype.Person;
import com.column.datatype.Text;
import com.export.ExportStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MicrosoftListsApplicationTests {
    MicrosoftList microsoftList;
    SmartList smartList;

    @BeforeEach
    public void setUp() {
        microsoftList = new MicrosoftList();
        microsoftList.createList("ABC");
        smartList = microsoftList.listCollection.get(0);
    }

    @Test
    void testInitDefaultListTemplate() {
        assertEquals(4, microsoftList.templates.size());
    }

    @Test
    void testCreateList() {
        microsoftList.createList("ABC");
        assertNotNull(
                microsoftList.listCollection.stream()
                        .filter(l -> l.getTitle().equals("ABC"))
                        .findFirst()
                        .orElse(null));
    }

    @Test
    void testAddFavouriteList() {
        microsoftList.createList("ABC");
        microsoftList.addFavourite(smartList);
        assertNotNull(microsoftList.favouriteCollection.stream()
                .filter(l -> l.getTitle().equals("ABC"))
                .findFirst()
                .orElse(null));
    }

    //TDD
    @Test
    void testSaveTemplate() {
        microsoftList.saveTemplate(smartList);
        assertNotNull(microsoftList.templates.stream().filter(l -> l.equals(smartList))
                .findFirst()
                .orElse(null));
    }


    @Test
    void testAddMultipleColumn() {
        IColumn textCol = smartList.createNewColumn(ColumnType.TEXT);
        IColumn timeCol = smartList.createNewColumn(ColumnType.DATE_AND_TIME);
        IColumn choiceCol = smartList.createNewColumn(ColumnType.CHOICE);
        IColumn personCol = smartList.createNewColumn(ColumnType.PERSON);
        IColumn numberCol = smartList.createNewColumn(ColumnType.NUMBER);

        smartList.createNewRow();

        Text text = new Text("text data");
        smartList.addData(textCol, 0, text);
        Choice choice = new Choice();
        smartList.addData(choiceCol, 0, choice);
        DateAndTime dateAndTime = new DateAndTime(new Date(), new Time(System.currentTimeMillis()));
        smartList.addData(timeCol, 0, dateAndTime);
        Person person = new Person("Khoa", new byte[]{});
        smartList.addData(personCol, 0, person);
        Number number = new Number(4.5, new byte[]{});
        smartList.addData(numberCol, 0, number);

        assertEquals(text.getText(), ((Text) smartList.getData(textCol, 0)).getText());
        assertEquals(choice.getName(), ((Choice) smartList.getData(choiceCol, 0)).getName());
        assertEquals(dateAndTime.getDate(), ((DateAndTime) smartList.getData(timeCol, 0)).getDate());
        assertEquals(person.getName(), ((Person) smartList.getData(personCol, 0)).getName());
        assertEquals(number.getNum(), ((Number) smartList.getData(numberCol, 0)).getNum());
    }


    @Test
    void testExportListToCSV() {
        assertEquals(ExportStatus.SUCCESS, microsoftList.exportToCSV(smartList, "list.csv").getStatus());
    }

    @Test
    void testAddComment() {
        Person person = new Person("Khoa", new byte[]{});
        smartList.getRows().get(0).getComments().add(new Comment(person, "new comment"));
        assertEquals(1, smartList.getRows().get(0).getComments().size());
    }

    @Test
    void testEditComment() {
        Person person = new Person("Khoa", new byte[]{});
        smartList.getRows().get(0).getComments().add(new Comment(person, "new comment"));
        smartList.getRows().get(0).getComments().get(0).setMessage("test edit comment");
        assertEquals("test edit comment", smartList.getRows().get(0).getComments().get(0).getMessage());
    }

    @Test
    void testCreateForm() {
        IColumn textCol = smartList.createNewColumn(ColumnType.TEXT);
        IColumn timeCol = smartList.createNewColumn(ColumnType.DATE_AND_TIME);
        IColumn choiceCol = smartList.createNewColumn(ColumnType.CHOICE);
        List<IColumn> columns = new ArrayList<>();
        columns.add(textCol);
        columns.add(timeCol);
        columns.add(choiceCol);

        smartList.createForm(columns);
        assertEquals(1, smartList.getForms().size());
    }

    @Test
    void testCustomizeForm() {
        smartList.getForms().get(0).editConditionFormula();
    }

    @Test
    void testShowColumn() {
        smartList.getColumns().get(0).setVisible(true);
        assertTrue(smartList.getColumns().get(0).isVisible());
    }

    @Test
    void testHideColumn() {
        smartList.getColumns().get(0).setVisible(false);
        assertFalse(smartList.getColumns().get(0).isVisible());
    }

    @Test
    void testSortDescColumn() {
        IColumn textCol = smartList.createNewColumn(ColumnType.TEXT);
        smartList.createNewRow();
        smartList.addData(textCol, 0, new Text("a"));
        smartList.createNewRow();
        smartList.addData(textCol, 1, new Text("b"));
        smartList.createNewRow();
        smartList.addData(textCol, 2, new Text("c"));
        smartList.sortDesc(textCol);

        assertEquals("a", ((Text) smartList.getRows().get(0).getDataList().get(0)).getText());
        assertEquals("b", ((Text) smartList.getRows().get(1).getDataList().get(0)).getText());
        assertEquals("c", ((Text) smartList.getRows().get(2).getDataList().get(0)).getText());
    }

    @Test
    void testSortAscColumn() {
        IColumn textCol = smartList.createNewColumn(ColumnType.TEXT);
        smartList.createNewRow();
        smartList.addData(textCol, 0, new Text("a"));
        smartList.createNewRow();
        smartList.addData(textCol, 1, new Text("b"));
        smartList.createNewRow();
        smartList.addData(textCol, 2, new Text("d"));
        smartList.createNewRow();
        smartList.addData(textCol, 3, new Text("c"));
        smartList.sortAsc(textCol);

        assertEquals("d", ((Text) smartList.getRows().get(0).getDataList().get(0)).getText());
        assertEquals("c", ((Text) smartList.getRows().get(1).getDataList().get(0)).getText());
        assertEquals("b", ((Text) smartList.getRows().get(2).getDataList().get(0)).getText());
        assertEquals("a", ((Text) smartList.getRows().get(3).getDataList().get(0)).getText());
    }

    @Test
    void testFilterColumn() {
        IColumn textCol = smartList.createNewColumn(ColumnType.TEXT);
        smartList.createNewRow();
        smartList.addData(textCol, 0, new Text("a"));
        smartList.createNewRow();
        smartList.addData(textCol, 1, new Text("b"));
        smartList.createNewRow();
        smartList.addData(textCol, 2, new Text("c"));
        List<Text> criteria = smartList.getListFilter(textCol);
        List<Row> filteredRows = smartList.filter(textCol, criteria.get(0));

        assertEquals(1, filteredRows.size());
        assertEquals("a", ((Text) filteredRows.get(0).getDataList().get(0)).getImportantData());
    }

    @Test
    void testGroupByColumn() {
        IColumn textCol = smartList.createNewColumn(ColumnType.TEXT);
        smartList.createNewRow();
        smartList.addData(textCol, 0, new Text("a"));
        smartList.createNewRow();
        smartList.addData(textCol, 1, new Text("b"));
        smartList.createNewRow();
        smartList.addData(textCol, 2, new Text("c"));
        smartList.createNewRow();
        smartList.addData(textCol, 3, new Text("c"));
        Map<Object, List<Row>> groupedRows = smartList.groupBy(textCol);

        assertNotNull(groupedRows);
        assertEquals(3, groupedRows.size());

    }

    @Test
    void testMoveLeftColumn() {
        IColumn textCol = smartList.createNewColumn(ColumnType.TEXT);
        IColumn timeCol = smartList.createNewColumn(ColumnType.DATE_AND_TIME);
        smartList.moveLeft(timeCol);
        assertEquals(timeCol, smartList.getColumns().get(0));
    }

    @Test
    void testMoveRightColumn() {
        IColumn textCol = smartList.createNewColumn(ColumnType.TEXT);
        IColumn timeCol = smartList.createNewColumn(ColumnType.DATE_AND_TIME);
        IColumn choiceCol = smartList.createNewColumn(ColumnType.CHOICE);
        IColumn personCol = smartList.createNewColumn(ColumnType.PERSON);
        smartList.moveRight(timeCol);
        assertEquals(timeCol, smartList.getColumns().get(2));
    }

    @Test
    void testCount() {
        IColumn textCol = smartList.createNewColumn(ColumnType.TEXT);
        smartList.createNewRow();
        smartList.addData(textCol, 0, new Text("a"));
        smartList.createNewRow();
        smartList.addData(textCol, 1, new Text("b"));
        smartList.createNewRow();
        smartList.addData(textCol, 2, new Text("c"));
        smartList.createNewRow();
        smartList.addData(textCol, 3, new Text("c"));
        assertEquals(4, smartList.count(textCol));

    }

    @Test
    void testCreateView() {
        assertTrue(smartList.createView(ViewType.LIST, "list view"));
    }


    @Test
    void testAddPermission() {
        Person person = new Person("Khoa", new byte[]{});
        smartList.getPermissionManagement().addPermission(person, Permission.ADD);
        List<Permission> permissions = smartList.getPermissionManagement().getMap().get(person);
        assertNotNull(permissions);
        assertTrue(permissions.contains(Permission.ADD));
    }

    @Test
    void testRemovePermission() {
        Person person = new Person("Khoa", new byte[]{});
        smartList.getPermissionManagement().addPermission(person, Permission.ADD);
        smartList.getPermissionManagement().removePermission(person, Permission.ADD);
        List<Permission> permissions = smartList.getPermissionManagement().getMap().get(person);
        assertTrue(permissions.isEmpty());
    }

    @Test
    void testCreateRule() {
        IColumn numberCol = smartList.createNewColumn(ColumnType.NUMBER);
        smartList.createNewRow();
        smartList.addData(numberCol, 0, new Number(15));
        smartList.createNewRow();
        smartList.addData(numberCol, 1, new Number(11));
        smartList.createNewRow();
        smartList.addData(numberCol, 2, new Number(5));
        smartList.createNewRow();
        smartList.addData(numberCol, 3, new Number(10));

        Rule rule = new Rule(numberCol, Condition.GREATER_THAN, 10.0);

        Number n1 = (Number) smartList.getData(numberCol, 0);
        Number n2 = (Number) smartList.getData(numberCol, 2);
        assertTrue(rule.evaluate(n1.getNum()));
        assertFalse(rule.evaluate(n2.getNum()));
    }

}

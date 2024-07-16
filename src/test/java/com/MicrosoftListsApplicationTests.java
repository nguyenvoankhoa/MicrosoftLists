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
    void testChangeListView() {
        smartList.setListView(ListView.TABLE);
        assertEquals(ListView.TABLE, smartList.getListView());
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
        smartList.getRows().get(0).getComments().add(new Comment("user 1", "new comment"));
        assertEquals(1, smartList.getRows().get(0).getComments().stream().count());
    }

    @Test
    void testEditComment() {
        smartList.getRows().get(0).getComments().add(new Comment("user 1", "new comment"));
        smartList.getRows().get(0).getComments().get(0).setMessage("test edit comment");
        assertEquals("test edit comment", smartList.getRows().get(0).getComments().get(0).getMessage());
    }

    @Test
    void testCreateForm() {
        smartList.createForm();
        assertEquals(1, smartList.getForms().stream().count());
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
    }

    @Test
    void testSortAscColumn() {
        IColumn textCol = smartList.createNewColumn(ColumnType.TEXT);
        smartList.createNewRow();
        smartList.addData(textCol, 0, new Text("a"));
        smartList.createNewRow();
        smartList.addData(textCol, 1, new Text("b"));
        smartList.createNewRow();
        smartList.addData(textCol, 2, new Text("c"));
        smartList.sortAsc(textCol);
    }

    @Test
    void testFilterColumn() {
        IColumn column = smartList.getColumns().get(0);
        smartList.filter(column, "abc");
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

    }

    @Test
    void testMoveColumn() {

    }

    @Test
    void testDoCalculation() {

    }

    @Test
    void testCreateView() {

    }

    @Test
    void testSaveView() {

    }

    @Test
    void testEditView() {

    }

    @Test
    void testFormatView() {

    }

    @Test
    void testSendLink() {

    }

    @Test
    void testManageAccess() {

    }

    @Test
    void testCreateRule() {

    }

    @Test
    void testManageRule() {

    }

    @Test
    void testNotify() {

    }
}

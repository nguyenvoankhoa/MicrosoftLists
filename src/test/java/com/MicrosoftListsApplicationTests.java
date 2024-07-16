package com;

import com.column.*;
import com.column.datatype.Choice;
import com.column.datatype.DateAndTime;
import com.column.factory.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.Date;

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
        assertEquals(4, microsoftList.templates.stream().count());
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


//    @Test
//    void testAddTextColumn() {
//        IColumn column = new TextColumn();
//        smartList.getColumns().add(column);
//        assertEquals(ColumnType.TEXT, (TextColumn) smartList.getColumns().get(0).getColumnType());
//    }

    @Test
    void testAddMultipleColumn() throws IllegalAccessException {
        TextColumn textCol = new TextColumn("text", "sample data");
        ChoiceColumn choiceCol = new ChoiceColumn();
        TimeColumn timeCol = new TimeColumn();
        smartList.createNewColumn(textCol);
        smartList.createNewRow();
        smartList.addData(textCol, 0, "text data");

        Choice choice = new Choice();
        smartList.createNewColumn(choiceCol);
        smartList.createNewRow();
        smartList.addData(choiceCol, 1, choice);

        DateAndTime dateAndTime = new DateAndTime(new Date(), new Time(System.currentTimeMillis()));
        smartList.createNewColumn(timeCol);
        smartList.createNewRow();
        smartList.addData(timeCol, 2, dateAndTime);
        assertEquals("text data", smartList.getData(textCol, 0));
        assertEquals(choice, smartList.getData(choiceCol, 1));
        assertEquals(dateAndTime, smartList.getData(timeCol, 2));
    }

//    @Test
//    void testAddChoiceColumn() {
//        IColumn column = new ChoiceColumnFactory().factory();
//        smartList.getColumns().add(column);
//        assertEquals(ColumnType.CHOICE, smartList.getColumns().get(0).getColumnType());
//    }
//
//    @Test
//    void testAddTimeColumn() {
//        IColumn column = new TimeColumnFactory().factory();
//        smartList.getColumns().add(column);
//        assertEquals(ColumnType.DATE_AND_TIME, smartList.getColumns().get(0).getColumnType());
//    }

    @Test
    void testAddMultipleLineColumn() {

    }

    @Test
    void testAddPersonColumn() {

    }

    @Test
    void testAddYesNoColumn() {

    }

    @Test
    void testAddHyperLinkColumn() {

    }

    @Test
    void testAddImageColumn() {

    }

    @Test
    void testAddLookupColumn() {

    }

    @Test
    void testAddAverageRatingColumn() {

    }

    @Test
    void testExportListToCSV() {
        microsoftList.exportToCSV(smartList);
    }

    @Test
    void testRestoreVersionHistory() {
        microsoftList.restore();
    }

    @Test
    void testFilterList() {
        smartList.filter(smartList.getColumns().get(0));
    }

    @Test
    void testAddItem() {
        Row row = new Row();
        smartList.getRows().add(row);
        assertEquals(2, smartList.getRows().stream().count());
    }

//    @Test
//    void testEditItem() {
//        smartList.getRows().get(0).setData("New Data");
//        assertEquals("New Data", smartList.getRows().get(0).getData());
//    }

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

//    @Test
//    void testCreateColumn() {
//        smartList.createColumn();
//        assertEquals(2, smartList.getColumns().stream().count());
//    }
//
//    @Test
//    void testShowColumn() {
//        smartList.getColumns().get(0).setVisible(true);
//        assertTrue(smartList.getColumns().get(0).isVisible());
//    }
//
//    @Test
//    void testHideColumn() {
//        smartList.getColumns().get(0).setVisible(false);
//        assertFalse(smartList.getColumns().get(0).isVisible());
//    }

    @Test
    void testSortColumn() {
        IColumn column = smartList.getColumns().get(0);
        smartList.sort(column);
    }

    @Test
    void testFilterColumn() {
        IColumn column = smartList.getColumns().get(0);
        smartList.filter(column);
    }

    @Test
    void testGroupByColumn() {
        IColumn column = smartList.getColumns().get(0);
        smartList.groupBy(column);
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

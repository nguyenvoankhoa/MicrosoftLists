package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MicrosoftListsApplicationTests {
    MicrosoftList microsoftList;
    SmartList smartList;

    @BeforeEach
    public void setUp() {
        microsoftList = new MicrosoftList();
        microsoftList.initDefaultTemplate();
        smartList = microsoftList.listCollection.get(0);
    }

    @Test
    void testInitDefaultTemplate() {
        assertEquals(11, microsoftList.templates.stream().count());
    }

    @Test
    void testCreateList() {
        microsoftList.createList(new SmartList());
        assertEquals(1, microsoftList.listCollection.stream().count());
    }

    @Test
    void testAddFavouriteList() {
        microsoftList.addFavourite(new SmartList());
        assertEquals(1, microsoftList.favouriteCollection.stream().count());
    }

    @Test
    void testDeleteList() {
        microsoftList.remove("List 1");
        assertEquals(0, microsoftList.listCollection.stream().count());
    }

    @Test
    void testSaveTemplate() {
        microsoftList.saveTemplate(new SmartList());
        assertEquals(12, microsoftList.templates.stream().count());
    }

    @Test
    void testChangeListView() {
        smartList.setListView(ListView.TABLE);
        assertEquals(ListView.TABLE, smartList.getListView());
    }

    @Test
    void testSaveList() {
        microsoftList.saveList(smartList);
        assertTrue(smartList.isSave());
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
        Item item = new Item();
        smartList.getItems().add(item);
        assertEquals(2, smartList.getItems().stream().count());
    }

    @Test
    void testEditItem() {
        smartList.getItems().get(0).setData("New Data");
        assertEquals("New Data", smartList.getItems().get(0).getData());
    }

    @Test
    void testAddComment() {
        smartList.getItems().get(0).getComments().add(new Comment("user 1", "new comment"));
        assertEquals(1, smartList.getItems().get(0).getComments().stream().count());
    }

    @Test
    void testEditComment() {
        smartList.getItems().get(0).getComments().add(new Comment("user 1", "new comment"));
        smartList.getItems().get(0).getComments().get(0).setMessage("test edit comment");
        assertEquals("test edit comment", smartList.getItems().get(0).getComments().get(0).getMessage());
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
    void testCreateColumn() {
        smartList.createColumn();
        assertEquals(2, smartList.getColumns().stream().count());
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
    void testSortColumn() {
        Column column = smartList.getColumns().get(0);
        smartList.sort(column);
    }

    @Test
    void testFilterColumn() {
        Column column = smartList.getColumns().get(0);
        smartList.filter(column);
    }

    @Test
    void testGroupByColumn() {
        Column column = smartList.getColumns().get(0);
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

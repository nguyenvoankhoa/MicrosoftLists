package com;

import com.model.column.ChoiceColumn;
import com.model.column.ColumnType;
import com.model.column.IColumn;
import com.model.datatype.*;
import com.model.*;
import com.model.view.View;
import com.model.view.ViewType;
import com.service.*;
import com.export.ExportStatus;
import com.util.Common;
import com.util.ConfigLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MicrosoftListsApplicationTests {
    MicrosoftList microsoftList;
    SmartList smartList;
    String listPath;
    String templatePath;
    JsonService jsonService;
    SmartListService sls;
    MicrosoftListService mls;


    @BeforeEach
    public void setUp() throws IOException {
        listPath = ConfigLoader.loadProperties("list.file.name");
        templatePath = ConfigLoader.loadProperties("template.file.name");
        jsonService = new JsonService();
        microsoftList = new MicrosoftList();
        microsoftList.setTemplates(jsonService.loadTemplatesFromJson(templatePath));
        mls = new MicrosoftListService();
        mls.createList(microsoftList, "New list");
        smartList = microsoftList.getListCollection().get(0);
        sls = new SmartListService();
    }

    @Test
    void testInitDefaultListTemplate() {
        assertFalse(microsoftList.getTemplates().isEmpty());
    }

    @Test
    void testCreateBlankList() {
        assertNotNull(mls.createList(microsoftList, "New blank list"));
    }

    @Test
    void testCreateListFromTemplate() {
        Template template = microsoftList.getTemplates().get(0);
        assertNotNull(mls.createListFromTemplate(microsoftList, template, "List from template"));
    }


    @Test
    void testAddFavouriteList() {
        mls.addFavourite(microsoftList, "New list");
        assertNotNull(microsoftList.getFavouriteCollection().stream()
                .filter(l -> l.equals("New list"))
                .findFirst()
                .orElse(null));
    }


    @Test
    void testAddMultipleColumn() {
        sls.createNewColumn(smartList, ColumnType.TEXT, "Title", false);
        sls.createNewColumn(smartList, ColumnType.DATE_AND_TIME, "Start day", false);
        ChoiceColumn column = (ChoiceColumn) sls.createNewColumn(smartList, ColumnType.CHOICE, "Session type", false);
        column.setChoices(column.createSimpleData("choice 1"));
        sls.createNewColumn(smartList, ColumnType.PERSON, "Speaker", false);
        sls.createNewColumn(smartList, ColumnType.NUMBER, "Price", false);
        sls.createNewColumn(smartList, ColumnType.HYPERLINK, "Link", false);
        sls.createNewColumn(smartList, ColumnType.YESNO, "Is active", false);
        assertEquals(6, smartList.getColumns().size());
    }


    @Test
    void testAddRowData() {
        sls.createNewColumn(smartList, ColumnType.TEXT, "Title", false);
        sls.createNewColumn(smartList, ColumnType.DATE_AND_TIME, "Start day", false);
        ChoiceColumn column = (ChoiceColumn) sls.createNewColumn(smartList, ColumnType.CHOICE, "Session type", false);
        column.setChoices(column.createSimpleData("choice 1"));
        sls.createNewColumn(smartList, ColumnType.PERSON, "Speaker", false);
        sls.createNewColumn(smartList, ColumnType.NUMBER, "Price", false);
        sls.createNewColumn(smartList, ColumnType.HYPERLINK, "Link", false);
        sls.createNewColumn(smartList, ColumnType.YESNO, "Is active", false);


        Map<String, String> mVal = new HashMap<>();
        mVal.put("Title", "abc");
        mVal.put("Start day", "17-07-2024");
        mVal.put("Session type", "choice 1");
        mVal.put("Speaker", "Khoa");
        mVal.put("Price", "12");
        mVal.put("Link", "facebook.com");
        mVal.put("Is active", "false");

        assertNotNull(sls.addRowData(smartList, mVal));
    }

    @Test
    void testRemoveColumn() {
        sls.createNewColumn(smartList, ColumnType.TEXT, "Title", false);
        sls.createNewColumn(smartList, ColumnType.DATE_AND_TIME, "Start day", false);
        sls.removeColumn(smartList, "Title");
        assertNull(Common.getColumnByName(smartList, "Title"));
    }


    @Test
    void testSaveDataToJson() {
        assertTrue(jsonService.saveToJson(microsoftList, listPath));
    }


    @Test
    void testLoadDataFromJson() {
        MicrosoftList sl = jsonService.loadListsFromJson(listPath);
        assertNotNull(sl);
    }


    @Test
    void testSortDescColumn() {
        sls.createNewColumn(smartList, ColumnType.TEXT, "Title", false);
        sls.createNewRow(smartList);
        sls.addCellData(smartList, "Title", 0, "a");
        sls.createNewRow(smartList);
        sls.addCellData(smartList, "Title", 1, "b");
        sls.createNewRow(smartList);
        sls.addCellData(smartList, "Title", 2, "c");
        Common.sortDesc(smartList, "Title");

        assertEquals("a", ((Text) smartList.getRows().get(0).getIDataList().get(0)).getStr());
        assertEquals("b", ((Text) smartList.getRows().get(1).getIDataList().get(0)).getStr());
        assertEquals("c", ((Text) smartList.getRows().get(2).getIDataList().get(0)).getStr());
    }

    @Test
    void testSortAscColumn() {
        sls.createNewColumn(smartList, ColumnType.TEXT, "Title", false);
        sls.createNewRow(smartList);
        sls.addCellData(smartList, "Title", 0, "a");
        sls.createNewRow(smartList);
        sls.addCellData(smartList, "Title", 1, "b");
        sls.createNewRow(smartList);
        sls.addCellData(smartList, "Title", 2, "c");
        Common.sortAsc(smartList, "Title");

        assertEquals("c", ((Text) smartList.getRows().get(1).getIDataList().get(0)).getStr());
        assertEquals("b", ((Text) smartList.getRows().get(2).getIDataList().get(0)).getStr());
        assertEquals("a", ((Text) smartList.getRows().get(3).getIDataList().get(0)).getStr());
    }

    @Test
    void testFilterColumn() {
        sls.createNewColumn(smartList, ColumnType.TEXT, "Title", false);
        sls.createNewRow(smartList);
        sls.addCellData(smartList, "Title", 0, "a");
        sls.createNewRow(smartList);
        sls.addCellData(smartList, "Title", 1, "b");
        sls.createNewRow(smartList);
        sls.addCellData(smartList, "Title", 2, "c");
        List<Object> criteria = Common.getListFilter(smartList, "Title");
        List<Row> filteredRows = Common.filter(smartList, "Title", criteria.get(0));

        assertEquals(1, filteredRows.size());
        assertEquals("a", filteredRows.get(0).getIDataList().get(0).getImportantData());
    }

    @Test
    void testGroupByColumn() {
        sls.createNewColumn(smartList, ColumnType.TEXT, "Title", false);
        sls.createNewRow(smartList);
        sls.addCellData(smartList, "Title", 0, "a");
        sls.createNewRow(smartList);
        sls.addCellData(smartList, "Title", 1, "b");
        sls.createNewRow(smartList);
        sls.addCellData(smartList, "Title", 2, "c");
        Map<Object, List<Row>> groupedRows = Common.groupBy(smartList, "Title");

        assertNotNull(groupedRows);
        assertEquals(3, groupedRows.size());

    }

    @Test
    void testCount() {
        sls.createNewColumn(smartList, ColumnType.TEXT, "Title", false);
        sls.createNewRow(smartList);
        sls.addCellData(smartList, "Title", 0, "a");
        sls.createNewRow(smartList);
        sls.addCellData(smartList, "Title", 1, "b");
        sls.createNewRow(smartList);
        sls.addCellData(smartList, "Title", 2, "c");
        assertEquals(4, Common.count(smartList, "Title"));

    }

    @Test
    void testMoveLeftColumn() {
        sls.createNewColumn(smartList, ColumnType.TEXT, "Title", false);
        sls.createNewColumn(smartList, ColumnType.DATE_AND_TIME, "Start day", false);
        sls.createNewColumn(smartList, ColumnType.CHOICE, "Session type", false);
        sls.moveLeft(smartList, "Start day");

        assertEquals(0, Common.getColumnIndexByName(smartList, "Start day"));
    }

    @Test
    void testMoveRightColumn() {
        sls.createNewColumn(smartList, ColumnType.TEXT, "Title", false);
        sls.createNewColumn(smartList, ColumnType.DATE_AND_TIME, "Start day", false);
        sls.createNewColumn(smartList, ColumnType.CHOICE, "Session type", false);
        sls.moveRight(smartList, "Start day");

        assertEquals(2, Common.getColumnIndexByName(smartList, "Start day"));
    }

    @Test
    void testCreateView() {
        View view = sls.createView(smartList, ViewType.LIST, "List view");
        assertEquals(ViewType.LIST, view.getViewType());
    }


    @Test
    void testExportListToCSV() throws IOException {
        String csvPath = ConfigLoader.loadProperties("csv.file.name");
        assertEquals(ExportStatus.SUCCESS, Common.exportToCSV(smartList, csvPath).getStatus());
    }

    @Test
    void testCreateForm() {
        IColumn textCol = sls.createNewColumn(smartList, ColumnType.TEXT, "Title", false);
        IColumn timeCol = sls.createNewColumn(smartList, ColumnType.DATE_AND_TIME, "Start day", false);
        IColumn choiceCol = sls.createNewColumn(smartList, ColumnType.CHOICE, "Session type", false);
        List<IColumn<?>> columns = new ArrayList<>();
        columns.add(textCol);
        columns.add(timeCol);
        columns.add(choiceCol);

        sls.createForm(smartList, columns, "New form");
        assertEquals(3, smartList.getForms().get(0).getColumns().size());
    }

    @Test
    void testEditForm() {
        IColumn textCol = sls.createNewColumn(smartList, ColumnType.TEXT, "Title", false);
        IColumn timeCol = sls.createNewColumn(smartList, ColumnType.DATE_AND_TIME, "Start day", false);
        IColumn choiceCol = sls.createNewColumn(smartList, ColumnType.CHOICE, "Session type", false);
        List<IColumn<?>> columns = new ArrayList<>();
        columns.add(textCol);
        columns.add(timeCol);
        columns.add(choiceCol);

        sls.createForm(smartList, columns, "New Form");
        Form f = Common.getFormByName(smartList, "New Form");
        f.removeColumn(textCol);
        assertEquals(3, smartList.getColumns().size());
    }


    @Test
    void testShowColumn() {
        sls.createNewColumn(smartList, ColumnType.TEXT, "Title", false);
        sls.showColumn(smartList, "Title");
        assertTrue(Common.getColumnByName(smartList, "Title").isVisible());
    }

    @Test
    void testHideColumn() {
        sls.createNewColumn(smartList, ColumnType.TEXT, "Title", false);
        sls.hideColumn(smartList, "Title");
        assertFalse(Common.getColumnByName(smartList, "Title").isVisible());
    }


}

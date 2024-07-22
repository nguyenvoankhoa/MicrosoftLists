package com;

import com.column.*;
import com.column.datatype.*;
import com.column.datatype.Number;
import com.permission.Permission;
import com.service.ConfigService;
import com.export.ExportStatus;
import com.service.JsonService;
import com.view.View;
import com.view.ViewType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Time;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MicrosoftListsApplicationTests {
    MicrosoftList microsoftList;
    SmartList smartList;
    String listPath;
    String templatePath;

    @BeforeEach
    public void setUp() throws IOException {
        listPath = ConfigService.loadProperties("smartlist.file.name");
        templatePath = ConfigService.loadProperties("template.file.name");
        microsoftList = new MicrosoftList(templatePath);
        microsoftList.createList("ABC");
        smartList = microsoftList.getListCollection().get(0);
    }

    @Test
    void testInitDefaultListTemplate() {
        assertFalse(microsoftList.getTemplates().isEmpty());
    }

    @Test
    void testCreateBlankList() {
        assertFalse(microsoftList.createList("ABC"));
    }

    @Test
    void testCreateListFromTemplate() {
        Template template = microsoftList.getTemplates().get(0);
        assertNotNull(microsoftList.createListFromTemplate(template, "List from template"));
    }


    @Test
    void testAddFavouriteList() {
        microsoftList.addFavourite(smartList);
        assertNotNull(microsoftList.getFavouriteCollection().stream()
                .filter(l -> l.getName().equals("ABC"))
                .findFirst()
                .orElse(null));
    }


    @Test
    void testAddMultipleColumn() {
        smartList.createNewColumn(ColumnType.TEXT, "Title");
        smartList.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        ChoiceColumn column = (ChoiceColumn) smartList.createNewColumn(ColumnType.CHOICE, "Session type");
        List<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Option 1"));
        choices.add(new Choice("Option 2"));
        choices.add(new Choice("Option 3"));
        column.setChoices(choices);
        smartList.createNewColumn(ColumnType.PERSON, "Speaker");
        smartList.createNewColumn(ColumnType.NUMBER, "Price");
        smartList.createNewColumn(ColumnType.HYPERLINK, "Link");
        smartList.createNewColumn(ColumnType.YESNO, "Is active");
        smartList.createNewRow();

        Text text = new Text("text data");
        smartList.addData("Title", 0, text);
        Choice choice = new Choice();
        smartList.addData("Session type", 0, choice);
        DateAndTime dateAndTime = new DateAndTime(new Date(), new Time(System.currentTimeMillis()));
        smartList.addData("Start day", 0, dateAndTime);
        Person person = new Person("Khoa", new byte[]{});
        smartList.addData("Speaker", 0, person);
        Number number = new Number(4.5);
        smartList.addData("Price", 0, number);
        HyperLink link = new HyperLink("facebook.com");
        smartList.addData("Link", 0, link);
        YesNo active = new YesNo(true);
        smartList.addData("Is active", 0, active);


        assertEquals(text.getStr(), ((Text) smartList.getData("Title", 0)).getStr());
        assertEquals(choice.getName(), ((Choice) smartList.getData("Session type", 0)).getName());
        assertEquals(dateAndTime.getDate(), ((DateAndTime) smartList.getData("Start day", 0)).getDate());
        assertEquals(person.getName(), ((Person) smartList.getData("Speaker", 0)).getName());
        assertEquals(number.getNum(), ((Number) smartList.getData("Price", 0)).getNum());
    }


    @Test
    void testAddRowData() {
        smartList.createNewColumn(ColumnType.TEXT, "Title");
        smartList.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        ChoiceColumn column = (ChoiceColumn) smartList.createNewColumn(ColumnType.CHOICE, "Session type");
        List<Choice> choices = new ArrayList<>();
        Choice c1 = new Choice("Option 1");
        Choice c2 = new Choice("Option 2");
        Choice c3 = new Choice("Option 3");
        choices.add(c1);
        choices.add(c3);
        choices.add(c2);
        column.setChoices(choices);
        smartList.createNewColumn(ColumnType.PERSON, "Speaker");
        smartList.createNewColumn(ColumnType.NUMBER, "Price");
        smartList.createNewColumn(ColumnType.HYPERLINK, "Link");
        smartList.createNewColumn(ColumnType.YESNO, "Is active");

        Text text = new Text("text data");
        DateAndTime dateAndTime = new DateAndTime(new Date(), new Time(System.currentTimeMillis()));
        Person person = new Person("Khoa", new byte[]{});
        Number number = new Number(4.5);
        HyperLink link = new HyperLink("facebook.com");
        YesNo active = new YesNo(true);
        Rating rating = new Rating(3);

        assertTrue(smartList.addRowData(text, dateAndTime, c1, person, number, link, active));
        assertFalse(smartList.addRowData(text, dateAndTime, c1, person, number, link, active, rating));
        assertTrue(smartList.addRowData(text, dateAndTime, c1, person, number, link));
        assertTrue(smartList.addRowData(text, c1, person, number, link));
    }

    @Test
    void testRemoveColumn() {
        smartList.createNewColumn(ColumnType.TEXT, "Title");
        smartList.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        Text text = new Text("text data");
        Text text2 = new Text("text data2");
        DateAndTime dateAndTime = new DateAndTime(new Date(), new Time(System.currentTimeMillis()));

        smartList.addRowData(text, dateAndTime);
        smartList.addRowData(text2, dateAndTime);

        smartList.removeColumn("Title");
        assertNull(Common.getColumnByName(smartList, "Title"));
        assertEquals(1, smartList.getRows().get(0).getIDataList().size());
    }

    @Test
    void testPaging() {
        smartList.createNewColumn(ColumnType.TEXT, "Title");
        smartList.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        ChoiceColumn column = (ChoiceColumn) smartList.createNewColumn(ColumnType.CHOICE, "Session type");
        List<Choice> choices = new ArrayList<>();
        Choice c1 = new Choice("Option 1");
        Choice c2 = new Choice("Option 2");
        Choice c3 = new Choice("Option 3");
        choices.add(c1);
        choices.add(c3);
        choices.add(c2);
        column.setChoices(choices);
        smartList.createNewColumn(ColumnType.PERSON, "Speaker");
        smartList.createNewColumn(ColumnType.NUMBER, "Price");
        smartList.createNewColumn(ColumnType.HYPERLINK, "Link");
        smartList.createNewColumn(ColumnType.YESNO, "Is active");

        Text text = new Text("text data");
        DateAndTime dateAndTime = new DateAndTime(new Date(), new Time(System.currentTimeMillis()));
        Person person = new Person("Khoa", new byte[]{});
        Number number = new Number(4.5);
        HyperLink link = new HyperLink("facebook.com");
        YesNo active = new YesNo(true);

        smartList.addRowData(text, dateAndTime, c1, person, number, link, active);
        smartList.addRowData(text, dateAndTime, c1, person, number, link, active);
        smartList.addRowData(text, dateAndTime, c1, person, number, link, active);

        assertEquals(3, Common.getPage(smartList, 1, 4).size());
        assertEquals(2, Common.getPage(smartList, 1, 2).size());

        smartList.addRowData(text, dateAndTime, c1, person, number, link, active);
        smartList.addRowData(text, dateAndTime, c1, person, number, link, active);

        assertEquals(1, Common.getPage(smartList, 2, 4).size());
    }


    @Test
    void testSaveDataToJson() throws IOException {
        smartList.createNewColumn(ColumnType.TEXT, "Title");
        smartList.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        ChoiceColumn column = (ChoiceColumn) smartList.createNewColumn(ColumnType.CHOICE, "Session type");
        List<Choice> choices = new ArrayList<>();
        Choice c1 = new Choice("Option 1");
        Choice c2 = new Choice("Option 2");
        Choice c3 = new Choice("Option 3");
        choices.add(c1);
        choices.add(c3);
        choices.add(c2);
        column.setChoices(choices);
        smartList.createNewColumn(ColumnType.PERSON, "Speaker");
        smartList.createNewColumn(ColumnType.NUMBER, "Price");
        smartList.createNewColumn(ColumnType.HYPERLINK, "Link");
        smartList.createNewColumn(ColumnType.YESNO, "Is active");
        smartList.createNewColumn(ColumnType.PERSON, "Speaker");
        smartList.createNewColumn(ColumnType.NUMBER, "Price");
        smartList.createNewColumn(ColumnType.HYPERLINK, "Link");
        smartList.createNewColumn(ColumnType.YESNO, "Is active");
        smartList.createNewRow();

        Text text = new Text("text data");
        smartList.addData("Title", 0, text);
        smartList.addData("Session type", 0, c1);
        DateAndTime dateAndTime = new DateAndTime(new Date(), new Time(System.currentTimeMillis()));
        smartList.addData("Start day", 0, dateAndTime);
        Person person = new Person("Khoa", new byte[]{});
        smartList.addData("Speaker", 0, person);
        Number number = new Number(4.5);
        smartList.addData("Price", 0, number);
        HyperLink link = new HyperLink("facebook.com");
        smartList.addData("Link", 0, link);
        YesNo active = new YesNo(true);
        smartList.addData("Is active", 0, active);

        assertTrue(JsonService.saveToJson(smartList, listPath));
    }


    @Test
    void testLoadDataFromJson() throws IOException {
        SmartList sl = JsonService.loadSmartListFromJson(listPath);
        assertNotNull(sl);
    }


    @Test
    void testSortDescColumn() {
        smartList.createNewColumn(ColumnType.TEXT, "Title");
        smartList.createNewRow();
        smartList.addData("Title", 0, new Text("a"));
        smartList.createNewRow();
        smartList.addData("Title", 1, new Text("b"));
        smartList.createNewRow();
        smartList.addData("Title", 2, new Text("c"));
        Common.sortDesc(smartList, "Title");

        assertEquals("a", ((Text) smartList.getRows().get(0).getIDataList().get(0)).getStr());
        assertEquals("b", ((Text) smartList.getRows().get(1).getIDataList().get(0)).getStr());
        assertEquals("c", ((Text) smartList.getRows().get(2).getIDataList().get(0)).getStr());
    }

    @Test
    void testSortAscColumn() {
        smartList.createNewColumn(ColumnType.TEXT, "Title");
        smartList.createNewRow();
        smartList.addData("Title", 0, new Text("a"));
        smartList.createNewRow();
        smartList.addData("Title", 1, new Text("b"));
        smartList.createNewRow();
        smartList.addData("Title", 2, new Text("d"));
        smartList.createNewRow();
        smartList.addData("Title", 3, new Text("c"));
        Common.sortAsc(smartList, "Title");

        assertEquals("d", ((Text) smartList.getRows().get(0).getIDataList().get(0)).getStr());
        assertEquals("c", ((Text) smartList.getRows().get(1).getIDataList().get(0)).getStr());
        assertEquals("b", ((Text) smartList.getRows().get(2).getIDataList().get(0)).getStr());
        assertEquals("a", ((Text) smartList.getRows().get(3).getIDataList().get(0)).getStr());
    }

    @Test
    void testFilterColumn() {
        smartList.createNewColumn(ColumnType.TEXT, "Title");
        smartList.createNewRow();
        smartList.addData("Title", 0, new Text("a"));
        smartList.createNewRow();
        smartList.addData("Title", 1, new Text("b"));
        smartList.createNewRow();
        smartList.addData("Title", 2, new Text("c"));
        List<Object> criteria = Common.getListFilter(smartList, "Title");
        List<Row> filteredRows = Common.filter(smartList, "Title", criteria.get(0));

        assertEquals(1, filteredRows.size());
        assertEquals("a", filteredRows.get(0).getIDataList().get(0).getImportantData());
    }

    @Test
    void testGroupByColumn() {
        smartList.createNewColumn(ColumnType.TEXT, "Title");
        smartList.createNewRow();
        smartList.addData("Title", 0, new Text("a"));
        smartList.createNewRow();
        smartList.addData("Title", 1, new Text("b"));
        smartList.createNewRow();
        smartList.addData("Title", 2, new Text("c"));
        smartList.createNewRow();
        smartList.addData("Title", 3, new Text("c"));
        Map<Object, List<Row>> groupedRows = Common.groupBy(smartList, "Title");

        assertNotNull(groupedRows);
        assertEquals(3, groupedRows.size());

    }

    @Test
    void testCount() {
        smartList.createNewColumn(ColumnType.TEXT, "Title");
        smartList.createNewRow();
        smartList.addData("Title", 0, new Text("a"));
        smartList.createNewRow();
        smartList.addData("Title", 1, new Text("b"));
        smartList.createNewRow();
        smartList.addData("Title", 2, new Text("c"));
        smartList.createNewRow();
        smartList.addData("Title", 3, new Text("c"));
        assertEquals(4, smartList.count("Title"));

    }

    @Test
    void testMoveLeftColumn() {
        smartList.createNewColumn(ColumnType.TEXT, "Title");
        smartList.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        smartList.createNewColumn(ColumnType.CHOICE, "Session type");
        smartList.moveLeft("Start day");

        assertEquals(0, Common.getColumnIndexByName(smartList, "Start day"));
    }

    @Test
    void testMoveRightColumn() {
        smartList.createNewColumn(ColumnType.TEXT, "Title");
        smartList.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        smartList.createNewColumn(ColumnType.CHOICE, "Session type");
        smartList.moveRight("Start day");

        assertEquals(2, Common.getColumnIndexByName(smartList, "Start day"));
    }

    @Test
    void testCreateView() {
        List<View> views = smartList.createBoardView(ViewType.LIST, "List view");
        var view = views.stream().filter(v -> v.getTitle().equals("List view"))
                .findFirst().orElse(null);
        assertNotNull(view);
        assertEquals(ViewType.LIST, view.getViewType());
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
    void testExportListToCSV() throws IOException {
        String csvPath = ConfigService.loadProperties("csv.file.name");
        assertEquals(ExportStatus.SUCCESS, Common.exportToCSV(smartList, csvPath).getStatus());
    }

    @Test
    void testCreateForm() {
        IColumn textCol = smartList.createNewColumn(ColumnType.TEXT, "Title");
        IColumn timeCol = smartList.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        IColumn choiceCol = smartList.createNewColumn(ColumnType.CHOICE, "Session type");
        List<IColumn> columns = new ArrayList<>();
        columns.add(textCol);
        columns.add(timeCol);
        columns.add(choiceCol);

        smartList.createForm(columns, "New form");
        assertEquals(3, smartList.getForms().get(0).getColumns().size());
    }

    @Test
    void testEditForm() {
        IColumn textCol = smartList.createNewColumn(ColumnType.TEXT, "Title");
        IColumn timeCol = smartList.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        IColumn choiceCol = smartList.createNewColumn(ColumnType.CHOICE, "Session type");
        List<IColumn> columns = new ArrayList<>();
        columns.add(textCol);
        columns.add(timeCol);
        columns.add(choiceCol);

        smartList.createForm(columns, "New Form");
        Form f = Common.getFormByName(smartList, "New Form");
        f.removeColumn(textCol);
        assertEquals(2, smartList.getColumns().size());
    }


    @Test
    void testShowColumn() {
        smartList.createNewColumn(ColumnType.TEXT, "Title");
        smartList.showColumn("Title");
        assertTrue(Common.getColumnByName(smartList, "Title").isVisible());
    }

    @Test
    void testHideColumn() {
        smartList.createNewColumn(ColumnType.TEXT, "Title");
        smartList.hideColumn("Title");
        assertFalse(Common.getColumnByName(smartList, "Title").isVisible());
    }


}

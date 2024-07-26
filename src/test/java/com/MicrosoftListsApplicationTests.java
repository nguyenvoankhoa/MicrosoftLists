package com;

import com.model.column.ChoiceColumn;
import com.model.column.ColumnType;
import com.model.column.IColumn;
import com.model.datatype.*;
import com.model.*;
import com.model.Permission;
import com.model.datatype.Number;
import com.model.view.View;
import com.model.view.ViewType;
import com.service.*;
import com.service.export.ExportStatus;
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
    JsonService jsonService;

    SmartListService sls;

    MicrosoftListService mls;

    @BeforeEach
    public void setUp() throws IOException {
        listPath = ConfigService.loadProperties("list.file.name");
        templatePath = ConfigService.loadProperties("template.file.name");
        jsonService = new JsonService();
        microsoftList = new MicrosoftList(templatePath, jsonService);
        mls = new MicrosoftListService();
        mls.createList("New list");
        smartList = microsoftList.getListCollection().get(0);
        sls = new SmartListService(smartList);
    }

    @Test
    void testInitDefaultListTemplate() {
        assertFalse(microsoftList.getTemplates().isEmpty());
    }

    @Test
    void testCreateBlankList() {
        assertNotNull(mls.createList("New list"));
    }

    @Test
    void testCreateListFromTemplate() {
        Template template = microsoftList.getTemplates().get(0);
        assertNotNull(mls.createListFromTemplate(template, "List from template"));
    }


    @Test
    void testAddFavouriteList() {
        mls.addFavourite("New list");
        assertNotNull(microsoftList.getFavouriteCollection().stream()
                .filter(l -> l.getName().equals("New list"))
                .findFirst()
                .orElse(null));
    }


    @Test
    void testAddMultipleColumn() {
        sls.createNewColumn(ColumnType.TEXT, "Title");
        sls.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        ChoiceColumn column = (ChoiceColumn) sls.createNewColumn(ColumnType.CHOICE, "Session type");
        List<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Option 1"));
        choices.add(new Choice("Option 2"));
        choices.add(new Choice("Option 3"));
        column.setChoices(choices);
        sls.createNewColumn(ColumnType.PERSON, "Speaker");
        sls.createNewColumn(ColumnType.NUMBER, "Price");
        sls.createNewColumn(ColumnType.HYPERLINK, "Link");
        sls.createNewColumn(ColumnType.YESNO, "Is active");
        sls.createNewRow();

        Text text = new Text("text data");
        sls.addData("Title", 0, text);
        Choice choice = new Choice();
        sls.addData("Session type", 0, choice);
        DateAndTime dateAndTime = new DateAndTime(new Date(), new Time(System.currentTimeMillis()));
        sls.addData("Start day", 0, dateAndTime);
        Person person = new Person("Khoa", new byte[]{});
        sls.addData("Speaker", 0, person);
        Number number = new Number(4.5);
        sls.addData("Price", 0, number);
        HyperLink link = new HyperLink("facebook.com");
        sls.addData("Link", 0, link);
        YesNo active = new YesNo(true);
        sls.addData("Is active", 0, active);


        assertEquals(text.getStr(), ((Text) sls.getData("Title", 0)).getStr());
        assertEquals(choice, ((ArrayList<?>) sls.getData("Session type", 0)).get(0));
        assertEquals(dateAndTime.getDate(), ((DateAndTime) sls.getData("Start day", 0)).getDate());
        assertEquals(person.getName(), ((Person) sls.getData("Speaker", 0)).getName());
        assertEquals(number.getNum(), ((Number) sls.getData("Price", 0)).getNum());
    }


    @Test
    void testAddRowData() {
        sls.createNewColumn(ColumnType.TEXT, "Title");
        sls.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        ChoiceColumn column = (ChoiceColumn) sls.createNewColumn(ColumnType.CHOICE, "Session type");
        List<Choice> choices = new ArrayList<>();
        Choice c1 = new Choice("Option 1");
        Choice c2 = new Choice("Option 2");
        Choice c3 = new Choice("Option 3");
        choices.add(c1);
        choices.add(c3);
        choices.add(c2);
        column.setChoices(choices);
        sls.createNewColumn(ColumnType.PERSON, "Speaker");
        sls.createNewColumn(ColumnType.NUMBER, "Price");
        sls.createNewColumn(ColumnType.HYPERLINK, "Link");
        sls.createNewColumn(ColumnType.YESNO, "Is active");

        Text text = new Text("text data");
        DateAndTime dateAndTime = new DateAndTime(new Date(), new Time(System.currentTimeMillis()));
        Person person = new Person("Khoa", new byte[]{});
        Number number = new Number(4.5);
        HyperLink link = new HyperLink("facebook.com");
        YesNo active = new YesNo(true);

        Map<String, Object> mVal = new HashMap<>();
        mVal.put("Title", text);
        mVal.put("Start day", dateAndTime);
        mVal.put("Session type", choices);
        mVal.put("Speaker", person);
        mVal.put("Price", number);
        mVal.put("Link", link);
        mVal.put("Is active", active);

        assertNotNull(sls.addRowData(mVal));
    }

    @Test
    void testRemoveColumn() {
        sls.createNewColumn(ColumnType.TEXT, "Title");
        sls.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        Text text = new Text("text data");
        Text text2 = new Text("text data2");
        DateAndTime dateAndTime = new DateAndTime(new Date(), new Time(System.currentTimeMillis()));

        sls.addRowData(text, dateAndTime);
        sls.addRowData(text2, dateAndTime);

        sls.removeColumn("Title");
        assertNull(Common.getColumnByName(smartList, "Title"));
        assertEquals(1, smartList.getRows().get(0).getIDataList().size());
    }

    @Test
    void testPaging() {
        sls.createNewColumn(ColumnType.TEXT, "Title");
        sls.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        ChoiceColumn column = (ChoiceColumn) sls.createNewColumn(ColumnType.CHOICE, "Session type");
        List<Choice> choices = new ArrayList<>();
        Choice c1 = new Choice("Option 1");
        Choice c2 = new Choice("Option 2");
        Choice c3 = new Choice("Option 3");
        choices.add(c1);
        choices.add(c3);
        choices.add(c2);
        column.setChoices(choices);
        sls.createNewColumn(ColumnType.PERSON, "Speaker");
        sls.createNewColumn(ColumnType.NUMBER, "Price");
        sls.createNewColumn(ColumnType.HYPERLINK, "Link");
        sls.createNewColumn(ColumnType.YESNO, "Is active");

        Text text = new Text("text data");
        DateAndTime dateAndTime = new DateAndTime(new Date(), new Time(System.currentTimeMillis()));
        Person person = new Person("Khoa", new byte[]{});
        Number number = new Number(4.5);
        HyperLink link = new HyperLink("facebook.com");
        YesNo active = new YesNo(true);

        sls.addRowData(text, dateAndTime, c1, person, number, link, active);
        sls.addRowData(text, dateAndTime, c1, person, number, link, active);
        sls.addRowData(text, dateAndTime, c1, person, number, link, active);

        assertEquals(3, Common.getPage(smartList, 1, 4).size());
        assertEquals(2, Common.getPage(smartList, 1, 2).size());

        sls.addRowData(text, dateAndTime, c1, person, number, link, active);
        sls.addRowData(text, dateAndTime, c1, person, number, link, active);

        assertEquals(1, Common.getPage(smartList, 2, 4).size());
    }


    @Test
    void testSaveDataToJson() {
        sls.createNewColumn(ColumnType.TEXT, "Title");
        sls.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        ChoiceColumn column = (ChoiceColumn) sls.createNewColumn(ColumnType.CHOICE, "Session type");
        List<Choice> choices = new ArrayList<>();
        Choice c1 = new Choice("Option 1");
        Choice c2 = new Choice("Option 2");
        Choice c3 = new Choice("Option 3");
        choices.add(c1);
        choices.add(c3);
        choices.add(c2);
        column.setChoices(choices);
        sls.createNewColumn(ColumnType.PERSON, "Speaker");
        sls.createNewColumn(ColumnType.NUMBER, "Price");
        sls.createNewColumn(ColumnType.HYPERLINK, "Link");
        sls.createNewColumn(ColumnType.YESNO, "Is active");
        sls.createNewRow();

        Text text = new Text("text data");
        sls.addData("Title", 0, text);
        sls.addData("Session type", 0, c1);
        DateAndTime dateAndTime = new DateAndTime(new Date(), new Time(System.currentTimeMillis()));
        sls.addData("Start day", 0, dateAndTime);
        Person person = new Person("Khoa", new byte[]{});
        sls.addData("Speaker", 0, person);
        Number number = new Number(4.5);
        sls.addData("Price", 0, number);
        HyperLink link = new HyperLink("facebook.com");
        sls.addData("Link", 0, link);
        YesNo active = new YesNo(true);
        sls.addData("Is active", 0, active);
        sls.createNewRow();
        Text text2 = new Text("string f");
        sls.addData("Title", 1, text2);

        assertTrue(jsonService.saveToJson(microsoftList, listPath));
    }


//    @Test
//    void testLoadDataFromJson() {
//        SmartList sl = jsonService.loadSmartListFromJson(listPath);
//        assertNotNull(sl);
//    }


    @Test
    void testSortDescColumn() {
        sls.createNewColumn(ColumnType.TEXT, "Title");
        sls.createNewRow();
        sls.addData("Title", 0, new Text("a"));
        sls.createNewRow();
        sls.addData("Title", 1, new Text("b"));
        sls.createNewRow();
        sls.addData("Title", 2, new Text("c"));
        Common.sortDesc(smartList, "Title");

        assertEquals("a", ((Text) smartList.getRows().get(0).getIDataList().get(0)).getStr());
        assertEquals("b", ((Text) smartList.getRows().get(1).getIDataList().get(0)).getStr());
        assertEquals("c", ((Text) smartList.getRows().get(2).getIDataList().get(0)).getStr());
    }

    @Test
    void testSortAscColumn() {
        sls.createNewColumn(ColumnType.TEXT, "Title");
        sls.createNewRow();
        sls.addData("Title", 0, new Text("a"));
        sls.createNewRow();
        sls.addData("Title", 1, new Text("b"));
        sls.createNewRow();
        sls.addData("Title", 2, new Text("d"));
        sls.createNewRow();
        sls.addData("Title", 3, new Text("c"));
        Common.sortAsc(smartList, "Title");

        assertEquals("d", ((Text) smartList.getRows().get(0).getIDataList().get(0)).getStr());
        assertEquals("c", ((Text) smartList.getRows().get(1).getIDataList().get(0)).getStr());
        assertEquals("b", ((Text) smartList.getRows().get(2).getIDataList().get(0)).getStr());
        assertEquals("a", ((Text) smartList.getRows().get(3).getIDataList().get(0)).getStr());
    }

    @Test
    void testFilterColumn() {
        sls.createNewColumn(ColumnType.TEXT, "Title");
        sls.createNewRow();
        sls.addData("Title", 0, new Text("a"));
        sls.createNewRow();
        sls.addData("Title", 1, new Text("b"));
        sls.createNewRow();
        sls.addData("Title", 2, new Text("c"));
        List<Object> criteria = Common.getListFilter(smartList, "Title");
        List<Row> filteredRows = Common.filter(smartList, "Title", criteria.get(0));

        assertEquals(1, filteredRows.size());
        assertEquals("a", filteredRows.get(0).getIDataList().get(0).getImportantData());
    }

    @Test
    void testGroupByColumn() {
        sls.createNewColumn(ColumnType.TEXT, "Title");
        sls.createNewRow();
        sls.addData("Title", 0, new Text("a"));
        sls.createNewRow();
        sls.addData("Title", 1, new Text("b"));
        sls.createNewRow();
        sls.addData("Title", 2, new Text("c"));
        sls.createNewRow();
        sls.addData("Title", 3, new Text("c"));
        Map<Object, List<Row>> groupedRows = Common.groupBy(smartList, "Title");

        assertNotNull(groupedRows);
        assertEquals(3, groupedRows.size());

    }

    @Test
    void testCount() {
        sls.createNewColumn(ColumnType.TEXT, "Title");
        sls.createNewRow();
        sls.addData("Title", 0, new Text("a"));
        sls.createNewRow();
        sls.addData("Title", 1, new Text("b"));
        sls.createNewRow();
        sls.addData("Title", 2, new Text("c"));
        sls.createNewRow();
        sls.addData("Title", 3, new Text("c"));
        assertEquals(4, sls.count("Title"));

    }

    @Test
    void testMoveLeftColumn() {
        sls.createNewColumn(ColumnType.TEXT, "Title");
        sls.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        sls.createNewColumn(ColumnType.CHOICE, "Session type");
        sls.moveLeft("Start day");

        assertEquals(0, Common.getColumnIndexByName(smartList, "Start day"));
    }

    @Test
    void testMoveRightColumn() {
        sls.createNewColumn(ColumnType.TEXT, "Title");
        sls.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        sls.createNewColumn(ColumnType.CHOICE, "Session type");
        sls.moveRight("Start day");

        assertEquals(2, Common.getColumnIndexByName(smartList, "Start day"));
    }

    @Test
    void testCreateView() {
        List<View> views = sls.createView(ViewType.LIST, "List view");
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
        IColumn textCol = sls.createNewColumn(ColumnType.TEXT, "Title");
        IColumn timeCol = sls.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        IColumn choiceCol = sls.createNewColumn(ColumnType.CHOICE, "Session type");
        List<IColumn<?>> columns = new ArrayList<>();
        columns.add(textCol);
        columns.add(timeCol);
        columns.add(choiceCol);

        sls.createForm(columns, "New form");
        assertEquals(3, smartList.getForms().get(0).getColumns().size());
    }

    @Test
    void testEditForm() {
        IColumn textCol = sls.createNewColumn(ColumnType.TEXT, "Title");
        IColumn timeCol = sls.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        IColumn choiceCol = sls.createNewColumn(ColumnType.CHOICE, "Session type");
        List<IColumn<?>> columns = new ArrayList<>();
        columns.add(textCol);
        columns.add(timeCol);
        columns.add(choiceCol);

        sls.createForm(columns, "New Form");
        Form f = Common.getFormByName(smartList, "New Form");
        f.removeColumn(textCol);
        assertEquals(3, smartList.getColumns().size());
    }


    @Test
    void testShowColumn() {
        sls.createNewColumn(ColumnType.TEXT, "Title");
        sls.showColumn("Title");
        assertTrue(Common.getColumnByName(smartList, "Title").isVisible());
    }

    @Test
    void testHideColumn() {
        sls.createNewColumn(ColumnType.TEXT, "Title");
        sls.hideColumn("Title");
        assertFalse(Common.getColumnByName(smartList, "Title").isVisible());
    }


}

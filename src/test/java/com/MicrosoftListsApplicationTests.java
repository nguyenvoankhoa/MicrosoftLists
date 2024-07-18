package com;

import com.column.*;
import com.column.datatype.*;
import com.column.datatype.Number;
import com.service.ConfigService;
import com.export.ExportStatus;
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

    // paging
    // search
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


        assertEquals(text.getText(), ((Text) smartList.getData("Title", 0)).getText());
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

        assertEquals(3, smartList.getPage(1, 4).size());
        assertEquals(2, smartList.getPage(1, 2).size());

        smartList.addRowData(text, dateAndTime, c1, person, number, link, active);
        smartList.addRowData(text, dateAndTime, c1, person, number, link, active);

        assertEquals(1, smartList.getPage(2, 4).size());
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

        assertTrue(microsoftList.getJsonService().saveToJson(smartList, listPath));
    }


    @Test
    void testLoadDataFromJson() throws IOException {
        SmartList sl = microsoftList.getJsonService().loadSmartListFromJson(listPath);
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
        smartList.sortDesc("Title");

        assertEquals("a", ((Text) smartList.getRows().get(0).getIDataList().get(0)).getText());
        assertEquals("b", ((Text) smartList.getRows().get(1).getIDataList().get(0)).getText());
        assertEquals("c", ((Text) smartList.getRows().get(2).getIDataList().get(0)).getText());
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
        smartList.sortAsc("Title");

        assertEquals("d", ((Text) smartList.getRows().get(0).getIDataList().get(0)).getText());
        assertEquals("c", ((Text) smartList.getRows().get(1).getIDataList().get(0)).getText());
        assertEquals("b", ((Text) smartList.getRows().get(2).getIDataList().get(0)).getText());
        assertEquals("a", ((Text) smartList.getRows().get(3).getIDataList().get(0)).getText());
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
        List<Object> criteria = smartList.getListFilter("Title");
        List<Row> filteredRows = smartList.filter("Title", criteria.get(0));

        assertEquals(1, filteredRows.size());
        assertEquals("a", ((Text) filteredRows.get(0).getIDataList().get(0)).getImportantData());
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
        Map<Object, List<Row>> groupedRows = smartList.groupBy("Title");

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
        IColumn textCol = smartList.createNewColumn(ColumnType.TEXT, "Title");
        IColumn timeCol = smartList.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        IColumn choiceCol = smartList.createNewColumn(ColumnType.CHOICE, "Session type");
        smartList.moveLeft("Start day");
        assertEquals(timeCol, smartList.getColumns().get(0));
    }

    @Test
    void testMoveRightColumn() {
        IColumn textCol = smartList.createNewColumn(ColumnType.TEXT, "Title");
        IColumn timeCol = smartList.createNewColumn(ColumnType.DATE_AND_TIME, "Start day");
        IColumn choiceCol = smartList.createNewColumn(ColumnType.CHOICE, "Session type");
        smartList.moveRight("Start day");
        assertEquals(timeCol, smartList.getColumns().get(2));
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
        IColumn numberCol = smartList.createNewColumn(ColumnType.NUMBER, "Grade");
        smartList.createNewRow();
        smartList.addData("Grade", 0, new Number(15));
        smartList.createNewRow();
        smartList.addData("Grade", 1, new Number(11));
        smartList.createNewRow();
        smartList.addData("Grade", 2, new Number(5));
        smartList.createNewRow();
        smartList.addData("Grade", 3, new Number(10));

        Rule rule = new Rule(numberCol, Condition.GREATER_THAN, 10.0);

        Number n1 = (Number) smartList.getData("Grade", 0);
        Number n2 = (Number) smartList.getData("Grade", 2);
        assertTrue(rule.evaluate(n1.getNum()));
        assertFalse(rule.evaluate(n2.getNum()));
    }

    @Test
    void testExportListToCSV() {
        assertEquals(ExportStatus.SUCCESS, microsoftList.exportToCSV(smartList, "list.csv").getStatus());
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

        smartList.createForm(columns);
        assertEquals(1, smartList.getForms().size());
    }


    @Test
    void testShowColumn() {
        smartList.createNewColumn(ColumnType.TEXT, "Title");
        smartList.showColumn("Title");
        assertTrue(smartList.getColumnByName("Title").isVisible());
    }

    @Test
    void testHideColumn() {
        smartList.createNewColumn(ColumnType.TEXT, "Title");
        smartList.hideColumn("Title");
        assertFalse(smartList.getColumnByName("Title").isVisible());
    }


}

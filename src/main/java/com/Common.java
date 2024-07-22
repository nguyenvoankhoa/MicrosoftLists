package com;

import com.column.IColumn;
import com.column.datatype.IData;
import com.export.ExportHandler;
import com.export.ExportResult;
import com.export.FileType;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Common {
    private Common() {
    }


    public static void sortDesc(SmartList sl, String name) {
        IColumn column = getColumnByName(sl, name);
        sort(sl, column, false);
    }

    public static void sortAsc(SmartList sl, String name) {
        IColumn column = getColumnByName(sl, name);
        sort(sl, column, true);
    }

    public static List<Object> getListFilter(SmartList sl, String colName) {
        int cId = getColumnIndexByName(sl, colName);
        return sl.getRows().stream().map(l -> l.getIDataList().get(cId).getImportantData())
                .distinct()
                .toList();
    }

    public static List<Row> filter(SmartList sl, String colName, Object criteria) {
        int cId = getColumnIndexByName(sl, colName);
        return sl.getRows().stream()
                .filter(l -> l.getIDataList().get(cId).getImportantData().equals(criteria))
                .toList();
    }

    public static Map<Object, List<Row>> groupBy(SmartList sl, String colName) {
        int cId = getColumnIndexByName(sl, colName);
        return sl.getRows().stream()
                .collect(Collectors
                        .groupingBy(row -> row.getIDataList()
                                .get(cId)
                                .getImportantData()));
    }

    public static List<Row> getPage(SmartList sl, int pageNumber, int pageSize) {
        int fromIndex = (pageNumber - 1) * pageSize;
        return sl.getRows().stream()
                .skip(Math.max(0, fromIndex))
                .limit(pageSize)
                .toList();
    }

    public static SmartList checkExist(MicrosoftList ml, String name) {
        return ml.getListCollection().stream().filter(s -> s.getName().equals(name))
                .findFirst().orElse(null);
    }

    public static ExportResult exportToCSV(SmartList smartList, String filename) {
        return ExportHandler.export(smartList, filename, FileType.CSV);
    }

    public static IColumn getColumnByName(SmartList sl, String name) {
        return sl.getColumns().stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public static int getColumnIndex(SmartList sl, IColumn<Object> column) {
        return IntStream.range(0, sl.getColumns().size())
                .filter(i -> sl.getColumns().get(i).equals(column))
                .findFirst()
                .orElse(-1);
    }

    public static int getColumnIndexByName(SmartList sl, String name) {
        IColumn c = getColumnByName(sl, name);
        return getColumnIndex(sl, c);
    }


    public static Form getFormByName(SmartList sl, String name) {
        return sl.getForms().stream().filter(f -> f.getName().equals(name))
                .findFirst().orElse(null);
    }

    public static void sort(SmartList sl, IColumn<Object> column, boolean ascending) {
        int cId = getColumnIndex(sl, column);

        Comparator<Row> rowComparator = (row1, row2) -> {
            IData<?> iData1 = row1.getIDataList().get(cId);
            IData<?> iData2 = row2.getIDataList().get(cId);
            Comparator<IData<?>> comparator = (Comparator<IData<?>>) iData1;
            return ascending ? comparator.compare(iData1, iData2) : comparator.compare(iData2, iData1);
        };

        sl.getRows().sort(rowComparator);
    }
}

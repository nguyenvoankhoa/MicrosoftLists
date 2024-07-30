package com.util;

import com.exception.ConstraintViolationException;
import com.exception.ExistException;
import com.exception.NotFoundException;
import com.model.Form;
import com.model.MicrosoftList;
import com.model.Row;
import com.model.SmartList;
import com.model.column.IColumn;
import com.model.datatype.IData;
import com.export.ExportHandler;
import com.export.ExportResult;
import com.export.FileType;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Common {
    private Common() {
    }

    public static void checkValid(boolean b){
        if(!b){
            throw new ConstraintViolationException();
        }
    }
    public static void checkExist(Object o) {
        if (o != null) {
            throw new ExistException();
        }
    }

    public static void checkNonExist(Object o) {
        if (o == null) {
            throw new NotFoundException();
        }
    }

    public static SmartList sortDesc(SmartList sl, String name) {
        IColumn<?> column = getColumnByName(sl, name);
        checkNonExist(column);
        return sort(sl, column, false);
    }

    public static SmartList sortAsc(SmartList sl, String name) {
        IColumn<?> column = getColumnByName(sl, name);
        checkNonExist(column);
        return sort(sl, column, true);
    }

    public static List<Object> getListFilter(SmartList sl, String colName) {
        int cId = getColumnIndexByName(sl, colName);
        return sl.getRows().stream().map(l -> l.getIDataList().get(cId).getImportantData())
                .distinct()
                .toList();
    }

    public static long count(SmartList sl, String colName) {
        int cId = Common.getColumnIndexByName(sl, colName);
        return sl.getRows().stream().filter(l -> l.getIDataList().get(cId) != null).count();
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



    public static boolean checkExistName(MicrosoftList ml, String name) {
        return ml.getListCollection().stream().anyMatch(s -> s.getName().equals(name));
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

    public static int getColumnIndex(SmartList sl, IColumn<?> column) {
        return IntStream.range(0, sl.getColumns().size())
                .filter(i -> sl.getColumns().get(i).equals(column))
                .findFirst()
                .orElse(-1);
    }

    public static int getColumnIndexByName(SmartList sl, String name) {
        IColumn<?> c = getColumnByName(sl, name);
        checkNonExist(c);
        return getColumnIndex(sl, c);
    }


    public static Form getFormByName(SmartList sl, String name) {
        return sl.getForms().stream().filter(f -> f.getName().equals(name))
                .findFirst().orElse(null);
    }

    public static SmartList sort(SmartList sl, IColumn<?> column, boolean ascending) {
        int cId = getColumnIndex(sl, column);
        Comparator<Row> rowComparator = (row1, row2) -> {
            IData<?> iData1 = row1.getIDataList().get(cId);
            IData<?> iData2 = row2.getIDataList().get(cId);
            Comparator<IData<?>> comparator = (Comparator<IData<?>>) iData1;
            return ascending ? comparator.compare(iData1, iData2) : comparator.compare(iData2, iData1);
        };
        sl.getRows().sort(rowComparator);
        return sl;
    }


}

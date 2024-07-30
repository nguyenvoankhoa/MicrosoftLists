package com.export;

import java.util.EnumMap;
import java.util.Map;

public class ExportFactory {
    private static final Map<FileType, IExportable> exports = new EnumMap<>(FileType.class);

    private ExportFactory() {
    }

    static {
        exports.put(FileType.CSV, new ExportCSV());
        exports.put(FileType.POWER_BI, new ExportPowerBI());
    }

    public static IExportable getExport(FileType type) {
        return exports.get(type);
    }
}

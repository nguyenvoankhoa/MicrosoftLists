package com.export;

import com.model.SmartList;

public class ExportHandler {
    private ExportHandler() {
        throw new IllegalStateException("Utility class");
    }
    public static ExportResult export(SmartList list, String filename, FileType fileType) {
        IExportable exp = ExportFactory.getExport(fileType);
        return exp.export(list, filename);
    }
}

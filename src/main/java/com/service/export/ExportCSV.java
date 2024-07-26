package com.service.export;

import com.model.SmartList;

public class ExportCSV implements IExportable{
    @Override
    public ExportResult export(SmartList smartList, String filename) {
        return new ExportResult(ExportStatus.SUCCESS, "Export to CSV");
    }
}

package com.export;

import com.SmartList;

public class ExportPowerBI implements IExportable {
    @Override
    public ExportResult export(SmartList smartList, String filename) {
        return new ExportResult(ExportStatus.SUCCESS, "Export to PowerBI");
    }
}

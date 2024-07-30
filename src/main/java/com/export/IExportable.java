package com.export;

import com.model.SmartList;

public interface IExportable {
    ExportResult export(SmartList smartList, String filename);
}

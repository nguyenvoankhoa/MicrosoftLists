package com.export;

import com.SmartList;

public interface IExportable {
    ExportResult export(SmartList smartList, String filename);
}

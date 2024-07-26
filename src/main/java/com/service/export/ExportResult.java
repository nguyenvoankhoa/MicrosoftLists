package com.service.export;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExportResult {
    private ExportStatus status;
    private String message;
}

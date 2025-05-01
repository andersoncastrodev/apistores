package br.com.asoft.apistores.report.core;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class ReportExcel {

    private final Workbook workbook;
    private final ByteArrayOutputStream byteOutStream;
    private Sheet currentSheet;
    private CellStyle headerStyle;
    private CellStyle defaultCellStyle;
    private CellStyle titleStyle;


    public ReportExcel() {
        this.workbook = new XSSFWorkbook();
        this.byteOutStream = new ByteArrayOutputStream();
        initializeDefaultStyles();
    }

    private void initializeDefaultStyles() {
        // Style for headers
        this.headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);

        // Default style for cells
        this.defaultCellStyle = workbook.createCellStyle();
        defaultCellStyle.setBorderBottom(BorderStyle.THIN);
        defaultCellStyle.setBorderTop(BorderStyle.THIN);
        defaultCellStyle.setBorderRight(BorderStyle.THIN);
        defaultCellStyle.setBorderLeft(BorderStyle.THIN);

        // Style for title
        this.titleStyle = workbook.createCellStyle();
        Font titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 14);
        titleStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
    }

    public ReportExcel createSheet(String sheetName) {
        this.currentSheet = workbook.createSheet(sheetName);
        return this;
    }

    public ReportExcel setCurrentSheet(String sheetName) {
        this.currentSheet = workbook.getSheet(sheetName);
        if (this.currentSheet == null) {
            this.currentSheet = workbook.createSheet(sheetName);
        }
        return this;
    }

    public ReportExcel addHeaders(String[] headers) {
        if (currentSheet == null) {
            createSheet("Sheet1");
        }

        // Determina a linha dos cabeçalhos (linha 1 se houver título, linha 0 caso contrário)
        int headerRowNum = (currentSheet.getPhysicalNumberOfRows() > 0 &&
                currentSheet.getRow(0) != null &&
                currentSheet.getRow(0).getCell(0) != null) ? 1 : 0;

        Row headerRow = currentSheet.createRow(headerRowNum);

        // Aplica o estilo de cabeçalho e preenche os valores
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);

            // Configuração opcional: filtro automático
            if (i == 0 && headerRowNum == 0) {
                currentSheet.setAutoFilter(new CellRangeAddress(
                        headerRowNum,
                        headerRowNum,
                        0,
                        headers.length - 1
                ));
            }
        }
        // Congela os cabeçalhos (opcional)
        currentSheet.createFreezePane(0, headerRowNum + 1);

        return this;
    }

    public ReportExcel addTitle(String title, int numberColMesclar) {
        if (currentSheet == null) {
            createSheet("Sheet1");
        }

        // Se já existir um título, sobrescreve
        Row titleRow = currentSheet.getRow(0);
        if (titleRow == null) {
            titleRow = currentSheet.createRow(0);
        } else {
            // Move os dados existentes para baixo
            currentSheet.shiftRows(0, currentSheet.getLastRowNum(), 1);
            titleRow = currentSheet.createRow(0);
        }

        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(title);
        titleCell.setCellStyle(titleStyle);

        // Merge cells para o título (opcional)
        currentSheet.addMergedRegion(new CellRangeAddress(
                0, // first row
                0, // last row
                0, // first column
                numberColMesclar  // last column (ajuste conforme necessário)
        ));

        return this;
    }

    public ReportExcel addData(List<Object[]> data) {
        if (currentSheet == null) {
            throw new IllegalStateException("No sheet created. Call createSheet() first.");
        }

        int rowNum = currentSheet.getLastRowNum() + 1;
        if (rowNum == 0) rowNum = 1; // If sheet is empty, start at row 1

        for (Object[] rowData : data) {
            Row row = currentSheet.createRow(rowNum++);
            for (int i = 0; i < rowData.length; i++) {
                Cell cell = row.createCell(i);
                setCellValue(cell, rowData[i]);
                cell.setCellStyle(defaultCellStyle);
            }
        }
        return this;
    }

    public ReportExcel addData(Map<String, Object[]> data) {
        if (currentSheet == null) {
            throw new IllegalStateException("No sheet created. Call createSheet() first.");
        }

        data.forEach((key, rowData) -> {
            int rowNum = Integer.parseInt(key); // Assuming keys are row numbers as strings
            Row row = currentSheet.createRow(rowNum);
            for (int i = 0; i < rowData.length; i++) {
                Cell cell = row.createCell(i);
                setCellValue(cell, rowData[i]);
                cell.setCellStyle(defaultCellStyle);
            }
        });
        return this;
    }

    private void setCellValue(Cell cell, Object value) {
        if (value == null) {
            cell.setCellValue("");
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof java.util.Date) {
            cell.setCellValue((java.util.Date) value);
            CellStyle dateStyle = workbook.createCellStyle();
            dateStyle.cloneStyleFrom(defaultCellStyle);
            CreationHelper createHelper = workbook.getCreationHelper();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
            cell.setCellStyle(dateStyle);
        } else {
            cell.setCellValue(value.toString());
        }
    }

    public ReportExcel autoSizeColumns() {
        if (currentSheet == null) return this;

        if (currentSheet.getPhysicalNumberOfRows() > 0) {
            Row row = currentSheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                currentSheet.autoSizeColumn(i);
            }
        }
        return this;
    }

    public ReportExcel writeToStream() throws IOException {
        this.workbook.write(this.byteOutStream);
        return this;
    }

    public ByteArrayInputStream getAsByteArrayInputStream() throws IOException {
        writeToStream();
        return new ByteArrayInputStream(this.byteOutStream.toByteArray());
    }

    public void close() throws IOException {
        this.workbook.close();
        this.byteOutStream.close();
    }

    // Advanced features
    public ReportExcel addCustomStyle(String styleName, CellStyle style) {
        // Could be implemented to store custom styles
        return this;
    }

    public ReportExcel applyStyleToRange(int firstRow, int lastRow, int firstCol, int lastCol, CellStyle style) {
        for (int rowNum = firstRow; rowNum <= lastRow; rowNum++) {
            Row row = currentSheet.getRow(rowNum);
            if (row == null) continue;

            for (int colNum = firstCol; colNum <= lastCol; colNum++) {
                Cell cell = row.getCell(colNum);
                if (cell != null) {
                    cell.setCellStyle(style);
                }
            }
        }
        return this;
    }
}

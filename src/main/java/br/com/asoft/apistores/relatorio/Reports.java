package br.com.asoft.apistores.relatorio;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.util.ObjectUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public final class Reports {

    private Table table;
    private Document document;
    private PdfDocument pdfDocument;
    private ByteArrayOutputStream byteOutStream;

    private Reports() {
        this.byteOutStream = new ByteArrayOutputStream();
        this.pdfDocument = new PdfDocument(new PdfWriter(this.byteOutStream));
        this.document = new Document(this.pdfDocument);

        this.document.setMargins(5f,10f,10f,5f);
    }

    public static Reports getInstance() {
        return new Reports();
    }

    public void setPageSize(PageSize pageSize) {
        this.pdfDocument.setDefaultPageSize(pageSize);
    }

    public void addNewLine() {
        this.document.add(new Paragraph(" ").setMargins(2f,10f,2f,5f));
    }

    public void addParagraph(Paragraph paragraph) {
        this.document.add(paragraph);
    }

    public void closeDocument() {
        this.document.close();
    }

    public void openTable(Integer columnWidth) {
        this.table = new Table(columnWidth);
        this.table.useAllAvailableWidth();
        this.table.setTextAlignment(TextAlignment.CENTER);

//        this.table.setMargins(5f,10f,10f,5f);
    }

    public void addTableHeader(String... headers) {
        this.validateTable();
        for (String header : headers) {
            this.table.addHeaderCell(header);
        }
    }

    public void addTableColumn(Object column) {
        this.validateTable();
        this.table.addCell(column.toString());
    }

    public void addTableFooter(Object... footers) {
        this.validateTable();
        for (Object footer : footers) {
            this.table.addFooterCell(footer == null ? "" : footer.toString());
        }

        this.table.getFooter().setBackgroundColor(ColorConstants.LIGHT_GRAY);
    }

    public void closeTable() {
        this.validateTable();
        this.document.add(this.table);
    }

    public ByteArrayInputStream getByteArrayInputStream() {
        return new ByteArrayInputStream(this.byteOutStream.toByteArray());
    }

    private void validateTable() {
        if (ObjectUtils.isEmpty(this.table)) {
            throw new RuntimeException("PDF Table cannot be null.");
        }
    }

}

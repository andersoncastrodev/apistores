package br.com.asoft.apistores.relatorio;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.util.ObjectUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
    Classe Responsavel por criar e padronizar os elementos
    utilizado no relatorios iText 7.
 */
public final class Reports {

    private Document document;
    private PdfDocument pdfDocument;
    private ByteArrayOutputStream byteOutStream;
    private Table table;

    public Reports(boolean rotatePage) {
        this.byteOutStream = new ByteArrayOutputStream();
        this.pdfDocument = new PdfDocument(new PdfWriter(this.byteOutStream));
        PageSize pageSize = rotatePage ? PageSize.A4.rotate() : PageSize.A4;
        this.document = new Document(this.pdfDocument, pageSize);
        this.document.setMargins(10f, 10f, 10f, 10f);
    }

    public void openTable(Float... vars) {
        this.table = new Table(UnitValue.createPercentArray(valuesDynamicFloat(vars)));
        this.table.setWidth(UnitValue.createPercentValue(100));
        this.table.setFixedLayout();
    }

    public void addCellLeft(Object cell) {
        this.validateTable();
        this.table.addCell(new Paragraph(cell.toString())).setTextAlignment(TextAlignment.LEFT);
    }

    public void addCellRight(Object cell) {
        this.table.addCell(new Paragraph(cell.toString())).setTextAlignment(TextAlignment.RIGHT);
    }

    public void addCellCenter(Object cell) {
        this.table.addCell(new Paragraph(cell.toString())).setTextAlignment(TextAlignment.CENTER);
    }

    public void addCellImagem(Image img) {
        Cell cell1 = new Cell().add(img).setBorder(null);
        this.table.addCell(cell1);
    }

    public void addCellLeftBordeLess(String cell) {
        Cell cell1 = new Cell().add(new Paragraph(cell))
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(null);
        this.table.addCell(cell1);
    }

    public void addCellRichtBordeLess(String cell) {
        Cell cell1 = new Cell().add(new Paragraph(cell))
                .setTextAlignment(TextAlignment.RIGHT)
                .setBorder(null);
        this.table.addCell(cell1);
    }

    public void addCellCenterBordeLess(Object cell) {
        Cell cell1 = new Cell().add(new Paragraph(cell.toString()))
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(null);
        this.table.addCell(cell1);
    }

    public void addCellRightBordeLess(Object cell) {
        Cell cell1 = new Cell().add(new Paragraph(cell.toString()))
                .setTextAlignment(TextAlignment.RIGHT)
                .setBorder(null);
        this.table.addCell(cell1);
    }

    public void addCellEmpty(String cell) {
        this.table.addCell(new Paragraph(cell)).setHeight(UnitValue.createPointValue(20));//Altura
    }
    public void addCellEmptyMerged(String cell, int line, int numberColumns) {
        Cell cell1 = new Cell(line, numberColumns).add(new Paragraph(cell)).setHeight(UnitValue.createPointValue(20));//Altura
        this.table.addCell(cell1);
    }

    public void addCellMerged(String cell, int line, int numberColumns) {
        Cell cell1 = new Cell(line, numberColumns).add(new Paragraph(cell));
        this.table.addCell(cell1);
    }

    public void addCellMergedCenter(String cell, int line, int numberColumns) {
        Cell cell1 = new Cell(line, numberColumns).add(new Paragraph(cell))
                .setTextAlignment(TextAlignment.CENTER);
        this.table.addCell(cell1);
    }

    public void addCellBackground(int line, int numberColuns) {
        Cell cell = this.table.getCell(line - 1, numberColuns - 1);
        cell.setBackgroundColor(new DeviceRgb(192, 192, 192)); // Cinza Claro
    }

    public void addCellBold(int line, int numberColuns) {
        Cell cell = this.table.getCell(line - 1, numberColuns - 1);
        cell.setBold();
    }

    public void addTableHeader(String... headers) {
        //this.validateTable();
        for (String header : headers) {
            Cell headerCell = new Cell().add(new Paragraph(header));
            headerCell.setBackgroundColor(new DeviceRgb(192, 192, 192));//Cinza Claro
            //headerCell.setBold();//Negrito
            headerCell.setTextAlignment(TextAlignment.CENTER);//Centro
            this.table.addHeaderCell(headerCell);
        }
    }

    public void closeTable() {
        this.document.add(this.table);
    }

    public Image addLogo() throws MalformedURLException {
        String imagePath = "Scheduler-Service/src/main/resources/static/logo.png";
        ImageData imageData = ImageDataFactory.create(imagePath);
        Image image = new Image(imageData);
        image.setWidth(130f);
        image.setHeight(25f);
        return image;
    }

    public void addLogoDocument() throws MalformedURLException {
        String imagePath = "Scheduler-Service/src/main/resources/static/logo.png";
        ImageData imageData = ImageDataFactory.create(imagePath);
        Image image = new Image(imageData);
        image.setWidth(130f);
        image.setHeight(25f);
        image.setHorizontalAlignment(HorizontalAlignment.CENTER);//Centralizar logo.
        this.document.add(image);
    }

    public void closeDocument() {
        this.document.close();
    }

    public void setPageSize(PageSize pageSize) {
        this.pdfDocument.setDefaultPageSize(pageSize);
    }

    public void addParagraph(Paragraph paragraph) {
        this.document.add(paragraph);
    }

    public void addNewLine() {
        this.document.add(new Paragraph(" ").setMargins(5f, 10f, 5f, 10f));
    }

    public ByteArrayInputStream getByteArrayInputStream() {
        return new ByteArrayInputStream(this.byteOutStream.toByteArray());
    }

    private void validateTable() {
        if (ObjectUtils.isEmpty(this.table)) {
            throw new RuntimeException("A tabela não pode ser nula.");
        }
    }

    private float[] valuesDynamicFloat(Float[] array) {
        float[] primitiveArray = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            primitiveArray[i] = array[i];
        }
        return primitiveArray;
    }

    public String getDatasCurrentFormatted() {
        Date dataAtual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formato.format(dataAtual);
    }

}

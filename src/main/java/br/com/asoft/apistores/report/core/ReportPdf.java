package br.com.asoft.apistores.report.core;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 Classe Responsavel por criar e padronizar os elementos
 utilizado no relatorios iText 7.
 */
public final class ReportPdf {

    private final Document document;
    private final PdfDocument pdfDocument;
    private final ByteArrayOutputStream byteOutStream;
    private Table table;

    /** Possivel modificação dependendo do diretorio e name da imagem */
    private final String imagePath = "Form-Management-Service/src/main/resources/static/unichristus.png";

    public enum Page {
        VERTICAL,
        HORIZONTAL
    }

    /**
     * Construtor que vai inicializar um objeto reports.
     * Onde podemos deixa a pagina estilo "retrato" vertical ou "paisagem" horizontal
     * @param page
     */
    public ReportPdf(Page page) {
        this.byteOutStream = new ByteArrayOutputStream();
        this.pdfDocument = new PdfDocument(new PdfWriter(this.byteOutStream));
        //Rodape
        this.pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new FooterEventHandler());

        PageSize pageSize = page.ordinal() == 1 ? PageSize.A4.rotate() : PageSize.A4;
        this.document = new Document(this.pdfDocument, pageSize);
        this.document.setMargins(20f, 20f, 20f, 20f);
    }

    /**
     * Cria uma tabela onde adicionamos os textos nas celulas.
     * O numeros de colunas criadas são indicado pelo numeros de parametros passado ao metodo.
     * Ex: 2f, 2f, 2f Então será uma tabela com 3 colunas e espaço é 2.
     * Obs: Os parametros é obrigatório ser Float.
     * @param vars
     */
    public void openTable(Float... vars) {
        this.table = new Table(UnitValue.createPercentArray(valuesDynamicFloat(vars)));
        this.table.setWidth(UnitValue.createPercentValue(100));
        this.table.setFixedLayout();
    }

    /**
     * Adiciona cabeçalhos de tabela com opções flexíveis
     *
     * @param headers Array de textos dos cabeçalhos
     * @param fontStyle "BOLD" ou "NORMAL" (opcional, padrão BOLD)
     * @param backgroundColor true para fundo cinza, false para sem cor (opcional, padrão true)
     * @param alignments Array de alinhamentos ("LEFT", "CENTER", "RIGHT") (opcional, padrão CENTER)
     * @param fontSize Tamanho da fonte (opcional, padrão 12)
     */
    public void addTableHeader(
            String[] headers,
            String fontStyle,
            boolean backgroundColor,
            String[] alignments,
            float fontSize)     throws IOException {

        if (headers == null || headers.length == 0) return;

        // Valores padrão
        if (fontStyle == null) fontStyle = "BOLD";
        if (alignments == null || alignments.length != headers.length) {
            // Preenche com CENTER se não fornecido ou tamanho incompatível
            alignments = new String[headers.length];
            Arrays.fill(alignments, "CENTER");
        }
        if (fontSize <= 0) fontSize = 12;

        for (int i = 0; i < headers.length; i++) {
            String header = headers[i];
            String alignment = alignments[i];

            // Cria parágrafo com estilo de fonte
            Paragraph paragraph = new Paragraph(header)
                    .setFont(PdfFontFactory.createFont(
                            "BOLD".equalsIgnoreCase(fontStyle) ?
                                    StandardFonts.HELVETICA_BOLD :
                                    StandardFonts.HELVETICA))
                    .setFontSize(fontSize);

            // Cria célula
            Cell headerCell = new Cell().add(paragraph);

            // Configura cor de fundo
            if (backgroundColor) {
                headerCell.setBackgroundColor(new DeviceRgb(192, 192, 192));
            }

            // Configura alinhamento
            TextAlignment textAlign = TextAlignment.CENTER;
            if ("LEFT".equalsIgnoreCase(alignment)) {
                textAlign = TextAlignment.LEFT;
            } else if ("RIGHT".equalsIgnoreCase(alignment)) {
                textAlign = TextAlignment.RIGHT;
            }
            headerCell.setTextAlignment(textAlign);

            this.table.addHeaderCell(headerCell);
        }
    }

    // Sobrecarga
    // Sem tamanho de fonte (usa padrão 12)
    public void addTableHeader(String[] headers,
                               String fontStyle,
                               boolean backgroundColor,
                               String[] alignments) throws IOException {
        addTableHeader(headers, fontStyle, backgroundColor, alignments, 12);
    }


    // Metodo Princial
    public void addCell(String content, String fontStyle, String alignment, float fontSize, boolean noBorder) throws IOException {
        Paragraph paragraph;

        // Define o estilo da fonte
        if ("BOLD".equalsIgnoreCase(fontStyle)) {
            paragraph = new Paragraph(content)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD));
        } else {
            paragraph = new Paragraph(content)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA));
        }
        // Configura tamanho da fonte
        paragraph.setFontSize(fontSize);

        // Converte String para TextAlignment
        TextAlignment textAlign;
        switch (alignment.toUpperCase()) {
            case "LEFT":
                textAlign = TextAlignment.LEFT;
                break;
            case "RIGHT":
                textAlign = TextAlignment.RIGHT;
                break;
            case "CENTER":
            default:
                textAlign = TextAlignment.CENTER;
                break;
        }

        Cell cell = new Cell().add(paragraph).setTextAlignment(textAlign);

        // Reduzindo o padding para todos os lados pode ter valores entre: -3,-2,-1 0,1,2,3
        // cell.setPadding(0);
        // Ou para lados específicos pode ter valores entre: -3,-2,-1 0,1,2,3
        cell.setPaddingTop(0).setPaddingRight(2).setPaddingBottom(0).setPaddingLeft(2);

        if(noBorder) {
            cell.setBorder(Border.NO_BORDER);
        }
        this.table.addCell(cell);
    }

    // Sobrecarga
    public void addCell(String content) throws IOException {
        addCell(content,"BASIC","LEFT",12,false);
    }

    // Sobrecarga
    public void addCell(String content, String fontStyle,String alignment ) throws IOException {
        addCell(content, fontStyle,alignment,12,false);
    }

    // Sobrecarga
    public void addCell(String content, String alignment) throws IOException {
        addCell(content,"BASIC",alignment,12,false);
    }

    // Sobrecarga
    public void addCell(String content, String alignment, float fontSize ) throws IOException {
        addCell(content,"BASIC",alignment,fontSize,false);
    }

    // Sobrecarga
    public void addCell(String content, String alignment, boolean noBorder ) throws IOException {
        addCell(content,"BASIC",alignment,12, noBorder);
    }

    public void addCell(String content,String fontStyle, String alignment, boolean noBorder ) throws IOException {
        addCell(content,fontStyle,alignment,12, noBorder);
    }


    /**
     * Adiciona parágrafo com opções flexíveis
     *
     * @param content Texto do parágrafo
     * @param fontStyle "BOLD" ou "NORMAL" (opcional, padrão BOLD)
     * @param alignment "LEFT", "CENTER" ou "RIGHT" (opcional, padrão CENTER)
     * @param fontSize Tamanho da fonte (opcional, padrão 18)
     */
    public void addParagraph(String content, String fontStyle, String alignment, float fontSize) throws IOException {
        Paragraph paragraph;

        // Define o estilo da fonte
        if ("BOLD".equalsIgnoreCase(fontStyle)) {
            paragraph = new Paragraph(content)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD));
        } else {
            paragraph = new Paragraph(content)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA));
        }
        // Configura tamanho da fonte
        paragraph.setFontSize(fontSize);

        // Converte String para TextAlignment
        TextAlignment textAlign;
        switch (alignment.toUpperCase()) {
            case "LEFT":
                textAlign = TextAlignment.LEFT;
                break;
            case "RIGHT":
                textAlign = TextAlignment.RIGHT;
                break;
            case "CENTER":
            default:
                textAlign = TextAlignment.CENTER;
                break;
        }
        paragraph.setTextAlignment(textAlign);

        this.document.add(paragraph);
    }

    //Sobrecarga
    // Versão minimalista (BOLD, CENTER, 18)
    public void addParagraph(String content) throws IOException {
        addParagraph(content, "BOLD", "CENTER", 18);
    }

    //Sobrecarga
    // Versão com estilo e alinhamento
    public void addParagraph(String content, String fontStyle, String alignment) throws IOException {
        addParagraph(content, fontStyle, alignment, 18);
    }


    // Metodo Princial
    public void addCellMerged(String content,int rowSpan, int colSpan, String fontStyle, String alignment, float fontSize, boolean noBorder) throws IOException {
        Paragraph paragraph;

        // Define o estilo da fonte
        if ("BOLD".equalsIgnoreCase(fontStyle)) {
            paragraph = new Paragraph(content)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD));
        } else {
            paragraph = new Paragraph(content)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA));
        }
        // Configura tamanho da fonte
        paragraph.setFontSize(fontSize);

        // Converte String para TextAlignment
        TextAlignment textAlign;
        switch (alignment.toUpperCase()) {
            case "LEFT":
                textAlign = TextAlignment.LEFT;
                break;
            case "RIGHT":
                textAlign = TextAlignment.RIGHT;
                break;
            case "CENTER":
            default:
                textAlign = TextAlignment.CENTER;
                break;
        }
        Cell cell = new Cell(rowSpan, colSpan).add(paragraph).setTextAlignment(textAlign);

        // Reduzindo o padding para todos os lados pode ter valores entre: -3,-2,-1 0,1,2,3
        // cell.setPadding(0);
        // Ou para lados específicos pode ter valores entre: -3,-2,-1 0,1,2,3
        cell.setPaddingTop(0).setPaddingRight(2).setPaddingBottom(0).setPaddingLeft(2);

        if(noBorder) {
            cell.setBorder(Border.NO_BORDER);
        }
        this.table.addCell(cell);
    }

    //Sobrecarga
    public void addCellMerged(String content, int rowSpan, int colSpan, String fontStyle) throws IOException {
        addCellMerged(content, rowSpan, colSpan, fontStyle, "LEFT", 12, false);
    }
    //Sobrecarga
    public void addCellMerged(String content, int rowSpan, int colSpan) throws IOException {
        addCellMerged(content, rowSpan, colSpan, "BASIC", "LEFT", 12, false);
    }
    //Sobrecarga
    public void addCellMerged(String content, int rowSpan, int colSpan, String alignment, boolean noBorder) throws IOException {
        addCellMerged(content, rowSpan, colSpan, "BASIC", alignment, 12, noBorder);
    }
    //Sobrecarga
    public void addCellMerged(String content, int rowSpan, int colSpan, String alignment, float fontSize, boolean noBorder) throws IOException {
        addCellMerged(content, rowSpan, colSpan, "BASIC", alignment, fontSize, noBorder);
    }

    public void addCellImagem(String imagePath, float width, float height) throws MalformedURLException {
        ImageData imageData = ImageDataFactory.create(imagePath);
        Image image = new Image(imageData);
        image.setWidth(width);
        image.setHeight(height);
        image.setHorizontalAlignment(HorizontalAlignment.CENTER);//CENTRALIZADA logo.
        Cell newCell = new Cell().add(image).setBorder(null);
        this.table.addCell(newCell);
    }

    public void addCellImagemText(String imagePath, float width, float height, String text) throws IOException {
        // Cria a imagem
        ImageData imageData = ImageDataFactory.create(imagePath);
        Image image = new Image(imageData);
        image.setWidth(width);
        image.setHeight(height);
        image.setHorizontalAlignment(HorizontalAlignment.CENTER); // CENTRALIZA a imagem

        // Cria o parágrafo com o texto
        Paragraph textoParagrafo = new Paragraph(text)
                .setFontSize(12)
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setTextAlignment(TextAlignment.CENTER) // Centraliza o texto
                .setMarginTop(5); // Adiciona um pequeno espaço entre a imagem e o texto

        // Cria a célula sem borda e adiciona a imagem e o texto
        Cell newCell = new Cell()
                .add(image)
                .add(textoParagrafo)
                .setBorder(null);

        this.table.addCell(newCell);
    }

    /**
     * Adiciona uma celula com cor de fundo.
     * Obs: Podemos mudar a cor de fundo em: new DeviceRgb(192, 192, 192).
     * @param line linha onde vamos adicionar a cor
     * @param numberColuns numero das colunas a receber a cor
     */
    public void addCellBackground(int line, int numberColuns) {
        Cell newCell = this.table.getCell(line - 1, numberColuns - 1);
        newCell.setBackgroundColor(new DeviceRgb(192, 192, 192)); // Cinza Claro
    }

    /**
     * Fecha uma tabela criada.
     * Obs: É obrigatório usar esse metodo sempre quando terminar uma tabela.
     */
    public void closeTable() {
        this.document.add(this.table);
    }

    /**
     * Adiciona uma logo no relatório
     * Posição: A Esquerda
     * Obs: O caminho da imagem logo é definido no inicio da classe
     * Obs: O tamanho da logo é definido em: image.setWidth(110f); e image.setHeight(25f);
     * @throws MalformedURLException
     */
    public void addLogoDocument(float width, float height) throws MalformedURLException {
        ImageData imageData = ImageDataFactory.create(imagePath);
        Image image = new Image(imageData);
        image.setWidth(width);
        image.setHeight(height);
        image.setHorizontalAlignment(HorizontalAlignment.LEFT);//Centralizar logo.
        this.document.add(image);
    }

    /**
     * Fecha um documento criado.
     * Obs: É obrigatório usar esse metodo para fechar o Documento pdf.
     */
    public void closeDocument() {
        this.document.close();
    }

    public void addParagraphBlue(String paragraph) {
        // Definindo a cor personalizada #174477 (RGB) - Azul UniChristus
        Color color = new DeviceRgb(23, 68, 119); // iText 7 usa o DeviceRgb para cores RGB
        // Criando um objeto Text com a cor definida
        Text text = new Text(paragraph).setFontColor(color);
        this.document.add(new Paragraph().add(text));
    }
    /**
     * Adicionar ao Documento uma linha em branco
     * Obs: As margens é definido em: .setMargins(5f, 10f, 5f, 10f));
     */
    public void addNewLine() {
        this.document.add(new Paragraph(" ").setMargins(5f, 20f, 5f, 20f));
    }

    public void addDrawLine() {
        Div line = new Div();
        line.setHeight(0.5f);// Altura da linha
        line.setWidth(UnitValue.createPercentValue(100)); //horizontal 100%
        line.setBackgroundColor(ColorConstants.BLACK);
        this.document.add(line);
    }

    /**
     * Faz a compilação de tudo que foi criado no documento.
     * Obs: É obrigatório usar esse metodo para retorna o dados do documento é usado no return do metodos
     * Ex: return reports.getByteArrayInputStream();.
     * @return
     */
    public ByteArrayInputStream getByteArrayInputStream() {
        return new ByteArrayInputStream(this.byteOutStream.toByteArray());
    }

    private float[] valuesDynamicFloat(Float[] array) {
        float[] primitiveArray = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            primitiveArray[i] = array[i];
        }
        return primitiveArray;
    }

    //Classe Interna que vai gerar o rodapé de forma dinâmica
    private class FooterEventHandler implements IEventHandler {
        private SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));

        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            Rectangle pageSize = page.getPageSize();
            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

            // Obter a data/hora atual formatada
            String currentDate = dateFormat.format(new Date());
            currentDate = capitalizeFirstLetter(currentDate); //Formatando para "Segunda-feira, 21/11/2024 22:33:12"

            String pageText = "Página " + pdfDoc.getPageNumber(page);

            // Configurar a fonte
            PdfFont font;
            try {
                font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Posicionar o texto no rodapé
            float y = pageSize.getBottom() + 20; // Margens

            // Desenhar a data alinhada à esquerda
            canvas.beginText()
                    .setFontAndSize(font, 10) //tamanho da fonte
                    .moveText(pageSize.getLeft() + 20, y)
                    .showText(currentDate)
                    .endText();

            // Desenhar o número da página alinhado à direita
            canvas.beginText()
                    .setFontAndSize(font, 10) //tamanho da fonte
                    .moveText(pageSize.getRight() - 50, y)
                    .showText(pageText)
                    .endText();

            canvas.release();
        }

        //Metodo para capitalizar a primeira letra do dia da semana
        private String capitalizeFirstLetter(String dateString) {
            if (dateString == null || dateString.isEmpty()) {
                return dateString;
            }

            // Encontra a vírgula que separa o dia da semana do resto da data
            int commaIndex = dateString.indexOf(',');
            if (commaIndex <= 0) {
                return dateString;
            }

            String dayOfWeek = dateString.substring(0, commaIndex).toLowerCase();
            String restOfDate = dateString.substring(commaIndex);

            // Capitaliza a primeira letra do dia da semana
            dayOfWeek = dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1);

            return dayOfWeek + restOfDate;
        }
    }

}

package odontomais.model.especial;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

/*
 * Author: phlab
 * Date: 24/10/18
 */
public abstract class Template {

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 8,
            Font.BOLD);

    private Document document;

    public final Document criarPDF(String file) {
        document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            addMetaData();
            addContent(document);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return document;

    }

    private void addMetaData() {
        document.addTitle(LocalDateTime.now().toString());
        document.addSubject("PDF OdontoMais");
        document.addKeywords("");
        document.addAuthor("Odonto Mais");
        document.addCreator("Odonto Mais");
    }

    public void addTitleAndSubject(String title, String subject) {
        document.addTitle(title);
        document.addSubject(subject);
    }

    public void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public abstract void addContent(Document document) throws DocumentException;

    public void addTitlePage(String TITULO) throws DocumentException {
        Paragraph preface = new Paragraph();

        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph(TITULO, catFont));
        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Relatório gerado por: " + System.getProperty("user.name") + ", " + LocalDateTime.now().toString() + "Rev: 1.0", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 3);

        document.add(preface);
    }
}

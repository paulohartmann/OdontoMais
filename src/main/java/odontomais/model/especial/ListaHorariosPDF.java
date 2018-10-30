package odontomais.model.especial;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import odontomais.model.Agendamento;
import odontomais.service.util.DataUtil;

import java.time.LocalDate;

/*
 * Author: phlab
 * Date: 24/10/18
 */
public class ListaHorariosPDF extends Template {


    private static String TITULO = "Lista de Horários";


    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);


    private java.util.List<Agendamento> listAgendamento;

    public ListaHorariosPDF(java.util.List<Agendamento> listAgendamento){
        this.listAgendamento = listAgendamento;
    }

    @Override
    public void addContent(Document document) throws DocumentException {
        addTitleAndSubject("Lista Horarios" + LocalDate.now().toString(), "Teste 1");
        addTitlePage(TITULO);
        Paragraph preface = new Paragraph();

        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);


        PdfPCell c1 = new PdfPCell(new Phrase(DataUtil.converteDataToString(listAgendamento.get(0).getDataAgenda())));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for(Agendamento str : listAgendamento) {
            table.addCell(DataUtil.converteTimeToString(str.getHoraInicio()) + " - " + str.getPaciente().getNomeCompleto());
        }

        document.add(table);
        document.add(preface);
    }

}

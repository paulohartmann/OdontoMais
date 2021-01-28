package odontomais.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import odontomais.model.especial.HistoricoMovimentoCaixa;
import odontomais.persistence.jpa.GenericDAO;
import odontomais.service.HistoricoMovimentoCaixaService;
import odontomais.service.util.DataUtil;
import odontomais.view.tabmod.TabMovimentoCaixa;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.math.BigDecimal;

public class ListaMovimentoCaixa extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTable tblHistoricoMovimentoCaixa;
    private DatePicker dataInicial;
    private DatePicker dataFinal;
    private JButton pesquisarButton;
    private JLabel lblPagamento;
    private JLabel lblDebito;
    private JLabel lblFiltros;
    private JLabel lblSomatorio;

    private TabMovimentoCaixa tabMovimentoCaixa;
    HistoricoMovimentoCaixa historico;

    final static Logger logger = Logger.getLogger(ListaMovimentoCaixa.class);

    public ListaMovimentoCaixa() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        pesquisarButton.addActionListener(e -> goPesquisar());
        buttonOK.addActionListener(e -> onOK());

    }

    private void goPesquisar() {
        HistoricoMovimentoCaixaService service = new HistoricoMovimentoCaixaService();
        historico = service.getMovimento(dataInicial.getDate(), dataFinal.getDate());

        lblFiltros.setText("Resultado:");
        try {
            lblDebito.setText("R$" + historico.somaSaida().toString());
            lblPagamento.setText("R$" + historico.somaEntrada().toString());

            BigDecimal soma = historico.somaEntrada().subtract(historico.somaSaida());
            lblSomatorio.setText("De " + DataUtil.converteDataToString(dataInicial.getDate()) + " até " +
                    DataUtil.converteDataToString(dataFinal.getDate()) + ": R$");
            lblSomatorio.setText(lblSomatorio.getText() + soma.toString());

        } catch (Exception ex) {
            logger.error("Erro ao preencher somatório Movimento Caixa", ex.fillInStackTrace());
        }
        atualizaTabela();
    }

    private void atualizaTabela() {
        tabMovimentoCaixa = new TabMovimentoCaixa(historico.getListaMovimentoCaixa());

        tblHistoricoMovimentoCaixa.setModel(tabMovimentoCaixa);
        tblHistoricoMovimentoCaixa.getTableHeader().setReorderingAllowed(false);
        tblHistoricoMovimentoCaixa.getTableHeader().setVisible(true);
        tblHistoricoMovimentoCaixa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblHistoricoMovimentoCaixa.getColumnModel().getColumn(0).setMinWidth(210);
        tblHistoricoMovimentoCaixa.getColumnModel().getColumn(0).setPreferredWidth(210);
        tabMovimentoCaixa.fireTableDataChanged();
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void createUIComponents() {
        DatePickerSettings ds2 = new DatePickerSettings();
        ds2.setFormatForDatesCommonEra("dd/MM/yyyy");
        ds2.setFormatForDatesBeforeCommonEra("dd/MM/yyyy");

        dataFinal = new DatePicker(ds2);
        dataFinal.setDateToToday();
        dataFinal.setDate(dataFinal.getDate().plusDays(1));

        DatePickerSettings ds = new DatePickerSettings();
        ds.setFormatForDatesCommonEra("dd/MM/yyyy");
        ds.setFormatForDatesBeforeCommonEra("dd/MM/yyyy");

        dataInicial = new DatePicker(ds);
        dataInicial.setDate(dataFinal.getDate().minusDays(30));
    }
}

package odontomais.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import odontomais.service.util.FormatadoresTexto;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class NovoAgendamento extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox edtComboTipoAgendamento;
    private JTextField edtNomePaciente;
    private JButton btnProcurarPaciente;
    private JTextArea edtObs;
    private JFormattedTextField edtCelular;
    private JFormattedTextField edtResidencial;
    private JComboBox edtComboConvenio;
    private JButton btnNovoConvenio;
    private JButton btnNovoTipoAgendamento;
    private DatePicker edtData;
    private TimePicker edtHoraIni;
    private TimePicker edtHoraFim;
    private JComboBox edtComboProfissional;

    public NovoAgendamento() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        edtData.setDateToToday();
        edtHoraIni.setTimeToNow();
        edtHoraFim.setTimeToNow();

    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void createUIComponents() {
        edtCelular = new JFormattedTextField(FormatadoresTexto.foneCelFormat());
        edtResidencial = new JFormattedTextField(FormatadoresTexto.foneFixoFormat());

        DatePickerSettings ds = new DatePickerSettings();
        ds.setFormatForDatesCommonEra("dd/MM/yyyy");
        ds.setFormatForDatesBeforeCommonEra("dd/MM/yyyy");
        edtData = new DatePicker(ds);
        edtData.setDateToToday();

        TimePickerSettings timeSettings = new TimePickerSettings();
        timeSettings.use24HourClockFormat();
        edtHoraFim = new TimePicker(timeSettings);
        edtHoraIni = new TimePicker(timeSettings);


    }
}

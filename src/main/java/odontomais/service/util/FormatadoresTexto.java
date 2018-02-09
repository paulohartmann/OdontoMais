package odontomais.service.util;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Author: phlab
 * Date: 01/02/18
 */
public class FormatadoresTexto {

    public static MaskFormatter foneFixoFormat() {

        MaskFormatter mf1 = null;
        try {
            mf1 = new MaskFormatter("(##)####-####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mf1.setPlaceholderCharacter('_');
        return mf1;
    }

    public static MaskFormatter foneCelFormat() {

        MaskFormatter mf1 = null;
        try {
            mf1 = new MaskFormatter("(##)#####-####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mf1.setPlaceholderCharacter('_');
        return mf1;

    }

    public static MaskFormatter horaMinFormat() {

        MaskFormatter mf1 = null;
        try {
            mf1 = new MaskFormatter("##:##");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mf1.setPlaceholderCharacter('_');
        return mf1;
    }

    public static MaskFormatter rgFormat() {

        MaskFormatter mf1 = null;
        try {
            mf1 = new MaskFormatter("##########");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mf1.setPlaceholderCharacter('_');
        return mf1;
    }

    public static MaskFormatter cpfFormat() {

        MaskFormatter mf1 = null;
        try {
            mf1 = new MaskFormatter("###.###.###-##");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mf1.setPlaceholderCharacter('_');
        return mf1;
    }

    public static MaskFormatter dataFormat() {

        MaskFormatter mf1 = null;
        try {
            mf1 = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mf1.setPlaceholderCharacter('_');
        return mf1;
    }

    public static String horaToString(LocalDate hora){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String t = hora.format(formatter);
        return t;
    }




}
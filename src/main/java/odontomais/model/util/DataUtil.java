package odontomais.model.util;

import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DataUtil {

    final static Logger logger = Logger.getLogger(DataUtil.class);
    final static DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    final static DateTimeFormatter horaFormat = DateTimeFormatter.ofPattern("HH:mm");

    public static LocalDate converteStringToDate(String data){
        try{
            return LocalDate.parse(data, dataFormat);
        }catch (Exception ex){
            logger.error("Erro ao converter String para Data", ex.fillInStackTrace());
            return null;
        }
    }

    public static String converteDataToString(LocalDate date){
        try{
            return date.format(dataFormat);
        }catch (Exception ex){
            logger.error("Erro ao converter Data para String", ex.fillInStackTrace());
            return null;
        }
    }

    public static LocalTime converteStringToTime(String time){
        try{
            return LocalTime.parse(time, horaFormat);
        }catch (Exception ex) {
            logger.error("Erro ao converter String para LocalTime", ex.fillInStackTrace());
            return null;
        }
    }

    public static String converteTimeToString(LocalTime time){
        try{
            return time.format(horaFormat);
        }catch (Exception ex){
            logger.error("Erro ao converter LocalTime para String", ex.fillInStackTrace());
            return null;
        }
    }
}

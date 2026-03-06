package pp3.st.utils;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public final class Fecha {
    
    //LocalDate -> long
    public static long getFechaLong(LocalDate fecha){
        String dia = String.valueOf(fecha.getDayOfMonth());
        String mes = String.valueOf(fecha.getMonthValue());
        String ano = String.valueOf(fecha.getYear());
        String fechaString = dia+"/"+mes+"/"+ano+" 00:00:00";
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date;
        long millis;
        try{
            date = sdf.parse(fechaString);
            millis = date.getTime();
        }catch(Exception ex){
            System.out.println("Error al convertir de LocalDate a long: "+ex);
            millis = 0;
        }
        return millis;
    }
    
    //long -> LocalDate
    public static LocalDate getFechaLocalDate(long millis){
        return new java.sql.Date(millis).toLocalDate();
    }
    
    //long -> String
    public static String getFechaString(long millis){
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
    
}

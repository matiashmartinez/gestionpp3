package pp3.st.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class time {
    
//la fecha
    public String fecha(){
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        return(sdf.format(fecha));       
    }
    
    public String consultaFecha(){
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return(sdf.format(fecha));       
    }
    
    //mes
    public String fechaMes(){
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        return(sdf.format(fecha));       
    }
    public String fechaAnio(){
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return(sdf.format(fecha));       
    }
    
    
    
}

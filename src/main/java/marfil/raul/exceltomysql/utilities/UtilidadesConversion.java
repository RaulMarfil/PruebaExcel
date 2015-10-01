


package marfil.raul.exceltomysql.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author rmarfilc
 */
public class UtilidadesConversion {

    static byte ceroOuno;
    
    static Date dateFecha;
    static java.sql.Timestamp timestampFecha;
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("es","ES"));
    static String fechaRetorno;
    static int texToInt;
    
    public static byte ConvertBooleanToBit (String ValorExcel){
        
        
        if (!ValorExcel.isEmpty() || ValorExcel.contains("Si") || ValorExcel.contains("Sí")){
        ceroOuno = 1;
        } 
        
        if (ValorExcel.isEmpty() || ValorExcel.contains("No")){
        ceroOuno = 0;
        }
        
        return ceroOuno;
    
    }
    
    public static java.sql.Timestamp FromStringToTimeStamp (String FechaExcel) throws ParseException{
    if (!FechaExcel.isEmpty()){
                dateFecha = sdf.parse(FechaExcel);
                timestampFecha = new java.sql.Timestamp(dateFecha.getTime());
            } else {
                timestampFecha = null;
            }
       
    return timestampFecha;
    }
    
    public static int FromTexToInt (String ValorExcel){
    
       texToInt = Integer.parseInt(ValorExcel);
       return texToInt;
    
    }
    
    
}

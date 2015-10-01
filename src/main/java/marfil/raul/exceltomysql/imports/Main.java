


package marfil.raul.exceltomysql.imports;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import marfil.raul.exceltomysql.utilities.Importar;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


//Prueba



public class Main {


public static void main(String[] args) throws SQLException, IOException, FileNotFoundException, InvalidFormatException, ParseException {


    Importar.ExcelToSQL("F1-EXTR-INC-En_Curso", "INC_EnCurso");
    
    Importar.ExcelToSQL("F1-EXTR-INC-Por_Fecha_Cierre", "INC_Por_Fecha_Cierre");
    
    Importar.ExcelToSQL("F1-EXTR-INC-Por_Fecha_Apertura", "INC_Por_Fecha_Apertura");
    
    Importar.ExcelToSQL("F1-EXTR-INC-Por_Fecha_Resolucion", "INC_Por_Fecha_Resolucion");

}

}







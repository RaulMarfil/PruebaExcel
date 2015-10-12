


package marfil.raul.exceltomysql.imports;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import marfil.raul.exceltomysql.utilities.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


//Prueba



public class Main {


public static void main(String[] args) throws SQLException, IOException, FileNotFoundException, InvalidFormatException, ParseException {


//    Importar.ExtraccionF1ToSQL("F1-EXTR-INC-En_Curso", "INC_EnCurso");
//    
//    Importar.ExtraccionF1ToSQL("F1-EXTR-INC-Por_Fecha_Cierre", "INC_Por_Fecha_Cierre");
//    
//    Importar.ExtraccionF1ToSQL("F1-EXTR-INC-Por_Fecha_Apertura", "INC_Por_Fecha_Apertura");
//    
//    Importar.ExtraccionF1ToSQL("F1-EXTR-INC-Por_Fecha_Resolucion", "INC_Por_Fecha_Resolucion");
    
//     Importar.SistemaNegocioToSQL("SistemasNegocio", "SistemasNegocio");
    
    Calculo.INC_Cerradas();

}     

}







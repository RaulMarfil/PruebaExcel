


package marfil.raul.exceltomysql.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class Importar {
    
    public static void ExtraccionF1ToSQL(String Fichero, String Tabla) throws SQLException, FileNotFoundException, IOException, InvalidFormatException, ParseException{
    
        int countReg = 0;
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://172.16.224.137/prova?zeroDateTimeBehavior=convertToNull","root","vulcano1");
            con.setAutoCommit(false);
            PreparedStatement pstm = null ;
            InputStream input = new FileInputStream("/Users/rmarfilc/temp/ITSM-Incidencias-Extracciones_julio_2015_ITSM/"+Fichero +".xls");
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("es","ES"));
            
            Workbook wb = WorkbookFactory.create(input);
            Sheet sheet = wb.getSheetAt(0);
            Row row;
            for(int i=1; i<=sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);
                
                String TicketID = row.getCell(0).getStringCellValue();
                if (TicketID.isEmpty()){break;}
                
                String CatOP2 = row.getCell(1).getStringCellValue();
                
                Date dateFechaCreacion = sdf.parse(row.getCell(2).getStringCellValue());
                java.sql.Timestamp timestampCreacion = new java.sql.Timestamp(dateFechaCreacion.getTime());
                
                String Estado = row.getCell(3).getStringCellValue();
                String Desc_Estado = row.getCell(4).getStringCellValue();
                String EnlacePPM = row.getCell(5).getStringCellValue();
                String GrupoEntrada = row.getCell(6).getStringCellValue();
                String GrupoActual = row.getCell(7).getStringCellValue();
                String OrgSoporte = row.getCell(8).getStringCellValue();
                String CategProd_1 = row.getCell(9).getStringCellValue();
                String CategProd_2 = row.getCell(10).getStringCellValue();
                String CategProd_3 = row.getCell(11).getStringCellValue();
                String NombreProd = row.getCell(12).getStringCellValue();
                String FechaResolucion = row.getCell(13).getStringCellValue();
                String FechaCierre = row.getCell(14).getStringCellValue();
                String FechaObjetivo = row.getCell(15).getStringCellValue();
                String ExcluirANS = row.getCell(16).getStringCellValue(); // es SI / NO. Convertir boolean
                String FechaUltimaMod = row.getCell(17).getStringCellValue();
                String CanalEntrada = row.getCell(18).getStringCellValue();
                String REQSolicitudServicio = row.getCell(19).getStringCellValue(); // REQUEST F1
                String IndispoibilidadServicio = row.getCell(20).getStringCellValue(); // es SI / NO. Convertir boolean
                String AfectacionUsuarios = row.getCell(21).getStringCellValue();
                String Impacto = row.getCell(22).getStringCellValue();
                String Prioridad = row.getCell(23).getStringCellValue();
                String INCDisfundida = row.getCell(24).getStringCellValue();
                String LocalidadCliente = row.getCell(25).getStringCellValue();
                String UbicacionCliente = row.getCell(26).getStringCellValue();
                String ClienteID = row.getCell(27).getStringCellValue(); // usuario gas (000...)
                String NombreCliente = row.getCell(28).getStringCellValue();
                String ApellidosCliente = row.getCell(29).getStringCellValue();
                String AmbitoSS = row.getCell(30).getStringCellValue();
                String CatOperacional1 = row.getCell(31).getStringCellValue();
                String Resumen = row.getCell(32).getStringCellValue();
                String Notas = row.getCell(33).getStringCellValue();
                String NumReasing = row.getCell(34).getStringCellValue();// entero
                String NumReaperturas = row.getCell(35).getStringCellValue(); //entero
                String Memo = row.getCell(36).getStringCellValue();
                String Remitente = row.getCell(37).getStringCellValue();
                String UsuarioAsignado = row.getCell(38).getStringCellValue();
                String Resolucion = row.getCell(39).getStringCellValue();
                String TipoIncidencia = row.getCell(40).getStringCellValue();
                String VIP = row.getCell(41).getStringCellValue(); // es SI / NO. Convertir boolean
                String FechaInicioReal = row.getCell(42).getStringCellValue();
                String FechaProgramadaDeInicio = row.getCell(43).getStringCellValue();
                String FechaProgramadaDeFinalizacion = row.getCell(44).getStringCellValue();
                String Patron = row.getCell(45).getStringCellValue();
                String CausaRaiz = row.getCell(46).getStringCellValue();
                String GrupoPredetRemitente = row.getCell(47).getStringCellValue();
                String UsuarioResolutor = row.getCell(48).getStringCellValue();
                String MotivoResolucion = row.getCell(49).getStringCellValue();
                
                
                
                
                String sql = "INSERT INTO "+Tabla +" (TicketID,CatOP2,FechaCreacion,Estado,Desc_Estado,Enlace_PPM,Grupo_Entrada,Grupo_Actual,Org_Soporte,Categ_Prod_1,"
                        + "Categ_Prod_2,Categ_Prod_3,Nombre_Prod,FechaResolucion,FechaCierre,FechaObjetivo,ExcluirANS,FechaUltimaMod,CanalEntrada,"
                        + "REQSolicitudServicio,IndispoibilidadServicio,AfectacionUsuarios,Impacto,Prioridad,INCDisfundida,LocalidadCliente,UbicacionCliente,ClienteID,"
                        + "NombreCliente,ApellidosCliente,AmbitoSS,CatOperacional1,Resumen,Notas,NumReasing,NumReaperturas,Memo,Remitente,UsuarioAsignado,Resolucion,TipoIncidencia,VIP,FechaInicioReal"
                        + ",FechaProgramadaDeInicio,FechaProgramadaDeFinalizacion,Patron,CausaRaiz,GrupoPredetRemitente,UsuarioResolutor,MotivoResolucion)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                
                pstm = (PreparedStatement) con.prepareStatement(sql);
                pstm.setString(1, TicketID);
                pstm.setString(2,CatOP2);
                pstm.setTimestamp(3,timestampCreacion);
                pstm.setString(4,Estado);
                pstm.setString(5,Desc_Estado);
                pstm.setString(6,EnlacePPM);
                pstm.setString(7,GrupoEntrada);
                pstm.setString(8,GrupoActual);
                pstm.setString(9,OrgSoporte);
                pstm.setString(10,CategProd_1);
                pstm.setString(11,CategProd_2);
                pstm.setString(12,CategProd_3);
                pstm.setString(13,NombreProd);
                
                
                pstm.setTimestamp(14,UtilidadesConversion.FromStringToTimeStamp(FechaResolucion));
                pstm.setTimestamp(15,UtilidadesConversion.FromStringToTimeStamp(FechaCierre));
                pstm.setTimestamp(16,UtilidadesConversion.FromStringToTimeStamp(FechaObjetivo));
                pstm.setByte(17, UtilidadesConversion.ConvertBooleanToBit(ExcluirANS));
                pstm.setTimestamp(18,UtilidadesConversion.FromStringToTimeStamp(FechaUltimaMod));
                
                pstm.setString(19,CanalEntrada);
                pstm.setString(20, REQSolicitudServicio);
                
                pstm.setByte(21, UtilidadesConversion.ConvertBooleanToBit(IndispoibilidadServicio));
                pstm.setString(22, AfectacionUsuarios);
                pstm.setString(23, Impacto);
                pstm.setString(24, Prioridad);
                pstm.setString(25, INCDisfundida);
                pstm.setString(26, LocalidadCliente);
                pstm.setString(27, UbicacionCliente);
                pstm.setString(28, ClienteID);
                pstm.setString(29, NombreCliente);
                pstm.setString(30, ApellidosCliente);
                pstm.setString(31, AmbitoSS);
                pstm.setString(32, CatOperacional1);
                pstm.setString(33, Resumen);
                pstm.setString(34, Notas);
                pstm.setInt(35, UtilidadesConversion.FromTexToInt(NumReasing));
                pstm.setInt(36, UtilidadesConversion.FromTexToInt(NumReaperturas));
                pstm.setString(37, Memo);
                pstm.setString(38, Remitente);
                pstm.setString(39, UsuarioAsignado);
                pstm.setString(40, Resolucion);
                pstm.setString(41, TipoIncidencia);
                pstm.setByte(42, UtilidadesConversion.ConvertBooleanToBit(VIP));
                pstm.setTimestamp(43, UtilidadesConversion.FromStringToTimeStamp(FechaInicioReal));
                pstm.setTimestamp(44, UtilidadesConversion.FromStringToTimeStamp(FechaProgramadaDeInicio));
                pstm.setTimestamp(45, UtilidadesConversion.FromStringToTimeStamp(FechaProgramadaDeFinalizacion));
                pstm.setString(46, Patron);
                pstm.setString(47, CausaRaiz);
                pstm.setString(48, GrupoPredetRemitente);
                pstm.setString(49, UsuarioResolutor);
                pstm.setString(50, MotivoResolucion);
                
                pstm.execute();
                
                countReg = i;
                //System.out.println("Import rows "+i);
            }
            con.commit();
            pstm.close();
            con.close();
            input.close();
            System.out.println("Importación del fichero " + Fichero + " a la tabla " + Tabla + " efectuado correctamente. " + countReg + " Registros.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Importar.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    public static void SistemaNegocioToSQL(String Fichero, String Tabla) throws SQLException, FileNotFoundException, IOException, InvalidFormatException, ParseException{
    
        int countReg = 0;
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://172.16.224.137/prova?zeroDateTimeBehavior=convertToNull","root","vulcano1");
            con.setAutoCommit(false);
            PreparedStatement pstm = null ;
            InputStream input = new FileInputStream("/Users/rmarfilc/temp/ITSM-Incidencias-Extracciones_julio_2015_ITSM/"+Fichero +".xlsx");
            
           
            
            Workbook wb = WorkbookFactory.create(input);
            Sheet sheet = wb.getSheetAt(0);
            Row row;
            for(int i=1; i<=sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);
                
                String AppGNF = row.getCell(0).getStringCellValue();
                if (AppGNF.isEmpty()){break;}
                
                String Negocio = row.getCell(1).getStringCellValue();
                
                
                
                String sql = "INSERT INTO "+Tabla +" (AppGNF,Negocio)"
                        + "VALUES(?,?)";
                
                pstm = (PreparedStatement) con.prepareStatement(sql);
                pstm.setString(1, AppGNF);
                pstm.setString(2,Negocio);
                
                pstm.execute();
                
                countReg = i;
                //System.out.println("Import rows "+i);
            }
            con.commit();
            pstm.close();
            con.close();
            input.close();
            System.out.println("Importación del fichero " + Fichero + " a la tabla " + Tabla + " efectuado correctamente. " + countReg + " Registros.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Importar.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    
    
    
    
    
    
}

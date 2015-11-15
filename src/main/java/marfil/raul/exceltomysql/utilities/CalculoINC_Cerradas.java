


package marfil.raul.exceltomysql.utilities;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.util.logging.Level;
import java.util.logging.Logger;




public class CalculoINC_Cerradas {

    public static void Negocio() throws SQLException, ParseException {
        
        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
        
        String queryNegocio = "SELECT INC_Por_Fecha_Cierre.TicketID, INC_Por_Fecha_Cierre.Categ_Prod_2, SistemasNegocio.Negocio, AmbitoSS, Org_Soporte, FechaCierre \n" +
        ", NumReasing,IndisponibilidadServicio, FechaCreacion, Prioridad, NombreCliente, FechaResolucion, NumReaperturas,"
                + "Categ_Prod_2 FROM INC_Por_Fecha_Cierre LEFT JOIN SistemasNegocio ON INC_Por_Fecha_Cierre.Categ_Prod_2 = SistemasNegocio.AppGNF;";
        String insertCalculo = "";
        String Negocio, fechaCierre,Org_Soporte,AmbitoSS,CategProd2,TicketID = "";
        int numEscalados = 0;
        int escalados0a2 = 0;
        int escalados3a5 = 0;
        int escalados5mas = 0;
        int indisponibilidad = 0;
        int resuelto = 0;
        Integer calculoResuelto = 0;
        Timestamp fechaCreacionTS = null;
        Timestamp fechaResolucionTS = null;
        Timestamp FechaCierreTS = null;
        String Prioridad;
        boolean UsuarioCritico = false;
        boolean sinNegocioInformado = false;
        boolean reabierto = false;
        boolean puestoCliente = false;
        boolean comunicacionesGNF = false;
        
        
    try (Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://172.16.224.137/prova?zeroDateTimeBehavior=convertToNull","root","vulcano1");
            PreparedStatement pstmQuery = con.prepareStatement(queryNegocio)
             )
    
    {
            Class.forName("com.mysql.jdbc.Driver");
            
            con.setAutoCommit(false);
            ResultSet rs = pstmQuery.executeQuery();
            
            while (rs.next()){
                Negocio = rs.getString("Negocio");
                
                TicketID = rs.getString("TicketID");
                CategProd2 = rs.getString("Categ_Prod_2");
                
                AmbitoSS = rs.getString("AmbitoSS");
                Org_Soporte = rs.getString("Org_Soporte");
                
                fechaCierre = new SimpleDateFormat("yyyy-MM").format(rs.getTimestamp("FechaCierre"));
                
                numEscalados = rs.getInt("NumReasing");
                
                indisponibilidad = rs.getInt("IndisponibilidadServicio");
                
                //Informamos Negocio Final
              
                if (CategProd2.startsWith("MX - ")){
                    Negocio = "LATAM";
                }
                        
                if (CategProd2.startsWith("NI - ")){
                    Negocio = "LATAM";
                }
                
                if (CategProd2.startsWith("PA - ")){
                    Negocio = "LATAM";
                }
                
                if (CategProd2.startsWith("BR - ")){
                    Negocio = "LATAM";
                }
                
                if (CategProd2.startsWith("CO - ")){
                    Negocio = "LATAM";
                }
                   
                if (CategProd2.startsWith("CO-O")){
                    Negocio = "LATAM";
                }
                
                if (CategProd2.startsWith("ARG")){
                    Negocio = "LATAM";
                }
                
                if (CategProd2.startsWith("GT - ")){
                    Negocio = "LATAM";
                }
                    
                if (AmbitoSS.equals("GNFT") && Org_Soporte.equals("TELECOMUNICACIONES - ESP")) {
                    Negocio = "NO GNF";
                }
                
                if (Negocio == null) {Negocio = "No Informado";}
                
                
                // Fin Negocio Final
                
                //Informamos los campos Escalados 0-2, Escalados 3-5 y Escalados > 5. Se indica 1 en cada campo si cumple la condición.
                
                if (numEscalados >= 0 && numEscalados < 2) {
                    escalados0a2 = 1;
                }
                if (numEscalados >= 3 && numEscalados < 5) {
                    escalados3a5 = 1;
                }
                  if (numEscalados > 5 ) {
                    escalados5mas = 1;
                }
                //Fin Calculo escalados.
                
                //Inicio calculo factor resulto
                  
                fechaCreacionTS = rs.getTimestamp("FechaCreacion");
                fechaResolucionTS = rs.getTimestamp("FechaResolucion");
                FechaCierreTS = rs.getTimestamp("FechaCierre");
                
                if (fechaResolucionTS == null){
                resuelto = (int) ((FechaCierreTS.getTime() - fechaCreacionTS.getTime()) / MILLSECS_PER_DAY);
                
                } else {
                resuelto = (int) ((fechaResolucionTS.getTime() - fechaCreacionTS.getTime()) / MILLSECS_PER_DAY);
                
                }
               
                if (resuelto < 0){calculoResuelto = null;} 
                
                if (resuelto > 0 && resuelto <= 1) {calculoResuelto = 1;} // Resuelto en menos de 1
                
                if (resuelto > 1 && resuelto <= 2) {calculoResuelto = 2;} //Resuelto en 1-2
                
                if (resuelto > 3 && resuelto <= 5) {calculoResuelto = 3;} // Resuelto en 3-5
                
                if (resuelto > 5) {calculoResuelto = 4;} // Resuelto en más de 5
                
                        
                        
                //Fin calculo factor resulto
                
                //Inicio Inicar Prioridad (Crítco, Grave, Media, Leve)
                
                Prioridad = rs.getString("Prioridad");
                
                // Fin Prioridad
                
                //Inicio Afectación Cliente Crítico
                
                if (rs.getString("NombreCliente").equals("USUARIO CRITICO")){
                    UsuarioCritico = true;    
                }
                
                //Fin afectación Cliente crítico
                
                //Inicio Negocio Informado?
                if (Negocio.equals("No Informado")){
                    sinNegocioInformado = true;
                }
                // Fin negocio informado
                
                //inicio Reabierto
                if (rs.getDouble("NumReaperturas") > 0) {
                    reabierto = true;
                }
                
                //inicio Puesto cliente
                
                if (rs.getString("Categ_Prod_2").equals("EQUIPO Y SOFTWARE")){
                    puestoCliente = true;
                }
                
                //fin puesto cliente
                
                //Inicio comunicaciones GAS
                if (Org_Soporte.equals("TELECOMUNICACIONES - ESP") && AmbitoSS.equals("GasnaturalUF")) {
                    comunicacionesGNF = true;
                }
                //fin comunicaciones GAS
                
                //Inicio BATCH
                //TODO
                //Fin BATCH
                
                System.out.println("Ticket:" + TicketID + " Negocio :" + Negocio + " FechaCierre: " + fechaCierre + 
                        " Escalados 0 - 2: " + escalados0a2 + " Escalados 3 - 5: " + escalados3a5 + " Escalados > 5: " +escalados5mas + 
                        " Indisponibilidad: " + indisponibilidad + " Resuelto en: " + calculoResuelto + " Tipo Gravedad: " + Prioridad
                        + " Usuario Crítico: " + UsuarioCritico + " Negocio NO Informado: " + sinNegocioInformado + " Reabierto? :" + reabierto +
                        " Puesto Cliente: " + puestoCliente + " Comunicaciones GNF: " + comunicacionesGNF
                
                );
//                
//               insertCalculo =  "INSERT INTO INC_Por_Fecha_Cierre_Result (TicketID,Negocio)"
//                       + "VALUES(?,?)";
//                
//                PreparedStatement pstmInsert = con.prepareCall(insertCalculo);
//                pstmInsert.setString(1, TicketID);
//                pstmInsert.setString(2, Negocio);
//                
//                pstmInsert.execute();
//                
//                con.commit();
//               
//                pstmInsert.close();
                
                
                //inicializamos variables.
                
                escalados0a2 = 0;
                escalados3a5 = 0;
                escalados5mas = 0;
                resuelto = 0;
                calculoResuelto = 0;
                UsuarioCritico = false;
                sinNegocioInformado = false;
                reabierto = false;
                puestoCliente = false;
                comunicacionesGNF = false;
                        
                
            }
            
            
            
    }catch (SQLException | ClassNotFoundException e ){
        Logger.getLogger(CalculoINC_Cerradas.class.getName()).log(Level.SEVERE, null, e);
    } 
        
      
    
    
    }
    
    
    
   }
    
    
    
    

    
   
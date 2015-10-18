


package marfil.raul.exceltomysql.utilities;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.util.logging.Level;
import java.util.logging.Logger;




public class CalculoINC_Cerradas {

    public static void Negocio() throws SQLException, ParseException {
        
        
        String queryNegocio = "SELECT INC_Por_Fecha_Cierre.TicketID, INC_Por_Fecha_Cierre.Categ_Prod_2, SistemasNegocio.Negocio, AmbitoSS, Org_Soporte, FechaCierre \n" +
        ", NumReasing FROM INC_Por_Fecha_Cierre LEFT JOIN SistemasNegocio ON INC_Por_Fecha_Cierre.Categ_Prod_2 = SistemasNegocio.AppGNF;";
        String insertCalculo = "";
        String Negocio, fechaCierre,Org_Soporte,AmbitoSS,CategProd2,TicketID = "";
        int numEscalados = 0;
        int escalados0a2 = 0;
        int escalados3a5 = 0;
        int escalados5mas = 0;
        
        
        
        
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
                
                
                
               
                
                
                System.out.println("Ticket:" + TicketID + " Negocio :" + Negocio + " FechaCierre: " + fechaCierre + 
                        " Escalados 0 - 2: " + escalados0a2 + " Escalados 3 - 5: " + escalados3a5 + " Escalados > 5: " +escalados5mas);
                
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
                
                
                //inicializamos contadores escalados.
                
                escalados0a2 = 0;
                escalados3a5 = 0;
                escalados5mas = 0;
                        
                
            }
            
            
            
    }catch (SQLException | ClassNotFoundException e ){
        Logger.getLogger(CalculoINC_Cerradas.class.getName()).log(Level.SEVERE, null, e);
    } 
        
      
    
    
    }
    
    
    
   }
    
    
    
    

    
   
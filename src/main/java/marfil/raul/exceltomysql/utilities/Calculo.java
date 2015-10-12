


package marfil.raul.exceltomysql.utilities;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.logging.Level;
import java.util.logging.Logger;




public class Calculo {

    public static void INC_Cerradas() {
        
        PreparedStatement pstmQuery, pstmInsert= null ;
        String queryNegocio = "SELECT INC_Por_Fecha_Cierre.TicketID, INC_Por_Fecha_Cierre.Categ_Prod_2, SistemasNegocio.Negocio, AmbitoSS, Org_Soporte \n" +
        "FROM INC_Por_Fecha_Cierre LEFT JOIN SistemasNegocio ON INC_Por_Fecha_Cierre.Categ_Prod_2 = SistemasNegocio.AppGNF;";
        String insertCalculo;
        
    try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://172.16.224.137/prova?zeroDateTimeBehavior=convertToNull","root","vulcano1");
            con.setAutoCommit(false);
            pstmQuery = (PreparedStatement) con.prepareStatement(queryNegocio);
            
            ResultSet rs = pstmQuery.executeQuery();
            
            while (rs.next()){
                String TicketID = rs.getString("TicketID");
                String CategProd2 = rs.getString("Categ_Prod_2");
                String Negocio = rs.getString("Negocio");
                String AmbitoSS = rs.getString("AmbitoSS");
                String Org_Soporte = rs.getString("Org_Soporte");
                
              
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
                
                
                insertCalculo =  "INSERT INTO INC_Por_Fecha_Cierre_Result (TicketID,Negocio)"
                        + "VALUES(?,?)";
                
                pstmInsert = (PreparedStatement) con.prepareCall(insertCalculo);
                pstmInsert.setString(1, TicketID);
                pstmInsert.setString(2, Negocio);
                
                pstmInsert.execute();
                
                
                
                
            }
            
            con.commit();
            pstmInsert.close();
            pstmQuery.close();
            
    }catch (SQLException | ClassNotFoundException e ){
        Logger.getLogger(Calculo.class.getName()).log(Level.SEVERE, null, e);
    }   
           
    
    
    }
    
    
    
    
}
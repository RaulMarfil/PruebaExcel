/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marfil.raul.exceltomysql.utilities;

/**
 *
 * @author rmarfilc
 */
public enum Nivel_dos {
    
    
    ubicacion_1("COMUNICACIONES TELECONTROL"),
    ubicacion_2("COMUNICACIONES REGULACION SECUNDARIA"),
    ubicacion_3("COMUNICACIONES HYDRA"),
    ubicacion_4("COMUNICACIONES PROTECCIONES"),
    ubicacion_5("CENTRAL TELEFONICA CORE"),
    ubicacion_6("CENTRAL TELEFONICA"),
    ubicacion_7("TELEFONIA"),
    ubicacion_8("TELEFONIA MÓVIL"),
    ubicacion_9("PUESTOS DE OPERADORAS");


    private String ubicacion;

    private Nivel_dos(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNivel_dos(){
        return ubicacion;
    }
    
}

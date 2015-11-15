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



public enum Nivel_tres {

    ubicacion_1("SERVICIOS DISTRIBUIDOS - SALVAT TMN, DEL."),
    ubicacion_2("SERVICIOS DISTRIBUIDOS - PUENTE PRINCESA, SUB."),
    ubicacion_3("SERVICIOS DISTRIBUIDOS - PUENTE NUEVO, C.F."),
    ubicacion_4("SERVICIOS DISTRIBUIDOS - MANUEL SILVELA 13, OFIC."),
    ubicacion_5("SERVICIOS DISTRIBUIDOS - MADRID AVDA. ARAGON, DEL."),
    ubicacion_6("SERVICIOS DISTRIBUIDOS - LERIDA 44, OFIC."),
    ubicacion_7("SERVICIOS DISTRIBUIDOS - GOYA 36, OFIC."),
    ubicacion_8("SERVICIOS DISTRIBUIDOS - GNI EDS, DEL."),
    ubicacion_9("SERVICIOS DISTRIBUIDOS - CORNELLA CITY PARK, DEL."),
    ubicacion_10("SERVICIOS DISTRIBUIDOS - CERRO PLATA II, OFIC."),
    ubicacion_11("SERVICIOS DISTRIBUIDOS - CENTROS NO PRINCIPALES"),
    ubicacion_12("SERVICIOS DISTRIBUIDOS - BESOS, C.C.C."),
    ubicacion_13("SERVICIOS DISTRIBUIDOS - AVDA. SAN LUIS SEDE SOCIAL, OFIC."),
    ubicacion_14("SERVICIOS DISTRIBUIDOS - AVDA. DE ARTEIXO FILIALES II, OFIC."),
    ubicacion_15("SERVICIOS DISTRIBUIDOS - AVDA. DE AMERICA, DEL."),
    ubicacion_16("SERVICIOS INTERNACIONAL"),
    ubicacion_17("CONTACT CENTER"),
    ubicacion_18("CCAU/SAU");





    private String ubicacion;

    private Nivel_tres(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNivel_tres(){
        return ubicacion;
    }


    }
    



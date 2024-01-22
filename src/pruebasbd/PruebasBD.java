/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebasbd;


import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static pruebasbd.Parse.convertirAXml;
/**
 *
 * @author Alex
 */
public class PruebasBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        
        String extraccion="SELECT * FROM asignaciones_gob";
        
        
        // Crear una consulta SQL para crear una tabla
        String consulta = "CREATE TABLE asignaciones_gob ("
                + "columna1 VARCHAR(255), "
                + "columna2 VARCHAR(255), "
                + "columna3 VARCHAR(255), "
                + "columna4 VARCHAR(255), "
                + "columna5 VARCHAR(255), "
                + "columna6 VARCHAR(255), "
                + "columna7 VARCHAR(255), "
                + "columna8 VARCHAR(255), "
                + "columna9 VARCHAR(255), "
                + "columna10 VARCHAR(255)"
                + ")";
        // Crear una consulta SQL para insertar datos
        String insertQuery = "INSERT INTO asignaciones_gob (columna1, columna2, columna3, columna4, columna5, columna6, columna7, columna8, columna9, columna10) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        //Creamos la conexion con la base de datos y la almacenamos en un objeto conection para poder trabajar con el posteriormente
        Conexion conex = new Conexion("trabajo_gob");
        conex.conectar();

        //Creamos una tabla para almacenar posteriormetne el xml
        conex.InsertBD(consulta);

        //Creamos un preparedStatement para poder hacer una insercion a continuacion
        PreparedStatement stmt;
        try {
            stmt = conex.getCx().prepareStatement(insertQuery);
            //Empezamos a trabajar con el XML, lo parseamos y cargamos en memoria
            Parse miXML = new Parse();

            String xmlPath = "xml/contratos-adjudicados-jun-22.xml";

            miXML.readXML(xmlPath);

            //Seleccionamos el nodo row(columna) de el xml y empieza la insercion
            NodeList filas = miXML.getDocumento().getElementsByTagName("Row");

            // Procesar las filas y realizar inserciones en la base de datos
            for (int i = 0; i < filas.getLength(); i++) {
                Element fila = (Element) filas.item(i);
                NodeList celdas = fila.getElementsByTagName("Cell");

                // Verificar si hay al menos 10 celdas, si no, completar con valores predeterminados
                int numCeldas = celdas.getLength();
                for (int j = 0; j < 10; j++) {
                    if (j < numCeldas) {
                        Element celda = (Element) celdas.item(j);
                        NodeList dataNodes = celda.getElementsByTagName("Data");

                        if (dataNodes.getLength() > 0) {
                            String valor = dataNodes.item(0).getTextContent();
                            stmt.setString(j + 1, valor); // Los índices de PreparedStatement comienzan en 1
                        } else {
                            // Si la celda no tiene un elemento "Data", usar un valor predeterminado
                            stmt.setString(j + 1, " ");
                        }
                    } else {
                        // Si no hay celda disponible, completar con valor predeterminado o en blanco
                        stmt.setString(j + 1, " ");
                    }
                }

                stmt.addBatch();

                // Insertar en lotes de 10 registros
                if ((i + 1) % 10 == 0) {
                    stmt.executeBatch();
                    stmt.clearBatch(); // Limpia el lote para la siguiente iteración
                }
            }
            // Insertar los registros restantes (si no son múltiplos de 10)
            stmt.executeBatch();

            // Cerrar recursos
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PruebasBD.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        
        //Llamamos al metodo para que añada todos los registros de la base de datos ha un List de la clase registros creada anteriormente.
        List<Registro> registros = conex.obtenerRegistrosDeBaseDeDatos(extraccion);
               
        convertirAXml(registros, "Resultado.xml");
        
        conex.closeConnection();
    }
}

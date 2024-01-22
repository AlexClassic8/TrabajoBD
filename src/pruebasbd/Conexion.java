/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebasbd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class Conexion {
   private String bd ="";
   private String url ="jdbc:mysql://localhost:3306/";
   private String user ="root";
   private String password ="";
   private String driver ="com.mysql.cj.jdbc.Driver";
   private Connection cx;

    public Conexion(String bd) { 
        this.bd=bd;
    }

    
    
    public Connection conectar(){
        try {
            Class.forName(driver);
            cx=DriverManager.getConnection(url+bd, user, password);
            System.out.println("SE CONECTO A BD "+bd);
        } catch (ClassNotFoundException |SQLException ex) {
            System.out.println("NO SE CONECTO A BD "+bd);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cx;
    }
    public ResultSet ConsultaBD(String consulta){
       ResultSet rs=null;
        try {
           PreparedStatement pStatement = cx.prepareStatement(consulta);
           rs = pStatement.executeQuery();
            System.out.println("La extraccion de datos ha sido exitosa");
       } catch (SQLException ex) {
           Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("La extraccion de datos ha fallado");
       }
       return rs;
    }
    public void InsertBD(String consulta) {
        try {
            Statement statement = cx.createStatement();

            // Ejecuta la consulta SQL
            statement.executeUpdate(consulta);

            // Cierra la declaración
            statement.close(); 
            System.out.println("LA CONSULTA SE REALIZO EXITOSAMENTE");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR AL EJECUTAR LA CONSULTA");
        }
    }
    public void closeConnection() {
        try {
            if (cx != null) {
                cx.close();
            }
            System.out.println("CONEXION CERRADA");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR AL CERRAR LA CONECXION");
        }
    } public  List<Registro> obtenerRegistrosDeBaseDeDatos(String query ) {
        List<Registro> registros = new ArrayList<>();

        // Lógica para conectarte a la base de datos y obtener registros
        try {
            
            PreparedStatement stmt = this.cx.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            String columna1 = obtenerValorNoNulo(rs.getString("columna1"));
            String columna2 = obtenerValorNoNulo(rs.getString("columna2"));
            String columna3 = obtenerValorNoNulo(rs.getString("columna3"));
            String columna4 = obtenerValorNoNulo(rs.getString("columna4"));
            String columna5 = obtenerValorNoNulo(rs.getString("columna5"));
            String columna6 = obtenerValorNoNulo(rs.getString("columna6"));
            String columna7 = obtenerValorNoNulo(rs.getString("columna7"));
            String columna9 = obtenerValorNoNulo(rs.getString("columna9"));
            String columna10 = obtenerValorNoNulo(rs.getString("columna10"));
                

                Registro registro = new Registro(columna1, columna2, columna3, columna4, columna5, columna6, columna7, columna9, columna10 );
                registros.add(registro);
                
                System.out.println("Registro agregado: " + registro);
            }

            // Cerrar recursos
            stmt.close();
            rs.close();
            this.cx.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return registros;
    }private String obtenerValorNoNulo(String valor) {
    return (valor != null) ? valor : ""; // Si es null, retorna un espacio en blanco
    }
    

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Connection getCx() {
        return cx;
    }

    public void setCx(Connection cx) {
        this.cx = cx;
    }
}

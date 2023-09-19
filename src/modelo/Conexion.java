/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author salaz
 */
public class Conexion {
    
    public Connection conexion;
    //jdbc:mysql://localhost:3306/?user=root
    private final String puerto = "3306";
    private final String bd= "db_empresa";
    private final String urlConexion = String.format("jdbc:mysql://localhost:%s/%s?serverTimezone=UTC",puerto, bd);
    private final String usuario = "root";
    private final String contra = "Collect1";
    private final String jdbc = "com.mysql.cj.jdbc.Driver";
    
    public void abirir_Conexion(){
        try{
         Class.forName(jdbc);
        conexion = DriverManager.getConnection(urlConexion,usuario,contra);
        System.out.println("Conexion exitosa");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Error: " + ex.getMessage());
        } 
    }
    
    public void cerrar_Conexion(){
        try{
            conexion.close();
            
        }catch(SQLException ex){
           System.out.println("Error: " + ex.getMessage());
        }
    }
    
}

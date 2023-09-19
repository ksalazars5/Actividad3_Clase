/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet ;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author salaz
 */
public class Cliente extends Persona{
    private int id;
    private String nit;

    Conexion cn;
     public Cliente (){} 
    public Cliente(String nit, int id, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
    super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
    this.id = id;
    this.nit = nit;  
    }
      public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
         cn = new Conexion ();
         cn.abirir_Conexion();
         String query;
         query = "SELECT id_clientes as id,nit,nombres,apellidos,direccion,telefono,fecha_nacimiento FROM clientes;";
         ResultSet consulta = cn.conexion.createStatement().executeQuery(query);
         String encabezado[]=
         { "id","nit","nombres","apellidos","direccion","telefono","fecha_nacimiento"};
         tabla.setColumnIdentifiers(encabezado);
         String datos[] = new String[7];
         while(consulta.next()){
         datos[0] = consulta.getString("id");
         datos[1] = consulta.getString("nit");
         datos[2] = consulta.getString("nombres");
         datos[3] = consulta.getString("apellidos");
         datos[4] = consulta.getString("direccion");
         datos[5] = consulta.getString("telefono");
         datos[6] = consulta.getString("fecha_nacimiento");
         tabla.addRow(datos);
         }
         cn.cerrar_Conexion();
        }catch(SQLException ex){
            System.out.println("error: "+ ex.getMessage() );
        }
        return tabla;
    }
    
    
    @Override
    public void crear(){
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abirir_Conexion();
            String query;
            query= "INSERT INTO clientes (nit,nombres,apellidos,direccion,telefono,fecha_nacimiento) VALUES  (?,?,?,?,?,?)";
            parametro =(PreparedStatement) cn.conexion.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getdireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            int executar = parametro.executeUpdate();
            System.out.println("Se inserto :" + Integer.toString(executar)+ "Registro");
            cn.cerrar_Conexion();
            JOptionPane.showMessageDialog(null,Integer.toString(executar)+ " Registro Agregado ", "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
           System.out.println(ex);

        }
    }
     
    @Override
      public void actualizar(){
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abirir_Conexion();
            String query;
            query= "update clientes set nit=?,nombres=?,apellidos=?,direccion=?,telefono=?,fecha_nacimiento=? where id_clientes=?;";
            parametro =(PreparedStatement) cn.conexion.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getdireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            parametro.setInt(7,getId());
            int executar = parametro.executeUpdate();
            System.out.println("Se Actualizo :" + Integer.toString(executar)+ "Registro");
            cn.cerrar_Conexion();
            JOptionPane.showMessageDialog(null,Integer.toString(executar)+ " Registro Actualizado ", "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE);
                    }catch(HeadlessException | SQLException ex){
            System.out.println("Error: "+ ex.getMessage());

        }
    }
      
    @Override
       public void borrar(){
       try{
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abirir_Conexion();
            String query;
            query= "delete from clientes where id_clientes=?;";
            parametro =(PreparedStatement) cn.conexion.prepareStatement(query);
            parametro.setInt(1,getId());
            int executar = parametro.executeUpdate();
            System.out.println("Se Actualizo :" + Integer.toString(executar)+ "Registro");
            cn.cerrar_Conexion();
            JOptionPane.showMessageDialog(null,Integer.toString(executar)+ " Registro Eliminado ", "Mensaje",
            JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
        System.out.println("Error: "+ ex.getMessage());

        }
    }
  }


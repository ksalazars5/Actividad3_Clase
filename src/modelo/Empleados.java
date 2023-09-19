/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class Empleados extends Persona {
     Conexion cn;
    private String codigo;
    private int id,id_puesto;

    public Empleados() {
        
    }

    public Empleados(String codigo, int id, int id_puesto, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo = codigo;
        this.id = id;
        this.id_puesto = id_puesto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }
    
     @Override
    public DefaultTableModel leer(){
    DefaultTableModel tabla = new DefaultTableModel();
    try{
       cn = new Conexion ();
       cn.abirir_Conexion();
       String query;
       query = "SELECT id_empleados as id ,e.codigo ,e.nombres ,e.apellidos ,e.direccion ,e.telefono ,e.fecha_nacimiento ,concat(p.id_puesto,') ',p.puesto) as puesto FROM  empleados as e inner join puestos as p on e.id_puesto = p.id_puesto ;";
       ResultSet consulta = cn.conexion.createStatement().executeQuery(query);
       String encabezado[] = 
       {"id","Codigo","Nombres","Apellidos","Direccion","Telefono","Nacimiento","Puesto"};
       tabla.setColumnIdentifiers(encabezado);
       String datos[] = new String[8];
       while (consulta.next()){
           datos[0] = consulta.getString("id");
           datos[1] = consulta.getString("codigo");
           datos[2] = consulta.getString("nombres");
           datos[3] = consulta.getString("apellidos");
           datos[4] = consulta.getString("direccion");
           datos[5] = consulta.getString("telefono");
           datos[6] = consulta.getString("fecha_nacimiento");
           datos[7] = consulta.getString("puesto");
           tabla.addRow(datos);
       }
       cn.cerrar_Conexion();
    }catch(SQLException ex){
        System.out.println("Error: " + ex.getMessage() );
    
    }
    
    return tabla;
    }
    public DefaultComboBoxModel leer_puesto(){
    DefaultComboBoxModel  combo = new DefaultComboBoxModel ();
    try{
       cn = new Conexion ();
       cn.abirir_Conexion();
       String query;
       query = "SELECT id_puesto as id,puesto from puestos";
       ResultSet consulta =  cn.conexion.createStatement().executeQuery(query);
       combo.addElement("0) Elija Puesto");
                  while (consulta.next())
                    {            
                      combo.addElement(consulta.getString("id")+") "+consulta.getString("puesto"));
                    }
              cn.cerrar_Conexion();
              
       
    }catch(SQLException ex){
        System.out.println("Error: " + ex.getMessage() );
    }
    return combo;
    }
     @Override
    public void crear(){
      try{
          PreparedStatement parametro;
          cn = new Conexion ();
          cn.abirir_Conexion();
          String query;
          query = "INSERT INTO empleados (codigo,nombres,apellidos,direccion,telefono,fecha_nacimiento,id_puesto) values (?,?,?,?,?,?,?);";
          parametro = (PreparedStatement) cn.conexion.prepareStatement(query);
          parametro.setString(1, getCodigo());
          parametro.setString(2, getNombres());
          parametro.setString(3, getApellidos());
          parametro.setString(4, getdireccion());
          parametro.setString(5, getTelefono());
          parametro.setString(6, getFecha_nacimiento());
          parametro.setInt(7, getId_puesto());
          int executar = parametro.executeUpdate();
          System.out.println(" Se inserto :" + Integer.toString(executar) + " Registro" );
          cn.cerrar_Conexion();
      }catch(SQLException ex){
          System.out.println("Error:" + ex.getMessage());
      
      }
  }  
     @Override
    public void actualizar (){
      try{
          PreparedStatement parametro;
          cn = new Conexion ();
          cn.abirir_Conexion();
          String query;
          query = "update empleados set codigo=?,nombres=?,apellidos=?,direccion=?,telefono=?,fecha_nacimiento=?,id_puesto=? where id_empleados = ?;";
          parametro = (PreparedStatement) cn.conexion.prepareStatement(query);
          parametro.setString(1, getCodigo());
          parametro.setString(2, getNombres());
          parametro.setString(3, getApellidos());
          parametro.setString(4, getdireccion());
          parametro.setString(5, getTelefono());
          parametro.setString(6, getFecha_nacimiento());
          parametro.setInt(7, getId_puesto());
          parametro.setInt(8, getId());
          int executar = parametro.executeUpdate();
          System.out.println(" Se Actualizo :" + Integer.toString(executar) + " Registro" );
          cn.cerrar_Conexion();
      }catch(SQLException ex){
          System.out.println("Error:" + ex.getMessage());
      
      }
  }  
     @Override
    public void borrar (){
      try{
          PreparedStatement parametro;
          cn = new Conexion ();
          cn.abirir_Conexion();
          String query;
          query = "delete from empleados where id_empleados = ?;";
          parametro = (PreparedStatement) cn.conexion.prepareStatement(query);
          parametro.setInt(1, getId());
          int executar = parametro.executeUpdate();
          System.out.println(" Se Elimino :" + Integer.toString(executar) + " Registro" );
          cn.cerrar_Conexion();
      }catch(SQLException ex){
          System.out.println("Error:" + ex.getMessage());
      
      }
  }  
}
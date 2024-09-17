package gm.datos;

import gm.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static gm.conexion.Conexion.getConexion;

//DAO = Data Access Object
public class EstudianteDAO {

    //Metodo para traer todos los estudiantes registrados en la BD
    public List<Estudiante> listar() throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }

        } catch (SQLException e) {
            System.out.println("Ocurrio un error al seleccionar datos: " + e.getMessage());
        } finally {
            try {
                con.close();
                System.out.println("Cerrando la conexion a la BD...");
            } catch (SQLException e) {
                System.out.println("Ocurrio un error al cerrar la conexion a la BD... : " + e.getMessage());
            }
        }

        return estudiantes;
    }

    //Metodo para buscar un estudiante por ID
    public boolean buscarxid(Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();

        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery(); //Se utiliza solamente cuando vamos a leer algo de la BD

            if (rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Ocurrio un error al buscar el estudiante: " + e.getMessage());
        } finally {
            try {
                con.close();
                System.out.println("Cerrando la conexion a la BD...");
            } catch (SQLException e) {
                System.out.println("Ocurrio un error al cerrar la conexion a la BD... : " + e.getMessage());
            }
        }
        return false;
    }

    //Metodo para agregar estudiante a la Base de datos
    public boolean agregar(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();

        String sql = "INSERT INTO estudiante(nombre, apellido, telefono, email) VALUES (?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute(); //Se utiliza execute por que es cuando queremos modificar algo en la BD (insertar)
            return true;
            //System.out.println("Se ha agregado un nuevo estudiante a la BD...");
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error al agregar estudiante: " + e.getMessage());
        } finally {
            try {
                con.close();
                System.out.println("Cerrando la conexion a la BD...");
            } catch (SQLException e) {
                System.out.println("Ocurrio un error al cerrar la conexion a la BD... : " + e.getMessage());
            }
        }

        return false;
    }

    //MEtodo para modificar estudiante
    public boolean modificar(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE estudiante SET nombre = ?, " +
                "apellido = ?, telefono=?, email=? WHERE id_estudiante = ?";

        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute(); //Estamos escribiendo en la BD

            return true;

        } catch (Exception e) {
            System.out.println("No se pudo modifiar el registro: " + estudiante);
        } finally {
            try {
                con.close();
                System.out.println("Cerrando la conexion a la BD...");
            } catch (SQLException e) {
                System.out.println("Ocurrio un error al cerrar la conexion a la BD... : " + e.getMessage());
            }
        }

        return false;
    }

    //Metodo para eliminar estudiante
    public boolean eliminar(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";

        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("No se pudo eliminar el estudiante: " + e.getMessage());
        } finally {
            try {
                con.close();
                System.out.println("Cerrando la conexion a la BD...");
            } catch (SQLException e) {
                System.out.println("Ocurrio un error al cerrar la conexion a la BD... : " + e.getMessage());
            }
        }
        return false;
    }


    //MAIN DE EstudianteDAO para hacer pruebas
    public static void main(String[] args) throws SQLException {
        var estudianteDao = new EstudianteDAO();

        //Agregar estudiante
        /*
        var nuevoestudiante = new Estudiante
                ("Karol", "Leal", "22334455", "karol@mail.com");
        var agregado = estudianteDao.agregar(nuevoestudiante);
        if(agregado){
            System.out.println("Estudiante agregado: " + nuevoestudiante);
        } else {
            System.out.println("No se pudo agregar el estudiante: " + nuevoestudiante);
        }

        System.out.println("\\n\n");

         */

        //Eliminar estudiante (7)
        /*
        var estudianteEliminar = new Estudiante(7);
        var eliminado = estudianteDao.eliminar(estudianteEliminar);
        if (eliminado) {
            System.out.println("Se ha eliminado el estudiante: " + estudianteEliminar);
        } else {
            System.out.println("NO se ha eliminado el registro: " +estudianteEliminar);
        }
        
         */

        //Listar estudiantes
        System.out.println("Realizando la consulta de TODOS los estudiantes en la BD...\n\n");
        System.out.println("Listado de estudiantes: \n\n");
        List<Estudiante> estudiantes = estudianteDao.listar();
        estudiantes.forEach(System.out::println);
        System.out.println("\n\n");

        //Buscar por ID
        /*
        var estudiante1 = new Estudiante(3);
        System.out.println("Estudiante a buscar: " + estudiante1);
        var encontrado = estudianteDao.buscarxid(estudiante1);
        if(encontrado){
            System.out.println("Estudiante encontrado: " + estudiante1);
        } else {
            System.out.println("No se ha encontrado el estudiante: " + estudiante1);
        }

         */

        //Modificar estudiante (3)
        /*
        var estudianteModificar = new Estudiante(3, "Juan Rafael",
                "Rivera S", "88889999", "rafa@mail.com");
        var modificado = estudianteDao.modificar(estudianteModificar);
        if (modificado){
            System.out.println("Se ha actualizado el registro: " + estudianteModificar);
        } else {
            System.out.println("No se ha podido modificar el registro");
        }

         */

    }


}

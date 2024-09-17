package gm.presentacion;

import gm.datos.EstudianteDAO;
import gm.dominio.Estudiante;

import java.sql.SQLException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SistemaEstudiantesApp {
    public static void main(String[] args) {
        var salir = false;
        var con = new Scanner(System.in);

        var estudianteDao = new EstudianteDAO();

        while(!salir){
            try{
                mostrarMenu();
                salir = ejecutarOpciones(con, estudianteDao);
            } catch (Exception e) {
                System.out.println("Ocurrió un error al ejecutar la opción: " + e.getMessage());
            }
        }
        System.out.println("");
    }

    private static void mostrarMenu(){
        System.out.println("""
                **** SISTEMA DE ESTUDIANTES ****
                1. Mostrar los estudiantes
                2. Buscar estudiante por ID
                3. Agregar estudiante
                4. Modificar estudiante
                5. Eliminar estudiante
                6. Salir del App
                Elige una opción: 
                """);
    }

    private static boolean ejecutarOpciones(Scanner con, EstudianteDAO estudianteDao) throws SQLException {
        var opcion = Integer.parseInt(con.nextLine());
        var salir = false;

        //Variables del objeto estudiante
        var nombre = "";
        var apellido = "";
        var telefono = "";
        var email = "";
        var idEstudiante = 0;

        switch (opcion){
            case 1: //Listar estudiantes
                System.out.println("Listado de estudiantes:");
                var estudiantes = estudianteDao.listar();
                estudiantes.forEach(System.out::println);
                System.out.print("\nPresione Enter para continuar...");
                con.nextLine();
                break;
            case 2: //Buscar estudiante por ID
                System.out.println("Buscar estudiante por ID:");
                System.out.println("Ingrese el ID del estudiante: ");
                idEstudiante = Integer.parseInt(con.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDao.buscarxid(estudiante);
                if (encontrado){
                    System.out.println("Estudiante encontrado: " + estudiante);
                } else {
                    System.out.println("No se ha encontrado ningún estudiante: " + estudiante);
                }
                System.out.print("\nPresione Enter para continuar...");
                con.nextLine();
                break;
            case 3: //Agregar estudiante
                System.out.println("Agregar estudiante:");
                System.out.print("Nombre: ");
                nombre = con.nextLine();
                System.out.print("Apellido: ");
                apellido = con.nextLine();
                System.out.print("Telefono: ");
                telefono = con.nextLine();
                System.out.print("Email: ");
                email = con.nextLine();
                var estudianteGuardar = new Estudiante(nombre, apellido, telefono, email);
                var guardado = estudianteDao.agregar(estudianteGuardar);

                if(guardado){
                    System.out.println("Se ha guardado el estudiante: " + estudianteGuardar);
                } else {
                    System.out.println("No se ha podido guardar el estudiante: " + estudianteGuardar);
                }
                System.out.print("\nPresione Enter para continuar...");
                con.nextLine();
                break;
            case 4: //Modificar estudiante
                System.out.println("Modificar estudiante:");
                System.out.print("Ingrese el ID: ");
                idEstudiante = Integer.parseInt(con.nextLine());
                System.out.print("Nombre: ");
                nombre = con.nextLine();
                System.out.print("Apellido: ");
                apellido = con.nextLine();
                System.out.print("Telefono: ");
                telefono = con.nextLine();
                System.out.print("Email: ");
                email = con.nextLine();

                var estudianteModificar = new Estudiante(idEstudiante, nombre, apellido, telefono, email);
                var modificado = estudianteDao.modificar(estudianteModificar);

                if(modificado){
                    System.out.println("Estudiante modificado: " + estudianteModificar);
                } else {
                    System.out.println("No se pudo modificar el estudiante: " + estudianteModificar);
                }
                System.out.print("\nPresione Enter para continuar...");
                con.nextLine();
                break;
            case 5: //Eliminar estudiante
                System.out.println("Eliminar estudiante: ");
                System.out.print("ID estudiante: ");
                idEstudiante = Integer.parseInt(con.nextLine());

                var estudianteEliminar = new Estudiante(idEstudiante);
                var eliminado = estudianteDao.eliminar(estudianteEliminar);

                if (eliminado) {
                    System.out.println("Se ha eliminado el estudiante: " + estudianteEliminar );
                } else {
                    System.out.println("No se ha podido eliminar: " + estudianteEliminar);
                }
                System.out.print("\nPresione Enter para continuar...");
                con.nextLine();
                break;
            case 6: //Salir
                System.out.println("Hasta pronto..!!");
                salir = true;
            default:
                System.out.print("No se reconoce esa opción...\nPresione Enter para continuar...");
                con.nextLine();


        }
        return salir;
    }
}
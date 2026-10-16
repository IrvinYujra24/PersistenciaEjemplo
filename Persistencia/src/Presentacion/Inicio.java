package presentacion;

import java.util.List;

import Datos.GestorBinario;
import Datos.GestorTexto;
import logica.Estudiante;

public class Inicio {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   PRUEBA 1: PERSISTENCIA EN TEXTO (.txt) ");
        System.out.println("==========================================");
        GestorTexto gestorTxt = new GestorTexto("estudiantes.txt");

        // 1. Creación e Inserción
        gestorTxt.insertar(new Estudiante(101, "Ana Gomez", 85.5));
        gestorTxt.insertar(new Estudiante(102, "Luis Perez", 72.0));
        gestorTxt.insertar(new Estudiante(103, "Carla Diaz", 91.2));

        // 2. Lectura inicial
        System.out.println("\n--- Lista actual en Texto ---");
        mostrarLista(gestorTxt.leerTodos());

        // 3. Edición (Subimos la nota a Luis Perez, ID 102)
        System.out.println("\n--- Editando registro ---");
        gestorTxt.editarPromedio(102, 88.0);
        mostrarLista(gestorTxt.leerTodos());

        // 4. Borrado del archivo
        System.out.println("\n--- Borrando archivo ---");
        gestorTxt.borrarArchivo();


        System.out.println("\n==========================================");
        System.out.println("  PRUEBA 2: PERSISTENCIA EN BINARIO (.dat)");
        System.out.println("==========================================");
        GestorBinario gestorBin = new GestorBinario("estudiantes.dat");

        // 1. Creación e Inserción
        gestorBin.insertar(new Estudiante(201, "Pedro Rojas", 65.0));
        gestorBin.insertar(new Estudiante(202, "Sofia Castro", 95.5));

        // 2. Lectura inicial
        System.out.println("\n--- Lista actual en Binario ---");
        mostrarLista(gestorBin.leerTodos());

        // 3. Edición (Subimos la nota a Pedro Rojas, ID 201)
        System.out.println("\n--- Editando registro ---");
        gestorBin.editarPromedio(201, 79.5);
        mostrarLista(gestorBin.leerTodos());

        // 4. Borrado del archivo
        System.out.println("\n--- Borrando archivo ---");
        gestorBin.borrarArchivo();
    }

    private static void mostrarLista(List<Estudiante> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay registros disponibles.");
        } else {
            for (Estudiante e : lista) {
                System.out.println(e);
            }
        }
    }
}

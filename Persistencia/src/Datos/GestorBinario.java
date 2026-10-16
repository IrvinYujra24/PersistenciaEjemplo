package Datos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import logica.Estudiante;

public class GestorBinario {
    private final String rutaArchivo;

    public GestorBinario(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    // Método auxiliar privado para guardar la lista completa en disco
    private void guardarLista(List<Estudiante> lista) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            salida.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Error al guardar en archivo binario: " + e.getMessage());
        }
    }

    // 1. CREACIÓN E INSERCIÓN DE DATOS
    public void insertar(Estudiante nuevoEstudiante) {
        List<Estudiante> lista = leerTodos(); // Recuperamos los existentes
        lista.add(nuevoEstudiante);           // Añadimos el nuevo a la lista
        guardarLista(lista);                  // Guardamos el bloque completo
        System.out.println("[BINARIO] Estudiante serializado: " + nuevoEstudiante.getNombre());
    }

    // 2. LECTURA DE CONTENIDO
    @SuppressWarnings("unchecked")
    public List<Estudiante> leerTodos() {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            // Deserializamos el objeto y hacemos el casting de vuelta a List<Estudiante>
            return (List<Estudiante>) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer el archivo binario: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // 3. EDICIÓN DE CONTENIDO
    public void editarPromedio(int idBuscado, double nuevoPromedio) {
        List<Estudiante> lista = leerTodos();
        boolean encontrado = false;

        for (Estudiante est : lista) {
            if (est.getId() == idBuscado) {
                est.setPromedio(nuevoPromedio);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            guardarLista(lista);
            System.out.println("[BINARIO] Promedio actualizado para el ID: " + idBuscado);
        } else {
            System.out.println("[BINARIO] No se encontró el estudiante con ID: " + idBuscado);
        }
    }

    // 4. BORRADO DEL ARCHIVO FÍSICO
    public void borrarArchivo() {
        File archivo = new File(rutaArchivo);
        if (archivo.exists() && archivo.delete()) {
            System.out.println("[BINARIO] Archivo '" + rutaArchivo + "' eliminado correctamente.");
        } else {
            System.out.println("[BINARIO] No se pudo eliminar el archivo o no existe.");
        }
    }
}
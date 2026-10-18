package Datos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Logica.Estudiante;

public class GestorTexto {
    private final String rutaArchivo;

    public GestorTexto(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    // 1. CREACIÓN E INSERCIÓN DE DATOS
    public void insertar(Estudiante estudiante) {
        // El segundo parámetro 'true' en FileWriter activa el modo "append" (añadir al final sin borrar lo anterior)
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            escritor.write(estudiante.toFormatoTexto());
            escritor.newLine(); // Salto de línea
            System.out.println("[TEXTO] Estudiante guardado: " + estudiante.getNombre());
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de texto: " + e.getMessage());
        }
    }

    // 2. LECTURA DE CONTENIDO
    public List<Estudiante> leerTodos() {
        List<Estudiante> lista = new ArrayList<>();
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            return lista; // Si no existe, retornamos la lista vacía sin lanzar error
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lista.add(Estudiante.desdeFormatoTexto(linea));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de texto: " + e.getMessage());
        }
        return lista;
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
            // Sobrescribimos el archivo completo ('false' en FileWriter borra el contenido previo)
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo, false))) {
                for (Estudiante est : lista) {
                    escritor.write(est.toFormatoTexto());
                    escritor.newLine();
                }
                System.out.println("[TEXTO] Promedio actualizado para el ID: " + idBuscado);
            } catch (IOException e) {
                System.err.println("Error al actualizar el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("[TEXTO] No se encontró el estudiante con ID: " + idBuscado);
        }
    }

    // 4. BORRADO DEL ARCHIVO FÍSICO
    public void borrarArchivo() {
        File archivo = new File(rutaArchivo);
        if (archivo.exists()) {
            if (archivo.delete()) {
                System.out.println("[TEXTO] Archivo '" + rutaArchivo + "' eliminado correctamente.");
            } else {
                System.err.println("[TEXTO] No se pudo eliminar el archivo.");
            }
        } else {
            System.out.println("[TEXTO] El archivo no existe.");
        }
    }
}

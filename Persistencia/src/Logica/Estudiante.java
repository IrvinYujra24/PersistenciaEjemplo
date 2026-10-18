package Logica;

import java.io.Serializable;

public class Estudiante implements Serializable {
    // Identificador de versión para asegurar compatibilidad al deserializar
    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private double promedio;

    

    public Estudiante() {
	}

	public Estudiante(int id, String nombre, double promedio) {
		this.id = id;
		this.nombre = nombre;
		this.promedio = promedio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPromedio() {
		return promedio;
	}

	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// Convierte el objeto a formato CSV para el archivo de texto
    public String toFormatoTexto() {
        return id + ";" + nombre + ";" + promedio;
    }

    // Reconstruye un objeto Estudiante a partir de una línea de texto
    public static Estudiante desdeFormatoTexto(String linea) {
        String[] partes = linea.split(";");
        int id = Integer.parseInt(partes[0]);
        String nombre = partes[1];
        double promedio = Double.parseDouble(partes[2]);
        return new Estudiante(id, nombre, promedio);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %-15s | Promedio: %.2f", id, nombre, promedio);
    }
    
    public void mostrar() {
    	System.out.print(String.format("ID: %d | Nombre: %-15s | Promedio: %.2f", id, nombre, promedio));
    }
}
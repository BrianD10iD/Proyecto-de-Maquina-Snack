package maquina_snacks_archivos.dominio;

import java.io.Serializable;
import java.util.Objects;

public class Snack implements Serializable {
    private static int contadorSnacks = 0;
    private int idSnack;
    private String nombre;
    private double precio;

    public Snack(){
        this.idSnack = Snack.contadorSnacks++;
    }

    public Snack(String nombre, double precio){
        this(); //debe ser la primera linea la llamada al constructor vacio
        this.nombre = nombre;
        this.precio = precio;
    }

    public static int getContadorSnacks() {
        return contadorSnacks;
    }

    public int getIdSnack() {
        return idSnack;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString(){
        return "Snack[ IdSnack : " + idSnack + ", nombre : " + nombre +
                ", precio : " + precio + "]";
    }

    public String escribirSnack(){
        return idSnack + ", " + nombre + ", " + precio;
    }

    @Override
    public boolean equals(Object o) { //comparacion de contenido
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return idSnack == snack.idSnack && Double.compare(precio, snack.precio) == 0 && Objects.equals(nombre, snack.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSnack, nombre, precio);
    }
}

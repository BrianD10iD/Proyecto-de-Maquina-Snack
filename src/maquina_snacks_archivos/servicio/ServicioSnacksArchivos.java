package maquina_snacks_archivos.servicio;

import maquina_snacks_archivos.dominio.Snack;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServicioSnacksArchivos implements IServicioSnacks{
    private final String nombre_archivo = "snacks.txt";

    //vamos a crear la lista de snacks
    private List<Snack> snacks = new ArrayList<>();

    public ServicioSnacksArchivos(){
        //Creamos el archivo si no existe
        var archivo = new File(nombre_archivo);
        var existe = false;

        try{
            existe = archivo.exists();

            if(existe){
               this.snacks = obtenerSnacks();
            }else{ //Creamos el archivo
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se ha creado el archivo");
            }
        }catch(Exception e){
            System.out.println("error al crear un archivo = " + e.getMessage());
        }

        //Si no existe, cargamos algunos snacks iniciales
        if(!existe){
            cargarSnacksIniciales();
        }
    }

    private void cargarSnacksIniciales(){
        this.agregarSnack(new Snack("Chocolate",2.50));
        this.agregarSnack(new Snack("Sanwich",40));
        this.agregarSnack(new Snack("Refresco",40));
    }

    private List<Snack> obtenerSnacks(){
        List<Snack> snacks = new ArrayList<>();

        try{
            List<String> lineas = Files.readAllLines(Paths.get(nombre_archivo)); //Cargando el archivo y leyendo el archivo

            for(String linea: lineas){
                String[] lineaSnack = linea.split(",");
                var idSnack = lineaSnack[0];
                var nombre = lineaSnack[1];
                var precio = Double.parseDouble(lineaSnack[2]);
                Snack snack = new Snack(nombre, precio);

                snacks.add(snack); //Agregamos el snack leido a la lista
            }
        }catch(Exception e){
            System.out.println("Error al leer archivo de snacks = " + e.getMessage());
            e.printStackTrace();
        }
        return snacks;
    }

    @Override
    public void agregarSnack(Snack snack) {
        //Agregamos el nuevo snack,
        // 1. a la lista en la memoria
        this.snacks.add(snack);
        //2. Guardamos el nuevo snack en el archivo
        this.agregarSnackArchivo(snack);
    }

    private void agregarSnackArchivo(Snack snack){
        boolean anexar = false;
        var archivo = new File(nombre_archivo);

        try{
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(snack.escribirSnack());
            salida.close(); //Se escribe la informacion en el archivo
        }catch(Exception e){
            System.out.println("error al agregar un snack = " + e.getMessage());
        }
    }

    @Override
    public void mostrarSnacks() {
        System.out.println("----Snacks en el inventario------");
        var inventarioSnacks = "";

        for(var snack: this.snacks){
            inventarioSnacks += snack.toString() + "\n";
        }
        System.out.println(inventarioSnacks);
    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }
}

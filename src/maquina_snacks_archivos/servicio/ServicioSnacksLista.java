package maquina_snacks_archivos.servicio;

import maquina_snacks_archivos.dominio.Snack;

import java.util.ArrayList;
import java.util.List;

public class ServicioSnacksLista implements IServicioSnacks{
    private static final List<Snack> snacks;

    //Bloque de tipo estatico inicializador   [el constructor es para crear objetos]

    static{
        snacks = new ArrayList<>(); //ya no podemos asignar un lista distinta
        snacks.add(new Snack("Papas",50));
        snacks.add(new Snack("Refresco",70));
        snacks.add(new Snack("Papas",50));
        snacks.add(new Snack("Papas",50));
    }

    public void agregarSnack(Snack snack){
        snacks.add(snack);
    }

    public void mostrarSnacks(){
        var inventarioSnacks = "";
        for(var snack: snacks){
            inventarioSnacks += snack.toString() + "\n";
        }
        System.out.println("-----Snacks en el inventario-------");
        System.out.println(inventarioSnacks);
    }

    public List<Snack> getSnacks(){
        return snacks;
    }
}

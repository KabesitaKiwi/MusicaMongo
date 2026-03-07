package musicote.main;

import musicote.gui.Controlador;
import musicote.gui.Modelo;
import musicote.gui.Vista;

public class Main {
    public static void main(String[] args)  {
        new Controlador(new Modelo(), new Vista());
    }
}

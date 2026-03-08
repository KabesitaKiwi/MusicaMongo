package musicote.main;

import com.formdev.flatlaf.FlatLightLaf;
import musicote.gui.Controlador;
import musicote.gui.Modelo;
import musicote.gui.Vista;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());

            Color pink = Color.decode("#FF4D8D");
            Color pinkSoft = Color.decode("#FFB3CD");
            Color bg = Color.decode("#FFF0F6");
            Color header = Color.decode("#FFE1EE");
            Color textDark = Color.decode("#4A0030");

            UIManager.put("Panel.background", bg);

            UIManager.put("Component.arc", 14);
            UIManager.put("Button.arc", 14);
            UIManager.put("TextComponent.arc", 14);

            UIManager.put("Component.focusColor", pink);
            UIManager.put("Component.borderColor", pinkSoft);

            UIManager.put("Button.background", pink);
            UIManager.put("Button.foreground", Color.WHITE);

            UIManager.put("TextField.selectionBackground", pinkSoft);
            UIManager.put("TextField.selectionForeground", Color.BLACK);

            UIManager.put("Table.selectionBackground", pinkSoft);
            UIManager.put("Table.selectionForeground", Color.BLACK);

            UIManager.put("TableHeader.background", header);
            UIManager.put("TableHeader.foreground", textDark);

        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            Modelo modelo = new Modelo();
            Vista vista = new Vista();
            Controlador controlador = new Controlador(modelo, vista);
        });
    }
}


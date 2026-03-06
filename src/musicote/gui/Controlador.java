package musicote.gui;

import musicote.base.Album;
import musicote.base.Autor;
import musicote.base.Cancion;
import musicote.util.Util;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controlador implements ActionListener, KeyListener, ListSelectionListener {
    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista){
        this.vista = vista;
        this.modelo = modelo;

        addActionListeners(this);
        addKeyListeners(this);
        addListSelectionListeners(this);

        try {
            modelo.conectar();
            vista.itemConectar.setText("Desconectar");
            vista.setTitle("Bar Manolo - <CONECTADO>");
            setBotonesActivados(true);
            listarAlbumes();
            listarAutores();
            listarCanciones();
        } catch (Exception ex) {
            Util.mostrarMensajeError("Imposible establecer conexión con el servidor.");
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "conexion":
                try {
                    if (modelo.getCliente() == null) {
                        modelo.conectar();
                        vista.itemConectar.setText("Desconectar");
                        vista.setTitle("Musica - <CONECTADO>");
                        setBotonesActivados(true);
                        listarCanciones();
                        listarAlbumes();
                        listarAutores();
                    } else {
                        modelo.desconectar();
                        vista.itemConectar.setText("Conectar");
                        vista.setTitle("Musica - <SIN CONEXION>");
                        setBotonesActivados(false);
                        vista.dlmAlbum.clear();
                        vista.dlmAutor.clear();
                        vista.dlmCaciones.clear();
                        limpiarCamposCanciones();
                        limpiarCamposAlbumes();
                        limpiarCamposArtistas();
                    }
                } catch (Exception ex) {
                    Util.mostrarMensajeError("Imposible establecer conexión con el servidor.");
                }
                break;
            case "salir":
                modelo.desconectar();
                System.exit(0);
                break;

        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {

    }

    private void addActionListeners(ActionListener listener){
        vista.botonAnadirCancion.addActionListener(listener);
        vista.botonAnadirAlbum.addActionListener(listener);
        vista.botonAnadirAutor.addActionListener(listener);
        vista.botonActualizarCancion.addActionListener(listener);
        vista.botonModificarAAlbum.addActionListener(listener);
        vista.botonModificarAutor.addActionListener(listener);
        vista.botonEliminarAlbum.addActionListener(listener);
        vista.botonEliminarCancion.addActionListener(listener);
        vista.botonEliminarAutor.addActionListener(listener);

        vista.itemConectar.addActionListener(listener);
        vista.itemSalir.addActionListener(listener);

    }

    private void addKeyListeners(KeyListener listener){
        vista.campoBuscarAlbum.addKeyListener(listener);
        vista.campoBuscarArtista.addKeyListener(listener);
        vista.campoBuscarCancion.addKeyListener(listener);
    }

    private void addListSelectionListeners(Controlador listener) {
        vista.listCancion.addListSelectionListener(listener);
        vista.listAlbum.addListSelectionListener(listener);
        vista.listArtista.addListSelectionListener(listener);
    }

    private void setBotonesActivados(boolean activados){
        vista.botonAnadirCancion.setEnabled(activados);
        vista.botonAnadirAlbum.setEnabled(activados);
        vista.botonAnadirAutor.setEnabled(activados);
        vista.botonActualizarCancion.setEnabled(activados);
        vista.botonModificarAAlbum.setEnabled(activados);
        vista.botonModificarAutor.setEnabled(activados);
        vista.botonEliminarAlbum.setEnabled(activados);
        vista.botonEliminarCancion.setEnabled(activados);
        vista.botonEliminarAutor.setEnabled(activados);
    }

    private void listarAutores() {
        vista.dlmAutor.clear();
        for (Autor autor : modelo.getAutor()) {
            vista.dlmAutor.addElement(autor);
        }
    }

    private void listarCanciones() {
        vista.dlmCaciones.clear();
        for (Cancion cancion : modelo.getCancion()) {
            vista.dlmCaciones.addElement(cancion);
        }
    }

    private void listarAlbumes() {
        vista.dlmAlbum.clear();
        for (Album album : modelo.getAlbum()) {
            vista.dlmAlbum.addElement(album);
        }
    }

    private void limpiarCamposCanciones(){
        vista.campoTituloCancion.setText("");
        vista.campoBuscarCancion.setText("");
        vista.comboAlbum.setSelectedIndex(0);
        vista.comboAutor.setSelectedIndex(0);
    }

    private void limpiarCamposAlbumes(){
        vista.campoTituloAlbum.setText("");
        vista.campoBuscarAlbum.setText("");
        vista.campoDuracion.setValue(1);
        vista.campoFechaLanzamiento.setText("");
    }

    private void limpiarCamposArtistas(){
        vista.campoNombreAutor.setText("");
        vista.campoNombreArtistico.setText("");
        vista.campoEdad.setValue(1);
    }



}

package musicote.gui;

import musicote.base.Album;
import musicote.base.Autor;
import musicote.base.Cancion;
import musicote.util.Util;
import org.bson.types.ObjectId;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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
            vista.setTitle("Musica - <CONECTADO>");
            setBotonesActivados(true);

            listarAlbumes();
            listarAutores();
            listarComboAlbum();
            listarComboAutor();
            listarCanciones();

            vista.listCancion.updateUI();
        } catch (Exception ex) {
            ex.printStackTrace();
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
                        listarComboAlbum();
                        listarComboAutor();
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
            case "addCancion":
                anadirCancion();
                break;
            case "modCancion":
                modificarCancion();
                break;
            case "delCancion":
                eliminarCancion();
                break;
            case "addAlbum":
                anadirAlbum();
                break;
            case "modAlbum":
                modificarAlbum();
                break;
            case "delAlbum":
                eliminarAlbum();
                break;
            case"addAutor":
                anadirAutor();
                break;
            case "modAutor":
                modificarAutor();
                break;
            case "delAutor":
                eliminarAutor();
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
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vista.campoBuscarArtista) {
            if (vista.campoBuscarArtista.getText().isEmpty()) {
                vista.dlmBuscarAutor.clear();
            } else {
                listarAutoresBusqueda(modelo.getAutor(vista.campoBuscarArtista.getText()));
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == vista.listCancion) {
            if (vista.listCancion.getSelectedValue() != null) {
                Cancion cancion = vista.listCancion.getSelectedValue();
                vista.campoTituloCancion.setText(cancion.getTitulo());

                for (int i = 0; i < vista.comboAlbum.getItemCount(); i++) {
                    AlbumItem item = (AlbumItem) vista.comboAlbum.getItemAt(i);
                    if (item.getAlbum().getId().equals(cancion.getIdAlbum())) {
                        vista.comboAlbum.setSelectedIndex(i);
                        break;
                    }
                }

                for (int i = 0; i < vista.comboAutor.getItemCount(); i++) {
                    ArtistaItem item = (ArtistaItem) vista.comboAutor.getItemAt(i);
                    if (item.getArtista().getId().equals(cancion.getIdAutor())) {
                        vista.comboAutor.setSelectedIndex(i);
                        break;
                    }
                }
            }

        } else if (e.getSource() == vista.listAlbum) {
            if (vista.listAlbum.getSelectedValue() != null) {
                Album album = vista.listAlbum.getSelectedValue();
                vista.campoTituloAlbum.setText(album.getTitulo());
                vista.campoFechaLanzamiento.setDate(album.getFechaEstreno());
                vista.campoDuracion.setValue(album.getDuracion());
            }

        } else if (e.getSource() == vista.listArtista) {
            if (vista.listArtista.getSelectedValue() != null) {
                Autor autor = vista.listArtista.getSelectedValue();
                vista.campoNombreAutor.setText(autor.getNombreReal());
                vista.campoNombreArtistico.setText(autor.getNombreArtistico());
                vista.campoEdad.setValue(autor.getEdad());
            }
        }
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
        vista.campoBuscarArtista.addKeyListener(listener);
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
            cancion.setAutor(null);
            cancion.setAlbum(null);

            for (Album album : modelo.getAlbum()) {
                if (album.getId().equals(cancion.getIdAlbum())) {
                    cancion.setAlbum(album);
                    break;
                }
            }

            for (Autor autor : modelo.getAutor()) {
                if (autor.getId().equals(cancion.getIdAutor())) {
                    cancion.setAutor(autor);
                    break;
                }
            }

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
        vista.comboAlbum.setSelectedIndex(0);
        vista.comboAutor.setSelectedIndex(0);
    }

    private void limpiarCamposAlbumes(){
        vista.campoTituloAlbum.setText("");
        vista.campoDuracion.setValue(1);
        vista.campoFechaLanzamiento.setText("");
    }

    private void limpiarCamposArtistas(){
        vista.campoNombreAutor.setText("");
        vista.campoNombreArtistico.setText("");
        vista.campoEdad.setValue(1);
    }

    public void anadirCancion(){
        if (!Util.comprobarCampoVacio(vista.campoTituloCancion)){
            Util.lanzaAlertaVacio(vista.campoTituloCancion);
        }else {
            AlbumItem idAlbum = (AlbumItem) vista.comboAlbum.getSelectedItem();
            ArtistaItem idAutor = (ArtistaItem) vista.comboAutor.getSelectedItem();
            Cancion cancion = new Cancion(
                    vista.campoTituloCancion.getText(),
                    idAlbum.getAlbum().getId(),
                    idAutor.getArtista().getId()
                    );

            cancion.setAlbum(idAlbum.getAlbum());
            cancion.setAutor(idAutor.getArtista());

            modelo.guardarObjeto(cancion);
            limpiarCamposCanciones();
        }
        listarCanciones();
    }

    public void modificarCancion(){
        if (!Util.comprobarCampoVacio(vista.campoTituloCancion)){
            Util.lanzaAlertaVacio(vista.campoTituloCancion);
        }else {
            AlbumItem idAlbum = (AlbumItem) vista.comboAlbum.getSelectedItem();
            ArtistaItem idAutor = (ArtistaItem) vista.comboAutor.getSelectedItem();
            Cancion cancion = vista.listCancion.getSelectedValue();
            cancion.setTitulo(vista.campoTituloCancion.getText());
            cancion.setIdAlbum(idAlbum.getAlbum().getId());
            cancion.setIdAutor(idAutor.getArtista().getId());

            cancion.setAlbum(idAlbum.getAlbum());
            cancion.setAutor(idAutor.getArtista());

            modelo.modificarObjeto(cancion);
            limpiarCamposCanciones();
        }
        listarCanciones();
    }

    public void eliminarCancion(){
        if (vista.listCancion.getSelectedValue()!=null){
            modelo.eliminarObjeto(vista.listCancion.getSelectedValue());
            listarCanciones();
            limpiarCamposCanciones();
        }else{
            JOptionPane.showMessageDialog(null, "No hay ningun elemento seleccionado");
        }
    }

    public void anadirAutor(){
        if (!Util.comprobarCampoVacio(vista.campoNombreAutor)){
            Util.lanzaAlertaVacio(vista.campoNombreAutor);
        } else if (!Util.comprobarCampoVacio(vista.campoNombreArtistico)){
            Util.lanzaAlertaVacio(vista.campoNombreArtistico);
        } else {
            Autor autor = new Autor(
                    vista.campoNombreAutor.getText(),
                    vista.campoNombreArtistico.getText(),
                    (int) vista.campoEdad.getValue()
            );

            modelo.guardarObjeto(autor);
            limpiarCamposArtistas();
        }

        listarAutores();
        listarComboAutor();
    }

    public void modificarAutor(){
        if (!Util.comprobarCampoVacio(vista.campoNombreAutor)){
            Util.lanzaAlertaVacio(vista.campoNombreAutor);
        } else if (!Util.comprobarCampoVacio(vista.campoNombreArtistico)){
            Util.lanzaAlertaVacio(vista.campoNombreArtistico);
        } else {
            Autor autor = vista.listArtista.getSelectedValue();

            if (autor != null) {
                autor.setNombreReal(vista.campoNombreAutor.getText());
                autor.setNombreArtistico(vista.campoNombreArtistico.getText());
                autor.setEdad((int) vista.campoEdad.getValue());

                modelo.modificarObjeto(autor);
                limpiarCamposArtistas();
            } else {
                JOptionPane.showMessageDialog(null, "No hay ningun autor seleccionado");
            }
        }

        listarAutores();
        listarComboAutor();
    }

    public void eliminarAutor(){
        if (vista.listArtista.getSelectedValue() != null){
            modelo.eliminarObjeto(vista.listArtista.getSelectedValue());
            listarAutores();
            listarComboAutor();
            limpiarCamposArtistas();
        } else {
            JOptionPane.showMessageDialog(null, "No hay ningun autor seleccionado");
        }
    }

    public void anadirAlbum(){
        if (!Util.comprobarCampoVacio(vista.campoTituloAlbum)){
            Util.lanzaAlertaVacio(vista.campoTituloAlbum);
        } else {
            Album album = new Album(
                    vista.campoTituloAlbum.getText(),
                    vista.campoFechaLanzamiento.getDate(),
                    Double.parseDouble(vista.campoDuracion.getValue().toString())
            );

            modelo.guardarObjeto(album);
            limpiarCamposAlbumes();
        }

        listarAlbumes();
        listarComboAlbum();
    }

    public void modificarAlbum(){
        if (!Util.comprobarCampoVacio(vista.campoTituloAlbum)){
            Util.lanzaAlertaVacio(vista.campoTituloAlbum);
        } else {
            Album album = vista.listAlbum.getSelectedValue();

            if (album != null) {
                album.setTitulo(vista.campoTituloAlbum.getText());
                album.setFechaEstreno(vista.campoFechaLanzamiento.getDate());
                album.setDuracion(Double.parseDouble(vista.campoDuracion.getValue().toString()));

                modelo.modificarObjeto(album);
                limpiarCamposAlbumes();
            } else {
                JOptionPane.showMessageDialog(null, "No hay ningun album seleccionado");
            }
        }

        listarAlbumes();
        listarComboAlbum();
    }

    public void eliminarAlbum(){
        if (vista.listAlbum.getSelectedValue() != null){
            modelo.eliminarObjeto(vista.listAlbum.getSelectedValue());
            listarAlbumes();
            listarComboAlbum();
            limpiarCamposAlbumes();
        } else {
            JOptionPane.showMessageDialog(null, "No hay ningun album seleccionado");
        }
    }



    public class AlbumItem {
        private Album album;

        public AlbumItem(Album album) {
            this.album = album;
        }

        public Album getAlbum() {
            return album;
        }

        @Override
        public String toString() {
            return album.getTitulo();
        }
    }

    public class ArtistaItem {
        private Autor artista;

        public ArtistaItem(Autor artista) {
            this.artista = artista;
        }

        public Autor getArtista() {
            return artista;
        }

        @Override
        public String toString() {
            return artista.getNombreArtistico();
        }
    }

    private void listarComboAlbum() {
        vista.comboAlbum.removeAllItems();

        for (Album album : modelo.getAlbum()) {
            vista.comboAlbum.addItem(new AlbumItem(album));
        }
    }

    private void listarComboAutor() {
        vista.comboAutor.removeAllItems();

        for (Autor autor : modelo.getAutor()) {
            vista.comboAutor.addItem(new ArtistaItem(autor));
        }
    }


    private void listarAutoresBusqueda(ArrayList<Autor> lista) {
        vista.dlmBuscarAutor.clear();

        if (lista == null) return;

        for (Autor autor : lista) {
            if (autor != null) {
                vista.dlmBuscarAutor.addElement(autor);
            }
        }
    }





}

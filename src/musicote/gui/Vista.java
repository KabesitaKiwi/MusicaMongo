package musicote.gui;

import com.github.lgooddatepicker.components.DatePicker;
import musicote.base.Album;
import musicote.base.Autor;
import musicote.base.Cancion;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class Vista extends JFrame {
    private JPanel panelPrincipal;
    // Canciones
    private JTextField campoTituloCancion;
    private JList<Cancion> listCancion;
    private JButton botonAnadirCancion;
    private JButton botonActualizarCancion;
    private JButton botonEliminarCancion;
    private JTextField campoBuscarCancion;
    private JList<Cancion> listBuscarCancion;
    private JComboBox comboAlbum;
    private JComboBox comboAutor;
    private JSpinner campoDuracion;

    // Autores
    private JTextField campoNombreAutor;
    private JTextField campoNombreArtistico;
    private JList<Autor> listArtista;
    private JButton botonEliminarAutor;
    private JButton botonAnadirAutor;
    private JButton botonModificarAutor;
    private JTextField campoBuscarArtista;
    private JList<Autor> listBuscarAutor;

    // Álbumes
    private JSpinner spinner1;
    private JList<Album> listAlbum;
    private JButton botonEliminarAlbum;
    private JButton botonModificarAAlbum;
    private JTextField campoTituloAlbum;
    private JTextField campoBuscarAlbum;
    private JList<Album> listBusquedaAlbum;
    private JButton botonAnadirAlbum;
    private DatePicker campoFechaLanzamiento;

    //modelos
    DefaultListModel<Cancion> dlmCaciones;
    DefaultListModel<Cancion> dlmBuscarCanciones;
    DefaultListModel<Autor> dlmAutor;
    DefaultListModel<Autor> dlmBuscarAutor;
    DefaultListModel<Album> dlmAlbum;
    DefaultListModel<Album> dlmBuscarAlbum;

    //menu
    JMenuItem itemConectar;
    JMenuItem itemSalir;

    public Vista(){
        setTitle("Autores - <SIN CONEXION>");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 650));
        setResizable(false);
        pack();
        setVisible(true);

        inicializarModelos();
        inicializarMenu();
    }

    private void inicializarModelos() {
        dlmCaciones = new DefaultListModel<>();
        listCancion.setModel(dlmCaciones);
        dlmAutor = new DefaultListModel<>();
        listArtista.setModel(dlmAutor);
        dlmAlbum = new DefaultListModel<>();
        listAlbum.setModel(dlmAlbum);
        dlmBuscarCanciones = new DefaultListModel<>();
        listBuscarCancion.setModel(dlmBuscarCanciones);
        dlmBuscarAutor = new DefaultListModel<>();
        listBuscarAutor.setModel(dlmBuscarAutor);
        dlmBuscarAlbum = new DefaultListModel<>();
        listBusquedaAlbum.setModel(dlmBuscarAlbum);
    }

    private void inicializarMenu() {
        itemConectar = new JMenuItem("Conectar");
        itemConectar.setActionCommand("conexion");
        itemSalir = new JMenuItem("Salir");
        itemSalir.setActionCommand("salir");

        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.add(itemConectar);
        menuArchivo.add(itemSalir);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuArchivo);

        setJMenuBar(menuBar);
    }

}

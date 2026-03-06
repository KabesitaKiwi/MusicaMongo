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
    public JTextField campoTituloCancion;
    public JList<Cancion> listCancion;
    public JButton botonAnadirCancion;
    public JButton botonActualizarCancion;
    public JButton botonEliminarCancion;
    public JTextField campoBuscarCancion;
    public JList<Cancion> listBuscarCancion;
    public JComboBox comboAlbum;
    public JComboBox comboAutor;
    public JSpinner campoDuracion;

    // Autores
    public JTextField campoNombreAutor;
    public JTextField campoNombreArtistico;
    public JList<Autor> listArtista;
    public JButton botonEliminarAutor;
    public JButton botonAnadirAutor;
    public JButton botonModificarAutor;
    public JTextField campoBuscarArtista;
    public JList<Autor> listBuscarAutor;

    // Álbumes
    public JSpinner campoEdad;
    public JList<Album> listAlbum;
    public JButton botonEliminarAlbum;
    public JButton botonModificarAAlbum;
    public JTextField campoTituloAlbum;
    public JTextField campoBuscarAlbum;
    public JList<Album> listBusquedaAlbum;
    public JButton botonAnadirAlbum;
    public DatePicker campoFechaLanzamiento;

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

    public Vista() {
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

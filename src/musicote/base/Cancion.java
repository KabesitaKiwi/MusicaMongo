package musicote.base;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class Cancion {
    private ObjectId id;
    private String titulo;
    private ObjectId idAlbum;
    private ObjectId idAutor;

    private Album album;
    private Autor autor;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Cancion(String titulo, ObjectId idAlbum, ObjectId idAutor) {
        this.titulo = titulo;
        this.idAlbum = idAlbum;
        this.idAutor = idAutor;
    }

    public Cancion() {

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ObjectId getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(ObjectId idAlbum) {
        this.idAlbum = idAlbum;
    }

    public ObjectId getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(ObjectId idAutor) {
        this.idAutor = idAutor;
    }


    @Override
    public String toString() {
        String tituloAlbum = (album != null) ? album.getTitulo() : "Sin álbum";
        String nombreAutor = (autor != null) ? autor.getNombreArtistico() : "Sin autor";

        return "Cancion{" +
                "titulo='" + titulo + '\'' +
                ", album='" + tituloAlbum + '\'' +
                ", autor='" + nombreAutor + '\'' +
                '}';
    }
}

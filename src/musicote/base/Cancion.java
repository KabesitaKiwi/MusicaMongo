package musicote.base;

import org.bson.types.ObjectId;

public class Cancion {
    private ObjectId id;
    private String titulo;
    private ObjectId idAlbum;
    private ObjectId idAutor;

    public Cancion(ObjectId id, String titulo, ObjectId idAlbum, ObjectId idAutor) {
        this.id = id;
        this.titulo = titulo;
        this.idAlbum = idAlbum;
        this.idAutor = idAutor;
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
        return "Cancion{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", idAlbum=" + idAlbum +
                ", idAutor=" + idAutor +
                '}';
    }
}

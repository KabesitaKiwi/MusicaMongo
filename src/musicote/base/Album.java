package musicote.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;

public class Album {
    private ObjectId id;
    private String titulo;
    private LocalDate fechaEstreno;
    private float duracion;

    public Album(ObjectId id, String titulo, LocalDate fechaEstreno, float duracion) {
        this.id = id;
        this.titulo = titulo;
        this.fechaEstreno = fechaEstreno;
        this.duracion = duracion;
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

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", fechaEstreno=" + fechaEstreno +
                ", duracion=" + duracion +
                '}';
    }
}

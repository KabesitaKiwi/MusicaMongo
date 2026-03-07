package musicote.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;

public class Album {
    private ObjectId id;
    private String titulo;
    private LocalDate fechaEstreno;
    private double duracion;

    public Album(  String titulo, LocalDate fechaEstreno, double duracion) {
        this.titulo = titulo;
        this.fechaEstreno = fechaEstreno;
        this.duracion = duracion;
    }

    public Album() {

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

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Album{"+
                ", titulo='" + titulo + '\'' +
                ", fechaEstreno=" + fechaEstreno +
                ", duracion=" + duracion +
                '}';
    }
}

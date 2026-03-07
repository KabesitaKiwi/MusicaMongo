package musicote.base;

import org.bson.types.ObjectId;

public class Autor {
    private ObjectId id;
    private String nombreReal;
    private String nombreArtistico;
    private int edad;

    public Autor(String nombreReal, String nombreArtistico, int edad) {

        this.nombreReal = nombreReal;
        this.nombreArtistico = nombreArtistico;
        this.edad = edad;
    }

    public Autor() {

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Autor" +
                ", nombreReal='" + nombreReal + '\'' +
                ", nombreArtistico='" + nombreArtistico + '\'' +
                ", edad=" + edad +
                '}';
    }
}

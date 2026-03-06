package musicote.gui;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import musicote.base.Album;
import musicote.base.Autor;
import musicote.base.Cancion;
import org.bson.Document;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Modelo {

    private MongoClient cliente;
    private MongoCollection<Document> canciones;
    private MongoCollection<Document> artistas;
    private MongoCollection<Document> album;

    public void conectar(){
        cliente = new MongoClient();
        String DATABASE = "Musica";
        MongoDatabase db =  cliente.getDatabase(DATABASE);

        String COLECCION_CANCIONES = "canciones";
        canciones = db.getCollection(COLECCION_CANCIONES);
        String COLECCION_ARTISTAS = "artistas";
        artistas = db.getCollection(COLECCION_ARTISTAS);
        String COLECCION_ALBUM = "album";
        album = db.getCollection(COLECCION_ALBUM);
    }

    public void desconectar(){
        cliente.close();
        cliente = null;
    }

    public MongoClient getCliente() {
        return cliente;
    }

    public ArrayList<Autor> getAutor(){
        ArrayList<Autor> lista = new ArrayList<>();
        for (Document document : artistas.find()){
            lista.add(documentToAutor(document));
        }
        return lista;
    }

    public Autor documentToAutor(Document dc){
        Autor autor = new Autor();
        autor.setId(dc.getObjectId("_id"));
        autor.setNombreReal(dc.getString("nombre"));
        autor.setNombreArtistico(dc.getString("nombreArtistico"));
        autor.setEdad(Integer.parseInt(String.valueOf(dc.getInteger("Edad"))));
        return autor;
    }

    public ArrayList<Autor> getAutor(String comparador) {
        ArrayList<Autor> lista = new ArrayList<>();
        Document query = new Document();
        List<Document> listaCriterios = new ArrayList<>();

        listaCriterios.add(new Document("nombre", new Document("$regex", "/*" + comparador + "/*")));
        query.append("$or", listaCriterios);

        for (Document document : artistas.find(query)) {
            lista.add(documentToAutor(document));
        }

        return lista;
    }

    public ArrayList<Cancion> getCancion(){
        ArrayList<Cancion> lista = new ArrayList<>();
        for(Document document : canciones.find()){
            lista.add(documentToCancion(document));
        }
        return lista;
    }

    public ArrayList<Cancion> getCancion(String comparador) {
        ArrayList<Cancion> lista = new ArrayList<>();
        Document query = new Document();
        List<Document> listaCriterios = new ArrayList<>();

        listaCriterios.add(new Document("nombre", new Document("$regex", "/*" + comparador + "/*")));
        query.append("$or", listaCriterios);

        for (Document document : artistas.find(query)) {
            lista.add(documentToCancion(document));
        }

        return lista;
    }

    public Cancion documentToCancion(Document dc){
        Cancion cancion = new Cancion();

        cancion.setId(dc.getObjectId("_id"));
        cancion.setTitulo(dc.getString("titulo"));
        cancion.setIdAlbum(dc.getObjectId("album"));
        cancion.setIdAutor(dc.getObjectId("autor"));

        return cancion;
    }

    public ArrayList<Album> getAlbum(){
        ArrayList<Album> lista = new ArrayList<>();
        for(Document document : canciones.find()){
            lista.add(documentToAlbum(document));
        }
        return lista;
    }

    public ArrayList<Album> getAlbum(String comparador) {
        ArrayList<Album> lista = new ArrayList<>();
        Document query = new Document();
        List<Document> listaCriterios = new ArrayList<>();

        listaCriterios.add(new Document("nombre", new Document("$regex", "/*" + comparador + "/*")));
        query.append("$or", listaCriterios);

        for (Document document : album.find(query)) {
            lista.add(documentToAlbum(document));
        }

        return lista;
    }

    public Album documentToAlbum(Document dc){
        Album album = new Album();

        album.setId(dc.getObjectId("_id"));
        album.setTitulo(dc.getString("titulo"));
        album.setFechaEstreno(LocalDate.parse(dc.getString("estreno")));
        album.setDuracion(Float.parseFloat(String.valueOf(dc.getDouble("duracion"))));


        return album;
    }
}

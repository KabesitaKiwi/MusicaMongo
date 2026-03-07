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
    private MongoCollection<Document> albumes;

    public void conectar(){
        cliente = new MongoClient();
        String DATABASE = "Musica";
        MongoDatabase db =  cliente.getDatabase(DATABASE);

        String COLECCION_CANCIONES = "Canciones";
        canciones = db.getCollection(COLECCION_CANCIONES);
        String COLECCION_ARTISTAS = "Artistas";
        artistas = db.getCollection(COLECCION_ARTISTAS);
        String COLECCION_ALBUM = "Album";
        albumes = db.getCollection(COLECCION_ALBUM);
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
        autor.setEdad(Integer.parseInt(String.valueOf(dc.getInteger("edad"))));
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
        for(Document document : albumes.find()){
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

        for (Document document : albumes.find(query)) {
            lista.add(documentToAlbum(document));
        }

        return lista;
    }

    public Album documentToAlbum(Document dc){
        Album album = new Album();

        album.setId(dc.getObjectId("_id"));
        album.setTitulo(dc.getString("titulo"));
        album.setFechaEstreno(LocalDate.parse(dc.getString("fechaDeEstreno")));
        album.setDuracion(Float.parseFloat(String.valueOf(dc.getDouble("duracion"))));


        return album;
    }

    public void guardarObjeto(Object obj){
        if (obj instanceof Cancion){
            canciones.insertOne(objectToDocument(obj));
        }else if (obj instanceof Album){
            albumes.insertOne(objectToDocument(obj));
        }else if (obj instanceof  Autor){
            artistas.insertOne(objectToDocument(obj));
        }
    }

    public void modificarObjeto(Object obj){
        if (obj instanceof Cancion){
            Cancion cancion = (Cancion) obj;
            canciones.replaceOne(new Document("_id", cancion.getId()),objectToDocument(cancion));
        }else if(obj instanceof Album){
            Album album = (Album) obj;
            albumes.replaceOne(new Document("_id",album.getId()),objectToDocument(album));
        }else if(obj instanceof Autor){
            Autor autor = (Autor) obj;
            artistas.replaceOne(new Document("_id", autor.getId()),objectToDocument(autor));
        }
    }

    public void eliminarObjeto(Object obj){
        if (obj instanceof Cancion){
            Cancion cancion = (Cancion) obj;
            canciones.deleteOne(objectToDocument(cancion));
        }else if (obj instanceof Album){
            Album album = (Album) obj;
            albumes.deleteOne(objectToDocument(album));
        }else if (obj instanceof  Autor){
            Autor autor = (Autor) obj;
            artistas.deleteOne(objectToDocument(objectToDocument(autor)));
        }
    }

    public Document objectToDocument(Object obj){
        Document dc = new Document();

        if (obj instanceof Cancion){
            Cancion cancion = (Cancion) obj;

            dc.append("titulo", cancion.getTitulo());
            dc.append("album", cancion.getIdAlbum());
            dc.append("autor", cancion.getIdAutor());
        }else if (obj instanceof Album){
            Album album = (Album) obj;
            dc.append("titulo", album.getTitulo());
            dc.append("duracion",album.getDuracion());
            dc.append("fechaDeEstreno", album.getFechaEstreno().toString());
        }else if (obj instanceof  Autor){
            Autor autor = (Autor) obj;
            dc.append("nombre", autor.getNombreReal());
            dc.append("nombreArtistico", autor.getNombreArtistico());
            dc.append("edad", autor.getEdad());
        }else{
            return null;
        }

        return dc;
    }
}

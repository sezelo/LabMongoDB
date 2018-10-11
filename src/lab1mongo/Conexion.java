package lab1mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author sergi
 */
public class Conexion {
    private DB db;
    //private MongoDatabase database;
    public Conexion(){
        try{
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            db = mongoClient.getDB("Lab1");
            System.out.println("Conexion exitosa");
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("Servidor listo");
    }
    
    public String readProductora(){
        DBCollection coll = db.getCollection("Productora");
        DBCursor cursor = coll.find();
        String cursorResultado = "";
        while(cursor.hasNext()){
            cursorResultado += (cursor.next() + "\n" + "\n");
        } 
        return cursorResultado;
    }
    
    public void insertarProductora(String id, String nombre,int año,String url){
        DBObject productora = new BasicDBObject("_id", id)
        .append("nombre", nombre)
        .append("año fundacion", año)
        .append("pagina web", url);
        DBCollection collection = db.getCollection("Productora");
        collection.insert(productora);
    }
    
    public void deleteProductora(String id){
        DBCollection collection = db.getCollection("Productora");
        BasicDBObject document = new BasicDBObject();
        document.put("_id", id);
        collection.remove(document);
    }
    
    public void updateProductora(String id, String nombre,int año,String url){
        BasicDBObject newDocument = new BasicDBObject();
	newDocument.append("$set", new BasicDBObject()
        .append("nombre", nombre)
        .append("año fundacion", año)
        .append("pagina web", url));
        BasicDBObject searchQuery = new BasicDBObject().append("_id", id);
        DBCollection collection = db.getCollection("Productora");
        collection.update(searchQuery, newDocument);   
    }
    
    public String readPelicula(){
        DBCollection coll = db.getCollection("Pelicula");
        DBCursor cursor = coll.find();
        String cursorResultado = "";
        while(cursor.hasNext()){
            cursorResultado += (cursor.next() + "\n" + "\n");
        } 
        return cursorResultado;
        
    }
    
    //id, nombre, genero, director, franquicia, pais de produccion, año de estreno, duracion, productora, actores
    public void insertPelicula (String id, String nombre, String genero, String director, String franquicia, String pais, int año, int duracion, String productora, String actor1, String actor2, String actor3){
        List<String> actores = Arrays.asList(actor1,actor2,actor3);
        DBObject pelicula = new BasicDBObject("_id", id)
        .append("nombre", nombre)
        .append("genero",genero)
        .append("director", director)
        .append("franquicia", franquicia)
        .append("pais de produccion", pais)
        .append("año de estreno", año)
        .append("duracion", duracion)
        .append("productora", productora)
        .append("actores", actores);
        DBCollection collection = db.getCollection("Pelicula");
        
        collection.insert(pelicula);
    }
    
    public void deletePelicula(String id){
        DBCollection collection = db.getCollection("Pelicula");
        BasicDBObject document = new BasicDBObject();
        document.put("_id", id);
        collection.remove(document);
    }
    
    public void updatePelicula(String id, String nombre,String genero,String director,String franquicia,String pais,int año, int duracion, String productora, String actor1, String actor2, String actor3){
        BasicDBObject newDocument = new BasicDBObject();
        List<String> actores = Arrays.asList(actor1,actor2,actor3);
	newDocument.append("$set", new BasicDBObject().append("nombre", nombre)
        .append("genero",genero)
        .append("director", director)
        .append("franquicia", franquicia)      
        .append("pais de produccion", pais)
        .append("año de estreno", año)
        .append("duracion", duracion)
        .append("productora", productora)
        .append("actores", actores)
        );
			
	BasicDBObject searchQuery = new BasicDBObject().append("_id", id);
        DBCollection collection = db.getCollection("Pelicula");
	collection.update(searchQuery, newDocument);
    }
    
    public String consultaPorTituloPelicula(String titulo){
        DBCursor cursor = db.getCollection("Pelicula").find(new BasicDBObject("nombre", titulo));
        String cursorResultado = "";
        while (cursor.hasNext()){
            cursorResultado += (cursor.next().toString() + "\n");
        } 
        return cursorResultado;
    }
    
    public String consultaPorFranquicia(String nombreFranquicia){
        DBCursor cursor = db.getCollection("Pelicula").find(new BasicDBObject("franquicia", nombreFranquicia));
        String cursorResultado = "";
        while (cursor.hasNext()){
            cursorResultado += (cursor.next().toString() + "\n" + "\n");
        } 
        return cursorResultado;
    }
    
    public String consultaPorRangoAño(int año1, int año2){
        BasicDBObject query = new BasicDBObject("año de estreno", new BasicDBObject("$gte", año1)).append("año de estreno", new BasicDBObject("$lte", año2));
        DBCursor cursor = db.getCollection("Pelicula").find(query);
        String cursorResultado = "";
        while (cursor.hasNext()){
            cursorResultado += (cursor.next().toString() + "\n" + "\n");
        } 
        return cursorResultado;
    }
    
    public String consultasProductora1(String productora){
        BasicDBObject query = new BasicDBObject("productora", productora);
        BasicDBObject x = new BasicDBObject();
        x.put("nombre",1);
        x.put("genero",1);
        x.put("año de estreno",1);
        x.put("_id" , 0);
        DBCursor cursor = db.getCollection("Pelicula").find(query,x);
        String cursorResultado = "";
        while (cursor.hasNext()){
           cursorResultado += (cursor.next().toString() + "\n");
        }   
        return cursorResultado;
    }
    
    public String cantidadPeliculas(String productora){
        BasicDBObject query = new BasicDBObject("productora", productora);
        DBCursor cursor = db.getCollection("Pelicula").find(query);
        return ("La cantidad de Peliculas que produce esta productora es: " + cursor.count());
    }
    
    public String peliculaMenorDuracion(String productora){
        BasicDBObject query = new BasicDBObject("productora", productora);
        BasicDBObject x = new BasicDBObject();
        x.put("nombre",1);
        x.put("duracion",1);
        x.put("_id",0);
        DBCursor cursor = db.getCollection("Pelicula").find(query, x).sort(new BasicDBObject("duracion", 1)).limit(1);
        String cursorResultado = "";
        while (cursor.hasNext()){
            cursorResultado += (cursor.next().toString());
        }      
        return ("\n" + "La pelicula con menor duracion es: " + cursorResultado);
    }
    
     public String peliculaMayorDuracion(String productora){
        BasicDBObject query = new BasicDBObject("productora", productora);
        BasicDBObject x = new BasicDBObject();
        x.put("nombre",1);
        x.put("duracion",1);
        x.put("_id",0);
        DBCursor cursor = db.getCollection("Pelicula").find(query, x).sort(new BasicDBObject("duracion", -1)).limit(1);
        String cursorResultado = "";
        while (cursor.hasNext()){
            cursorResultado += (cursor.next().toString());
        }    
        return ("\n" + "La pelicula con mayor duracion es: " + cursorResultado);
    }
     
    public String peliculaDuracionPromedio(String productora){
        BasicDBObject query = new BasicDBObject("productora", productora);
        BasicDBObject x = new BasicDBObject();
        x.put("duracion",1);
        x.put("_id",0);
        DBCursor cursor = db.getCollection("Pelicula").find(query, x);
        float promedio = 0;
        //String cursorResultado = "";
        while (cursor.hasNext()){
            String actual = cursor.next().get("duracion").toString();
            System.out.println(actual);
            int duracion = Integer.parseInt(actual);
            promedio += duracion;
        }    
        float cursorResultado = (promedio/cursor.count());
        return ("\n" + "La duracion promedio es: " + cursorResultado);
    }
    
}

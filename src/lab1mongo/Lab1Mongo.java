
package lab1mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

/**
 *
 * @author sergi
 */
public class Lab1Mongo {

    public static void main(String[] args) throws Exception{
        
        Interfaz_Principal n = new Interfaz_Principal();
        n.setVisible(true);
        Conexion conexion = new Conexion();
                        
        //conexion.readProductora();
        //conexion.insertPelicula("prueba1", "Maikol Jordan", "Comedia", "La media docena","Maikol Jordan Soto", "Costa Rica", 2015, 120, "La 1/2", "Daniel Moreno", "Edgar Murillo", "Mario Chacon");
        //conexion.insertPelicula("prueba2", "Maikol Jordan 2", "Comedia", "La media docena","Maikol Jordan Soto", "Costa Rica", 2018, 129, "La 1/2", "Daniel Moreno", "Edgar Murillo", "Mario Chacon");
        //conexion.insertPelicula("prueba3", "Maikol Jordan 3", "Comedia", "La media docena","Maikol Jordan Soto", "Costa Rica", 2020, 143, "La 1/2", "Daniel Moreno", "Edgar Murillo", "Mario Chacon");
        //conexion.insertarProductora("p1", "La 1/2", 2005, "www.lamediadocena.com");
        //conexion.deleteProductora("p1");
        //conexion.deletePelicula("prueba1");
        //conexion.updateProductora("p1", "La 1/2", 2005, "www.lamediadocena.com");
        //conexion.updatePelicula("prueba1", "White Chicks", "Comedia", "Keenen Ivory Wayans", "White Chicks", "USA", 2004, 109, "Columbia Pictures", "Shawn Wayans", "Marlon Wayans", "Terry Crews");
        
        /*conexion.consultaPorTituloPelicula("Maikol Jordan 3");
        System.out.println();
        conexion.consultaPorFranquicia("Maikol Jordan Soto");
        System.out.println();
        conexion.consultaPorRangoAño(2004, 2016);
        System.out.println();
        conexion.consultasProductora1("La 1/2");
        System.out.println();
        conexion.cantidadPeliculas("La 1/2");
        System.out.println();
        conexion.peliculaMenorDuracion("La 1/2");
        System.out.println();
        conexion.peliculaMayorDuracion("La 1/2");
        System.out.println();
        conexion.peliculaDuracionPromedio("La 1/2");*/
        

            
    }
    
    
}


package Entidades;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Eli
 */
public class Diccionario {
      //<editor-fold desc="Atributos">
    private ArrayList<Palabras> lista;

    // </editor-fold>
    //<editor-fold desc="Constructores">

    public Diccionario()
    {
        lista = new ArrayList<Palabras>();
    }
    
    public Diccionario(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // </editor-fold>    
    //<editor-fold desc="Get-Set">
    //</editor-fold>
    //<editor-fold desc="Metodo">
    
    public void Cargar()
    {
        getLista().add(new Palabras("casa",1,"lugar donde se habita"));
        getLista().add(new Palabras("martillo",2,"Herramienta para golpear"));
        getLista().add(new Palabras("estrella",3,"Herramienta para golpear"));
       
    }

       @Override
    public String toString()
    {
        String retorno = "";
        for (Palabras p : this.getLista())
        {
            retorno += p.toString() + "\n";
        }
        return retorno;
    }

    
     public static void guardarDiccionario(Diccionario lista,String nombreArchivo)
    {
        XMLEncoder encoder = null;

        try
        {
            FileOutputStream fos = new FileOutputStream(nombreArchivo);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            encoder = new XMLEncoder(bos);
        }
        catch (FileNotFoundException fileNotFound)
        {
            fileNotFound.getMessage();
            System.out.println("ERROR- file not found");
        }

        encoder.writeObject(lista);
        encoder.close();
    }
    public static Diccionario cargarDiccionario(String nombreArchivo)
    {
        XMLDecoder decoder = null;

        try
        {
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nombreArchivo)));
        }
        catch (FileNotFoundException fileNotFound)
        {
            fileNotFound.getMessage();
            System.out.println("ERROR- archivo no encontrado");
        }

        Diccionario nuevo = (Diccionario) decoder.readObject();

        return nuevo;
    }
    
     public void agregarPalabra(String nombre, int nivel, String pista) 
     {
        if (nombre.length() < 15)
        {
            this.getLista().add(new Palabras(nombre, nivel, pista));
        }

        else
        {
            System.out.println("Palabra muy Larga");
        }

    }
     public Diccionario FiltradoPorNivel(int nivel)
     {
         Diccionario filtrado=new Diccionario();
         
         for(Palabras p : this.getLista())
         {
             if(p.getNivel()==nivel)
             {
                 filtrado.getLista().add(p);
                        
             }                
         }
              
        return filtrado;
     }
     
     
     public Palabras elegirPalabraAleatoria() 
    {
        int i = Tools.Aleatorio.Entero(0, this.getLista().size() - 1);
        return this.getLista().get(i);
    }
     

    public ArrayList<Palabras> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Palabras> lista) {
        this.lista = lista;
    }

    public void setLista(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

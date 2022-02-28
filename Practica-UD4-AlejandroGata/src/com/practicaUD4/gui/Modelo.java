package com.practicaUD4.gui;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.practicaUD4.base.Editorial;
import com.practicaUD4.base.Puzzle;
import com.practicaUD4.util.Util;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase Modelo
 *
 * @author
 */
public class Modelo {

    //Campos
    private final static String COLECCION_PUZZLE = "Puzzles";
    private final static String COLECCION_EDITORIAL = "Editoriales";
    private final static String COLECCION_VENDEDOR = "Vendedores";
    private final static String DATABASE = "TiendaPuzzle";

    private MongoClient client;
    private MongoDatabase baseDatos;
    private MongoCollection coleccionPuzzles;
    private MongoCollection coleccionEditoriales;
    private MongoCollection coleccionVendedores;

    /**
     * Método conectar(), conectas con la base de datos
     */
    public void conectar() {
        client = new MongoClient();
        baseDatos = client.getDatabase(DATABASE);
        coleccionPuzzles = baseDatos.getCollection(COLECCION_PUZZLE);
        coleccionEditoriales = baseDatos.getCollection(COLECCION_EDITORIAL);
        coleccionVendedores = baseDatos.getCollection(COLECCION_VENDEDOR);
    }

    /**
     * Método desconectar(), para desconectar la base de datos
     */
    public void desconectar() {
        client.close();

    }

    /**
     * Método guardarPuzzle(), para guardar puzzles en la base de datos
     *
     * @param unPuzzle de tipo puzzle
     */
    public void guardarPuzzle(Puzzle unPuzzle) {
        coleccionPuzzles.insertOne(puzzleToDocument(unPuzzle));

    }

    /**
     * Método guardarEditorial(), para guardar Editorial en la base de datos
     *
     * @param unEditorial de tipo Editorial
     */
    public void guardarEditorial(Editorial unEditorial) {
        coleccionEditoriales.insertOne(editorialToDocument(unEditorial));

    }

    /**
     * Método getpuzzles(), lista puzzles
     *
     * @return lista
     */
    public List<Puzzle> getPuzzles() {
        ArrayList<Puzzle> lista = new ArrayList<>();

        Iterator<Document> it = coleccionPuzzles.find().iterator();
        while (it.hasNext()) {
            lista.add(documentToPuzzle(it.next()));
        }
        return lista;
    }

    /**
     * Método getEditorials(), lista Editorials
     *
     * @return lista
     */
    public List<Editorial> getEditoriales() {
        ArrayList<Editorial> lista = new ArrayList<>();

        Iterator<Document> it = coleccionEditoriales.find().iterator();
        while (it.hasNext()) {
            lista.add(documentToEditorial(it.next()));
        }
        return lista;
    }


    /**
     * Método getPuzzles(),listo los puzzles atendiendo a 2 criterios basados en expresiones regulares.
     *
     * @param text de tipo String
     * @return lista
     */
    public List<Puzzle> getPuzzles(String text) {
        ArrayList<Puzzle> lista = new ArrayList<>();

        Document query = new Document();
        List<Document> listaCriterios = new ArrayList<>();
        listaCriterios.add(new Document("nombre", new Document("$regex", "/*" + text + "/*")));
        listaCriterios.add(new Document("marca", new Document("$regex", "/*" + text + "/*")));
        query.append("$or", listaCriterios);

        Iterator<Document> iterator = coleccionPuzzles.find(query).iterator();
        while (iterator.hasNext()) {
            lista.add(documentToPuzzle(iterator.next()));
        }

        return lista;
    }

    /**
     * Método getEditorial(),listo los Editorials atendiendo a 2 criterios basados en expresiones regulares.
     *
     * @param text de tipo String
     * @return lista
     */
    public List<Editorial> getEditorial(String text) {
        ArrayList<Editorial> lista = new ArrayList<>();

        Document query = new Document();
        List<Document> listaCriterios = new ArrayList<>();
        listaCriterios.add(new Document("nombre", new Document("$regex", "/*" + text + "/*")));
        listaCriterios.add(new Document("marca", new Document("$regex", "/*" + text + "/*")));
        query.append("$or", listaCriterios);

        Iterator<Document> iterator = coleccionEditoriales.find(query).iterator();
        while (iterator.hasNext()) {
            lista.add(documentToEditorial(iterator.next()));
        }

        return lista;
    }

    /**
     * Método puzzleToDocument() de tipo Document
     *
     * @param unPuzzle de tipo puzzle
     * @return documento
     */
    public Document puzzleToDocument(Puzzle unPuzzle) {
        Document documento = new Document();
        documento.append("nombre", unPuzzle.getNombre());
        documento.append("marca", unPuzzle.getMarca());
        documento.append("precio", unPuzzle.getPrecio());
        documento.append("fechaFabricacion", Util.formatearFecha(unPuzzle.getFechaFabricacion()));
        return documento;
    }

    /**
     * Método EditorialToDocument() de tipo Document
     *
     * @param unEditorial de tipo Editorial
     * @return documento
     */
    public Document editorialToDocument(Editorial unEditorial) {
        Document documento = new Document();
        documento.append("nombre", unEditorial.getNombre());
        documento.append("direccion", unEditorial.getDireccion());
        documento.append("mediaVentas", unEditorial.getMediaVentas());
        documento.append("fechaCreacion", Util.formatearFecha(unEditorial.getFechaCreacion()));
        return documento;
    }

    /**
     * Método documentToPuzzle(), registro de las filas de Puzzles
     *
     * @param document de tipo Document
     * @return unPuzzle
     */
    public Puzzle documentToPuzzle(Document document) {
        Puzzle unPuzzle = new Puzzle();
        unPuzzle.setId(document.getObjectId("_id"));
        unPuzzle.setNombre(document.getString("nombre"));
        unPuzzle.setMarca(document.getString("marca"));
        unPuzzle.setPrecio(document.getDouble("precio"));
        unPuzzle.setFechaFabricacion(Util.parsearFecha(document.getString("fechaFabricacion")));
        return unPuzzle;
    }

    /**
     * Método documentToEditorial(), registro de las filas de Editorial
     *
     * @param document de tipo Document
     * @return unEditorial
     */
    public Editorial documentToEditorial(Document document) {
        Editorial e = new Editorial();
        e.setId(document.getObjectId("_id"));
        e.setNombre(document.getString("nombre"));
        e.setDireccion(document.getString("direccion"));
        e.setMediaVentas(document.getDouble("mediaVentas"));
        e.setFechaCreacion(Util.parsearFecha(document.getString("fechaCreacion")));
        return e;
    }

    /**
     * Método modificarPuzzle(), sirve para modificar Puzzles
     *
     * @param unPuzzle de Puzzle
     */
    public void modificarPuzzle(Puzzle unPuzzle) {
        coleccionPuzzles.replaceOne(new Document("_id", unPuzzle.getId()), puzzleToDocument(unPuzzle));
    }

    /**
     * Método modificarEditorial(), sirve para modificar Editorials
     *
     * @param unEditorial de Editorial
     */
    public void modificarEditorial(Editorial unEditorial) {
        coleccionEditoriales.replaceOne(new Document("_id", unEditorial.getId()), editorialToDocument(unEditorial));
    }

    /**
     * Método borrarPuzzle(), para borrar una Puzzle
     *
     * @param unPuzzle de Puzzle
     */
    public void borrarPuzzle(Puzzle unPuzzle) {
        coleccionPuzzles.deleteOne(puzzleToDocument(unPuzzle));
    }

    /**
     * Método borrarEditorial(), para borrar una Editorial
     *
     * @param unEditorial de Editorial
     */
    public void borrarEditorial(Editorial unEditorial) {
        coleccionEditoriales.deleteOne(editorialToDocument(unEditorial));

    }
}

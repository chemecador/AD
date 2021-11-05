package com.alexgg.practica1ud1.gui;

import com.alexgg.practica1ud1.base.JuegoDeMesa;
import com.alexgg.practica1ud1.base.Maqueta;
import com.alexgg.practica1ud1.base.Producto;
import com.alexgg.practica1ud1.base.Puzzle;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProductosModelo {
    private ArrayList<Producto> listaProductos;

    public ProductosModelo() {
        listaProductos = new ArrayList<Producto>();
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public void altaPuzzle(Double precio, String marca, int id, LocalDate fechaProduccion, int numPiezas) {
        Puzzle nuevoPuzzle = new Puzzle(precio, marca, id, fechaProduccion, numPiezas);
        listaProductos.add(nuevoPuzzle);
    }

    public void altaMaqueta(Double precio, String marca, int id, LocalDate fechaProduccion, int numFiguras) {
        Maqueta nuevaMaqueta = new Maqueta(precio, marca, id, fechaProduccion, numFiguras);
        listaProductos.add(nuevaMaqueta);
    }

    public void altaJuegoDeMesa(Double precio, String marca, int id, LocalDate fechaProduccion, int numJugadores) {
        JuegoDeMesa nuevoJuego = new JuegoDeMesa(precio, marca, id, fechaProduccion, numJugadores);
        listaProductos.add(nuevoJuego);
    }

    public boolean existeId(int id) {
        for (Producto unProducto : listaProductos) {
            if (unProducto.getId() == id)
                return true;
        }
        return false;
    }

    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        //añado el nodo raiz - la primera etiqueta (contiene las demas)
        Element raiz = documento.createElement("Productos");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoProducto = null;
        Element nodoDatos = null;
        Text texto = null;

        for (Producto producto : listaProductos) {
            if (producto instanceof Puzzle) {
                nodoProducto = documento.createElement("puzzle");
            } else if (producto instanceof Maqueta) {
                nodoProducto = documento.createElement("maqueta");
            } else if (producto instanceof JuegoDeMesa) {
                nodoProducto = documento.createElement("juego-de-mesa");
            }
            raiz.appendChild(nodoProducto);

            nodoDatos = documento.createElement("precio");
            nodoProducto.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(producto.getPrecio()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("marca");
            nodoProducto.appendChild(nodoDatos);
            texto = documento.createTextNode(producto.getMarca());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("id");
            nodoProducto.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(producto.getId()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("fecha");
            nodoProducto.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(producto.getFechaProduccion()));
            nodoDatos.appendChild(texto);

            if (producto instanceof Puzzle) {
                nodoDatos = documento.createElement("numero-piezas");
                nodoProducto.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Puzzle) producto).getNumPiezas()));
                nodoDatos.appendChild(texto);
            } else if (producto instanceof Maqueta) {
                nodoDatos = documento.createElement("numero-figuras");
                nodoProducto.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Maqueta) producto).getNumFiguras()));
                nodoDatos.appendChild(texto);
            } else if (producto instanceof JuegoDeMesa) {
                nodoDatos = documento.createElement("numero-jugadores");
                nodoProducto.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((JuegoDeMesa) producto).getNumJugadores()));
                nodoDatos.appendChild(texto);
            }
            //guardo los datos en fichero
            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(fichero);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, resultado);
        }
    }

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaProductos = new ArrayList<Producto>();
        Puzzle puzzle = null;
        Maqueta maqueta = null;
        JuegoDeMesa juego = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i = 0; i < listaElementos.getLength(); i++) {
            Element nodoProductos = (Element) listaElementos.item(i);

            if (nodoProductos.getTagName().equals("puzzle")) {
                puzzle = new Puzzle();
                puzzle.setPrecio(Double.parseDouble(nodoProductos.getChildNodes().item(0).getTextContent()));
                puzzle.setMarca(nodoProductos.getChildNodes().item(1).getTextContent());
                puzzle.setId(Integer.parseInt(nodoProductos.getChildNodes().item(2).getTextContent()));
                puzzle.setFechaProduccion(LocalDate.parse(nodoProductos.getChildNodes().item(3).getTextContent()));
                puzzle.setNumPiezas(Integer.parseInt(nodoProductos.getChildNodes().item(4).getTextContent()));
                listaProductos.add(puzzle);
            }
            else if (nodoProductos.getTagName().equals("maqueta")) {
                maqueta = new Maqueta();
                maqueta.setPrecio(Double.parseDouble(nodoProductos.getChildNodes().item(0).getTextContent()));
                maqueta.setMarca(nodoProductos.getChildNodes().item(1).getTextContent());
                maqueta.setId(Integer.parseInt(nodoProductos.getChildNodes().item(2).getTextContent()));
                maqueta.setFechaProduccion(LocalDate.parse(nodoProductos.getChildNodes().item(3).getTextContent()));
                maqueta.setNumFiguras(Integer.parseInt(nodoProductos.getChildNodes().item(4).getTextContent()));
                listaProductos.add(maqueta);
            }
            else if (nodoProductos.getTagName().equals("juego-de-mesa")) {
                juego = new JuegoDeMesa();
                juego.setPrecio(Double.parseDouble(nodoProductos.getChildNodes().item(0).getTextContent()));
                juego.setMarca(nodoProductos.getChildNodes().item(1).getTextContent());
                juego.setId(Integer.parseInt(nodoProductos.getChildNodes().item(2).getTextContent()));
                juego.setFechaProduccion(LocalDate.parse(nodoProductos.getChildNodes().item(3).getTextContent()));
                juego.setNumJugadores(Integer.parseInt(nodoProductos.getChildNodes().item(4).getTextContent()));
                listaProductos.add(juego);
            }
        }
    }
}

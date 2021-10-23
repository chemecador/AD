package com.alexg.ficherosxml;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class EscribirFicherosXML {
    public static void main(String[] args) {
        ArrayList<Producto> listaProductos = null;
        Producto producto1 = new Producto("Producto1", 1);
        Producto producto2 = new Producto("Producto2", 2);
        Producto producto3 = new Producto("Producto3", 3);

        listaProductos = new ArrayList<>();
        listaProductos.add(producto1);
        listaProductos.add(producto2);
        listaProductos.add(producto3);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            documento = dom.createDocument(null, "xml", null);
            Element raiz = documento.createElement("Productos");
            documento.getDocumentElement().appendChild(raiz);
            Element nodoProducto = null;
            Element nodoDatos = null;
            Text texto = null;
            for (Producto producto : listaProductos) {
                nodoProducto = documento.createElement("Producto");
                raiz.appendChild(nodoProducto);
                nodoDatos = documento.createElement("nombre");
                nodoProducto.appendChild(nodoDatos);
                texto = documento.createTextNode(producto.getNombre());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("precio");
                nodoProducto.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(producto.getPrecio()));
                nodoDatos.appendChild(texto);
            }
            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(new File("fichero.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source,resultado);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}

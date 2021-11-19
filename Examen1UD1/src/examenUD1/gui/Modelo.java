package examenUD1.gui;

import examenUD1.base.Ordenador;
import examenUD1.base.Portatil;
import examenUD1.base.SobreMesa;
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

public class Modelo {
    private ArrayList<Ordenador> listaRecambios;

    public Modelo(){listaRecambios = new ArrayList<Ordenador>();}

    public ArrayList<Ordenador> obtenerRecambios(){return listaRecambios;}

    public void altaPortatil(String nombre, String cantidad, String precio, String touchpad, LocalDate fechaOferta){
        //completar
        Portatil nuevoPortatil = new Portatil(nombre,cantidad,precio,fechaOferta,touchpad);
        listaRecambios.add(nuevoPortatil);
    }

    public void altaSobreMesa(String nombre, String cantidad, String precio, String raton, LocalDate fechaOferta){
        //completar
        SobreMesa nuevoSobreMesa = new SobreMesa(nombre,cantidad,precio,fechaOferta,raton);
        listaRecambios.add(nuevoSobreMesa);
    }

    public boolean existeNombre(String nombre) {
        for (Ordenador ordenador:listaRecambios) {
            if(ordenador.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public boolean eliminarRecambio(String nombre){
        for (Ordenador ordenador:listaRecambios) {
            if(ordenador.getNombre().equals(nombre)) {
                listaRecambios.remove(ordenador);
                return true;
            }
        }
        return false;
    }

    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        //completar
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        Element raiz = documento.createElement("Recambios");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoOrdenador = null;
        Element nodoDatos = null;
        Text texto = null;
        for (Ordenador ordenador : listaRecambios) {
            if (ordenador instanceof Portatil) {
                nodoOrdenador = documento.createElement("Portatil");
            } else if (ordenador instanceof SobreMesa) {
                nodoOrdenador = documento.createElement("Sobremesa");
            }
            raiz.appendChild(nodoOrdenador);

            nodoDatos = documento.createElement("nombre");
            nodoOrdenador.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(ordenador.getNombre()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("cantidad");
            nodoOrdenador.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(ordenador.getCantidad()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("precio");
            nodoOrdenador.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(ordenador.getPrecio()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("fechaOferta");
            nodoOrdenador.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(ordenador.getFechaOferta()));
            nodoDatos.appendChild(texto);

            if (ordenador instanceof  Portatil){
                nodoDatos = documento.createElement("Touchpad");
                nodoOrdenador.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Portatil) ordenador).getTouchpad()));
                nodoDatos.appendChild(texto);
            }
            else if (ordenador instanceof  SobreMesa){
                nodoDatos = documento.createElement("Raton");
                nodoOrdenador.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((SobreMesa) ordenador).getRaton()));
                nodoDatos.appendChild(texto);
            }
            else {
                //aquí nunca entra porque no hay más tipos de ordenador (todavía)
            }
            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(fichero);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, resultado);
        }
    }

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        //completar
        listaRecambios = new ArrayList<Ordenador>();
        Portatil portatil = null;
        SobreMesa sobremesa = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i = 0; i < listaElementos.getLength(); i++) {
            Element nodoProductos = (Element) listaElementos.item(i);

            if (nodoProductos.getTagName().equals("Portatil")) {
                portatil = new Portatil();
                portatil.setNombre(nodoProductos.getChildNodes().item(0).getTextContent());
                portatil.setCantidad(nodoProductos.getChildNodes().item(1).getTextContent());
                portatil.setPrecio(nodoProductos.getChildNodes().item(2).getTextContent());
                portatil.setFechaOferta(LocalDate.parse(nodoProductos.getChildNodes().item(3).getTextContent()));
                portatil.setTouchpad(nodoProductos.getChildNodes().item(4).getTextContent());
                listaRecambios.add(portatil);
            }
            else if (nodoProductos.getTagName().equals("Sobremesa")) {
                sobremesa = new SobreMesa();
                sobremesa.setNombre(nodoProductos.getChildNodes().item(0).getTextContent());
                sobremesa.setCantidad(nodoProductos.getChildNodes().item(1).getTextContent());
                sobremesa.setPrecio(nodoProductos.getChildNodes().item(2).getTextContent());
                sobremesa.setFechaOferta(LocalDate.parse(nodoProductos.getChildNodes().item(3).getTextContent()));
                sobremesa.setRaton(nodoProductos.getChildNodes().item(4).getTextContent());
                listaRecambios.add(sobremesa);
            }
        }


    }
}

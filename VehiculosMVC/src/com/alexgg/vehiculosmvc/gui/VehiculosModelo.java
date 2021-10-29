package com.alexgg.vehiculosmvc.gui;

import com.alexgg.vehiculosmvc.base.Coche;
import com.alexgg.vehiculosmvc.base.Moto;
import com.alexgg.vehiculosmvc.base.Vehiculo;
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

public class VehiculosModelo {
    private ArrayList<Vehiculo> listaVehiculos;

    public VehiculosModelo(){
        listaVehiculos = new ArrayList<Vehiculo>();
    }

    public ArrayList<Vehiculo> obtenerVehiculos() {
        return listaVehiculos;
    }
    public void altaCoche(String matricula, String marca, String modelo,
                          LocalDate fechaMatriculacion, int numPlazas){
        Coche nuevoCoche = new Coche(matricula, marca,
                modelo, fechaMatriculacion, numPlazas);
        listaVehiculos.add(nuevoCoche);

    }
    public void altaMoto(String matricula, String marca, String modelo,
                         LocalDate fechaMatriculacion, double kms){
        Moto nuevaMoto = new Moto(matricula,marca, modelo, fechaMatriculacion, kms);
    }
    public boolean existeMatricula(String matricula){
        for (Vehiculo unVehiculo:listaVehiculos){
            if (unVehiculo.getMatricula().equalsIgnoreCase(matricula))
                return true;
        }
        return false;
    }
    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null,
                "xml", null);

        //añado el nodo raiz: la primera etiqueta (contiene las demas)
        Element raiz = documento.createElement("Vehiculos");
        documento.getDocumentElement().appendChild(raiz);
        Element nodoVehiculo = null;
        Element nodoDatos = null;
        Text texto = null;

        for (Vehiculo vehiculo : listaVehiculos) {
            //añado dentro de la etiqueta raiz Vehiculos
            //en funcion del tipo de vehiculo: coche o moto
            if (vehiculo instanceof Coche) {
                nodoVehiculo = documento.createElement("Coche");
            } else if (vehiculo instanceof Moto) {
                nodoVehiculo = documento.createElement("Moto");
            }
            raiz.appendChild(nodoVehiculo);

            nodoDatos = documento.createElement("Matricula");
            nodoVehiculo.appendChild(nodoDatos);
            texto = documento.createTextNode(vehiculo.getMatricula());
            nodoDatos.appendChild(texto);
            raiz.appendChild(nodoVehiculo);

            nodoDatos = documento.createElement("marca");
            nodoVehiculo.appendChild(nodoDatos);
            texto = documento.createTextNode(vehiculo.getMarca());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("modelo");
            nodoVehiculo.appendChild(nodoDatos);
            texto = documento.createTextNode(vehiculo.getModelo());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("fecha-matriculacion");
            nodoVehiculo.appendChild(nodoDatos);
            texto = documento.createTextNode(vehiculo.getFechaMatriculacion().toString());
            nodoDatos.appendChild(texto);

            if (vehiculo instanceof Coche){
                nodoDatos = documento.createElement("numero-plazas");
                nodoVehiculo.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Coche) vehiculo).getNumPlazas()));
                nodoDatos.appendChild(texto);
            }
            else if (vehiculo instanceof Moto){
                nodoDatos = documento.createElement("kms");
                nodoVehiculo.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Moto) vehiculo).getKms()));
                nodoDatos.appendChild(texto);

            }
        }
        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source,resultado);
    }

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaVehiculos = new ArrayList<Vehiculo>();
        Coche nuevoCoche = null;
        Moto nuevaMoto = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");
        for (int i = 0; i<listaElementos.getLength(); i++){
            Element nodoVehiculo = (Element) listaElementos.item(i);
            if (nodoVehiculo.getTagName().equalsIgnoreCase("coche")){
                nuevoCoche = new Coche();
                nuevoCoche.setMatricula(nodoVehiculo.getChildNodes().item(0).getTextContent());
                nuevoCoche.setMarca(nodoVehiculo.getChildNodes().item(1).getTextContent());
                nuevoCoche.setModelo(nodoVehiculo.getChildNodes().item(2).getTextContent());
                nuevoCoche.setFechaMatriculacion(LocalDate.parse(nodoVehiculo.getChildNodes().item(3).getTextContent()));
                nuevoCoche.setNumPlazas(Integer.parseInt(nodoVehiculo.getChildNodes().item(4).getTextContent()));
                listaVehiculos.add(nuevoCoche);
            }
            else if (nodoVehiculo.getTagName().equalsIgnoreCase("moto")){

                nuevaMoto = new Moto();
                nuevaMoto.setMatricula(nodoVehiculo.getChildNodes().item(0).getTextContent());
                nuevaMoto.setMarca(nodoVehiculo.getChildNodes().item(1).getTextContent());
                nuevaMoto.setModelo(nodoVehiculo.getChildNodes().item(2).getTextContent());
                nuevaMoto.setFechaMatriculacion(LocalDate.parse(nodoVehiculo.getChildNodes().item(3).getTextContent()));
                nuevaMoto.setKms(Double.parseDouble(nodoVehiculo.getChildNodes().item(4).getTextContent()));
                listaVehiculos.add(nuevaMoto);
            }
        }

    }

}

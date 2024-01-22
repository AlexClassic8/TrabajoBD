/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebasbd;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Alex
 */
public class Parse {

    
    
    private Document documento;
    
    public Parse() {
    }
    
    public Document createXML(){
        try{
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            documento = builder.newDocument();
            System.out.println("El documento se ha creado con exito");
        
            
        }catch (ParserConfigurationException e){
            e.printStackTrace();
            System.out.println("El documento no se ha creado con exito");
        }
        return documento;
    }
        
    
    public Document readXML(String xmlFilePath){
        try{
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            documento = builder.parse(new File(xmlFilePath));
            
            
        } catch (IOException | ParserConfigurationException | SAXException e){
            e.printStackTrace();
        }return documento;
    }
    private void processDocumetn(Document document, String elemento){
        
        NodeList elements = document.getElementsByTagName(elemento);
         for (int i = 0; i < elements.getLength(); i++) {
            Element element = (Element) elements.item(i);
            String contenido = element.getTextContent();
            System.out.println("Contenido del elemento: " + contenido);
                                    
        }
    }
    public static void convertirAXml(List<Registro> registros, String nombreArchivo) {
        try {
            // Crear un nuevo documento XML
          
             Parse doc= new Parse();
             doc.createXML();
            // Crear el elemento raíz
            Element rootElement = doc.getDocumento().createElement("Registros");
            doc.getDocumento().appendChild(rootElement);

            // Iterar sobre la lista de registros y crear elementos para cada uno
            for (Registro registro : registros) {
                Element elementoRegistro = doc.getDocumento().createElement("Registro");
                rootElement.appendChild(elementoRegistro);

                // Agregar elementos para cada columna
                agregarElemento(doc.getDocumento(), elementoRegistro, "Columna1", registro.getColumna1());
                agregarElemento(doc.getDocumento(), elementoRegistro, "Columna2", registro.getColumna2());
                agregarElemento(doc.getDocumento(), elementoRegistro, "Columna3", registro.getColumna3());
                agregarElemento(doc.getDocumento(), elementoRegistro, "Columna4", registro.getColumna4());
                agregarElemento(doc.getDocumento(), elementoRegistro, "Columna5", registro.getColumna5());
                agregarElemento(doc.getDocumento(), elementoRegistro, "Columna6", registro.getColumna6());
                agregarElemento(doc.getDocumento(), elementoRegistro, "Columna7", registro.getColumna7());
                agregarElemento(doc.getDocumento(), elementoRegistro, "Columna9", registro.getColumna9());
                agregarElemento(doc.getDocumento(), elementoRegistro, "Columna10", registro.getColumna10());
                
            }

            // Guardar el documento XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc.getDocumento());
            File archivoXML = new File(nombreArchivo);
            StreamResult result = new StreamResult(archivoXML);
            transformer.transform(source, result);

            System.out.println("Archivo XML creado con éxito: " + archivoXML.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se ha podido crear el archivo");
        }
    }
    //Funcion para poder insertar elementos en el nuevo xml
    private static void agregarElemento(Document doc, Element padre, String nombreElemento, String valor) {
        Element elemento = doc.createElement(nombreElemento);
        elemento.appendChild(doc.createTextNode(valor));
        padre.appendChild(elemento);
    }

    public Document getDocumento() {
        return documento;
    }

    public void setDocumento(Document documento) {
        this.documento = documento;
    }
    
    
}

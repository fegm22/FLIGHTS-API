package com.nexthink.quiz.question6.expression.util;

import com.nexthink.quiz.question6.expression.Expression;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Created by fogd on 12.06.17.
 */
public class Writer {

    private Expression expression;

    public Writer(Expression expression){
        this.expression = expression;
    }

    /**
     * This method write the file in the expecific path
     *
     * @param path
     * @return false if it was some error writing the file, tru if it was ok
     */
    public boolean serializeToXml(String path) {
        File outFile = new File(path);
        OutputStreamWriter writer;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(outFile));
            // Create document factory
            DocumentBuilderFactory docFact = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder;
            // Build document
            docBuilder = docFact.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            // Create root node
            Element root = doc.createElement("root");
            doc.appendChild(root);

            Element xml = expression.toXml(doc);
            // Append xml content to root node
            root.appendChild(xml);

            // set up a transformer
            TransformerFactory transfac = TransformerFactory.newInstance();
            Transformer trans = transfac.newTransformer();
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            // create string from xml tree
            StreamResult result = new StreamResult(writer);
            DOMSource source = new DOMSource(doc);
            trans.transform(source, result);
            writer.flush();
            writer.close();
        } catch (ParserConfigurationException e) {
            System.out.println("Configuration error: Unable to create document builder");
            return false;
        } catch (TransformerConfigurationException e) {
            System.out.println("Configuration error: Unable to create transformer");
            return false;
        } catch (TransformerException e) {
            System.out.println("Error while transforming XML document");
            return false;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return false;
        } catch (IOException e) {
            System.out.println("IO error");
            return false;
        }
        return true;
    }

}

package com.rsvier.workshop1.model;

import java.net.URL;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XMLReader {

    public Document parse(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document;
		document = reader.read(url);
        return document;
    }
}

package com.rsvier.workshop1.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLParser {

	DocumentBuilderFactory dbf;
	DocumentBuilder db;
	Document doc;
	Element element;
	Logger logger = Logger.getLogger(XMLParser.class.getName());

	public XMLParser() {
		dbf = DocumentBuilderFactory.newInstance();
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} try {
			doc = db.parse(new FileInputStream("dbconfig.xml"));
		} catch (SAXException | IOException ex) {
			logger.info("Error traversing the xml config.");
			ex.printStackTrace();
		}
		element = doc.getDocumentElement();
	}

	public String getDriver() {
		NodeList urlNode = element.getElementsByTagName("driver");
		return urlNode.item(0).getTextContent();
	}

	public String getUrl() {
		NodeList urlNode = element.getElementsByTagName("url");
		return urlNode.item(0).getTextContent();
	}

	public String getUsername() {
		NodeList usernameNode = element.getElementsByTagName("username");
		return usernameNode.item(0).getTextContent();
	}

	public String getPassword() {
		NodeList passwordNode = element.getElementsByTagName("password");
		return passwordNode.item(0).getTextContent();
	}
}

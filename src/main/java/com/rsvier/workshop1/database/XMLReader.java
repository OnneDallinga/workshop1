package com.rsvier.workshop1.database;

import java.io.FileInputStream;
import java.io.IOException;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

public class XMLReader {

	Document document = null;
	Element rootElement = null;

	public XMLReader() {
		DocumentBuilderFactory builderFactory =
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();  
		}
		try {
			document = (Document) builder.parse(
					new FileInputStream("dbproperties.xml"));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		rootElement = document.getRootElement();
	}

	public String getUrl() {
		Node urlNode = rootElement.selectSingleNode("url");
		return urlNode.getText();
	}

	public String getUsername() {
		Node usernameNode = rootElement.selectSingleNode("username");
		return usernameNode.getText();
	}

	public String getPassword() {
		Node passwordNode = rootElement.selectSingleNode("password");
		return passwordNode.getText();
	}
}

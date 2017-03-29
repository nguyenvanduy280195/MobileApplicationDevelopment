package com.example.lazyguy.exercisebonus;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by lazyguy on 29/03/2017.
 */

public class Price {
    String uri = "http://www.vietcombank.com.vn/ExchangeRates/ExrateXML.aspx";

    public double getPrice(String currencyCode) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(this.uri);

            Element element = document.getDocumentElement();
            element.normalize();


            NodeList nodeList = document.getElementsByTagName("Exrate");
            int index = indexItemElement(nodeList, currencyCode);
            if (index == -1){
                Log.e("Error: ", "fail");
                return -1;
            }
            Element e = (Element) nodeList.item(index);

            return Double.parseDouble(getValue("Sell", e));


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int indexItemElement(NodeList nodeList, String currencyCode) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            if (getValue("CurrencyCode", element).equalsIgnoreCase(currencyCode)) {
                return i;
            }
        }
        return -1;
    }

    public String getValue(String attribute, Element element) {
        return element.getAttributeNode(attribute).getNodeValue();
    }


}


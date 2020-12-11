
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a “de facto” standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 

The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/
import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
	Tv tv;
	Laptop laptop;
    Wear wear;
    Phone phone;
    Va va;
	Wireless wireless;
    static HashMap<String,Wear> wears;
	static HashMap<String,Tv> tvs;
	static HashMap<String,Laptop> laptops;
    static HashMap<String,Phone> phones;
    static HashMap<String,Va> vas;
	static HashMap<String,Wireless> wirelesss;
    String wearXmlFileName;
    String elementValueRead;
	String currentElement="";
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String wearXmlFileName) {
    this.wearXmlFileName = wearXmlFileName;
    wears = new HashMap<String, Wear>();
	tvs = new HashMap<String, Tv>();
	laptops = new HashMap<String, Laptop>();
	phones=new  HashMap<String, Phone>();
	vas=new HashMap<String, Va>();
	wirelesss=new HashMap<String, Wireless>();
	
	parseDocument();
	
	for(Map.Entry<String,Tv> p : SaxParserDataStore.tvs.entrySet()){
		MySqlDataStoreUtilities.insertProduct(p.getValue().getId(),p.getValue().getName(),p.getValue().getPrice(),p.getValue().getImage(),p.getValue().getRetailer(),p.getValue().getCondition(),p.getValue().getDiscount(),"tv");
	}
	
	for(Map.Entry<String,Laptop> p : SaxParserDataStore.laptops.entrySet()){
		MySqlDataStoreUtilities.insertProduct(p.getValue().getId(),p.getValue().getName(),p.getValue().getPrice(),p.getValue().getImage(),p.getValue().getRetailer(),p.getValue().getCondition(),p.getValue().getDiscount(),"laptop");
	}
	
	for(Map.Entry<String,Phone> p : SaxParserDataStore.phones.entrySet()){
		MySqlDataStoreUtilities.insertProduct(p.getValue().getId(),p.getValue().getName(),p.getValue().getPrice(),p.getValue().getImage(),p.getValue().getRetailer(),p.getValue().getCondition(),p.getValue().getDiscount(),"phone");
	}
	
	for(Map.Entry<String,Wear> p : SaxParserDataStore.wears.entrySet()){
		MySqlDataStoreUtilities.insertProduct(p.getValue().getId(),p.getValue().getName(),p.getValue().getPrice(),p.getValue().getImage(),p.getValue().getRetailer(),p.getValue().getCondition(),p.getValue().getDiscount(),"wear");
	}
	
	for(Map.Entry<String,Va> p : SaxParserDataStore.vas.entrySet()){
		MySqlDataStoreUtilities.insertProduct(p.getValue().getId(),p.getValue().getName(),p.getValue().getPrice(),p.getValue().getImage(),p.getValue().getRetailer(),p.getValue().getCondition(),p.getValue().getDiscount(),"va");
	}
	
	for(Map.Entry<String,Wireless> p : SaxParserDataStore.wirelesss.entrySet()){
		MySqlDataStoreUtilities.insertProduct(p.getValue().getId(),p.getValue().getName(),p.getValue().getPrice(),p.getValue().getImage(),p.getValue().getRetailer(),p.getValue().getCondition(),p.getValue().getDiscount(),"wireless");
	}
	
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(wearXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}

   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
	
	// when xml start element is parsed store the id into respective hashmap for tv,phones etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {
		
		if (elementName.equalsIgnoreCase("tv")) 
		{
			currentElement="tv";
			tv = new Tv();
            tv.setId(attributes.getValue("id"));
		}
		
		if (elementName.equalsIgnoreCase("laptop")) 
		{
			currentElement="laptop";
			laptop = new Laptop();
            laptop.setId(attributes.getValue("id"));
		}
		

        if (elementName.equalsIgnoreCase("wear")) 
		{
			currentElement="wear";
			wear = new Wear();
            wear.setId(attributes.getValue("id"));
		}
		
        if (elementName.equalsIgnoreCase("va"))
		{
			currentElement="va";
			va = new Va();
            va.setId(attributes.getValue("id"));
        }
		
		if (elementName.equalsIgnoreCase("wireless"))
		{
			currentElement="wireless";
			wireless = new Wireless();
            wireless.setId(attributes.getValue("id"));
        }
		
        if (elementName.equalsIgnoreCase("phone"))
		{
			currentElement="phone";
			phone= new Phone();
            phone.setId(attributes.getValue("id"));
        }
        


    }
	// when xml end element is parsed store the data into respective hashmap for wear,phones etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("wear")) {
			wears.put(wear.getId(),wear);
			return;
        }
		
		if (element.equals("tv")) {
			tvs.put(tv.getId(),tv);
			return;
        }
		if (element.equals("laptop")) {
			laptops.put(laptop.getId(),laptop);
			return;
        }
 
        if (element.equals("va")) {	
			vas.put(va.getId(),va);
			return;
        }
		
		if (element.equals("wireless")) {	
			wirelesss.put(wireless.getId(),wireless);
			return;
        }
		
        if (element.equals("phone")) {	  
			phones.put(phone.getId(),phone);
			return;
        }
        
		
        if (element.equalsIgnoreCase("image")) {
		    if(currentElement.equals("wear"))
				wear.setImage(elementValueRead);
			if(currentElement.equals("tv"))
				tv.setImage(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setImage(elementValueRead);
        	if(currentElement.equals("phone"))
				phone.setImage(elementValueRead);
            if(currentElement.equals("va"))
				va.setImage(elementValueRead); 
			if(currentElement.equals("wireless"))
				wireless.setImage(elementValueRead);          
			return;
        }
        

		if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equals("wear"))
				wear.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("tv"))
				tv.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("laptop"))
				laptop.setDiscount(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("phone"))
				phone.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("va"))
				va.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("wireless"))
				wireless.setDiscount(Double.parseDouble(elementValueRead));
            return;
	    }


		if (element.equalsIgnoreCase("condition")) {
            if(currentElement.equals("wear"))
				wear.setCondition(elementValueRead);
			if(currentElement.equals("tv"))
				tv.setCondition(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setCondition(elementValueRead);
        	if(currentElement.equals("phone"))
				phone.setCondition(elementValueRead);
            if(currentElement.equals("va"))
				va.setCondition(elementValueRead);
			if(currentElement.equals("wireless"))
				wireless.setCondition(elementValueRead);
            return;  
		}

		if (element.equalsIgnoreCase("manufacturer")) {
            if(currentElement.equals("wear"))
				wear.setRetailer(elementValueRead);
			if(currentElement.equals("tv"))
				tv.setRetailer(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setRetailer(elementValueRead);
        	if(currentElement.equals("phone"))
				phone.setRetailer(elementValueRead);
            if(currentElement.equals("va"))
				va.setRetailer(elementValueRead);
			if(currentElement.equals("wireless"))
				wireless.setRetailer(elementValueRead);
            return;
		}

        if (element.equalsIgnoreCase("name")) {
            if(currentElement.equals("wear"))
				wear.setName(elementValueRead);
			if(currentElement.equals("tv"))
				tv.setName(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setName(elementValueRead);
        	if(currentElement.equals("phone"))
				phone.setName(elementValueRead);
            if(currentElement.equals("va"))
				va.setName(elementValueRead);
			if(currentElement.equals("wireless"))
				wireless.setName(elementValueRead);
            return;
	    }
	
        if(element.equalsIgnoreCase("price")){
			if(currentElement.equals("wear"))
				wear.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("tv"))
				tv.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("laptop"))
				laptop.setPrice(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("phone"))
				phone.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("va"))
				va.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("wireless"))
				wireless.setPrice(Double.parseDouble(elementValueRead));
            return;
        }
		
		
		

	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\Tutorial_1\\ProductCatalog.xml");
    } 
}


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
    Console console;
    Game game;
	Sound sound;
    Tablet tablet;
	
	
	Laptop laptop;
    Accessory accessory;
    static HashMap<String,Console> consoles;
    static HashMap<String,Game> games;
    static HashMap<String,Tablet> tablets;
    static HashMap<String,Laptop> laptops;
	
    static HashMap<String,Sound> sounds;

    static HashMap<String,Accessory> accessories;
    String consoleXmlFileName;
	HashMap<String,String> accessoryHashMap;
    String elementValueRead;
	String currentElement="";
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String consoleXmlFileName) {
    this.consoleXmlFileName = consoleXmlFileName;
    consoles = new HashMap<String, Console>();
	games=new  HashMap<String, Game>();
	tablets=new HashMap<String, Tablet>();
	laptops=new HashMap<String, Laptop>();
	
	sounds=new HashMap<String, Sound>();
	
	accessories=new HashMap<String, Accessory>();
	accessoryHashMap=new HashMap<String,String>();
	parseDocument();
	
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(consoleXmlFileName, this);
		
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
	
	// when xml start element is parsed store the id into respective hashmap for console,games etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("console")) 
		{
			currentElement="console";
			console = new Console();
            console.setId(attributes.getValue("id"));
		}
        if (elementName.equalsIgnoreCase("tablet"))
		{
			currentElement="tablet";
			tablet = new Tablet();
            tablet.setId(attributes.getValue("id"));
        }
		if (elementName.equalsIgnoreCase("sound"))
		{
			currentElement="sound";
			sound = new Sound();
            sound.setId(attributes.getValue("id"));
        }
		
		if (elementName.equalsIgnoreCase("laptop"))
		{
			currentElement="laptop";
			laptop = new Laptop();
            laptop.setId(attributes.getValue("id"));
        }
		
        if (elementName.equalsIgnoreCase("game"))
		{
			currentElement="game";
			game= new Game();
            game.setId(attributes.getValue("id"));
        }
        if (elementName.equals("accessory") &&  !currentElement.equals("console"))
		{
			currentElement="accessory";
			accessory=new Accessory();
			accessory.setId(attributes.getValue("id"));
	    }


    }
	// when xml end element is parsed store the data into respective hashmap for console,games etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("console")) {
			consoles.put(console.getId(),console);
			return;
        }
 
        if (element.equals("tablet")) {	
			tablets.put(tablet.getId(),tablet);
			return;
        }
		 if (element.equals("sound")) {	
			sounds.put(sound.getId(),sound);
			return;
        }
		
		 if (element.equals("laptop")) {	
			laptops.put(laptop.getId(),laptop);
			return;
        }
		
        if (element.equals("game")) {	  
			games.put(game.getId(),game);
			return;
        }
        if (element.equals("accessory") && currentElement.equals("accessory")) {
			accessories.put(accessory.getId(),accessory);       
			return; 
        }
		if (element.equals("accessory") && currentElement.equals("console")) 
		{
			accessoryHashMap.put(elementValueRead,elementValueRead);
		}
      	if (element.equalsIgnoreCase("accessories") && currentElement.equals("console")) {
			console.setAccessories(accessoryHashMap);
			accessoryHashMap=new HashMap<String,String>();
			return;
		}
        if (element.equalsIgnoreCase("image")) {
		    if(currentElement.equals("console"))
				console.setImage(elementValueRead);
        	if(currentElement.equals("game"))
				game.setImage(elementValueRead);
			if(currentElement.equals("sound"))
				sound.setImage(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setImage(elementValueRead);
            if(currentElement.equals("tablet"))
				tablet.setImage(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setImage(elementValueRead);          
			return;
        }
        

		if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equals("console"))
				console.setDiscount(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("game"))
				game.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("sound"))
				sound.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("laptop"))
				laptop.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("tablet"))
				tablet.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setDiscount(Double.parseDouble(elementValueRead));          
			return;
	    }


		if (element.equalsIgnoreCase("condition")) {
            if(currentElement.equals("console"))
				console.setCondition(elementValueRead);
        	if(currentElement.equals("game"))
				game.setCondition(elementValueRead);
            if(currentElement.equals("tablet"))
				tablet.setCondition(elementValueRead);
			if(currentElement.equals("sound"))
				sound.setCondition(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setCondition(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setCondition(elementValueRead);          
			return;  
		}

		if (element.equalsIgnoreCase("manufacturer")) {
            if(currentElement.equals("console"))
				console.setRetailer(elementValueRead);
        	if(currentElement.equals("game"))
				game.setRetailer(elementValueRead);
			if(currentElement.equals("sound"))
				sound.setRetailer(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setRetailer(elementValueRead);
            if(currentElement.equals("tablet"))
				tablet.setRetailer(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setRetailer(elementValueRead);          
			return;
		}

        if (element.equalsIgnoreCase("name")) {
            if(currentElement.equals("console"))
				console.setName(elementValueRead);
        	if(currentElement.equals("game"))
				game.setName(elementValueRead);
			if(currentElement.equals("sound"))
				sound.setName(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setName(elementValueRead);
			
            if(currentElement.equals("tablet"))
				tablet.setName(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setName(elementValueRead);          
			return;
	    }

if (element.equalsIgnoreCase("inventory")) {
            if(currentElement.equals("console"))
				console.setInventory(Integer.parseInt(elementValueRead));
        	if(currentElement.equals("game"))
				game.setInventory(Integer.parseInt(elementValueRead));
            if(currentElement.equals("tablet"))
				tablet.setInventory(Integer.parseInt(elementValueRead));
			
			if(currentElement.equals("sound"))
				sound.setInventory(Integer.parseInt(elementValueRead));
			 if (currentElement.equals("laptop"))
                laptop.setInventory(Integer.parseInt(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setInventory(Integer.parseInt(elementValueRead));          
			return;
	    }
	
        if(element.equalsIgnoreCase("price")){
			if(currentElement.equals("console"))
				console.setPrice(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("game"))
				game.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("sound"))
				sound.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("tablet"))
				tablet.setPrice(Double.parseDouble(elementValueRead));
			 if(currentElement.equals("laptop"))
				laptop.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setPrice(Double.parseDouble(elementValueRead));          
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
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\Tutorial_3\\ProductCatalog.xml");
    } 
	
	
	
}

package utils.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class XMLFileReader {


    private static List<File> getXMLFilesByDirectory(String path) {

        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Invalid directory path or empty");
        }//End if()

        File directory = new File(path);
        if (!directory.isDirectory() || !directory.exists()) {
            throw new IllegalArgumentException("The directory doesn't exist");
        }//End if()

        File[] files = directory.listFiles();

        //Check the files do not equal null
        assert files != null;

        //Create availableXmlFiles array to store the xml files
        List<File> availableXmlFiles = new ArrayList<>();

        for (File file : files) {

            //Adding just the XML files to availableXmlFiles array
            if (file.isFile() && file.getName().endsWith(".xml")) {
                availableXmlFiles.add(file);
            }//End if()

        }//End for()

        return availableXmlFiles;

    }//End getXMLFilesByDirectory()

    /**
     * this method search for element (tag name) in multiple files.
     */
    private static XMLSearchResult searchForElementInsideFile(File xmlFile, String tagName) {

        try {

            //Define the DOM parser to use the information in an XML document
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            //Get the element by tag name and store it in node list.
            NodeList nodeList = document.getElementsByTagName(tagName);

            //Check if node list is not empty
            if (nodeList.getLength() > 0) {

                Node node = nodeList.item(0);

                //Check if the node type is element node type
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    return new XMLSearchResult(true, node.getTextContent());
                }//End if()

            } else {
                return new XMLSearchResult(false, null);
            }//End else

        }//End searchElementTextInsideFile()
        catch (Exception e) {
            e.printStackTrace();
        }
        return new XMLSearchResult(false, null);
    }//End searchElementTextInsideFile()

    /**
     * this method get the element text found in xml file
     */
    public static String getElementTextByTagName(String directoryPath, String tagName) {

        try {
            //define list to get the xml files
            List<File> xmlFiles = getXMLFilesByDirectory(directoryPath);

            //Search for element inside each XML file
            for (File xmlFile : xmlFiles) {

                XMLSearchResult searchResult = searchForElementInsideFile(xmlFile, tagName);

                //Check if the element found in the current file
                if (searchResult.isFound()) {

                    return searchResult.getElementText();
                } else {
                    continue;
                }//End else
            }//End for()
            throw new ElementNotFoundInXMLException("Element Not Found");

        } catch (ElementNotFoundInXMLException exception) {

            exception.printStackTrace();
            return null;
        }
    }//End getElementTextByTagName

}//End class----------------------
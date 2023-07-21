package utils.readers;


public class XMLSearchResult {

    private boolean isFound;
    private String elementText;


    public XMLSearchResult(boolean isFound, String elementText) {
        this.isFound = isFound;
        this.elementText = elementText;
    }


    /**
     * @return the isFound
     */
    public boolean isFound() {
        return isFound;
    }

    /**
     * @param found the found to set
     */
    public void setFound(boolean found) {
        isFound = found;
    }


    /**
     * @return the elementText
     */

    public String getElementText() {
        return elementText;
    }

    /**
     * @param elementText the elementContent to set
     */

    public void setElementText(String elementText) {
        this.elementText = elementText;
    }


}//End XmlSearchResult class
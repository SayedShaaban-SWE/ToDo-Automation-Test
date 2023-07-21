package utils.reading_helper;

import core.custom_exceptions.UnSupportedYetException;
import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;


public class FileExtensionValidator {

    /*************** Class Variables Section ***************/
    // This variable is a detector of a file content
    private static final Tika tika = new Tika();

    /*************** Class Methods Section ***************/
    // This method to validate provided file path type valid or not
    private static String fileTypeDetector(String filePath) {
        // define file variable
        File file = new File(filePath);
        // define fileType variable to save the type inside
        String fileType = null;
        try {
            // detect the file content and save type of content inside provided file
            fileType = tika.detect(file);
        } catch (IOException e) {
            throw new UnSupportedYetException();
        }
        // return fileType as string
        return fileType;
    }

    // This method to validate provided file type with it's content as Properties file type
    public static void verifyPropertiesFileType(String filePath) {
        // define fileType String for Properties file
        String fileType = "text/x-java-properties";
        // validate file content matches fileType String
        if (!fileTypeDetector(filePath).equalsIgnoreCase(fileType)) {
            // If not then send report error and fail test case
            throw new UnSupportedYetException();
        }
    }

    // This method to validate provided file type with it's content as Excel Sheet file type
    public static void verifyExcelFileType(String filePath) {
        // define fileType String for Excel Sheet file
        String fileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        // validate file content matches fileType String
        if (!fileTypeDetector(filePath).equalsIgnoreCase(fileType)) {
            // If not then send report error and fail test case
            throw new UnSupportedYetException();
        }
    }

    // This method to validate provided file type with it's content as Properties file type
    public static void verifyJSONFileType(String filePath) {
        // define fileType String for json file
        String fileType = "application/json";
        // validate file content matches fileType String
        if (!fileTypeDetector(filePath).equalsIgnoreCase(fileType)) {
            // If not then send report error and fail test case
            throw new UnSupportedYetException();
        }
    }
}
package utils.readers;

import core.custom_exceptions.UnSupportedYetException;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static utils.reading_helper.FileHelper.getFileAbsolutePath;
import static utils.reading_helper.FileStatusValidator.isFileExist;
import static utils.reading_helper.FileStatusValidator.verifyFileStatus;


public class TextReader {

    // This method to read TXT File and return it's content as List of string
    public static List<String> readTxtFile(String txtFile) {
        // Check if file exist and get file absolute path name
        if (!isFileExist(txtFile))
            // build full path for txtFile
            txtFile = getFileAbsolutePath(txtFile);
        // Verify txtFile Status
        verifyFileStatus(txtFile);
        // Get file content line by line and save them to List of string
        try (Stream<String> lines = new BufferedReader(new InputStreamReader(new FileInputStream(txtFile), StandardCharsets.UTF_8)).lines()) {
            // Return File content after removing null or empty lines
            return removeListNullEmptyValues(lines.collect(Collectors.toList()));
        } catch (Throwable throwable) {
            // In case something went wrong
            // Throw an error to Report and fail test case
            throw new UnSupportedYetException();
        }
    }

    // This method to read TXT File and returns the content as String
    public static String readTxtFileAsString(String txtFile) {
        // Read Txt File as List then join it to String
        return StringUtils.join(readTxtFile(txtFile));
    }

    // This method to read TXT File and return specific row of the file
    public static String getRowOfTxtFile(String fileName, int rowNumber) {
        // Define List of strings to save all file content inside the list
        List<String> textContent = readTxtFile(new File(fileName).getName());
        // Check if Row number is provided and valid
        rowNumber = rowNumber >= 0 ? rowNumber : 0;
        // Check if Text file has content and not empty and the row contains data
        if (textContent.size() > 0 && textContent.size() > rowNumber && !isStringBlank(textContent.get(rowNumber)))
            return textContent.get(rowNumber);
        else {
            throw new UnSupportedYetException();
        }
    }

    // This method remove all null or empty lines from provided List
    public static List<String> removeListNullEmptyValues(List<String> listContent) {
        // Remove any line similar to "" or null (equals to empty and null)
        listContent.removeAll(Arrays.asList("", null));
        // Return cleaned List
        return listContent;
    }

    // This method checks if String isn't null, empty or blank
    public static boolean isStringBlank(String string) {
        return string == null || string.trim().isEmpty();
    }
}
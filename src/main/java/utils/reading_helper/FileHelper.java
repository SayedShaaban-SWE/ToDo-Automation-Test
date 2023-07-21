package utils.reading_helper;


import core.custom_exceptions.UnSupportedYetException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class FileHelper {
    // This variable is a detector of a file content
    private static final Tika tika = new Tika();

    // This method validate if Provided File is file or directory
    public static Boolean verifyFileIsFolder(String filePath) {
        return new File(filePath).isDirectory();
    }

    // This method validate if Provided File contains Data or Empty
    public static Boolean verifyFileContainsData(String filePath) {
        return new File(filePath).length() != 0;
    }

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

    // This method search in Test Resources Folders/sub to retrieve the file
    public static String getFileAbsolutePath(String fileName) {
        // Save file partial path if user provides fileName with path, Like: test/admin/ismail.csv
        String filePath = fileName.replace(new File(fileName).getName(), "");
        fileName = new File(fileName).getName();
        // Initialize Iterator and get all sub-Directories and files
        Iterator<File> iterator = FileUtils.iterateFiles(new File("src/"), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        // Iterate all Files content and search for File name
        while (iterator.hasNext()) {
            // Get File Path as File
            File file = iterator.next();
            // Check if File name is equal to Provided one
            if (file.getName().equalsIgnoreCase(fileName)) {
                // Check fileName contains partial path
                if (file.getPath().contains(filePath))
                    return file.getAbsolutePath();
            }
        }
        // If file not exists
        return null;
    }

    // This method search in All Project Folders/sub to retrieve the file
    public static String getFileAbsolutePath(String fileName, boolean searchAllProject) {
        // If searchAllProject is false then search inside resources in main module
        if (!searchAllProject)
            return getFileAbsolutePath(fileName);
        else {
            // Save file partial path if user provides fileName with path, Like: test/admin/test_case.csv
            String filePath = fileName.replace(new File(fileName).getName(), "");
            fileName = new File(fileName).getName();
            // Initialize Iterator and get all sub-Directories and files
            Iterator<File> iterator = FileUtils.iterateFiles(new File(System.getProperty("user.dir")),
                    TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
            // Iterate all Files content and search for File name
            while (iterator.hasNext()) {
                // Get File Path as File
                File file = iterator.next();
                // Check if File name is equal to Provided one
                if (file.getName().equalsIgnoreCase(fileName)) {
                    // Check fileName contains partial path
                    if (file.getPath().contains(filePath))
                        return file.getAbsolutePath();
                }
            }
            // If file not exists
            return null;
        }
    }

    // This method creates directory and sub-directories the file
    public static void createFolders(String filePath) {
        // Create file parent directories
        // Here a bug in case the folders exists and need to get absolute path
        if (new File(filePath).getParentFile() != null)
            new File(filePath).getParentFile().mkdirs();
    }
}
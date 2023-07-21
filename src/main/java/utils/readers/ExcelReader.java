package utils.readers;


import core.custom_exceptions.UnSupportedYetException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static utils.reading_helper.FileExtensionValidator.verifyExcelFileType;
import static utils.reading_helper.FileHelper.getFileAbsolutePath;
import static utils.reading_helper.FileStatusValidator.isFileExist;
import static utils.reading_helper.FileStatusValidator.verifyFileStatus;


public class ExcelReader {

    public static String readExcelSheet(String fileName, String sheetName, int rowNumber, String headerName) {
        ArrayList<String> sheetData = getColumnData(fileName, sheetName, headerName);

        try {
            String cellData = sheetData.get(rowNumber - 2);
            return cellData;
        } catch (Exception ex) {
            throw new UnSupportedYetException();
        }
    }

    // This method retrieve all excel sheets from excel file
    private static Workbook readExcelWorkbook(String excelFile) {
        // Check if provided file name only without path then add test resources full path
        if (!isFileExist(excelFile))
            // build full path for excelSheet file
            excelFile = getFileAbsolutePath(excelFile, true);
        // Verify excelFile Status
        verifyFileStatus(excelFile);
        // Verify excelFile content Type
        verifyExcelFileType(excelFile);
        // Define then Initialize Workbook Object
        Workbook workbook = null;
        try {
            // Read Excel file and retrieve all sheets and content
            workbook = WorkbookFactory.create(new FileInputStream(excelFile));
        } catch (IOException e) {
            throw new UnSupportedYetException();
        }
        return workbook;
    }

    //This method retrieve a specific sheet by sheet name
    private static Sheet getExcelSheetByName(String excelFile, String sheetName) {
        try {
            return readExcelWorkbook(excelFile).getSheet(sheetName);
        } catch (Throwable throwable) {
            throw new UnSupportedYetException();
        }
    }

    //This method retrieve a specific sheet by sheet index number
    private static Sheet getExcelSheetByIndex(String excelFile, int sheetNo) {
        try {
            // Check if sheetNo is zero
            sheetNo = sheetNo > 0 ? sheetNo : 1;
            // Get sheet name
            String sheetName = readExcelWorkbook(excelFile).getSheetAt(sheetNo - 1).getSheetName();
            return getExcelSheetByName(excelFile, sheetName);
        } catch (Throwable throwable) {
            throw new UnSupportedYetException();
        }
    }

    private static String getCellValue(Cell cell) {
        // Extract Cell value from Cell object
        switch (cell.getCellType()) {
            case _NONE:
            case BLANK:
            case ERROR:
            case FORMULA: {
                return "";
            }
            case NUMERIC: {
                String value = String.valueOf(cell.getNumericCellValue());
                return !value.endsWith(".0") ? value : value.replace(".0", "");
            }
            case STRING: {
                return cell.getStringCellValue();
            }
            case BOOLEAN: {
                return String.valueOf(cell.getBooleanCellValue());
            }

            default:
                return "";
        }
    }

    private static ArrayList<String> getRowData(Row row) {
        // Check if row object isn't null
        if (row == null)
            throw new UnSupportedYetException();
        // Get each cell value and save them to Array
        ArrayList<String> rowData = new ArrayList<>();
        for (Cell cell : row) {
            String cellValue = getCellValue(cell) != null ? getCellValue(cell) : "";
            // Add data to the list
            rowData.add(cellValue);
        }
        // Remove all empty values from Array List
        rowData.removeAll(List.of(""));
        return rowData;
    }

    private static ArrayList<ArrayList<String>> getSheetData(String excelFile, int sheetNo) {
        // Retrieve all Sheet Data
        return getSheetData(getExcelSheetByIndex(excelFile, sheetNo));
    }

    // This method retrieves all Rows in specific excel sheet name
    public static ArrayList<ArrayList<String>> getSheetData(String excelFile, String sheetName) {
        // Retrieve all Sheet Data
        return getSheetData(getExcelSheetByName(excelFile, sheetName));
    }

    private static ArrayList<ArrayList<String>> getSheetData(Sheet sheet) {
        // Define sheet Rows as Iterator
        Iterator<Row> allRows = sheet.rowIterator();
        // Define rowsData variable to save data of each Row
        ArrayList<ArrayList<String>> rowsData = new ArrayList<>();
        // Retrieve all data of each row and save as item inside rowsData
        while (allRows.hasNext()) {
            // Save current row data
            Row row = allRows.next();
            ArrayList<String> rowData = getRowData(row);
            // Retrieve values of row
            if (rowData.size() > 0)
                rowsData.add(rowData);
        }
        return rowsData;
    }

    // This method retrieves column values with file, sheet name and column header
    private static ArrayList<String> getColumnData(String excelFile, String sheetName, String headerName) {
        // Retrieve all sheet data rows
        ArrayList<ArrayList<String>> rows = getSheetData(excelFile, sheetName);
        // Extract column index from header name
        int columnNo = rows.get(0).indexOf(headerName) + 1;
        // Retrieve column values
        return getColumnData(rows, columnNo);
    }

    // This method retrieves column values with file, sheet number and column header
    private static ArrayList<String> getColumnData(String excelFile, int sheetNo, String headerName) {
        // Retrieve all sheet data rows
        ArrayList<ArrayList<String>> rows = getSheetData(excelFile, sheetNo);
        // Extract column index from header name
        int columnNo = rows.get(0).indexOf(headerName) + 1;
        // Check if column number is zero(that means the provided header name isn't exist)
        if (columnNo == 0)
            throw new UnSupportedYetException();
        // Retrieve column values
        return getColumnData(rows, columnNo);
    }

    private static ArrayList<String> getColumnData(ArrayList<ArrayList<String>> rows, int columnNo) {
        // Define column values list
        ArrayList<String> columnValues = new ArrayList<>();
        // Check if column index is zero
        columnNo = columnNo > 0 ? columnNo : 1;
        for (ArrayList<String> row : rows) {
            // Check if cell values exists in the row
            String cellValue = row.size() >= columnNo ? row.get(columnNo - 1) : "";
            columnValues.add(cellValue);
        }
        // Remove header info from values
        columnValues.remove(0);
        return columnValues;
    }

}
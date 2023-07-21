package utils.readers;


public class ExcelEnums {

    public static final String EMPTY_SHEET_ERROR;
    public static final String IO_ERROR;
    public static final String EXISTED_SHEET_ERROR;
    public static final String EXISTED_HEADER_NAME_ERROR;
    public static final String NULL_ROW_DATA_ERROR;
    public static final String FILE_NOT_FOUND_ERROR;
    public static final String FOLDER_INSTEAD_OF_FILE_ERROR;
    public static final String EMPTY_FILE_ERROR;
    public static final String FILER_ERROR_TYPE;
    public static final String EXCEL_FILE_TYPE;
    public static final String NUMBER_FORMAT_ERROR;

    static {
        EMPTY_SHEET_ERROR = "The sheet is empty, please check your input file";
        IO_ERROR = "Unable to read provided excel sheet, due to this exception:\n ";
        EXISTED_SHEET_ERROR = "Please check if you had provided an existed sheet name, You had provided sheet name: ";
        EXISTED_HEADER_NAME_ERROR = "Please check the excel header name, the one you'd provided doesn't exist, You'd provided: ";
        NULL_ROW_DATA_ERROR = "You provided Row Object with null value in getRowData method.";
        FILE_NOT_FOUND_ERROR = "File isn't exist, Please check File Path: ";
        FOLDER_INSTEAD_OF_FILE_ERROR = "The Provided is folder not a file, Please check Folder Path: ";
        EMPTY_FILE_ERROR = "The Provided an Empty File, Please check file Path: ";
        FILER_ERROR_TYPE = "The Provided File isn't Excel Sheet fileType, please check the file: ";
        NUMBER_FORMAT_ERROR = "The Provided row isn't existed, please check the row value: ";
        EXCEL_FILE_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    }
}
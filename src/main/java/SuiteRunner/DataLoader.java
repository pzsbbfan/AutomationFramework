package SuiteRunner;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class DataLoader {

    public static void loadConfig(String configFilePath) throws IOException {
        Properties properties = new Properties();
        try (FileInputStream configInput = new FileInputStream(configFilePath)) {
            properties.load(configInput);
        }

        for (String key : properties.stringPropertyNames()) {
            System.setProperty(key, properties.getProperty(key));
        }
    }


    public static List<Map<String, String>> loadTestData(String excelFilePath) throws IOException {
        List<Map<String, String>> testData = new ArrayList<>();
        try (FileInputStream excelFile = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(excelFile)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                Map<String, String> dataRow = new HashMap<>();
                for (int j = 0; j < currentRow.getLastCellNum(); j++) {
                    dataRow.put(headerRow.getCell(j).getStringCellValue(), currentRow.getCell(j).getStringCellValue());
                }
                testData.add(dataRow);
            }
        }
        return testData;
    }
}

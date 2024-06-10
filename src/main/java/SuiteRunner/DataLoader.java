package SuiteRunner;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class DataLoader {

    public static Properties loadConfig(String configFilePath) throws IOException {
        Properties config = new Properties();
        try (FileInputStream input = new FileInputStream(configFilePath)) {
            config.load(input);
        }

        // Set each property to system properties
        for (String key : config.stringPropertyNames()) {
            System.setProperty(key, config.getProperty(key));
        }

        return config;
    }


    public static List<Map<String, String>> loadTestData(String testDataFilePath) throws IOException {
        List<Map<String, String>> testData = new ArrayList<>();
        try (FileInputStream excelFile = new FileInputStream(testDataFilePath);
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

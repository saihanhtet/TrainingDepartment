package com.database;

import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.entity.Course;

public class ExcelReader {
    public static List<Course> readCoursesFromExcel(InputStream inputStream) {
        List<Course> courseList = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> rowIterator = sheet.iterator();
            int rowNum = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                rowNum++;

                if (rowNum == 1) {
                    // Skip the header row
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();
                String courseName = "";
                double price = 0;

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();

                    switch (columnIndex) {
                        case 0:
                            // Assuming course name is in the first column (0-based)
                        	// changed getCellType into getCellTypeEnum
                            if (cell.getCellTypeEnum() == CellType.STRING) {
                                courseName = cell.getStringCellValue();
                            }
                            break;

                        case 1:
                            // Assuming price is in the second column (0-based)
                            if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                price = cell.getNumericCellValue();
                            }
                            break;

                        default:
                            // Handle additional columns if needed
                            break;
                    }
                }

                // Create and add Course object to the list
                courseList.add(new Course(courseName, price));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return courseList;
    }
}

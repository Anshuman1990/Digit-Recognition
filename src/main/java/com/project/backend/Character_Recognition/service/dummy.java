package com.project.backend.Character_Recognition.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class dummy {

    public static void main(String[] args) throws IOException {
        File template = new File("D:\\Ansprojects\\Character Recognition\\src\\main\\resources\\templates\\Template.xlsx");
        FileInputStream fsIP = new FileInputStream(template);
        XSSFWorkbook workbook = new XSSFWorkbook(fsIP);
        XSSFSheet sheet = workbook.getSheet("INTERNALS");
        Row row = sheet.getRow(9);
        for(int i=0;i<10;i++){
            row.createCell(i).setCellValue("qwerty");
        }
        System.out.println(row.getCell(1).getStringCellValue());

        try {
            // this Writes the workbook gfgcontribute
            FileOutputStream out = new FileOutputStream(template);
            workbook.write(out);
            out.close();
            System.out.println("gfgcontribute.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

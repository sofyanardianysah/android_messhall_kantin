package dev.sofie.messhalkantin.helper;

import android.os.Environment;
import android.util.Log;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import dev.sofie.messhalkantin.model.Report;

public class ExcelHelper {
    public static boolean report(String filename, List<Report> reports){
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");
        Boolean result;
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Report"); //Creating a sheet
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("No");
        headerRow.createCell(1).setCellValue("Transaksi");
        headerRow.createCell(2).setCellValue("Total");
        headerRow.createCell(3).setCellValue("Tanggal");

        for(int  i=0; i<reports.size(); i++){
            int j = i+1;
            Row row = sheet.createRow(j);
            row.createCell(0).setCellValue(j);
            row.createCell(1).setCellValue(reports.get(i).getTransaksi());
            row.createCell(2).setCellValue(MoneyHelper.keRupiah(Double.valueOf(reports.get(i).getTotal())));
            row.createCell(3).setCellValue(reports.get(i).getDay());
        }

        String fileName = filename+".xls";

        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File folder = new File(extStorageDirectory, "MesshallReport");// Name of the folder you want to keep your file in the local storage.
        folder.mkdir(); //creating the folder
        File file = new File(folder, fileName);
        try {
            file.createNewFile();
        } catch (IOException e1) {
            Log.e("ERROR New File",e1.getMessage());
            e1.printStackTrace();
        }

        try {
            FileOutputStream fileOut = new FileOutputStream(file); //Opening the file
            workbook.write(fileOut); //Writing all your row column inside the file
            fileOut.close(); //closing the file and done
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("File Not Found",e.getMessage());
            result = false;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("IO Exception",e.getMessage());
            result = false;
        }
        return result;

    }

}

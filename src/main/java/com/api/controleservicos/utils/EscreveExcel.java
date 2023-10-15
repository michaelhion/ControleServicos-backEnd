package com.api.controleservicos.utils;

import com.api.controleservicos.dto.DadosFaturamento;
import com.api.controleservicos.models.Faturamento;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class EscreveExcel {

    private static String[] columns = {"Nome da Cliente", "Nome do Serviço", "Data", "Valor"};

    public byte[] gerarExcel(List<DadosFaturamento> faturamento) throws IOException {
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

    /* CreationHelper helps us create instances of various things like DataFormat,
       Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Faturamento");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());


        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Define columns
        String[] columns = {"Cliente", "Serviço", "Data", "Valor"};

        // Create cells for headers
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

        CellStyle currencyCellStyle = workbook.createCellStyle();
        currencyCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("R$ #,##0.00"));

        // Create rows and cells with faturamento data
        int rowNum = 1;
        for (DadosFaturamento fat : faturamento) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(fat.nomeCliente());

            row.createCell(1)
                    .setCellValue(fat.nomeServico());

            Cell dateOfBirthCell = row.createCell(2);
            dateOfBirthCell.setCellValue(fat.data());
            dateOfBirthCell.setCellStyle(dateCellStyle);

            Cell valorCell = row.createCell(3);
            valorCell.setCellValue(fat.valor().toString());
            valorCell.setCellStyle(currencyCellStyle);

        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the workbook content to a byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        workbook.close();

        return byteArrayOutputStream.toByteArray();
    }


}

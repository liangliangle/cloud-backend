package com.lianglianglee.cloud.common.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;


/**
 * excel处理.
 */
public class ExcelUtil {
  public static void createExcel(HttpServletResponse response, List<List<String>> entities, String fileName) {
    Workbook xwb = new HSSFWorkbook();
    // 获取excel表的第一个sheet
    Sheet xsheet = xwb.createSheet();

    for (int i = 0; i < entities.size(); i++) {
      Row row = xsheet.createRow(i);
      List<String> rowData = entities.get(i);
      for (int j = 0; j < rowData.size(); j++) {
        Cell valueCell = row.createCell(j);
        valueCell.setCellValue(rowData.get(j));
      }
    }
    OutputStream out = null;
    try {
      FileOutputStream fout = new FileOutputStream(fileName + ".xls");
      xwb.write(fout);
      fout.close();
      out = response.getOutputStream();
      //FileOutputStream os = new FileOutputStream("e:/123.xls");
      File file = new File(fileName + ".xls");
      response.setHeader("content_Length", String.valueOf(file.length()));
      int b = 0;
      byte[] buff = new byte[1024];
      FileInputStream is = new FileInputStream(file);
      while ((b = is.read(buff)) != -1) {
        out.write(buff, 0, b);
      }
      is.close();
      out.flush();
      xwb.close();
      file.delete();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (out != null) {
        try {
          out.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

  }


}

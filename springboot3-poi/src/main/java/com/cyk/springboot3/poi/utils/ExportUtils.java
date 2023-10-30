package com.cyk.springboot3.poi.utils;

import com.cyk.springboot3.poi.entity.BootUser;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author cyk
 * @date 2023/10/30 21:41
 */
public class ExportUtils {

    public static void ExportBootUser(HttpServletResponse response, String sheetName, String[] title, List<BootUser> bootUserList) {
        //新建文档实例
        XSSFWorkbook workbook = new XSSFWorkbook();

        //在文档中添加表单
        XSSFSheet sheet = workbook.createSheet(sheetName);

        //创建单元格格式，并设置居中
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFCellStyle style2 = workbook.createCellStyle();

        style.setAlignment(HorizontalAlignment.CENTER);
        style2.setAlignment(HorizontalAlignment.CENTER);

        //标题边框
        //下边框
        style.setBorderBottom(BorderStyle.THICK);
        //左边框
        style.setBorderLeft(BorderStyle.THICK);
        //右边框
        style.setBorderRight(BorderStyle.THICK);
        //上边框
        style.setBorderTop(BorderStyle.THICK);

        //普通边框
        //下边框
        style2.setBorderBottom(BorderStyle.THIN);
        //左边框
        style2.setBorderLeft(BorderStyle.THIN);
        //右边框
        style2.setBorderRight(BorderStyle.THIN);
        //上边框
        style2.setBorderTop(BorderStyle.THIN);

        //标题字体
        XSSFFont font = workbook.createFont();
        font.setFontName("仿宋_GB2312");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setColor(Font.COLOR_RED);
        style.setFont(font);

        //创建第一行，用于填充标题
        XSSFRow titleRow = sheet.createRow(0);

        //填充表头标题
        for (int i = 0; i < title.length; i++) {
            sheet.setColumnWidth(i, 18 * 256);
            //创建单元格
            XSSFCell cell = titleRow.createCell(i);
            //设置单元格内容
            cell.setCellValue(title[i]);
            //设置单元格样式
            cell.setCellStyle(style);
        }

        //填充内容
        //行号
        int i = 1;

        XSSFRow row;
        for (BootUser bootUser : bootUserList) {
            //创建行
            row = sheet.createRow(i);

            //创建单元格
            XSSFCell cell0 = row.createCell(0);
            //设置单元格内容
            cell0.setCellValue(bootUser.getUserName());
            //设置单元格样式
            cell0.setCellStyle(style2);

            //创建单元格
            XSSFCell cell1 = row.createCell(1);
            //设置单元格内容
            cell1.setCellValue(bootUser.getUserSex() == 0 ? "男" : "女");
            //设置单元格样式
            cell1.setCellStyle(style2);

            //创建单元格
            XSSFCell cell2 = row.createCell(2);
            //设置单元格内容
            cell2.setCellValue(bootUser.getUserAge());
            //设置单元格样式
            cell2.setCellStyle(style2);

            //创建单元格
            XSSFCell cell3 = row.createCell(3);
            //设置单元格内容
            cell3.setCellValue(bootUser.getUserPassword());
            //设置单元格样式
            cell3.setCellStyle(style2);

            //创建单元格
            XSSFCell cell4 = row.createCell(4);
            //设置单元格内容
            cell4.setCellValue(bootUser.getUserStatus() == 0 ? "启用" : "禁用");
            //设置单元格样式
            cell4.setCellStyle(style2);

            i++;
        }

        //声明输出流
        OutputStream outputStream = null;
        //响应到客户端
        try {
            //表格文件名称
            String fileName = sheetName + ".xlsx";
            //设置响应头
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            //获取输出流
            outputStream = response.getOutputStream();

            //用文档写输出流
            workbook.write(outputStream);

            //刷新输出流
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭输出流
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

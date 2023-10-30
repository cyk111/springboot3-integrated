package com.cyk.springboot3.poi.utils;

import com.cyk.springboot3.poi.entity.BootUser;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author cyk
 * @date 2023/10/30 21:48
 */
public class ImportUtils {

    public static List<BootUser> ImportBootUser(MultipartFile file) {
        List<BootUser> bootUsers = Lists.newArrayList();
        XSSFWorkbook workBook = null;
        try (InputStream inputStream = file.getInputStream()) {
            //读取文件流
            workBook = new XSSFWorkbook(inputStream);
            //读取工作表
            XSSFSheet sheet = workBook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                //读取行
                XSSFRow row = sheet.getRow(i);

                //读取单元格
                XSSFCell cell0 = row.getCell(0);
                XSSFCell cell1 = row.getCell(1);
                XSSFCell cell2 = row.getCell(2);
                XSSFCell cell3 = row.getCell(3);
                XSSFCell cell4 = row.getCell(4);

                //设置单元格类型
                cell2.setCellType(CellType.STRING);
                cell3.setCellType(CellType.STRING);

                BootUser bootUser = new BootUser();
                bootUser.setUserName(cell0.getStringCellValue());
                bootUser.setUserSex("男".equals(cell1.getStringCellValue().trim()) ? 0 : 1);
                bootUser.setUserAge((int) Float.parseFloat(cell2.getStringCellValue()));
                bootUser.setUserPassword(cell3.getStringCellValue());
                bootUser.setUserStatus("启用".equals(cell4.getStringCellValue().trim()) ? 0 : 1);
                bootUsers.add(bootUser);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workBook != null) {
                try {
                    workBook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bootUsers;
    }
}

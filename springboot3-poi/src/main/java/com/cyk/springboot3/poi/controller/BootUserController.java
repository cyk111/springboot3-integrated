package com.cyk.springboot3.poi.controller;

import com.cyk.springboot3.poi.common.Result;
import com.cyk.springboot3.poi.entity.BootUser;
import com.cyk.springboot3.poi.service.BootUserService;
import com.cyk.springboot3.poi.utils.ExportUtils;
import com.cyk.springboot3.poi.utils.ImportUtils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author cyk
 * @date 2023/10/30 21:49
 */
@RestController
@RequestMapping("/boot-user")
public class BootUserController {

    @Resource
    private BootUserService bootUserService;

    @Operation(description = "查询全部用户")
    @GetMapping("/bootUserList")
    public Result<List<BootUser>> bootUserList() {
        return Result.success(bootUserService.list());
    }

    @Operation(description = "导出用户数据")
    @GetMapping("exportBootUser")
    public void exportBootUser(HttpServletResponse response) {
        String sheetName = "用户数据";
        String[] title = {"用户名", "性别", "年龄", "密码", "状态"};
        List<BootUser> bootUserList = bootUserService.list();
        ExportUtils.ExportBootUser(response, sheetName, title, bootUserList);
    }

    @Operation(description = "导入用户数据")
    @PostMapping("importBootUser")
    public void importBootUser(@RequestParam("file") MultipartFile file) {
        List<BootUser> users = ImportUtils.ImportBootUser(file);
        for (BootUser user : users) {
            bootUserService.save(user);
        }
    }
}

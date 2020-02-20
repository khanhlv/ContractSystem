package com.contract.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.contract.exception.NotFoundDBException;
import com.contract.form.TemplateForm;
import com.contract.model.Template;
import com.contract.model.TemplateFile;
import com.contract.service.CategoryService;
import com.contract.service.CompanyService;
import com.contract.service.TemplateFileService;
import com.contract.service.TemplateService;

@Controller
@RequestMapping(value = "/template")
public class TemplateController extends AbstractController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateFileService templateFileService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "/list"})
    public String index(Model model, TemplateForm templateForm) {
        model.addAttribute("form", templateForm);
        model.addAttribute("templateList", templateService.findAllWithPagination(templateForm));
        model.addAttribute("companyList", companyService.findByStatus(1L));
        model.addAttribute("categoryList", categoryService.findCategoryByStatus(1L));
        return "template/list";
    }

    @GetMapping("/add")
    public String add(Model model, TemplateForm templateForm) {
        model.addAttribute("form", templateForm);
        model.addAttribute("companyList", companyService.findByStatus(1L));
        model.addAttribute("categoryList", categoryService.findCategoryByStatus(1L));
        model.addAttribute("templateFileList", new ArrayList<TemplateFile>());
        return "template/form";
    }

    private List<File> uploadFile(Long templateId, TemplateForm templateForm) {
        // Thư mục gốc upload file.
        String uploadRootPath = "D:/ContractSystem/data/template/" + templateId + "/";

        File file = new File(uploadRootPath);

        try {
            FileUtils.forceMkdir(file);
        }catch (Exception ex) {

        }

        MultipartFile[] fileDatas = templateForm.getFiles();

        List<File> uploadedFiles = new ArrayList<>();

        List<String> failedFiles = new ArrayList<>();

        for (MultipartFile fileData : fileDatas) {

            // Tên file gốc tại Client.
            String name = fileData.getOriginalFilename();

            if (name != null && name.length() > 0) {
                try {
                    // Tạo file tại Server.
                    File serverFile = new File(uploadRootPath  + name);

                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();

                    uploadedFiles.add(serverFile);
                } catch (Exception e) {
                    failedFiles.add(name);
                }
            }
        }

        return uploadedFiles;
    }
    @PostMapping("/doSave")
    public String doSave(Model model, HttpServletRequest request, TemplateForm templateForm) {
        if (templateForm.getTemplateId() != null) {
            Template template = templateService.getTemplateRepository()
                    .findById(templateForm.getTemplateId()).orElse(null);

            if (template == null) {
                throw new NotFoundDBException("Mã mẫu hợp đồng không tồn tại.");
            }

            BeanUtils.copyProperties(templateForm, template, "createdDate", "createdUserId");

            template.setUpdatedDate(new Date());
            template.setUpdatedUserId(getUserId(request));

            templateService.getTemplateRepository().save(template);

            model.addAttribute("messsage", "Chỉnh sửa mẫu hợp đồng thành công. Xin vui lòng đợi để chuyển hướng.");
            model.addAttribute("redirectPath", "/template/list");
        } else {
            Template template = new Template();
            BeanUtils.copyProperties(templateForm, template);

            template.setCreatedDate(new Date());
            template.setCreatedUserId(getUserId(request));

            templateService.getTemplateRepository().save(template);

            List<File> fileList = uploadFile(template.getTemplateId(), templateForm);

            fileList.forEach(f -> {
                TemplateFile templateFile = new TemplateFile();
                templateFile.setCreatedDate(new Date());
                templateFile.setCreatedUserId(getUserId(request));
                templateFile.setTemplateFileName(f.getName());
                templateFile.setTemplateFilePath(f.getAbsolutePath());
                templateFile.setTemplateId(template.getTemplateId());
                templateFileService.getTemplateFileRepository().save(templateFile);
            });

            model.addAttribute("messsage", "Thêm mới mẫu hợp đồng thành công. Xin vui lòng đợi để chuyển hướng.");
            model.addAttribute("redirectPath", "/template/list");
        }

        model.addAttribute("form", templateForm);

        return "template/form";
    }

    @GetMapping("/edit/{id}")
    public String add(Model model,
                      @PathVariable(name = "id") String id,
                      TemplateForm templateForm) {

        Template template = templateService.getTemplateRepository()
                .findById(NumberUtils.toLong(id)).orElse(null);

        if (template == null) {
            throw new NotFoundDBException("Mã mẫu hợp đồng không tồn tại.");
        }

        BeanUtils.copyProperties(template, templateForm);
        model.addAttribute("companyList", companyService.findByStatus(1L));
        model.addAttribute("categoryList", categoryService.findCategoryByStatus(1L));
        model.addAttribute("form", templateForm);

        model.addAttribute("templateFileList", templateFileService.findByTemplateId(Long.valueOf(id)));

        return "template/form";
    }

    @GetMapping("/deleteFile/{id}")
    public String deleteFile(Model model,
                             @PathVariable(name = "id") String id) {
        TemplateFile templateFile = templateFileService.getTemplateFileRepository()
                .findById(NumberUtils.toLong(id)).orElse(null);

        if (templateFile == null) {
            throw new NotFoundDBException("File mẫu hợp đồng không tồn tại.");
        }

        // Delete template
        templateFileService.getTemplateFileRepository().deleteById(templateFile.getTemplateFileId());

        FileUtils.deleteQuietly(new File(templateFile.getTemplateFilePath()));

        return "redirect:/template/edit/" + templateFile.getTemplateId();
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(name = "id") String id) {
        Template template = templateService.getTemplateRepository()
                .findById(NumberUtils.toLong(id)).orElse(null);

        if (template == null) {
            throw new NotFoundDBException("Mã mẫu hợp đồng không tồn tại.");
        }

        // Delete template
        templateService.getTemplateRepository().deleteById(template.getTemplateId());

        // Delete template file

        // Delete file

        return "redirect:/template/list";
    }
}

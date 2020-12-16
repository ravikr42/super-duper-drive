package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.FileDoc;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication auth, Model model) {
        Integer userId = userService.getUserIdByName(auth.getName());
        if (fileUpload.isEmpty()) {
            model.addAttribute("errorMessage", "File not supplied for Upload, Kindly Upload File.");
            return "result";
        } else if (fileService.isDuplicateFile(fileUpload, userId)) {
            model.addAttribute("errorMessage", "File Already Exists, Try Uploading a Different File.");
            return "result";
        } else {
            int rowAdded = fileService.addFile(fileUpload, userId);
            if (rowAdded != 1) {
                model.addAttribute("isError", true);
                return "result";
            }
        }
        model.addAttribute("isSuccess", true);
        return "result";
    }

    @GetMapping("/download/{fileId:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable Integer fileId) {
        FileDoc file = fileService.getFile(fileId);
        try {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" +
                            file.getFilename() + "\"")
                    .body(new ByteArrayResource(file.getFiledata()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/delete/{fileId:.+}")
    public String deleteFile(@PathVariable Integer fileId, Model model) {
        int rowsUpdated = fileService.deleteFile(fileId);
        if (rowsUpdated == 1) {
            model.addAttribute("isSuccess", true);
            return "result";
        } else {
            model.addAttribute("isError", true);
            return "result";
        }

    }


}

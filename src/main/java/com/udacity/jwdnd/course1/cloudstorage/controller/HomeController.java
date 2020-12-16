package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.FileDoc;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private UserService userService;
    private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;
    private EncryptionService encryptionService;


    public HomeController(UserService userService, FileService fileService, NoteService noteService,
                          CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public String getHomeView(Authentication auth, Model model) {

        Integer userId = userService.getUserIdByName(auth.getName());
        List<FileDoc> files = fileService.getFilesByUserId(userId);
        List<Note> notes = noteService.getNotesByUserId(userId);
        List<Credential> credentials = credentialService.getCredentialsListById(userId);


        model.addAttribute("username", StringUtils.capitalize(auth.getName()));
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("files", files);
        model.addAttribute("notes", notes);
        model.addAttribute("credentials", credentials);

        return "home";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }

    @GetMapping("/logout")
    public String logoutView() {
        return "redirect:/login?logout";
    }

}

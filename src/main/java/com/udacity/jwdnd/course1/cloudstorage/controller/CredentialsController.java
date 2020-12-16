package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping("credentials")
public class CredentialsController {
    private UserService userService;
    private CredentialService credentialService;
    private EncryptionService encryptionService;

    public CredentialsController(UserService userService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @PostMapping("/add")
    public String addCredential(Credential credential, Authentication auth, Model model) {
        if (credential.getCredentialId() == null) {
            Integer userId = userService.getUserIdByName(auth.getName());
            String key = encryptionService.getKey();
            credential.setKeya(key);
            credential.setPassword(encryptionService.encryptValue(credential.getPassword(), key));
            credential.setUserId(userId);
            int rowUpdated = credentialService.addCredential(credential);
            if (rowUpdated == 1) {
                model.addAttribute("isSuccess", true);
                return "result";
            } else {
                model.addAttribute("isError", true);
                return "result";
            }
        } else {
            Credential updatedCredential = credential;
            String key = encryptionService.getKey();
            updatedCredential.setKeya(key);
            updatedCredential.setPassword(encryptionService.encryptValue(credential.getPassword(), key));
            int rowsUpdated = credentialService.updateCredential(credential);
            if (rowsUpdated == 1) {
                model.addAttribute("isSuccess", true);
                return "result";
            } else {
                model.addAttribute("isError", true);
                return "result";
            }

        }

    }

    @GetMapping("/delete/{credId:.+}")
    public String deleteCred(@PathVariable Integer credId, Model model) {
        int rowsUpdated = credentialService.deleteCredential(credId);
        if (rowsUpdated == 1) {
            model.addAttribute("isSuccess", true);
            return "result";
        } else {
            model.addAttribute("isError", true);
            return "result";
        }
    }


}

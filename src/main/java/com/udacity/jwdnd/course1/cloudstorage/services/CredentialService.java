package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private CredentialsMapper mapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialsMapper mapper, EncryptionService encryptionService) {
        this.mapper = mapper;
        this.encryptionService = encryptionService;
    }

    public int addCredential(Credential credential) {
        return mapper.insert(credential);
    }

    public List<Credential> getCredentialsListById(Integer userId) {
        return mapper.getCredentialsByUserId(userId);
    }

    public int updateCredential(Credential credential) {
        return mapper.updateCredential(credential);
    }

    public int deleteCredential(Integer id) {
        return mapper.deleteCredential(id);
    }
}

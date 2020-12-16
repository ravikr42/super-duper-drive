package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.FileDoc;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int addFile(MultipartFile multipartFile, Integer userId) {
        FileDoc file = new FileDoc();
        try {
            file.setContenttype(multipartFile.getContentType());
            file.setFiledata(multipartFile.getBytes());
            file.setFilename(multipartFile.getOriginalFilename());
            file.setUserid(userId);
            file.setFilesize(multipartFile.getSize() + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileMapper.insertFile(file);
    }

    public boolean isDuplicateFile(MultipartFile multipartFile, Integer userId) {
        FileDoc file = fileMapper.findDuplicateFile(multipartFile.getOriginalFilename(), userId);
        if (file != null) {
            return true;
        } else
            return false;
    }

    public List<FileDoc> getFilesByUserId(Integer userId) {
        return fileMapper.getFileByUserId(userId);

    }

    public FileDoc getFile(Integer fileid) {
        return fileMapper.getFile(fileid);
    }

    public int deleteFile(Integer id){
        return fileMapper.deleteFile(id);
    }
}

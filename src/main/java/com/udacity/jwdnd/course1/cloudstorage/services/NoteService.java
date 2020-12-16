package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }


    public int addNote(Note note) {
        return noteMapper.addNote(note);
    }

    public int updateNote(Note note) {
        return noteMapper.updateNote(note);
    }

    public List<Note> getNotesByUserId(Integer id) {
        return noteMapper.getNotesByUserId(id);
    }

    public int deleteNote(Integer id){
        return noteMapper.deleteNote(id);
    }

}

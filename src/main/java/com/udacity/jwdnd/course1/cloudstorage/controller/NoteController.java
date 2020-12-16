package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class NoteController {
    private NoteService noteService;
    private UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createNote(Note note, Authentication auth, Model model) {
        Integer userId = userService.getUserIdByName(auth.getName());
        if (note.getNoteId() == null) {
            Note newNote = new Note();
            newNote.setUserid(userId);
            newNote.setNoteTitle(note.getNoteTitle());
            newNote.setNoteDesc(note.getNoteDesc());
            int rowAdded = noteService.addNote(newNote);
            if (rowAdded != 1) {
                model.addAttribute("isError", true);
                return "result";
            } else {
                model.addAttribute("isSuccess", true);
                return "result";
            }
        } else {
            int rowUpdated = noteService.updateNote(note);
            if (rowUpdated == 1) {
                model.addAttribute("isSuccess", true);
                return "result";
            } else {
                model.addAttribute("isError", true);
                return "result";
            }
        }
    }

    @GetMapping("/delete/{noteId:.+}")
    public String deleteNote(@PathVariable Integer noteId, Model model) {
        int rowsUpdated = noteService.deleteNote(noteId);
        if (rowsUpdated == 1) {
            model.addAttribute("isSuccess", true);
            return "result";
        } else {
            model.addAttribute("isError", true);
            return "result";
        }
    }


}

package com.udacity.jwdnd.course1.cloudstorage.models;


public class Note {
    private Integer noteId;
    private String noteTitle;
    private String noteDesc;
    private Integer userid;

    public Note() {
    }

    public Note(Integer noteId, String noteTitle, String noteDesc, Integer userid) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDesc = noteDesc;
        this.userid = userid;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDesc() {
        return noteDesc;
    }

    public void setNoteDesc(String noteDesc) {
        this.noteDesc = noteDesc;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteDesc='" + noteDesc + '\'' +
                ", userid=" + userid +
                '}';
    }
}


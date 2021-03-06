package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("insert into notes (noteTitle, notedesc, userid) values (#{noteTitle}, #{noteDesc}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int addNote(Note note);

    @Select("select * from NOTES  where userid = #{id}")
    List<Note> getNotesByUserId(Integer id);

    @Update("update notes set notetitle=#{noteTitle}, notedesc=#{noteDesc} where noteId = #{noteId}")
    int updateNote(Note note);

    @Delete("Delete from notes where noteid=#{noteId}")
    int deleteNote(Integer id);

}

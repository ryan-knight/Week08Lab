/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.NoteDB;
import database.NotesDBException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import models.Note;

/**
 *
 * @author 763198
 */
public class NoteService
{

    private NoteDB noteDB;

    public NoteService()
    {
        this.noteDB = new NoteDB();
    }

    public Note get(int noteId)
    {
        try
        {
            return noteDB.getNote(noteId);
        } catch (NotesDBException ex)
        {
            return null;
        }
    }

    public List<Note> getAll()
    {
        try
        {
            return noteDB.getAll();
        } catch (NotesDBException ex)
        {
            return null;
        }
    }

    public int update(int noteId, String contents)
    {
        try
        {
            return noteDB.update(new Note(noteId, null, contents));
        } catch (NotesDBException ex)
        {
            return 0;
        }
    }

    public int delete(int noteId)
    {
        try
        {
            return noteDB.delete(new Note(noteId, null, null));
        } catch (NotesDBException ex)
        {
            return 0;
        }
    }

    public int insert(String contents)
    {
        Note note = new Note();
        note.setContents(contents);

        Date date = new Date(System.currentTimeMillis());
        note.setDateCreated(date);

        try
        {
            int id = 0;
            ArrayList<Note> list = (ArrayList<Note>) noteDB.getAll();
            for (Iterator<Note> it = list.iterator(); it.hasNext();)
            {
                Note currNote = it.next();
                if (id < currNote.getNoteid())
                {
                    id = currNote.getNoteid();
                }
            }

            note.setNoteid(id + 1);

            return noteDB.insert(note);
        } catch (NotesDBException ex)
        {
            return 0;
        }
    }
}

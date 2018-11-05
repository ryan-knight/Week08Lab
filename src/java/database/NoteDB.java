/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Note;

/**
 *
 * @author 763198
 */
public class NoteDB
{

    public int insert(Note note) throws NotesDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {
            trans.begin();
            em.persist(note);
            trans.commit();
            return 1;
        }
        finally
        {
            em.close();
        }
    }

    public int update(Note note) throws NotesDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {
            trans.begin();
            em.merge(note);
            trans.commit();
            return 1;
        }
        finally
        {
            em.close();
        }
    }

    public List<Note> getAll() throws NotesDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            List<Note> list = (List<Note>) em.createQuery("SELECT a FROM Note a").getResultList();
            return list;
        }
        finally
        {
            em.close();
        }
    }

    public Note getNote(int noteId) throws NotesDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            Note note = em.find(Note.class, noteId);
            return note;
        }
        finally
        {
            em.close();
        }
    }

    public int delete(Note note) throws NotesDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {
            trans.begin();
            em.remove(em.merge(note));
            trans.commit();
            return 1;
        }
        finally
        {
            em.close();
        }
    }
}

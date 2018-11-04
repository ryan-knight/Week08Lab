/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Note;

/**
 *
 * @author 763198
 */
public class NoteDB
{

    public int insert(Note note) throws NotesDBException
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String preparedSQL = "INSERT INTO notes "
                + "(noteid, datecreated, contents) "
                + "SELECT MAX(noteid) + 1, ?, ? FROM notes;";
        int rowCount = 0;

        try
        {
            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ps.setDate(1, note.getDateCreated());
            ps.setString(2, note.getContents());
            rowCount = ps.executeUpdate();
        } catch (SQLException ex)
        {
            throw new NotesDBException("insert error occured");
        } finally
        {
            pool.freeConnection(connection);
        }

        return rowCount;
    }

    public int update(Note note) throws NotesDBException
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String preparedSQL = "UPDATE notes SET "
                + "contents = ? "
                + "WHERE noteid = ?;";
        int rowCount = 0;

        try
        {
            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ps.setInt(2, note.getNoteId());
            ps.setString(1, note.getContents());
            rowCount = ps.executeUpdate();
        } catch (SQLException ex)
        {
            throw new NotesDBException("update error occured");
        } finally
        {
            pool.freeConnection(connection);
        }

        return rowCount;
    }

    public List<Note> getAll() throws NotesDBException
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String preparedSQL = "SELECT * FROM notes;";
        ArrayList<Note> list = new ArrayList<>();

        try
        {
            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Note note = new Note(rs.getInt(1), rs.getDate(2), rs.getString(3));
                list.add(note);
            }
            rs.close();
        } catch (SQLException ex)
        {
            throw new NotesDBException("fetch error occured");
        } finally
        {
            pool.freeConnection(connection);
        }

        return list;
    }

    public Note getNote(int noteId) throws NotesDBException
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String preparedSQL = "SELECT * FROM notes WHERE noteid = ?;";
        Note note = null;

        try
        {
            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ps.setInt(1, noteId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            note = new Note(rs.getInt(1), rs.getDate(2), rs.getString(3));
            rs.close();
        } catch (SQLException ex)
        {
            throw new NotesDBException("fetch note error occured");
        } finally
        {
            pool.freeConnection(connection);
        }

        return note;
    }

    public int delete(Note note) throws NotesDBException
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String preparedSQL = "DELETE FROM notes WHERE noteid = ?;";
        int rowsDeleted = 0;

        try
        {
            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ps.setInt(1, note.getNoteId());
            rowsDeleted = ps.executeUpdate();
        } catch (SQLException ex)
        {
            throw new NotesDBException("delete error occured");
        } finally
        {
            pool.freeConnection(connection);
        }
        return rowsDeleted;
    }
}

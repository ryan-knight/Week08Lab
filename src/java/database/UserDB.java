package database;

import java.sql.SQLException;
import models.User;
import java.util.List;
import javax.persistence.EntityManager;

public class UserDB {
    
    public int insert(User user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            em.persist(user);
            return 1;
        }
        finally
        {
            em.close();
        }
    }
    
    public int update(User user) throws NotesDBException, SQLException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            em.merge(user);
            return 1;
        }
        finally
        {
            em.close();
        }
    }
    
    public List<User> getAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            List<User> list = em.createQuery("SELECT a FROM User a").getResultList();
            return list;
        }
        finally
        {
            em.close();
        }
    }

    public User getUser(String username) throws NotesDBException, SQLException{
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            User user = em.find(User.class, username);
            return user;
        }
        finally
        {
            em.close();
        }
    }
    
    public int delete(User user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            em.remove(user);
            return 1;
        }
        finally
        {
            em.close();
        }
    }
}
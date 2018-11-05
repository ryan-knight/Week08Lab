package database;

import java.sql.SQLException;
import models.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDB {
    
    public int insert(User user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {
            trans.begin();
            em.persist(user);
            trans.commit();
            return 1;
        }
        finally
        {
            em.close();
        }
    }
    
    public int update(User user) throws NotesDBException, SQLException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {
            trans.begin();
            em.merge(user);
            trans.commit();
            em.flush();
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
        EntityTransaction trans = em.getTransaction();
        try
        {
            trans.begin();
            em.remove(em.merge(user));
            trans.commit();
            return 1;
        }
        finally
        {
            em.close();
        }
    }
}
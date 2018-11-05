/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author 763198
 */
public class DBUtil
{
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("NotesPU");
    
    public static EntityManagerFactory getEmFactory()
    {
        return emf;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;
import services.NoteService;

/**
 *
 * @author 763198
 */
public class NoteServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        NoteService ns = new NoteService();
        request.setAttribute("notes", ns.getAll());
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String action = request.getParameter("action");
        if(action == null)
            getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
        
        NoteService ns = new NoteService();
        
        String textArea = request.getParameter("textarea");
                
        if(action.equals("Add"))
        {
            ns.insert(textArea);
        }
        else if(action.startsWith("Delete"))
        {
            int toRemove = Integer.parseInt(action.substring(6));
            ns.delete(toRemove);
        }
        else if(action.startsWith("Edit"))
        {
            int toEdit = Integer.parseInt(action.substring(4));
            request.setAttribute("editnote", ns.get(toEdit));
        }
        else if(action.startsWith("Save"))
        {
            int toSave = Integer.parseInt(action.substring(4));
            ns.update(toSave, textArea);
            request.removeAttribute("editnote");
        }
        
        request.setAttribute("notes", ns.getAll());
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

}

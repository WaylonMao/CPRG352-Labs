/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import modules.Note;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
/**
 * @author Weilong Mao
 */
@WebServlet(name = "NoteServlet", urlPatterns = {"/note"})
public class NoteServlet extends HttpServlet {

    private final String FILE_PATH = "/WEB-INF/note.txt";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Note note = readNoteFile(getServletContext().getRealPath(FILE_PATH));
        request.setAttribute("note", note);        
        if (request.getQueryString() == null || !request.getQueryString().equals("edit")) {
            getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
        } else {
            note.setContents(note.getContents().replaceAll("<br />","\r\n"));
            request.setAttribute("note", note); 
            getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp").forward(request, response);
        }
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        contents = contents.replaceAll("\r\n", "<br />");
        Note note = new Note(title, contents);
        request.setAttribute("note", note);
        writeNoteFile(note, getServletContext().getRealPath(FILE_PATH));
        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
        return;
    }

    private Note readNoteFile(String file_path) {
        try {
            BufferedReader rd = new BufferedReader(new FileReader(new File(file_path)));
            return new Note(rd.readLine(), rd.readLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeNoteFile(Note note, String realPath) {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(realPath, false)));
            pw.println(note.getTitle());
            pw.println(note.getContents());
            pw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

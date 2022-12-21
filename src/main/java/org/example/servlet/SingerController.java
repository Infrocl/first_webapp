package org.example.servlet;

import org.example.hibernate.DAO.SingerDAOImpl;
import org.example.hibernate.entity.Singer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/singer/*")
public class SingerController extends HttpServlet {
    private static String INSERT_OR_EDIT = "/singer.jsp";
    private static String LIST_SINGERS = "/showSingers.jsp";
    private SingerDAOImpl dao;

    public SingerController() {
        super();
        dao = new SingerDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        switch (action) {
            case "delete" -> {
                long singerId = Long.parseLong(request.getParameter("singerId"));
                Singer singer = dao.getSingerById(singerId);
                dao.deleteSinger(singer);
                forward = LIST_SINGERS;
                request.setAttribute("singerList", dao.getAllSingers());
            }
            case "update" -> {
                forward = INSERT_OR_EDIT;
                long singerId = Long.parseLong(request.getParameter("singerId"));
                request.setAttribute("singer", dao.getSingerById(singerId));
            }
            case "showSingers" -> {
                forward = LIST_SINGERS;
                request.setAttribute("singerList", dao.getAllSingers());
            }
            case "add" -> forward = INSERT_OR_EDIT;
            case "autocomplete" -> {
                String letter = request.getParameter("letter");
                List<Singer> singers = dao.getSingersStartBy(letter);
                response.setContentType("text/plain;charset=UTF-8");
                PrintWriter out = response.getWriter();
                for(Singer singer : singers) {
                    out.println(singer.getName());
                }
                out.close();
                return;
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Singer singer = new Singer();
        singer.setName(request.getParameter("singerName"));
        String singerId = request.getParameter("singerId");

        if (singerId == null || singerId.isEmpty()) {
            dao.addSinger(singer);
        } else {
            singer.setId(Long.parseLong(singerId));
            dao.updateSinger(singer);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_SINGERS);
        request.setAttribute("singerList", dao.getAllSingers());
        view.forward(request, response);
    }
}

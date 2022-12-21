package org.example.servlet;

import org.example.hibernate.DAO.AlbumDAOImpl;
import org.example.hibernate.DAO.SingerDAOImpl;
import org.example.hibernate.entity.Album;
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

@WebServlet("/album/*")
public class AlbumController extends HttpServlet {
    private static String INSERT_OR_EDIT = "/album.jsp";
    private static String LIST_ALBUMS = "/showAlbums.jsp";
    private AlbumDAOImpl dao;

    public AlbumController() {
        super();
        dao = new AlbumDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        switch (action) {
            case "delete" -> {
                long albumId = Long.parseLong(request.getParameter("albumId"));
                Album album = dao.getAlbumById(albumId);
                dao.deleteAlbum(album);
                forward = LIST_ALBUMS;
                request.setAttribute("albumList", dao.getAllAlbums());
            }
            case "update" -> {
                forward = INSERT_OR_EDIT;
                long albumId = Long.parseLong(request.getParameter("albumId"));
                request.setAttribute("album", dao.getAlbumById(albumId));
            }
            case "showAlbums" -> {
                String type = request.getParameter("type");
                if (type == null || type.isEmpty())
                    request.setAttribute("albumList", dao.getAllAlbums());
                else
                    request.setAttribute("albumList", dao.getAlbumsWithTwoSongs());
                forward = LIST_ALBUMS;
            }
            case "add" -> forward = INSERT_OR_EDIT;
            case "autocomplete" -> {
                String type = request.getParameter("type");
                String letter = request.getParameter("letter");
                if (type.equals("0")) {
                    List<Album> albums = dao.getAlbumsStartBy(letter);
                    response.setContentType("text/plain;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    for (Album album : albums) {
                        out.println(album.getName());
                    }
                    out.close();
                } else {
                    List<Long> id = dao.getSingerIdStartBy(letter);
                    PrintWriter out = response.getWriter();
                    for (Long singerId : id) {
                        out.println(singerId);
                    }
                    out.close();
                }
                return;
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SingerDAOImpl singerDAO = new SingerDAOImpl();
        Album album = new Album();
        album.setName(request.getParameter("albumName"));
        String albumId = request.getParameter("albumId");
        album.setGenre(request.getParameter("albumGenre"));
        long singerId = Long.parseLong(request.getParameter("singerId"));
        Singer singer = singerDAO.getSingerById(singerId);
        if (singer == null) {
            response.getWriter().println("<script type='text/javascript'>alert('The singer with the entered id does not exist!');location='album.jsp'</script>");
        } else {
            album.setSinger(singer);
            if (albumId == null || albumId.isEmpty()) {
                dao.addAlbum(album);
            } else {
                album.setId(Long.parseLong(albumId));
                dao.updateAlbum(album);
            }
            RequestDispatcher view = request.getRequestDispatcher(LIST_ALBUMS);
            request.setAttribute("albumList", dao.getAllAlbums());
            view.forward(request, response);
        }
    }
}

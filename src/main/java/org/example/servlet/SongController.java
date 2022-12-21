package org.example.servlet;

import org.example.hibernate.DAO.AlbumDAOImpl;
import org.example.hibernate.DAO.SongDAOImpl;
import org.example.hibernate.entity.Album;
import org.example.hibernate.entity.Song;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.List;

@WebServlet("/song/*")
public class SongController extends HttpServlet {
    private static String INSERT_OR_EDIT = "/song.jsp";
    private static String LIST_SONGS = "/showSongs.jsp";
    private SongDAOImpl dao;

    public SongController() {
        super();
        dao = new SongDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        switch (action) {
            case "delete" -> {
                long songId = Long.parseLong(request.getParameter("songId"));
                Song song = dao.getSongById(songId);
                dao.deleteSong(song);
                forward = LIST_SONGS;
                request.setAttribute("songList", dao.getAllSongs());
            }
            case "update" -> {
                forward = INSERT_OR_EDIT;
                long songId = Long.parseLong(request.getParameter("songId"));
                request.setAttribute("song", dao.getSongById(songId));
            }
            case "showSongs" -> {
                String type = request.getParameter("type");
                if (type == null || type.isEmpty())
                    request.setAttribute("songList", dao.getAllSongs());
                else
                    request.setAttribute("songList", dao.getSongsByGenre("pop"));
                forward = LIST_SONGS;
            }
            case "add" -> forward = INSERT_OR_EDIT;
            case "autocomplete" -> {
                String type = request.getParameter("type");
                String letter = request.getParameter("letter");
                if (type.equals("0")) {
                    List<Song> songs = dao.getSongsStartBy(letter);
                    response.setContentType("text/plain;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    for (Song song : songs) {
                        out.println(song.getName());
                    }
                    out.close();
                } else {
                    List<Long> albumId = dao.getAlbumIdStartBy(letter);
                    response.setContentType("text/plain;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    for (Long id : albumId) {
                        out.println(id);
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
        AlbumDAOImpl albumDAO = new AlbumDAOImpl();
        Song song = new Song();
        song.setName(request.getParameter("songName"));
        String songId = request.getParameter("songId");
        //  try {
        song.setDuration(Time.valueOf(request.getParameter("songDuration")));
        //   } catch (IllegalArgumentException e) {
        //    response.getWriter().println("<script type='text/javascript'>alert('Please, enter a song less than 24 hours as 00:00:00!');location='song.jsp'</script>");
        //   }
        long albumId = Long.parseLong(request.getParameter("albumId"));
        Album album = albumDAO.getAlbumById(albumId);
        if (album == null) {
            response.getWriter().println("<script type='text/javascript'>alert('The album with the entered id does not exist!');location='song.jsp'</script>");
        } else {
            song.setAlbum(album);
            if (songId == null || songId.isEmpty()) {
                dao.addSong(song);
            } else {
                song.setId(Long.parseLong(songId));
                dao.updateSong(song);
            }
            RequestDispatcher view = request.getRequestDispatcher(LIST_SONGS);
            request.setAttribute("songList", dao.getAllSongs());
            view.forward(request, response);
        }
    }
}

package org.example;

import org.example.hibernate.DAO.AlbumDAOImpl;
import org.example.hibernate.DAO.SingerDAOImpl;
import org.example.hibernate.DAO.SongDAOImpl;
import org.example.hibernate.entity.Album;
import org.example.hibernate.HibernateSessionFactoryUtil;
import org.example.hibernate.entity.Singer;
import org.example.hibernate.entity.Song;
import org.hibernate.Session;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

public class HibernateApp {
    public static void main(String[] args) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        AlbumDAOImpl service = new AlbumDAOImpl();
//        Singer singer = new Singer();
//        singer.setName("Alice");
//        List<Album> albums = new LinkedList<>();
//        Album firstAlbum = new Album();
//        firstAlbum.setName("One");
//        firstAlbum.setGenre("pop");
//        firstAlbum.setSinger(singer);
//        List<Song> songs = new LinkedList<>();
//        firstAlbum.setSongs(songs);
//        Song firstSong = new Song();
//        firstSong.setAlbum(firstAlbum);
//        firstSong.setDuration(Time.valueOf("00:03:25"));
//        firstSong.setName("Help me!");
//        songs.add(firstSong);
//        albums.add(firstAlbum);
//        singer.setAlbums(albums);
//        session.save(singer);
//        session.getTransaction().commit();
        service.getSingerIdStartBy("1");
    }
}

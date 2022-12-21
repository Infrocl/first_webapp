package org.example.hibernate.DAO;

import org.example.hibernate.HibernateSessionFactoryUtil;
import org.example.hibernate.entity.Album;
import org.example.hibernate.entity.Song;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;

public class SongDAOImpl implements SongDAO {
    @Override
    public void addSong(Song song) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(song);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateSong(Song song) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(song);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Song getSongById(Long song_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Song song = session.get(Song.class, song_id);
        session.close();
        return song;
    }

    @Override
    public List<Song> getAllSongs() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Song> songs = session.createQuery("From Song").list();
        session.close();
        return songs;
    }

    @Override
    public void deleteSong(Song song) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(song);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Song> getSongsByGenre(String genre) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                """
                     SELECT s.id from Song s
                     inner join s.album a
                     WHERE a.genre = :genre""");
        query.setString("genre", genre);
        List<Long> id = (List<Long>) query.list();
        List<Song> songs = new LinkedList<>();
        for (Long songId: id) {
            songs.add(getSongById(songId));
        }
        session.close();
        return songs;
    }

    @Override
    public List<Song> getSongsStartBy(String str) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Song> songs = session.createQuery("FROM Song WHERE name LIKE '" +str+"%'").list();
        session.close();
        return songs;
    }

    @Override
    public List<Long> getAlbumIdStartBy(String str) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Long> albums = session.createQuery("select id from Album where to_char(id,'9999') like '%" + str+ "%'").list();
        session.close();
        return albums;
    }
}

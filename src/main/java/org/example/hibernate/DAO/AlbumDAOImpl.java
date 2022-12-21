package org.example.hibernate.DAO;

import org.example.hibernate.HibernateSessionFactoryUtil;
import org.example.hibernate.entity.Album;
import org.example.hibernate.entity.Singer;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class AlbumDAOImpl implements AlbumDAO {
    @Override
    public void addAlbum(Album album) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(album);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateAlbum(Album album) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(album);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Album getAlbumById(Long album_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Album album = session.get(Album.class, album_id);
        session.close();
        return album;
    }

    @Override
    public List<Album> getAllAlbums() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Album> albums = session.createQuery("From Album").list();
        session.close();
        return albums;
    }

    @Override
    public void deleteAlbum(Album album) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(album);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Album> getAlbumsWithTwoSongs() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                """
                        SELECT a.id
                        from Album a inner join a.songs s
                        group by a.id
                        having (count(s.name) > 1)
                        """);
        List<Long> id = (List<Long>) query.list();
        List<Album> albums = new LinkedList<>();
        for (Long albumId: id) {
            albums.add(getAlbumById(albumId));
        }
        session.close();
        return albums;
    }

    @Override
    public List<Album> getAlbumsStartBy(String letter) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Album> albums = session.createQuery("FROM Album WHERE name LIKE '" + letter+"%'").list();
        session.close();
        return albums;
    }

    @Override
    public List<Long> getSingerIdStartBy(String str) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Long> albums = session.createQuery("select id from Singer where to_char(id,'9999') like '%" + str+ "%'").list();
        session.close();
        return albums;
    }
}

package org.example.hibernate.DAO;

import org.example.hibernate.HibernateSessionFactoryUtil;
import org.example.hibernate.entity.Singer;
import org.hibernate.Session;

import java.util.List;


public class SingerDAOImpl implements SingerDAO {
    @Override
    public void addSinger(Singer singer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(singer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateSinger(Singer singer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(singer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Singer getSingerById(Long singer_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Singer singer = session.get(Singer.class, singer_id);
        session.close();
        return singer;
    }

    @Override
    public List<Singer> getAllSingers() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Singer> singers = session.createQuery("FROM Singer").list();
        session.close();
        return singers;
    }

    @Override
    public void deleteSinger(Singer singer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(singer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Singer> getSingersStartBy(String letter) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Singer> singers = session.createQuery("FROM Singer WHERE name LIKE '" + letter+"%'").list();
        session.close();
        return singers;
    }
}

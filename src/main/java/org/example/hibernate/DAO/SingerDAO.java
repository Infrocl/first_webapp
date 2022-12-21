package org.example.hibernate.DAO;

import org.example.hibernate.entity.Singer;

import java.util.List;

public interface SingerDAO {
    void addSinger(Singer singer);

    void updateSinger(Singer singer);

    Singer getSingerById(Long singer_id);

    List<Singer> getAllSingers();

    void deleteSinger(Singer singer);

    List<Singer> getSingersStartBy(String letter);
}

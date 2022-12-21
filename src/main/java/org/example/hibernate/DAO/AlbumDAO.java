package org.example.hibernate.DAO;

import org.example.hibernate.entity.Album;
import org.example.hibernate.entity.Singer;

import java.util.List;

public interface AlbumDAO {
    void addAlbum(Album album);

    void updateAlbum(Album album);

    Album getAlbumById(Long album_id);

    List<Album> getAllAlbums();

    void deleteAlbum(Album album);

    List<Album> getAlbumsWithTwoSongs();

    List<Album> getAlbumsStartBy(String string);

    List<Long> getSingerIdStartBy(String str);
}

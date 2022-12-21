package org.example.hibernate.DAO;

import org.example.hibernate.entity.Album;
import org.example.hibernate.entity.Song;

import java.util.List;

public interface SongDAO {
    void addSong(Song song);

    void updateSong(Song song);

    Song getSongById(Long song_id);

    List<Song> getAllSongs();

    void deleteSong(Song song);

    List<Song> getSongsByGenre(String genre);

    List<Song> getSongsStartBy(String str);

    List<Long> getAlbumIdStartBy(String str);
}

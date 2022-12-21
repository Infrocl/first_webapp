package org.example.hibernate.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ALBUM")
public class Album {
    private String name;
    private String genre;
    private Long id;
    private Singer singer;
    private List<Song> songs;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "album_seq")
    @SequenceGenerator(name = "album_seq",
            sequenceName = "SEQ_ALBUM")
    @Column(name = "ID", updatable = false, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "GENRE")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SINGER_ID")
    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}

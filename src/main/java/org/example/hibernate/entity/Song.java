package org.example.hibernate.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "SONG")
public class Song {
    private Long id;
    private String name;
    private Time duration;
    private Album album;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "song_seq")
    @SequenceGenerator(name = "song_seq",
            sequenceName = "SEQ_SONG")
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

    @Column(name = "DURATION")
    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    @ManyToOne
    @JoinColumn(name = "ALBUM_ID")
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}

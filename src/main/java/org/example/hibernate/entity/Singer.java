package org.example.hibernate.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SINGER")
public class Singer {
    private String name;
    private Long id;
    private List<Album> albums;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "singer_seq")
    @SequenceGenerator(name = "singer_seq",
            sequenceName = "SEQ_SINGER")
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

    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL)
    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}

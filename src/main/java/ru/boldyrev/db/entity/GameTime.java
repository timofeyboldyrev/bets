package ru.boldyrev.db.entity;

import javax.persistence.*;

/**
 * Created by t.boldyrev on 16.03.2017.
 */
@Entity
@Table(name = "TEST_THREE", indexes = {
        @Index(columnList = "GAME_ID", name = "FOR_KEY_GAME_TIME_GAME")
})
public class GameTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "GAME_ID", nullable = false)
    private Long gameId;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private Integer score1;

    @Column(nullable = false)
    private Integer score2;

    @Override
    public String toString() {
        return "GameTime{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", time='" + time + '\'' +
                ", score1=" + score1 +
                ", score2=" + score2 +
                '}';
    }
}

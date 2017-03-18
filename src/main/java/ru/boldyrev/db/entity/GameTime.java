package ru.boldyrev.db.entity;

import javax.persistence.*;

/**
 * Created by Timofey Boldyrev on 16.03.2017.
 */
@Entity
@Table(name = "GAME_TIME", indexes = {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getScore1() {
        return score1;
    }

    public void setScore1(Integer score1) {
        this.score1 = score1;
    }

    public Integer getScore2() {
        return score2;
    }

    public void setScore2(Integer score2) {
        this.score2 = score2;
    }

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

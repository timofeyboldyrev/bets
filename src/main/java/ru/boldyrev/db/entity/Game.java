package ru.boldyrev.db.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by t.boldyrev on 16.03.2017.
 */
@Entity
@Table(name = "GAME", indexes = {
        @Index(columnList = "GAME_DATE,TEAM1,TEAM2", name = "UN_KEY_GAME_TEAMS_DATE", unique = true),
})
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "GAME_DATE")
    private LocalDate gameDate;

    @Column(nullable = false)
    private String team1;

    @Column(nullable = false)
    private String team2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getGameDate() {
        return gameDate;
    }

    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameDate=" + gameDate +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                '}';
    }
}

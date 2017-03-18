package ru.boldyrev.db.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by t.boldyrev on 16.03.2017.
 */
@Entity
@Table(name = "TEST_TWO", indexes = {
        @Index(columnList = "GAME_DATE,TEAM1,TEAM2", name = "UN_KEY_GAME_TEAMS_DATE", unique = true),
})
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "GAME_DATE", columnDefinition = "DATE default TRUNC(SYSDATE) not null", insertable = false)
    private LocalDate gameDate;

    @Column(nullable = false)
    private String team1;

    @Column(nullable = false)
    private String team2;

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

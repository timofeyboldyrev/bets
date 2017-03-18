package ru.boldyrev.db.entity;

import javax.persistence.*;

/**
 * Created by t.boldyrev on 16.03.2017.
 */
@Entity
@Table(name = "COEFFICIENT", indexes = {
        @Index(columnList = "GAME_TIME_ID, BET_TYPE_ID", name = "UN_KEY_COEFFICIENT_GAME_TIME_BET_TYPE", unique = true)
})
public class Coefficient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "GAME_TIME_ID", nullable = false)
    private Long gameTimeId;

    @Column(name = "BET_TYPE_ID", nullable = false)
    private Long betTypeId;

    @Column(nullable = false)
    private Double coefficient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameTimeId() {
        return gameTimeId;
    }

    public void setGameTimeId(Long gameTimeId) {
        this.gameTimeId = gameTimeId;
    }

    public Long getBetTypeId() {
        return betTypeId;
    }

    public void setBetTypeId(Long betTypeId) {
        this.betTypeId = betTypeId;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "Coefficient{" +
                "id=" + id +
                ", gameTimeId=" + gameTimeId +
                ", betTypeId=" + betTypeId +
                ", coefficient=" + coefficient +
                '}';
    }
}

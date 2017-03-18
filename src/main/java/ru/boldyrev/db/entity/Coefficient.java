package ru.boldyrev.db.entity;

import javax.persistence.*;

/**
 * Created by t.boldyrev on 16.03.2017.
 */
@Entity
@Table(name = "TEST_FOUR", indexes = {
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

}

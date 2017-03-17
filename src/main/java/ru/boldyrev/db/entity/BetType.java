package ru.boldyrev.db.entity;

import javax.persistence.*;

/**
 * Created by t.boldyrev on 16.03.2017.
 */
@Entity
@Table(name = "TEST_ONE", indexes = {
        @Index(columnList = "NAME", name = "UN_KEY_BET_TYPE_NAME", unique = true),
})
@SequenceGenerator(name = "BetTypeIdGenerator", sequenceName = "BET_TYPE_SEQ")
public class BetType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BetTypeIdGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BetTypeName name;

    @Override
    public String toString() {
        return "BetType{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}

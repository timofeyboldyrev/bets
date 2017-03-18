package ru.boldyrev.db.entity;

import javax.persistence.*;

/**
 * Created by Timofey Boldyrev on 16.03.2017.
 */
@Entity
@Table(name = "BET_TYPE", indexes = {
        @Index(columnList = "NAME", name = "UN_KEY_BET_TYPE_NAME", unique = true),
})
public class BetType {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BetTypeName name;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BetType{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}

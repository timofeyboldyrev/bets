package ru.boldyrev.db.repository;

import org.springframework.data.repository.CrudRepository;
import ru.boldyrev.db.entity.Game;

import java.time.LocalDate;

/**
 * Created by t.boldyrev on 17.03.2017.
 */
public interface GameRepository extends CrudRepository<Game,Long> {

    Game findByTeam1AndTeam2AndGameDate(String team1, String team2, LocalDate gameDate);

}

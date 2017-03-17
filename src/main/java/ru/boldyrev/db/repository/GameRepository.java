package ru.boldyrev.db.repository;

import org.springframework.data.repository.CrudRepository;
import ru.boldyrev.db.entity.Game;

/**
 * Created by t.boldyrev on 17.03.2017.
 */
public interface GameRepository extends CrudRepository<Game,Long> {
}

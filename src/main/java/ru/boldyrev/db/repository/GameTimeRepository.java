package ru.boldyrev.db.repository;

import org.springframework.data.repository.CrudRepository;
import ru.boldyrev.db.entity.GameTime;

/**
 * Created by Timofey Boldyrev on 17.03.2017.
 */
public interface GameTimeRepository extends CrudRepository<GameTime,Long> {
}

package ru.boldyrev.db.repository;

import org.springframework.data.repository.CrudRepository;
import ru.boldyrev.db.entity.BetType;

/**
 * Created by t.boldyrev on 17.03.2017.
 */
public interface BetTypeRepository extends CrudRepository<BetType,Long> {
}

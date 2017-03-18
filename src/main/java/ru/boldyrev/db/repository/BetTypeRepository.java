package ru.boldyrev.db.repository;

import org.springframework.data.repository.CrudRepository;
import ru.boldyrev.db.entity.BetType;
import ru.boldyrev.db.entity.BetTypeName;

/**
 * Created by Timofey Boldyrev on 17.03.2017.
 */
public interface BetTypeRepository extends CrudRepository<BetType,Long> {

    BetType findByName(BetTypeName name);

}

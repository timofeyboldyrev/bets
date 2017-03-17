package ru.boldyrev.db;

import org.springframework.stereotype.Service;
import ru.boldyrev.parsing.ParseResult;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by t.boldyrev on 16.03.2017.
 */
@Service
public class DbManager {

    public void save(List<ParseResult> info) {
        info.stream().forEach(this::save);
        System.out.println(LocalDateTime.now());
        System.out.println("\n\n\n");
    }

    public void save(ParseResult info) {
        System.out.println(info);
    }

}

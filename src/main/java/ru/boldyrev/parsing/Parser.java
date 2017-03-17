package ru.boldyrev.parsing;

import java.util.List;

/**
 * Created by t.boldyrev on 16.03.2017.
 */
public interface Parser {

    List<ParseResult> parsePage(String page);

}

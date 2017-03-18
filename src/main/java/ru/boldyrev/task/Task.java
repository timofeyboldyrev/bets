package ru.boldyrev.task;

import ru.boldyrev.parsing.Parser;

import java.net.URL;

/**
 * Created by Timofey Boldyrev on 16.03.2017.
 */
public class Task {

    private URL url;
    private Parser parser;

    public Task(URL url, Parser parser) {
        this.url = url;
        this.parser = parser;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    @Override
    public String toString() {
        return "Task{" +
                "url=" + url +
                ", parser=" + parser +
                '}';
    }
}

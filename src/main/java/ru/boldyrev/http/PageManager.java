package ru.boldyrev.http;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Timofey Boldyrev on 15.03.2017.
 */
@Service
public class PageManager {

    public String loadPageContent(URL url) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            StringBuilder pageContent = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null)
                pageContent.append(line);
            in.close();
            return pageContent.toString();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка во время получения контета страницы по адресу " + url, e);
        }
    }

}

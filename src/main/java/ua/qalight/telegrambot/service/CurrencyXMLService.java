package ua.qalight.telegrambot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ua.qalight.telegrambot.enums.Emoji;
import ua.qalight.telegrambot.model.CurrencyJSON;
import ua.qalight.telegrambot.model.CurrencyXML;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("application.properties")
public class CurrencyXMLService implements CurrencyService {

    private static String URL;

    private static List<CurrencyXML> currencyXMLList = new ArrayList<>();

    @Value("${xml.url}")
    public void setURL(String url) {
        CurrencyXMLService.URL = url;
    }

    @Override
    @SneakyThrows
    public String getResponse(String currency) {
        if (currencyXMLList.isEmpty()) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URL))
                    .GET()
                    .build();

            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String res = response.body().toString();
            XmlMapper mapper = new XmlMapper(); // todo help us to convert XML (!!!)

            TypeReference<List<CurrencyXML>> listType = new TypeReference<List<CurrencyXML>>() {
            };
            currencyXMLList = mapper.readValue(res, listType);
        }

        CurrencyXML curr = getCurrencyJSON(currency);

        String result = curr != null ?
                curr.getCurrency() + " rate is " + curr.getRate() + Emoji.SMILE.getEmoji() + Emoji.SMILE.getEmoji() : "ERROR!!!";
        String res = EmojiParser.parseToUnicode(result);

        return res;
    }

    private static CurrencyXML getCurrencyJSON(String currency) {
        CurrencyXML currencyXML = currencyXMLList.stream()
                .filter(el -> el.getCurrency().equals(currency))
                .findAny()
                .orElse(null);

        return currencyXML;
    }
}


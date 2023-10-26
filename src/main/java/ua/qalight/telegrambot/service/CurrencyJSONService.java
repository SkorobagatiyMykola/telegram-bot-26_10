package ua.qalight.telegrambot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ua.qalight.telegrambot.model.CurrencyJSON;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("application.properties")
public class CurrencyJSONService {

    private static String URL;

    private static List<CurrencyJSON> currencyJSONList = new ArrayList<>();

    @Value("${json.url}")
    public void setURL(String url) {
        CurrencyJSONService.URL=url;
    }

    public String getResponse(String currency) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client =HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .GET()
                .build();

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String res=response.body().toString();
        ObjectMapper mapper = new ObjectMapper(); // todo help us to convert JSON (!!!)

        TypeReference<List<CurrencyJSON>> listType = new TypeReference<List<CurrencyJSON>>(){};
        currencyJSONList =mapper.readValue(res, listType );

        CurrencyJSON curr = getCurrencyJSON(currency);

        String result = curr!=null ?
                curr.getCurrency()+" rate is "+curr.getRate() : "ERROR!!!";

        return result;
    }

    private static CurrencyJSON getCurrencyJSON(String currency){
        CurrencyJSON currencyJSON = currencyJSONList.stream()
                .filter(el->el.getCurrency().equals(currency))
                .findAny()
                .orElse(null);

        return currencyJSON;
    }
}

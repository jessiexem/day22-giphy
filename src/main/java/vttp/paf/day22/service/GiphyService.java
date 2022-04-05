package vttp.paf.day22.service;

import jakarta.json.JsonException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import vttp.paf.day22.model.Giphy;

import java.util.List;
import java.util.Optional;

@Service
public class GiphyService {

    @Value("${GIF_API_KEY}")
    private String apiKey;


    private static final String GIPHY_URL= "https://api.giphy.com/v1/gifs/search";

    //https://api.giphy.com/v1/gifs/search?api_key=apikey&q=pokemon&limit=25&offset=0&rating=g&lang=en

    public Optional<List<Giphy>> getGiphys(String q) {
        return getGiphys(q,"10","pg");
    }

    public Optional<List<Giphy>> getGiphys(String q, String limit) {
        return getGiphys(q,limit,"pg");
    }

    public Optional<List<Giphy>> getGiphys(String phrase, String limit, String rating) {
        String url = UriComponentsBuilder
                .fromUriString(GIPHY_URL)
                .queryParam("api_key",apiKey)
                .queryParam("q",phrase)
                .queryParam("limit",limit)
                .queryParam("offset",0)
                .queryParam("rating",rating)
                .queryParam("lang","en")
                .toUriString();

        RequestEntity req = RequestEntity.get(url).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp =template.exchange(req,String.class);

        //System.out.println(">>>>>>> GiphyService: "+resp.getBody());

        try {
            List<Giphy> giphyList = Giphy.create(resp.getBody());
            return Optional.of(giphyList);
        } catch (JsonException e ) {
            System.out.println(">>>> QuotationService - getQuotations: JsonException"+e.getMessage());
        }
        return Optional.empty();

    }
}

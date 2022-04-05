package vttp.paf.day22.model;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Giphy {

    private String id;
    private String title;
    private String rating;
    private String imageUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static List<Giphy> create (String json) {
        List<Giphy> list = new ArrayList<>();
        try {
            InputStream is = new ByteArrayInputStream(json.getBytes());
            JsonReader reader = Json.createReader(is);
            JsonObject obj = reader.readObject();

            JsonArray data = obj.getJsonArray("data");

            data.stream().map(v->(JsonObject) v)
                    .forEach(v->{
                        Giphy giphy = new Giphy();
                        giphy.setId(v.getString("id"));
                        giphy.setTitle(v.getString("title"));
                        giphy.setRating(v.getString("rating"));
                        String imageUrl = v.getJsonObject("images")
                                .getJsonObject("fixed_width")
                                .getString("url");
                        giphy.setImageUrl(imageUrl);
                        list.add(giphy);
                    });
        } catch (Exception e) {
            System.out.println("----- Svc: createGiphy: unable to convert to JsonObject");
        }

        return list;
    }
}

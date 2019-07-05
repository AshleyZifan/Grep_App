package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import static ca.jrvs.apps.twitter.example.JsonParser.toObjectFromJson;


public class TwitterRestDao implements CrdRepository<Tweet, String>{

    private HttpHelper helper;

    public TwitterRestDao(HttpHelper helper){
        this.helper = helper;
    }

    @Override
    public Tweet save(Tweet entity) {
        //Construct URI
        String text = entity.getText();
        Double lat = entity.getCoordinates().getLatitude();
        Double longi = entity.getCoordinates().getLongtitude();
        String new_text = text.replaceAll("\\s+","+");
        String url = "https://api.twitter.com/1.1/statuses/update.json?status=" + new_text + "&lat=" + lat + "&long=" + longi;
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //Execute HTTP Request
        HttpResponse response = helper.httpPost(uri);
        //Validate response and deser response to Tweet object
        Tweet tweet = null;
        try {
            String jsonStr = EntityUtils.toString(response.getEntity());
            tweet = toObjectFromJson(jsonStr, Tweet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tweet;
    }


    @Override
    public Tweet findById(String s) {
        //Construct URI
        String url = "https://api.twitter.com/1.1/statuses/show.json?id=" + s;
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //Execute HTTP Request
        HttpResponse response = helper.httpGet(uri);
        //Validate response and deser response to Tweet object
        Tweet tweet = null;
        try {
            String jsonStr = EntityUtils.toString(response.getEntity());
            tweet = toObjectFromJson(jsonStr, Tweet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tweet;
    }

    @Override
    public Tweet deleteById(String s) {
        //Construct URI
        String url = "https://api.twitter.com/1.1/statuses/destroy/" + s + ".json";
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //Execute HTTP Request
        HttpResponse response = helper.httpPost(uri);
        //Validate response and deser response to Tweet object
        Tweet tweet = null;
        try {
            String jsonStr = EntityUtils.toString(response.getEntity());
            tweet = toObjectFromJson(jsonStr, Tweet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tweet;
    }




}

package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;


public class TwitterRestDao implements CrdRepository<Tweet, String>{

    @Override
    public Tweet save(Tweet entity) {
        //Construct URI
        String url = "https://api.twitter.com/1.1/statuses/update.json";
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //Transfer Tweet object to StringEntiry
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        String json = null;
        try {
            json = mapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        StringEntity result_entity = null;
        try {
            result_entity = new StringEntity(json);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //Execute HTTP Request
        HttpHelper helper = new ApacheHttpHelper();
        HttpResponse response = helper.httpPost(uri, result_entity);
        //Validate response and deser response to Tweet object
        ObjectMapper objectMapper = new ObjectMapper();
        Tweet tweet = null;
        try {
            tweet = objectMapper.readValue(response.getEntity().getContent(), Tweet.class);
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
        HttpHelper helper = new ApacheHttpHelper();
        HttpResponse response = helper.httpGet(uri);
        //Validate response and deser response to Tweet object
        ObjectMapper objectMapper = new ObjectMapper();
        Tweet tweet = null;
        try {
            tweet = objectMapper.readValue(response.getEntity().getContent(), Tweet.class);
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
        HttpHelper helper = new ApacheHttpHelper();
        HttpResponse response = helper.httpPost(uri);
        //Validate response and deser response to Tweet object
        ObjectMapper objectMapper = new ObjectMapper();
        Tweet tweet = null;
        try {
            tweet = objectMapper.readValue(response.getEntity().getContent(), Tweet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tweet;
    }




}

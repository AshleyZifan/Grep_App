package ca.jrvs.apps.twitter.dao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static ca.jrvs.apps.twitter.example.JsonParser.toObjectFromJson;


public class TwitterRestDao implements CrdRepository<Tweet, String>{

    //URI constants
    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy";
    //URI symbols
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";

    //Response code
    private static final int HTTP_OK = 200;
    private HttpHelper helper;

    public TwitterRestDao(HttpHelper helper){
        this.helper = helper;
    }

    @Override
    public Tweet save(Tweet entity) {
        //Construct URI
        URI uri;
        try {
            uri = getPostUri(entity);
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Invalid tweet input", e);
        }

        //Execute HTTP Request
        HttpResponse response = helper.httpPost(uri);

        //Validate response and deser response to Tweet object
        return parseResponseBody(response, HTTP_OK);
    }


    @Override
    public Tweet findById(String s) {
        //Construct URI
        URI uri;
        try {
            uri = getShowUri(s);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Unable to construct URI", e);
        }

        //Execute HTTP Request
        HttpResponse response = helper.httpGet(uri);

        //Validate response and deser response to Tweet object
        return parseResponseBody(response, HTTP_OK);
    }

    @Override
    public Tweet deleteById(String s) {
        //Construct URI
        URI uri;
        try {
            uri = getDeleteUri(s);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Unable to construct URI", e);
        }

        //Execute HTTP Request
        HttpResponse response = helper.httpPost(uri);
        return parseResponseBody(response, HTTP_OK);
    }

    /**
     * Check response status code Convert Response Entity to Tweet
     */
    protected Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
        Tweet tweet = null;

        //Check response status
        int status = response.getStatusLine().getStatusCode();
        if (status != expectedStatusCode) {
            throw new RuntimeException("Unexpected HTTP status:" + status);
        }

        if (response.getEntity() == null) {
            throw new RuntimeException("Empty response body");
        }

        //Convert Response Entity to str
        String jsonStr;
        try {
            jsonStr = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert entity to String", e);
        }

        //Deser JSON string to Tweet object
        try {
            tweet = (Tweet) toObjectFromJson(jsonStr, Tweet.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to convert JSON str to Object", e);
        }

        return tweet;
    }

    /**
     * helper function to append one query param
     */
    private void appendQueryParam(StringBuilder sb, String key, String value,
                                  boolean firstParam) {
        if (!firstParam) {
            sb.append(AMPERSAND);
        }
        sb.append(key)
                .append(EQUAL)
                .append(value);
    }

    protected URI getPostUri(Tweet tweet)
            throws URISyntaxException, UnsupportedEncodingException {
        String text = tweet.getText();
        Double lat = tweet.getCoordinates().getLatitude();
        Double longi = tweet.getCoordinates().getLongtitude();

        if (!validatePostTweet(text, longi, lat)){
            throw new RuntimeException("text<150, -180<longi,lat<180");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(API_BASE_URI)
                .append(POST_PATH)
                .append(QUERY_SYM);

        appendQueryParam(sb, "status", URLEncoder.encode(text, StandardCharsets.UTF_8.name()), true);
        appendQueryParam(sb, "long", longi.toString(), false);
        appendQueryParam(sb, "lat", lat.toString(), false);

        return new URI(sb.toString());
    }

    protected URI getShowUri(String id) throws URISyntaxException {
        StringBuilder sb = new StringBuilder();
        sb.append(API_BASE_URI)
                .append(SHOW_PATH)
                .append(QUERY_SYM);
        appendQueryParam(sb, "id", id, true);
        return new URI(sb.toString());
    }

    protected URI getDeleteUri(String id) throws URISyntaxException {
        StringBuilder sb = new StringBuilder();
        sb.append(API_BASE_URI)
                .append(DELETE_PATH)
                .append("/")
                .append(id)
                .append(".json");

        return new URI(sb.toString());
    }

    public static boolean validatePostTweet(String text, Double longitude, Double latitude) {
        //make sure you validate tweet text and coordinates (e.g. tweet text less than 150 characters and  lon/lat max/min values)
        if((text.length() > 150)) {
            System.out.println("text length exceeds 150 chars");
            return false;
        } else if (( longitude > 180) || ( longitude < -180)){
            System.out.println("usage: -180 < longitude < 180");
            return false;
        } else if (( latitude > 180) || ( latitude < -180)){
            System.out.println("usage: -180 < latitude < 180");
            return false;
        }
        return true;
    }


}

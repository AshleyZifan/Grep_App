package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.dto.coordinates;

import java.lang.reflect.Field;

public class TwitterServiceImp implements TwitterService {
    @Override
    public void postTweet(String text, Double latitude, Double Longitude) {
        TwitterRestDao dao = new TwitterRestDao();
        Tweet tweet = new Tweet();
        coordinates coordenate = new coordinates(Longitude, latitude);
        tweet.setCoordinates(coordenate);
        tweet.setText(text);
        dao.save(tweet);
    }

    @Override
    public void showTweet(String id, String[] fields) {
        TwitterRestDao dao = new TwitterRestDao();
        Tweet tweet = dao.findById(id);
        Class cls = tweet.getClass();
        for(String s: fields){
            Field field = null;
            try {
                field = cls.getDeclaredField("s");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            System.out.println(field.toString());
        }
    }

    @Override
    public void deleteTweets(String[] ids) {
        TwitterRestDao dao = new TwitterRestDao();
        for(String id: ids){
           Tweet tweet = dao.deleteById(id);
        }
    }
}

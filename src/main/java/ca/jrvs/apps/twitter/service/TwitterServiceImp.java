package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.dto.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class TwitterServiceImp implements TwitterService {

    private CrdRepository dao;

    @Autowired
    public TwitterServiceImp(CrdRepository dao){
        this.dao =dao;
    }

    @Override
    public void postTweet(String text, Double latitude, Double Longitude) {
        Tweet tweet = new Tweet();
        Coordinates coordenates = new Coordinates(Longitude, latitude);
        tweet.setCoordinates(coordenates);
        tweet.setText(text);
        dao.save(tweet);
    }

    @Override
    public void showTweet(String id, String[] fields) {
        Tweet tweet = (Tweet) dao.findById(id);
        Class cls = tweet.getClass();
        for(String s: fields){
            Field field = null;
            try {
                field = cls.getDeclaredField(s);
                field.setAccessible(true);
                System.out.println(field.get(tweet));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteTweets(String[] ids) {
        for(String id: ids){
           dao.deleteById(id);
        }
    }
}

package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
public class TwitterServiceImpTest {

    HttpHelper helper = new ApacheHttpHelper();
    CrdRepository dao = new TwitterRestDao(helper);
    TwitterService service = new TwitterServiceImp(dao);

   @Test
    public void postTweet() throws Exception {
        String now = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        Double longitude = -11.11;
        Double latitude = 22.22;
        service.postTweet(now, latitude, longitude);
    }

    @Test
    public void showTweet() throws Exception {
        String[] fields = {"text","coordinates"};
        String id = "1147177933611646976";
        service.showTweet(id,fields);
    }

   @Test
    public void deleteTweets() throws Exception {
        String[] ids = {"1147181380121616384","1147180954395578368"};
        service.deleteTweets(ids);
    }
}

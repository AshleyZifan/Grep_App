package ca.jrvs.apps.twitter.dao;
import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class TwitterRestDaoIntTest {

    private Tweet expect;
    private CrdRepository dao;
    private String id;

    @Before
    public void setup(){
        //setup a expectedTweet
        this.expect = new Tweet();
        Coordinates coordinates = new Coordinates(11.11, 22.22);
        coordinates.setType("Point");
        this.expect.setCoordinates(coordinates);
        String now = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        this.expect.setText(now);
        //setup dao
        HttpHelper helper = new ApacheHttpHelper();
        this.dao = new TwitterRestDao(helper);
    }

    @After
    public void cleanup(){
        //remove tweet
        dao.deleteById(this.id);
    }

    @Test
    public void create() throws Exception {
        //create tweet
        Tweet postTweet = (Tweet)dao.save(this.expect);
        //validate tweet
        assertTweets(expect, postTweet);
        this.id = postTweet.getId_str();

        Tweet showTweet = (Tweet)dao.findById(this.id);
        assertTweets(expect, showTweet);
    }


    public void assertTweets(Tweet expect, Tweet actual){
        assertNotNull(actual);
        assertNotNull(actual.getId_str());
        assertEquals(actual.getText(),expect.getText());
        assertEquals(actual.getCoordinates(), expect.getCoordinates());
    }
}

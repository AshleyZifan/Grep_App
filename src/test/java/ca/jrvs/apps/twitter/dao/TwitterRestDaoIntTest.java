package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static junit.framework.TestCase.assertNotNull;

public class TwitterRestDaoIntTest {

    HttpHelper helper = new ApacheHttpHelper();
    TwitterRestDao dao = new TwitterRestDao(helper);

    @Test
    public void save() throws Exception {
        Tweet entity = new Tweet();
        String now = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        entity.setText(now);
        Coordinates coordinates = new Coordinates(11.11,22.22);
        entity.setCoordinates(coordinates);
        Tweet result = dao.save(entity);
        assertNotNull(result);
        System.out.println(result.getText());
    }

    @Test
    public void findById() throws Exception {
        Tweet result = dao.findById("1146879043251449858");
        assertNotNull(result);
        System.out.println(result.getText());
    }

    @Test
    public void deleteById() throws Exception {
        Tweet result = dao.deleteById("1146880489938137088");
        assertNotNull(result);
        System.out.println(result.getText());
    }
}

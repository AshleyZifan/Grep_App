package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static junit.framework.TestCase.assertNotNull;

public class TwitterRestDaoIntTest {

    TwitterRestDao dao = new TwitterRestDao();

    @Test
    public void save() throws Exception {
        Tweet entity = new Tweet();
        String now = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        entity.setText(now);
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

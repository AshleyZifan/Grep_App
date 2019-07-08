package ca.jrvs.apps.twitter.dao.helper;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import java.net.URI;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ApacheHttpHelperIntTest {

    private HttpHelper httpHelper = new ApacheHttpHelper();

    @Test
    public void httpPost() throws Exception {
        String uri = "https://api.twitter.com/1.1/statuses/update.json?status=Hello+world"
                + System.currentTimeMillis() + "+%23MeanwhileInCanada";
        HttpResponse response = httpHelper.httpPost(new URI(uri));
        System.out.println(EntityUtils.toString(response.getEntity()));
        assertNotNull(response);
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    public void httpPost_test2() throws Exception {
        String uri ="https://api.twitter.com/1.1/statuses/update.json?status=testing+hi";
        StringEntity stringEntity = new StringEntity("hello");
        HttpResponse response = httpHelper.httpPost(new URI(uri),stringEntity);
        System.out.println(EntityUtils.toString(response.getEntity()));
        assertNotNull(response);
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    public void httpGet() throws Exception {
        String uri ="https://api.twitter.com/1.1/statuses/show.json?id=1146787116933951488";
        HttpResponse response = httpHelper.httpGet(new URI(uri));
        System.out.println(EntityUtils.toString(response.getEntity()));
        assertNotNull(response);
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

}

package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwitterCLIRunner {

    private TwitterService service;

    @Autowired
    public TwitterCLIRunner(TwitterService service){
        this.service = service;

    }

    public void run(String[] args){
        if (args.length < 2){
            throw new RuntimeException("Usage: TwitterCLI post/show/delete");
        }

        switch (args[0].toLowerCase()){
            case "post":
                postTweet(args);
                break;
            case "show":
                showTweet(args);
                break;
            case "delete":
                deleteTweet(args);
                break;
            default:
                System.out.println("Usage:TwitterCLI post/show/delete");

        }
    }

    protected void postTweet(String[] args){
        if (args.length != 3){
            throw new RuntimeException("USAGE: TwitterCLI post \"tweet_text\" \"latitude:longitude\"");
        }
        String txt = args[1];
        String coordinates = args[2];
        String[] coor_list = coordinates.split(":");
        Double lat = Double.parseDouble(coor_list[0]);
        Double longi = Double.parseDouble(coor_list[1]);
        service.postTweet(txt,lat,longi);
    }

    protected void showTweet(String[] args){
        if (args.length != 3){
            throw new RuntimeException("USAGE: TwitterCLI show tweet_id fields");
        }
        String id = args[1];
        String fields = args[2];
        String[] fields_list = fields.split(",");

        service.showTweet(id,fields_list);

    }

    protected void deleteTweet(String[] args){
        if (args.length != 2){
            throw new RuntimeException("USAGE: TwitterCLI delete tweet_ids");
        }
        String ids = args[1];
        String[] ids_list = ids.split(",");
        service.deleteTweets(ids_list);

    }
}

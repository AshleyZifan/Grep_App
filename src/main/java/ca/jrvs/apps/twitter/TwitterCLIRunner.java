package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.service.TwitterService;

public class TwitterCLIRunner {

    private TwitterService service;

    public TwitterCLIRunner(TwitterService service){
        this.service = service;

    }

    public void run(String[] args){
        if (args.length < 2){
            throw new RuntimeException("Usage: TwitterCLI post[show]deleteTweets");
        }

        switch (args[0].toLowerCase()){
            case "post":
                postTweet(args);
        }
    }

    protected void postTweet(String[] args){
        if (args.length != 3){
            throw new RuntimeException("Usage: TwitterCLI post ");
        }
    }
}

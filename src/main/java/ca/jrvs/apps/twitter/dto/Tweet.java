package ca.jrvs.apps.twitter.dto;

import java.util.List;

public class Tweet {
    private String created_at;
    private long id;
    private String id_str;
    private Entities entities;
    private coordinates coordinates;
    private int retweet_count;
    private int favorite_count;
    private boolean favorited;
    private boolean retweeted;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    public Tweet.coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Tweet.coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getRetweet_count() {
        return retweet_count;
    }

    public void setRetweet_count(int retweet_count) {
        this.retweet_count = retweet_count;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public boolean isRetweeted() {
        return retweeted;
    }

    public void setRetweeted(boolean retweeted) {
        this.retweeted = retweeted;
    }

    public class Entities{
        private List<Hashtag> hashtags;
        private List<User_mention> user_mentions;

        public List<Hashtag> getHashtags() {
            return hashtags;
        }

        public void setHashtags(List<Hashtag> hashtags) {
            this.hashtags = hashtags;
        }

        public List<User_mention> getUser_mentions() {
            return user_mentions;
        }

        public void setUser_mentions(List<User_mention> user_mentions) {
            this.user_mentions = user_mentions;
        }
    }

    public class Hashtag{
        private List<Integer> indices;
        private String text;

        public List<Integer> getIndices() {
            return indices;
        }

        public void setIndices(List<Integer> indices) {
            this.indices = indices;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public class User_mention{
        private long id;
        private String id_str;
        private List<Integer> indices;
        private String name;
        private String screen_name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getId_str() {
            return id_str;
        }

        public void setId_str(String id_str) {
            this.id_str = id_str;
        }

        public List<Integer> getIndices() {
            return indices;
        }

        public void setIndices(List<Integer> indices) {
            this.indices = indices;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getScreen_name() {
            return screen_name;
        }

        public void setScreen_name(String screen_name) {
            this.screen_name = screen_name;
        }
    }

    public class coordinates{
        private Float longtitude;
        private Float latitude;

        public Float getLongtitude() {
            return longtitude;
        }

        public void setLongtitude(Float longtitude) {
            this.longtitude = longtitude;
        }

        public Float getLatitude() {
            return latitude;
        }

        public void setLatitude(Float latitude) {
            this.latitude = latitude;
        }
    }


}

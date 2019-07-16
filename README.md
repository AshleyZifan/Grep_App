# Java_apps Summary

This repository contains three small java apps: 

- Twitter CLI APP (with SpringBoot)
- Java Grep App
- JDBC



# 1.Twitter CLI App

## Introduction

A Twitter CLI App that can create, read, delete tweets.

## Usage

##### Create a tweet on your timeline

```
USAGE: TwitterCLI post "tweet_text" "latitude:longitude"

Description: Create a tweet with a geotag and output the created tweet object(simplifeid version) in JSON format.
Arguments:
tweet_text - tweet_text cannot exceed 140
UTF-8 encoded characters.
latitude:longitude - Geo location.
```

##### Read/Show a tweet by ID

```
USAGE: TwitterCLI show tweet_id [field1,fields2]

e.g.
TwitterCLI show 1097607853932564480
"id,text,retweet_count"
{
"id": 1097607853932564480,
"text": "test with loc223",
"retweet_count": 0
}

Description: Lookup a tweet by ID and print the tweet object in JSON format. Show all fields in JSON document if [field1,fields2] is empty. Otherwise, only show user specified [fields] in the JSON document.

Arguments:
tweet_id - Tweet ID. Same as id_str in the tweet
object
[field1,fields2] - (Optional) A comma-separated
list of top-level fields from the tweet object
(similar to SELECT clause in SQL)
```

##### Delete a tweet by tweet ID

```
USAGE: TwitterCLI delete tweet_ids

Description: Delete a list of tweets by id Output deleted tweet id and print deleted tweet object.

Arguments:
tweet_ids - A comma-separated list of tweets.
```

## Design and Implementation

##### Libraries

HTTP Client with Oauth setup

```
<!--oauth 1.0 and httpclient4-->
<dependency>
<groupId>oauth.signpost</groupId>
<artifactId>signpostcommonshttp4</
artifactId>
<version>1.2.1.2</version>
</dependency>
```

Jackson JSON

```
<!--Jackson JSON-->
<dependency>
<groupId>com.fasterxml.jackson.core</groupId>
<artifactId>jackson-databind</artifactId>
<version>2.9.4</version>
</dependency>
```

##### Diagrams

| Component  | Description                                                  |
| ---------- | :----------------------------------------------------------- |
| HttpHelper | Making HTTP requests (GET/PUT/DELETE) and<br/>handle auth    |
| Dao        | Data Access Object which handles tweet object<br/>(Dao depends on HttpHelper) |
| Service    | Business logic. In other words, it depends on<br/>Dao, and manipulate twitter object according<br/>to application requirements (e.g. select certain<br/>fields when showing tweet object) |
| Runnner    | Parse user CLI inputs and then calls the<br/>corresponding service methods |
| Main       | Create above components and start<br/>applications           |



# 2.Java Grep App

## Introduction

A simple grep app in Java. The app searches for a text pattern recursively in a given directory, and output matched lines to a file.

## Usage

```
USAGE: JavaGrepImp regex rootPath outFile

Similar to
egrep -r {regex} {rootPath} > {outFile}
```

## Design and Implementation

##### Pseudo code and workflow

```
matchedLines = []
for file in listFiles(rootDir)
    for line in readLines(file)
        if containsPattern(line)
          matchedLines.add(line)
writeToFile(matchedLines)
```



# 3.JDBC 

## Introduction

The JDBC application uses the Java Database Connectivity API to connect and send SQL queries to a Postgresql database. The database models a sales business. The app can create a new customer, delete a customer, update information of a customer, find customer information, and get order information

## Design and Implementation

##### Libraries

JDBC Driver

```
<dependency>
<groupId>org.postgresql</groupId>
<artifactId>postgresql</artifactId>
<version>42.2.5</version>
</dependency>
```

##### Diagrams
https://github.com/AshleyZifan/Jarvis_Apps/blob/master/jdbc.jpg?raw=true



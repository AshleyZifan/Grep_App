# 1.Java Grep App

## Introduction

A simple grep app in Java. The app searches for a text pattern recursively in a given directory, and output matched lines to a file.

## Usage

```
USAGE: JavaGrepImp regex rootPath outFile

Similar to
egrep -r {regex} {rootPath} > {outFile}
```



# 2.JDBC 

## Introduction

A JDBC application

## Usage

```
JDBCExecutor
```



# 3.Twitter CLI App

## Introduction

A Twitter CLI App that can create, read, delete tweets.

## Usage

Create a tweet on your timeline

```
USAGE: TwitterCLI post "tweet_text" "latitude:longitude"

Description: Create a tweet with a geotag and output the created tweet object(simplifeid version) in JSON format.
Arguments:
tweet_text - tweet_text cannot exceed 140
UTF-8 encoded characters.
latitude:longitude - Geo location.
```

Read/Show a tweet by ID

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

Delete a tweet by tweet ID

```
USAGE: TwitterCLI delete tweet_ids

Description: Delete a list of tweets by id Output deleted tweet id and print deleted tweet object.

Arguments:
tweet_ids - A comma-separated list of tweets.
```


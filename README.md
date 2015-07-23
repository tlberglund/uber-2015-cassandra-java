# Some Cassandra Java Examples

All of this were originally written by @beccam and just remixed by mere for a live-coding exercise at UberConf 2015.

To make these work, you'll have to have a C* instance running somewhere, and edit the IP that the `Cluster` is connecting to in the `connect()` method. The C* instance will need this table:

~~~sql
CREATE TABLE users (
    userid uuid,
    firstname text,
    lastname text,
    email text,
    created_date timestamp,
    PRIMARY KEY (userid)
);
~~~

Run `gradlew cassandra` to execute the `main()` method. Comment and uncomment things in that method to exercise the various driver features.

This is a quick hack and by no means a comprehensive demo of anything, but it may be useful to some. :grinning:

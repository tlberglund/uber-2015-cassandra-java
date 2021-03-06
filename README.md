# Some Cassandra Java Examples

All of this were originally written by [@beccam](https://github.com/beccam) and merely remixed by me for a live-coding exercise at UberConf 2015.

To make these work, you'll have to have a C* instance running somewhere, and edit the `clusterAddress` at the top of the `Cassandra` class to point to a node in that cluster. The C* instance will need this table:

~~~sql
CREATE TABLE users (
    user_id uuid,
    first_name text,
    last_name text,
    email text,
    created_date timestamp,
    PRIMARY KEY (user_id)
);
~~~

Run `gradlew cassandra` to execute the `main()` method. Comment and uncomment things in that method to exercise the various driver features.

This is a quick hack and by no means a comprehensive demo of anything, but it may be useful to some. :grinning:

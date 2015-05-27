AppDynamics Evaluation
===================
Submitted by:

 - Nikhil Katre (nkatre@ncsu.edu)

Submission Files:
>  - README.md
>  - URLMap.java
>  - input.txt

Question
-------------

 1. You’re given an input file. Each line consists of a timestamp (unix
    epoch in seconds) and a url separated by ‘|’ (pipe operator).
 2. The entries are not in any chronological order. Your task is to
    produce a summarized report on url hit count, sorted from highest
    hit count to lowest count, organized daily (use GMT) with the
    earliest date appearing first.
 3. You should display the number of times each url is visited. Your
    program should take in one command line argument: input file name.
    The output should be printed to stdout.
 4. You may assume that the number of unique hit counts and the number
    of days are much smaller than the number of unique URLs.
 5. You may also assume that number of unique URLs can fit in memory.
    Please provide a complexity analysis in Big-O notation for your
    program


Example
-------------
input.txt

    1407564301|www.nba.com
    1407478021|www.facebook.com
    1407478022|www.facebook.com
    1407481200|news.ycombinator.com
    1407478028|www.google.com
    1407564301|sports.yahoo.com
    1407564300|www.cnn.com
    1407564300|www.nba.com
    1407564300|www.nba.com
    1407564301|sports.yahoo.com
    1407478022|www.google.com
    1407648022|www.twitter.com

Output

    08/08/2014 GMT
    www.facebook.com 2
    www.google.com 2
    news.ycombinator.com 1
    08/09/2014 GMT
    www.nba.com 3
    sports.yahoo.com 2
    www.cnn.com 1
    08/10/2014 GMT
    www.twitter.com 1


Time Complexity
-------------

The worst case time complexity of the program is **O(n)** where n is the number of unique URLs

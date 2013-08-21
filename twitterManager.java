public class TwitterManager {
 
	SentimentClassifier sentClassifier;
	int LIMIT= 500; //the number of retrieved tweets
	ConfigurationBuilder cb;
	Twitter twitter;

	public TwitterManager() {
		cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey("***");
		cb.setOAuthConsumerSecret("***");
		cb.setOAuthAccessToken("***");
		cb.setOAuthAccessTokenSecret("***");
		twitter = new TwitterFactory(cb.build()).getInstance();
		sentClassifier = new SentimentClassifier();
	}

	public void performQuery(String inQuery) throws InterruptedException, IOException {
		Query query = new Query(inQuery);
		query.setCount(100);
	try {
		int count=0;
		QueryResult r;
		do {
			r = twitter.search(query);
			ArrayList ts= (ArrayList) r.getTweets();

			for (int i = 0; i &lt; ts.size() &amp;&amp; count &lt; LIMIT; i++) {
				count++;
				Status t = ts.get(i);
				String text = t.getText();
				System.out.println("Text: " + text);
				String name = t.getUser().getScreenName();
				System.out.println("User: " + name);
				String sent = sentClassifier.classify(t.getText());
				System.out.println("Sentiment: " + sent); 
			}   
		} while ((query = r.nextQuery()) != null &amp;&amp; count &lt; LIMIT);
	}
	
	catch (TwitterException te) {
		System.out.println("Couldn't connect: " + te);
	}
 }

}
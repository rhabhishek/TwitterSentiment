public class SentimentClassifier {

	String[] categories;
	LMClassifier class;

	public SentimentClassifier() {
	
	try {
		class= (LMClassifier) AbstractExternalizable.readObject(new File("abc.txt"));
		categories = class.categories();
	}
	catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	catch (IOException e) {
		e.printStackTrace();
	}

	}

	public String classify(String text) {
	ConditionalClassification classification = class.classify(text);
	return classification.bestCategory();
	}
	
}
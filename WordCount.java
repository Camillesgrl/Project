import java.util.*;
import java.text.DecimalFormat;

public class WordCount {
	private StringTokenizer tokenizer;
	private Hashtable wordsOccurenceDict;
	private int numberOfWords = 0;

	public WordCount() {
		wordsOccurenceDict = new Hashtable<String, Integer>();
	}
	
	public void addWordsLine(String line){
		//get the line words
		tokenizer = new StringTokenizer (line,"\t\n\r\f,.:;?![]' -\"");
		System.out.println("Line countains " + tokenizer.countTokens()+"tokens");
		
		numberOfWords = tokenizer.countTokens() + numberOfWords;
		
		while (tokenizer.hasMoreTokens()){
					String token = tokenizer.nextToken().toLowerCase();
					System.out.println("Token = "+token);
					//update the dictionary
					if(wordsOccurenceDict.containsKey(token)){
						//the word is already in the dictionary: we increment the occurence
						int occurence = (Integer)wordsOccurenceDict.get(token)+1;
						wordsOccurenceDict.put(token,occurence);
					}
					else{
						wordsOccurenceDict.put(token,1);
					}
							
		}
		//System.out.println("\n\nDictionnary:\n\n"+wordsOccurenceDict.toString());
    }
    
    public void showTheFrequency(){
		Enumeration<String> words = wordsOccurenceDict.keys();
		
		while (words.hasMoreElements()){
			String aWord = words.nextElement();
			DecimalFormat df = new DecimalFormat("#.##");      
			double percent = (double)((Integer)wordsOccurenceDict.get(aWord))/numberOfWords*100;
			percent = Double.valueOf(df.format(percent));

			System.out.println("Frequency for: "+ aWord + " is " + percent + "%" );		
							
		}
	}
}

import java.util.*;
import java.text.DecimalFormat;

/**
 * Class dealing with the word counts and their frequency for a file. 
 * @author Camille
 *
 */
public class WordCount
{
	//the file content
	private String fileContent=null;
	//the result hashtable containing each word and their occurrences
    private Hashtable<String, Integer> wordsOccurenceDict=null;
	//the number of words
    private int numberOfWords = 0;

    /**
     * WordCount constructor. 
     * @param content The file content that will be parsed to count the words.
     */
    public WordCount(String content)
    {
    	fileContent = content;
    }

    /**
     * @return The Hashtable object containing the words and their occurrence.
     */
    public Hashtable getHashtable()
    {
        return wordsOccurenceDict;
    }

    /**
     * Method that parse content and store the words and their occurrence in the result hashtable. 
     */
    public void countWords()
    {
		//prepare the result hashtable
        wordsOccurenceDict = new Hashtable<String, Integer>();
        //get the text words
        StringTokenizer tokenizer = new StringTokenizer (fileContent,"\t\n\r\f,.:;?![]' -\"");
		//set the number of words
        numberOfWords = tokenizer.countTokens();

		//this loop go throw the words of the file to populate the hashtable
        while (tokenizer.hasMoreTokens())
        {
            String token = tokenizer.nextToken().toLowerCase();//words with different case are considered as a same word
            //update the dictionary
            if(wordsOccurenceDict.containsKey(token))
            {
                //the word is already in the dictionary: we increment the occurence
                int occurence = (Integer)wordsOccurenceDict.get(token)+1;
                wordsOccurenceDict.put(token,occurence);
            }
            else
            {
                //the word is npt yet in the dictionary: we add it with an occurrence of 1
                wordsOccurenceDict.put(token,1);
            }

        }
    }

    /**
     * @return A HashTable containing the words and their frequency (as a String object)
     */
    public Hashtable<String, String> getFrequency()
    {
		Hashtable<String,String> res = new Hashtable<String, String>();
		//if the words had not yet been retrieved from the file, we do it now.
        if(fileContent==null)
            countWords();

		//To check the frequency, we read the hashtable (iterate through the words)
        Enumeration<String> words = wordsOccurenceDict.keys();
        while (words.hasMoreElements())
        {
            String aWord = words.nextElement();
			//we display the percentage as i.e. "0.25%")
            DecimalFormat df = new DecimalFormat("#.##");
            double percent = (double)((Integer)wordsOccurenceDict.get(aWord))/numberOfWords*100;
            percent = Double.valueOf(df.format(percent));
			//add the word and the percentage string in the result hashtable
            res.put(aWord, (percent+"%"));
        }
        return res;
    }
}

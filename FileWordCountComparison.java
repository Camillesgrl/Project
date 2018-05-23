import java.util.*;

/**
 * Class dealing with the the comparison of 2 files' words.
 * The class uses Vector to store the words of both files and their respective occurrences.
 * @author Camille
 *
 */
public class FileWordCountComparison
{
	//the hashtable containing the words of the file file and their occurrences
    private Hashtable<String, Integer> hashTable1;
	//the hashtable containing the words of the file file and their occurrences
    private Hashtable<String, Integer> hashTable2;
	//the resut vectors (for the words, and for the occurrences)
    private Vector<String> filesWords;
    private Vector<Integer> file1WordOcc;
    private Vector<Integer> file2WordOcc;

    /**
     * FileWordCountComparison constructor.
     * @param file1HashTable The HashTable containing the words and their occurrence for the first file.
     * @param file2HashTable The HashTable containing the words and their occurrence for the second file.
     */
    public FileWordCountComparison(Hashtable<String, Integer> file1HashTable, Hashtable<String, Integer> file2HashTable)
    {
        hashTable1 =  file1HashTable;
        hashTable2 =  file2HashTable;
    }

    /**
     * @return The vector containing the words of both files.
     */
    public Vector<String> getFilesWordsVector()
    {
        return filesWords;
    }

    /**
     * @return The vector containing the occurence of the words for the first file (the words are contained in BOTH files).
     */
    public Vector<Integer> getFile1WordOccVector()
    {
        return file1WordOcc;
    }

    /**
     * @return The vector containing the occurence of the words for the second file (the words are contained in BOTH files).
     */
    public Vector<Integer> getFile2WordOccVector()
    {
        return file2WordOcc;
    }

    /**
     * Method that check the number of words in both files, and prepare the result vectors.
     * The method set the values in the vectors in the ranking order OF THE FIRST FILE.
     */
    public void compare()
    {
		//prepare the result vectors
        filesWords = new Vector<String>();
        file1WordOcc = new Vector<Integer>();
        file2WordOcc = new Vector<Integer>();

		//go through the first hashtable to find the occurences in both files        
        Enumeration<String> wordsFile1 = hashTable1.keys();
        while (wordsFile1.hasMoreElements())
        {
            String aWord = wordsFile1.nextElement();
            int occurenceFirst =(Integer)hashTable1.get(aWord);
            //check if does this word exist in the second file
            int occurenceSecond = 0;
            if(hashTable2.get(aWord)!=null)
            {
                occurenceSecond = (Integer)hashTable2.get(aWord);
            }
            //insert the words so that the numbers of words is ranked against file 1
            int insertAt = 0;
            while(insertAt<filesWords.size() && occurenceFirst<file1WordOcc.elementAt(insertAt))
            {
                insertAt++;
            }
            filesWords.insertElementAt(aWord,insertAt);
            file1WordOcc.insertElementAt(occurenceFirst,insertAt);
            file2WordOcc.insertElementAt(occurenceSecond,insertAt);
        }
        //check words that are only in the second file
        Enumeration<String> wordsFile2 = hashTable2.keys();
        while (wordsFile2.hasMoreElements())
        {
            String aWord = wordsFile2.nextElement();
            if(hashTable1.get(aWord)!=null)
            {
                filesWords.add(aWord);
                file1WordOcc.add(0);
                file2WordOcc.add((Integer)hashTable2.get(aWord));
            }
        }
    }
}

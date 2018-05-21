import java.util.*;


public class FileWordCountComparison{
	
	private Hashtable<String, Integer> hashTable1;
	private Hashtable<String, Integer> hashTable2;
	
	public FileWordCountComparison(Hashtable<String, Integer> file1HashTable, Hashtable<String, Integer> file2HashTable){
			hashTable1 =  file1HashTable;
			hashTable2 =  file2HashTable;
	}
	
	public void compare(){
		 //go through the first hashtable to find the occurences in both files
		 Vector<String> filesWords = new Vector<String>();
		 Vector<Integer> file1WordOcc = new Vector<Integer>();
		 Vector<Integer> file2WordOcc = new Vector<Integer>();
		 
		 Enumeration<String> wordsFile1 = hashTable1.keys();
		while (wordsFile1.hasMoreElements()){
			String aWord = wordsFile1.nextElement();
			int occurenceFirst =(Integer)hashTable1.get(aWord);
			//check if does this word exist in the second file
			int occurenceSecond = 0;
			if(hashTable2.get(aWord)!=null){
				occurenceSecond = (Integer)hashTable2.get(aWord);
			}							
			//insert the words so that the numbers of words is ranked against file 1
			int insertAt = 0;
			while(insertAt<filesWords.size() && occurenceFirst<file1WordOcc.elementAt(insertAt)){
				insertAt++;
			}
			filesWords.insertElementAt(aWord,insertAt);
			file1WordOcc.insertElementAt(occurenceFirst,insertAt);
			file2WordOcc.insertElementAt(occurenceSecond,insertAt);				
		}
		//check words that are only in the second file
		Enumeration<String> wordsFile2 = hashTable2.keys();
		while (wordsFile2.hasMoreElements()){
			String aWord = wordsFile2.nextElement();
			if(hashTable1.get(aWord)!=null){
				filesWords.add(aWord);
				file1WordOcc.add(0);
				file2WordOcc.add((Integer)hashTable2.get(aWord));				
			}							
		}
		for(int i = 0; i< filesWords.size();i++){
			System.out.println(filesWords.elementAt(i)+"\t"+file1WordOcc.elementAt(i)+"\t"+file2WordOcc.elementAt(i)); 
		}
		
	}
}

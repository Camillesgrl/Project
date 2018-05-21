import java.io.*;


public class Reader{

	public static void main  (String args[]){
    
    int firstFileIndex;
    int secondFileIndex;
   	String fichiers [] = {"test1.txt", "test2.txt","test3.txt", "test4.txt","test5.txt"};

    try{
		firstFileIndex = Integer.parseInt(args[0]);
		secondFileIndex = Integer.parseInt(args[1]);
		if(firstFileIndex < 1 || firstFileIndex > fichiers.length || secondFileIndex < 1 || secondFileIndex > fichiers.length)
			throw new Exception("File indexes are not right.");
    }
    catch (Exception ex){
		System.out.println("Problem with arguments please use 2 numbers.");
		return;
	}
	
	FileReader lecture = null;
	BufferedReader buff = null;

		try{
			//read the first file
				lecture = new FileReader("./Project3Testfiles/"+fichiers[firstFileIndex-1]); 
				buff = new BufferedReader(lecture);
				String line;
				WordCount fileWordCounter = new WordCount();
		
				while ((line = buff.readLine()) != null){
					//System.out.println("Input: \t" + line);
					fileWordCounter.addWordsLine(line);
				}
				fileWordCounter.showTheFrequency();

				lecture.close();
				buff.close();
				
			//read the second file
			    System.out.println("\n");
				lecture = new FileReader("./Project3Testfiles/"+fichiers[secondFileIndex-1]); 
				buff = new BufferedReader(lecture);
				line = "";
				WordCount fileWordCounter2 = new WordCount();
		
				while ((line = buff.readLine()) != null){
					//System.out.println("Input: \t" + line);
					fileWordCounter2.addWordsLine(line);
				}
					
				fileWordCounter2.showTheFrequency();
				
				FileWordCountComparison fileWordComp = new FileWordCountComparison(fileWordCounter.getHashtable(),fileWordCounter2.getHashtable());
				fileWordComp.compare();
		}
		
		catch (Exception e){
			e.printStackTrace();
		}

		finally{

			try{ 

				if (buff != null)
					buff.close();
				if (lecture != null)
					lecture.close();
			}

			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}



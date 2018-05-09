import java.io.*;


public class Reader{

	public static void main  (String args[]){

	int fileIndex = 1;
	FileReader lecture = null;
	BufferedReader buff = null;
	//String[] fichiers = new String[5];
	String fichiers [] = {"test1.txt", "test2.txt","test3.txt", "test4.txt","test5.text"};

		try{
			for (int i = 0; i < fichiers.length; i++){
				lecture = new FileReader("./Project3Testfiles/test"+fileIndex+".txt");    
				buff = new BufferedReader(lecture);
				String line;
				fileIndex++;
		
				while ((line = buff.readLine()) != null){
				System.out.println(line);
				}
			}	

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



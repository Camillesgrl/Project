/**
 * Class of the Plagiarism programme, entry point of the programme.
 * This class launches the GUI of the programme.
 * @author Camille
 *
 */
public class Plagiarism
{
	//the names of the files to compare
    private static String files [] = {"test1.txt", "test2.txt","test3.txt", "test4.txt","test5.txt"};
	//the folder containing the files to compare
	private static String filesFolder = "./Project3Testfiles/";

    /**
     * Entry point of the Plagiarism progamme
     * @param args Arguments of the programme (not used)
     */
    public static void main (String args [])
    {		
        GUI g = new GUI();
    }

    /**
     * The files list used in the Plagiarism programme.
     * @return An array of String representing the name of the files.
     */
    public static String[] getFiles()
    {
        return files;
    }
	
	/**
	 * The folder containing the files used in the Plagiarism programme.
	 * @return The string representing the folder.
	 */
	public static String getFilesFolder(){
		return filesFolder;
	}
}

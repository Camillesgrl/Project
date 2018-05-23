import java.io.*;

/**
 * Class used to read a file.
 * @author Camille
 *
 */
public class Reader
{
	//the file path of the file
	private String filePath = null;
	//the content of the file
    private String fileText = null;    

    /**
     * Reader constructor.
     * @param path The full path of the file to be read.
     */
    public Reader(String path)
    {
        filePath =  path;
    }

    /**
     * Method to get the file content that has been read.
     * @return The file content.
     */
    public String getFileText()
    {
        return fileText;
    }

    /**
     * Method to read the file.
     */
    public void read()
    {
        FileReader lecture = null;
        BufferedReader buff = null;
		String line=""; //we need to use a temporary variable because at the last readLine call returns null
        try
        {            
            lecture = new FileReader(filePath);
            buff = new BufferedReader(lecture);
            while ((line = buff.readLine()) != null)
            {
                fileText = fileText+line;
            }
            lecture.close();
            buff.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
			//if an error occured, we still try to close the buffer and the file reader
            try{
                if (buff != null)
                    buff.close();
                if (lecture != null)
                    lecture.close();
            }
            catch (Exception e2)
            {
                e2.printStackTrace();
            }
        }
    }
}



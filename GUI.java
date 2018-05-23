import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * The GUI class handles the GUI of the Plagiarms programme.
 * @author      Camille
 */
public class GUI implements ActionListener
{
    private JComboBox c, c2; //the comboboxes that contain the list of files
    private JButton b, b2; //the buttons for showing the word frequencies of 2 files, and for showing the ranked word counts of 2 files
    private JFrame f = new JFrame ("Plagiarism"); //the frame of this GUI

    /**
     * GUI class contructor. The construsctor create the main window of the Plagiarism programme.
     */
    public GUI()
    {
		//The main window contains a panel organised, containing a left and right panel put one after the other.
        JPanel mainPanel = new JPanel();
        JPanel firstPanel = new JPanel();
        JPanel secondPanel = new JPanel();
		mainPanel.add(firstPanel);
        mainPanel.add(secondPanel);
		f.setContentPane(mainPanel);

		//the first panel contains the elements to select the files. It is organised in a 2x2 grid (labels and comboboxes)
		//set the label and the combobox for the first file selection
        firstPanel.add(new JLabel("Select the first file:"));
        c =  new JComboBox(Plagiarism.getFiles());
        c.setSelectedIndex(0);
        firstPanel.add(c);
		//set the label and the combobox for the second file selection
        firstPanel.add(new JLabel("Select the second file:"));
        c2 =  new JComboBox(Plagiarism.getFiles());
        c2.setSelectedIndex(0);
        firstPanel.add(c2);
        firstPanel.setLayout(new GridLayout(2,2));

		//the second panel only contains the action buttons one after the other (1 for getting the frequencies, the other for the words comparison)
        b = new JButton("Get words frequency");
        b.addActionListener(this);
        secondPanel.add(b);
        b2 = new JButton("Get words occurence comparison");
        secondPanel.add(b2);
        b2.addActionListener(this);

		//display the frame
        f.setSize(400,200);
		f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /** 
     * The method is triggered when the buttons are clicked.
     */
    public void actionPerformed(ActionEvent a)
    {
        //in every case we need to read the files and get the words and their occurrences
        //read the first file
        Reader reader = new Reader(Plagiarism.getFilesFolder()+c.getSelectedItem());
        reader.read();
        String firstFileText = reader.getFileText();
        //get the occurences of the first file
        WordCount firstFileWordCounter = new WordCount(firstFileText);
        firstFileWordCounter.countWords();
        Hashtable<String, Integer> firstFileWordCountHashTable = firstFileWordCounter.getHashtable();
		//read the second file
        Reader reader2 = new Reader("./Project3Testfiles/"+c2.getSelectedItem());
        reader2.read();
        String secondFileText = reader2.getFileText();
        WordCount secondFileWordCounter = new WordCount(secondFileText);
        secondFileWordCounter.countWords();
        Hashtable<String, Integer> secondFileWordCountHashTable = secondFileWordCounter.getHashtable();

        //Display the frequencies if the first button is hit
        if(a.getSource() == b)
        {
            Hashtable<String, String> firstFileFreqHashTable = firstFileWordCounter.getFrequency();
            Hashtable<String, String> secondFileFreqHashTable = secondFileWordCounter.getFrequency();
            showFreqWindow(firstFileFreqHashTable,secondFileFreqHashTable);
        }
        else if(a.getSource() == b2)
        {
            FileWordCountComparison fileWordComp = new FileWordCountComparison(firstFileWordCountHashTable,secondFileWordCountHashTable);
            fileWordComp.compare();
            showWordsCompWindow(fileWordComp.getFilesWordsVector(),fileWordComp.getFile1WordOccVector(),fileWordComp.getFile2WordOccVector());
        }
    }

    /**
     * Opens a dialog window containing the word frequency of each files.
     * @param firstFileFreqHashTable the hashtable containing the words and their frequencies of the first file
     * @param secondFileFreqHashTable the hashtable containing the words and their frequencies of the first file
     */
    private void showFreqWindow(Hashtable<String,String> firstFileFreqHashTable,Hashtable<String,String> secondFileFreqHashTable)
    {
		//the method uses the 2 hashtables to display their content in 2 text areas, side by side
		//the text areas are put into panels that are organised in a box to stack the label header and the text areas
		//these panels are contained to a main panel, organised into a GridLayout (1 row, 2 columns)
		//and displayed in a modal dialog window
        JDialog freqDialog = new JDialog(f,"Frequencies",true);
        JPanel mainPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
		mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        mainPanel.setLayout(new GridLayout(1,2));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        freqDialog.getContentPane().add(mainPanel);

		//content of the left panel
        JLabel label1 = new JLabel(c.getSelectedItem().toString());
        leftPanel.add(label1);
        JTextArea jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        JScrollPane jScroll = new JScrollPane(jTextArea);
        String text = "";
        Enumeration<String> wordsFile1 = firstFileFreqHashTable.keys();
        while (wordsFile1.hasMoreElements())
        {
            String aWord = wordsFile1.nextElement();
            String freq = firstFileFreqHashTable.get(aWord);
            text = (text + aWord + " : "+ freq + "\n");
        }
        jTextArea.setText(text);
        jTextArea.setCaretPosition(0);//if we dont put the cursor at 0, once the text is added, the scrollbar is down, this allow to see the text from beginning
        leftPanel.add(jScroll);

		//content of the second panel
        JLabel label2 = new JLabel(c2.getSelectedItem().toString());
        rightPanel.add(label2);
        JTextArea jTextArea2 = new JTextArea();
        jTextArea2.setEditable(false);
        JScrollPane jScroll2 = new JScrollPane(jTextArea2);
        text = "";
        Enumeration<String> wordsFile2 = secondFileFreqHashTable.keys();
        while (wordsFile2.hasMoreElements())
        {
            String aWord = wordsFile2.nextElement();
            String freq = secondFileFreqHashTable.get(aWord);
            text = (text + aWord + " : "+ freq + "\n");
        }
        jTextArea2.setText(text);
        jTextArea2.setCaretPosition(0);
        rightPanel.add(jScroll2);

		//display the dialog
        freqDialog.setSize(400,800);
        freqDialog.setVisible(true);

    }

    /**
     * @param fileWordsVector The vector containing the words of both files.
     * @param file1CountsVector The vector containing the occurrences of the words for the first file.
     * @param file2CountsVector The vector containing the occurrences of the words for the second file.
     */
    private void showWordsCompWindow(Vector<String> fileWordsVector,Vector<Integer> file1CountsVector, Vector<Integer> file2CountsVector)
    {
		//the method uses the vectors for the words, occurrences of the first file, and of the second file
		//to display the comparison result in a table
		//and displayed in a modal dialog window
        JDialog freqDialog = new JDialog(f,"Word count comparison",true);
        JPanel mainPanel = new JPanel();
        freqDialog.getContentPane().add(mainPanel);

		//Prepare the vector containing the column names to be put in the table
        Vector columnNameVector = new Vector();
        columnNameVector.add("Words");
        columnNameVector.add("First file");
        columnNameVector.add("Second File");

		//Prepare the vector containing vectors of each row of the table
        Vector tableData = new Vector();
        for(int i = 0; i<fileWordsVector.size(); i++)
        {
            Vector rowVector = new Vector();
            rowVector.add(fileWordsVector.elementAt(i));
            rowVector.add(file1CountsVector.elementAt(i));
            rowVector.add(file2CountsVector.elementAt(i));
            tableData.add(rowVector);
        }

		//create the table with the 2 vectors created above
        JTable jTable = new JTable(tableData, columnNameVector);
        jTable.setEnabled(false);
        JScrollPane jScroll = new JScrollPane(jTable);
        mainPanel.add(jScroll);

		//display the dialog
        freqDialog.setSize(500,500);
        freqDialog.setVisible(true);
    }
}

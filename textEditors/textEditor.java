import org.w3c.dom.xpath.XPathResult;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class textEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JTextArea textArea;
    JMenu file , edit;
    // menu Item for File
    JMenuItem newFile , openFile , saveFile ;
    // menu Item for Edit
    JMenuItem copy , cut , paste , selectAll , close;
    textEditor(){
        frame = new JFrame();
        menuBar = new JMenuBar();
        textArea = new JTextArea();

        file = new JMenu("File");
        edit = new JMenu("Edit");
        // adding file Menu Item
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        // before adding this into file add in the action Listener
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // adding Edit menuItem

        copy= new JMenuItem("Copy");
        cut = new JMenuItem("Cut");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close/Exit");

        // before adding in edit menu add in the actionListener
        copy.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        menuBar.add(file);
        menuBar.add(edit);


        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        // add to textArea Panel
         panel.add(textArea , BorderLayout.CENTER);
         // Create Scroll Pane
        JScrollPane scrollPane = new JScrollPane(textArea , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // add scrollPane to panel
        panel.add(scrollPane);


        frame.add(textArea); // in the starting


        frame.setBounds(150 , 200 ,400 , 400);
        frame.setVisible(true);
       // frame.setResizable(false);
        frame.setLayout(null);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
          if(actionEvent.getSource()== copy){
              textArea.copy();
          }
          if(actionEvent.getSource()== cut){
              textArea.cut();
          }
          if(actionEvent.getSource()== paste){
               textArea.paste();
          }
          if(actionEvent.getSource()== selectAll){
               textArea.selectAll();
          }
          if(actionEvent.getSource()== close) {
              System.exit(0);
          }

          if(actionEvent.getSource()==openFile){
              String s = "C:";
              String s1 = "C:";
              JFileChooser fileChooser = new JFileChooser("C:");  // not write currentDirectoryPath just write "" currentDirectoryPath automatically will come

              int chooseOption = fileChooser.showOpenDialog(null);
              // we have clicked on open button
              if(chooseOption==JFileChooser.APPROVE_OPTION){
                  // Getting selected file
                  File file = fileChooser.getSelectedFile();
                  // get the path of selected file
                  String filePath = file.getPath();
                  try {
                      // Initialize file reader
                      FileReader fileReader = new FileReader(filePath);
                      // initialize buffer reader
                      BufferedReader bufferedReader = new BufferedReader(fileReader);
                      String intermediate = "";
                      StringBuilder output = new StringBuilder();
                      // Read contents of file line by line

                      while((intermediate = bufferedReader.readLine())!=null){
                          output.append(intermediate).append("\n");
                      }

                      // set the output to textArea
                      textArea.setText(output.toString());
                  } catch (IOException fileNotFoundException){
                      fileNotFoundException.printStackTrace();
                  }
              }
          }
        if(actionEvent.getSource()==saveFile){
            // Initialize File picker
            JFileChooser fileChooser = new JFileChooser("C:");
            // Get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // check if we clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                // Create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsoluteFile()+".txt");
                try{
                    // initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    // initialize file buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args){
        textEditor txt = new textEditor();
    }


}

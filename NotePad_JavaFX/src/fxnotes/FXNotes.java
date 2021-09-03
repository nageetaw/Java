/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxnotes;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.application.Application;  
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;  
import javafx.scene.control.*;  
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;  
import javafx.stage.Stage;  
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javax.swing.JScrollPane;

/**
 *
 * @author Nageeta Wadhwani
 */


/*
FXNotes main class extends application class to use it's functionality
*/
public class FXNotes extends Application {
    
    @Override
    public void start(Stage primaryStage) {
  // creating a menubar to add edit, file and dialog options     
        MenuBar menubar= new MenuBar();
        
		// creating menu for menu bar
		Menu File = new Menu("File");
		Menu edit= new Menu("Edit");
		Menu help = new Menu("Help");
		
                // menu items for file menu
		MenuItem newFile= new MenuItem("New");
		MenuItem openFile= new MenuItem("Open");
		MenuItem saveFile= new MenuItem("Save");
		MenuItem saveAs= new MenuItem("Save as..");
		MenuItem exit= new MenuItem("Exit");
		
                // addinh menu items to file menu using its get items function then add all
                File.getItems().addAll(newFile,openFile,saveFile,saveAs,exit);
                
              // menu items for about menu
                MenuItem about= new MenuItem("ABout");
                help.getItems().add(about);
                // menu items for edit menu
                MenuItem delete= new MenuItem("Delete");
		MenuItem cut= new MenuItem("Cut");
		MenuItem copy= new MenuItem("Copy");
		MenuItem paste= new MenuItem("Paste");
		MenuItem selectAll= new MenuItem("Select All");
                //creating edibile text are
                TextArea textarea= new TextArea();
             // seting parementers for text are
             // min and max width and height
               textarea.setMinHeight(570);
               textarea.setMaxHeight(Double.MAX_VALUE);
               textarea.setMaxWidth(Double.MAX_VALUE);
               textarea.setMinWidth(400);
               textarea.setEditable(true);
               
               // action event with select all menu item and for using predefined fucntion selectAll() of text area 
                selectAll.setOnAction(ActionEvent ->{
                
                textarea.selectAll();
                
                });
                // action event with copy menu item for using copy() function of text area
                copy.setOnAction(ActioEvent ->{
                textarea.copy();
                });
                 // action event with cut menu item for using cut() function of text area
             
                cut.setOnAction(ActionEvent ->{
                textarea.cut();
                });
                 // action event with paste menu item for using paste() function of text area
             
               paste.setOnAction(ActionEvent ->{
               textarea.paste();
               });
                // action event with delete menu item for using delete() function of text area
             
                delete.setOnAction(ActionEvent ->{
                   
                textarea.deleteText(textarea.getSelection());
                });
                
                 // action event with newfile menu item and using clear() function to claer textarea for new file
             
                newFile.setOnAction(ActionEvent ->{
                textarea.clear();
                });
                
                // action event with open file menu item, that create a filechooser object and thn calling show open dialog method that allows user 
                // to select text file and the by using filereader object reading compelet file character by charater and store those characters in 
                // string later assign that string to text area
                
                openFile.setOnAction(ActionEvent->{
                    FileChooser filechooser= new FileChooser();
                    FileChooser.ExtensionFilter extension= new FileChooser.ExtensionFilter("text Files","*.txt");
                    File OpenFc = filechooser.showOpenDialog(primaryStage);
                    try{
                     FileReader reader= new FileReader(OpenFc);
                      int content;
                      String str="";
                        while ((content = reader.read()) != -1) {
                            str+=((char) content);
                        }
                        textarea.setText(str);
                     reader.read();
                     
                    }catch(Exception e){

                    }

                
                });
                
                // action event with save as  menu item, that create a filechooser object and thn calling show save dialog method that allows user
                // to enter new file name then  by using filewriter object writinh text from textarea to newly created file
               
                saveAs.setOnAction(ActionEvent->{
                FileChooser filechooser= new FileChooser();
                FileChooser.ExtensionFilter extension= new FileChooser.ExtensionFilter("text Files","*.txt");
                File saveFc = filechooser.showSaveDialog(primaryStage);
                try{
                 saveFc.createNewFile();
                 FileWriter writer= new FileWriter(saveFc);
                 writer.write(textarea.getText());
                 writer.close();
                }catch(Exception e){
                
                }
                
                });
                // action event with exit menu item, usiny system.exit to close application
                exit.setOnAction(ActionEvent->{ System.exit(0); });
                
                // using alert to show user information in about menu item
                Alert a = new Alert(AlertType.NONE);
                //setting action event with about menu item, then assign name and student number to alert using setContent method the set the alert type
              about.setOnAction(ActionEvent ->{
                a.setContentText("AKinlana Ibrahim : 3017507");
                a.setAlertType(AlertType.INFORMATION);
               
                        // show the dialog
                        a.show();
              });
                        
                   // action event with save file menu item, that create a filechooser object and thn calling show save dialog method that allows user
                // to enter new file name then  by using filewriter object writing text from textarea into file
              
               saveFile.setOnAction(ActionEvent -> {
                FileChooser filechooser= new FileChooser();
                FileChooser.ExtensionFilter extension= new FileChooser.ExtensionFilter("text Files","*.txt");
                File saveFc = filechooser.showSaveDialog(primaryStage);
                try{
                 FileWriter writer= new FileWriter(saveFc);
                 writer.write(textarea.getText());
                 writer.close();
                }catch(Exception e){
                
                }
               });
               
                
                // adding menu items to edit menu
                edit.getItems().addAll(delete,cut,copy,paste,selectAll);
                // adding menu's to menu bar
                menubar.getMenus().addAll(File,edit,help);
                
              // creating an object of grid pane
                GridPane grid = new GridPane();  
                // adding menubar and text area on thier positions
                grid.add(menubar, 0, 0);
                grid.add(textarea,0,1);
               
                 
              // creating group object and adding grid as a child
               Group group= new Group();
               group.getChildren().add(grid);
              
               // creating scene using group
               Scene scene= new Scene(group,600,600);
            // seting stage title and style
               primaryStage.initStyle(StageStyle.UTILITY);
                primaryStage.setTitle("FXNotes");
                // setting scene using scene object 
                primaryStage.setScene(scene);  
                //show stage
                 primaryStage.show();  
                
		

		
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

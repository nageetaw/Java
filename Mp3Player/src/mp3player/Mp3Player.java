/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3player;


import java.awt.Panel;
import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author nigeetawadhwani
 */
public class Mp3Player extends Application {
    
    ObservableList<String>  AvailableListItems,SelectedListItems;
    ListView<String> listViewSelected,listView;
    String path ="";
     Slider vol,status;
    Media media;
    MediaPlayer player;
    Button Remove,RemoveAll,Pause,Add,Stop,Play;
    Label statusLabel;
    
    @Override
    public void start(Stage primaryStage) {
       
        
        primaryStage.setTitle("MP3 Media Player");
        
        Play = new Button();
        Play.setText("Play");
        Play.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                
                player.play();
                vol.setValue(player.getVolume() * 100);
                
                player.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
                    if (!status.isValueChanging()) {
                        status.setValue(newTime.toSeconds());
                        statusLabel.setText("Status : "+newTime.toMinutes() );
                    }
                });
       
                Pause.setDisable(false);
                Stop.setDisable(false);
            }
        });
        
        
         Add = new Button();
       Add.setText("Add >>");
        Add.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               if(listView.getSelectionModel().getSelectedIndex() >= 0) {
			
			SelectedListItems.add(listView.getSelectionModel().getSelectedItem());
			AvailableListItems.remove(listView.getSelectionModel().getSelectedIndex());
			RemoveAll.setDisable(false); 
			
			
		}
            }
        });
       
        Remove= new Button();
       Remove.setText("< Remove");
        Remove.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
             if(listViewSelected.getSelectionModel().getSelectedIndex() >=0) {
			
			AvailableListItems.add(listViewSelected.getSelectionModel().getSelectedItem().toString());
			SelectedListItems.remove(listViewSelected.getSelectionModel().getSelectedIndex());
			
		}
            }
        });
        
        RemoveAll= new Button();
       RemoveAll.setText("<< RemoveAll");
       RemoveAll.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                for (int i=0;i<SelectedListItems.size();i++) {
			
			AvailableListItems.add(SelectedListItems.get(i));
			
			
		}
		SelectedListItems.clear();
            }
        });
       
       Pause= new Button();
       Pause.setText("Pause");
       
       Pause.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                player.pause();
            }
        });
       
       Stop= new Button();
       Stop.setText("Stop");
       Stop.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               player.stop();
            }
        });
       
        
       
       VBox.setMargin(Pause, new Insets(10, 10, 10, 10));
       VBox.setMargin(Play, new Insets(10, 10, 10, 10));
       VBox.setMargin(Remove, new Insets(10, 10, 10, 10));
       VBox.setMargin(RemoveAll, new Insets(10, 10, 10, 10));
       VBox.setMargin(Add, new Insets(90, 10, 10, 10));
       VBox.setMargin(Stop, new Insets(10, 10, 10, 10));
       
       RemoveAll.setDisable(true);
       Remove.setDisable(true);
       Add.setDisable(true);
       Play.setDisable(true);
       Pause.setDisable(true);
       Stop.setDisable(true);
       
       Play.setStyle("-fx-background-color: #f28482");
       Pause.setStyle("-fx-background-color: #f28482");
       Remove.setStyle("-fx-background-color: #f28482");
       Add.setStyle("-fx-background-color: #f28482");
       RemoveAll.setStyle("-fx-background-color: #f28482");
       Stop.setStyle("-fx-background-color: #f28482");
       
       Font font = Font.font("Courier New", FontWeight.BOLD, 20);

       Play.setFont(font); Pause.setFont(font); Stop.setFont(font); Remove.setFont(font); RemoveAll.setFont(font);
       Add.setFont(font);
       
       Label avail= new Label("Available Songs");
       avail.setStyle("-fx-background-color: #f28482");
       Label selected= new Label("Selected SOngs");
       selected.setStyle("-fx-background-color: #f28482");
       Label volume= new Label("Volume");
       volume.setStyle("-fx-background-color: #f28482");
       statusLabel= new Label("Sttaus");
       statusLabel.setStyle("-fx-background-color: #f28482");
       vol= new Slider();
        VBox. setMargin(avail, new Insets(30, 20, 10, 40));
        VBox. setMargin(selected, new Insets(30, 10, 10, 10));
        
     
      vol.valueProperty().addListener(new ChangeListener<Number>() {
          @Override 
          public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) { 
              player.setVolume(vol.getValue() / 100); 
          } 
      });
       
       avail.setFont(font); selected.setFont(font); 
       status = new Slider();
      
        VBox centerbuttons = new VBox();  
       centerbuttons.setStyle("-fx-padding: 20;"); 
       
        centerbuttons.getChildren().addAll(Add,Remove,RemoveAll,Play,Pause,Stop,volume,vol);
        
        
        AvailableListItems= FXCollections.observableArrayList();
		try{
		 File directoryPath = new File(".//music");
		 File MusicList[] = directoryPath.listFiles();
		 for(File file : MusicList) {
			 AvailableListItems.add(file.getName());	 
		 }
                }catch(Exception e){
                  e.printStackTrace();
                }
                
      listView = new ListView<String>(AvailableListItems);
      listView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          // Your action here
          Add.setDisable(false);
        });
        
        SelectedListItems= FXCollections.observableArrayList();
        listViewSelected = new ListView<String>(SelectedListItems);
        listViewSelected.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          // Your action here
         if(listViewSelected.getSelectionModel().getSelectedIndex() >= 0){ 
          path = new File(".\\music\\"+listViewSelected.getSelectionModel().getSelectedItem()).toURI().toString();
           media = new Media(path);
           player= new MediaPlayer(media);
          Play.setDisable(false);
          Remove.setDisable(false);
         }
          if(SelectedListItems.size() ==0){
           Play.setDisable(true);
           Remove.setDisable(true);
           Pause.setDisable(true);
           RemoveAll.setDisable(true);
           Stop.setDisable(true);
            Add.setDisable(true);
          }
        });
        
         VBox selectedListPane= new VBox();
        selectedListPane.getChildren().addAll(selected,listViewSelected,statusLabel,status);
       VBox. setMargin(listViewSelected, new Insets(50, 10, 10, 10));
       
        VBox availableListPane= new VBox();
        availableListPane.getChildren().addAll(avail,listView);
       VBox. setMargin(listView, new Insets(50, 10, 10, 50));
       
       BackgroundFill background_fill = new BackgroundFill(Color.BLACK, 
                                          CornerRadii.EMPTY, Insets.EMPTY);
  
       Background background = new Background(background_fill);
  
        GridPane root=new GridPane();
        root.setBackground(background);
        root.addColumn(0,availableListPane);
        root.addColumn(1, centerbuttons);
        root.addColumn(2,selectedListPane);
        
        Scene scene = new Scene(root,800,760);
        scene.setFill(Color.web("#81c483"));
        primaryStage.setScene(scene);  
        primaryStage.show();  
    }

    /*public void getCollection(){
    AvailableListItems= (ObservableList<String>) new ArrayList<String>();
		
		 File directoryPath = new File(".//music");
		 File MusicList[] = directoryPath.listFiles();
		 for(File file : MusicList) {
			 AvailableListItems.add(file.getName());	 
		 }
    }*/
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    
}

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import java.util.Optional;
import javafx.event.EventHandler;
public class javaFXGUI
{
    private static TextField uniName;
    private static TextField uniRank; // Initialising the variables for the text boxes
    private static TextField uniPlace;
    private static TextField uniGrades;
    private static ListView<University> uniListView;
    private static ArrayList<University> UniArrList = new ArrayList<University>();
    public static void main(String args[]){
        launchFX();
    }
    public static void displayCloseDialog(WindowEvent we){
        System.out.println("Close button clicked");
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation Dialog");
        alert.setContentText("Are you sure you want to quit?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            terminate();
        }
        else{
            we.consume();
        }
    }
    private static void terminate(){
        System.out.println("Goodbye");
        System.exit(0);
    }
    private static void launchFX(){
        // Initialising the JavaFX program
        new JFXPanel();
        // Runs initialisation on the JavaFX thread
        Platform.runLater(() -> initialiseGUI());
    }
    private static void initialiseGUI(){
        Stage stage = new Stage(); // Initialise stage
        stage.setOnCloseRequest((WindowEvent we) -> displayCloseDialog(we));
        stage.setTitle("University Printing --- Finn");
        stage.setResizable(false); // Remains the same size
        Pane rootPane = new Pane();   // Iniatialise new pane
        stage.setScene(new Scene(rootPane));
        stage.setWidth(768); // stage width and height is equal, 768
        stage.setHeight(768);
        stage.show();
        
        Image image = new Image("sam.jpg");     // Find the image, initialising as an object
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setLayoutX(20);    // setting layout position of image
        iv1.setLayoutY(250);
        iv1.setFitWidth(420);
        iv1.setPreserveRatio(true);
        rootPane.getChildren().add(iv1);    // add image to pane
        
        uniListView = new ListView<University>();
        uniListView.setLayoutX(450);
        uniListView.setLayoutY(50);
        rootPane.getChildren().add(uniListView);
        
        Label label = new Label("Enter details of University");
        label.setLayoutX(100);
        label.setLayoutY(20);
        rootPane.getChildren().add(label);
        
        uniName = new TextField();  // sets uniName to new text field
        uniName.setLayoutX(100); 
        uniName.setLayoutY(50);     // sets layout position of text field
        uniName.setPrefWidth(300);
        uniName.setPromptText("University Name");
        rootPane.getChildren().add(uniName);
        
        uniRank = new TextField();
        uniRank.setLayoutX(100);
        uniRank.setLayoutY(80);
        uniRank.setPrefWidth(300);
        uniRank.setPromptText("University Rank");
        rootPane.getChildren().add(uniRank);
       
        uniPlace = new TextField();
        uniPlace.setLayoutX(100);
        uniPlace.setLayoutY(110);
        uniPlace.setPrefWidth(300);
        uniPlace.setPromptText("University Country");
        rootPane.getChildren().add(uniPlace);
        
        uniGrades = new TextField();
        uniGrades.setLayoutX(100);
        uniGrades.setLayoutY(140);
        uniGrades.setPrefWidth(300);
        uniGrades.setPromptText("Required Grades of entry");
        rootPane.getChildren().add(uniGrades);
        
        Button btn = new Button();
        btn.setText("Print");
        btn.setLayoutX(384);
        btn.setLayoutY(150);
        btn.setOnAction((ActionEvent ae) -> printuniInfo());
        rootPane.getChildren().add(btn);
    }
    private static void printuniInfo(){
        String uniN = uniName.getText();
        int uniR = Integer.parseInt(uniRank.getText());
        String uniP = uniPlace.getText();
        String uniG = uniGrades.getText();
        
        System.out.println(uniN);
        System.out.println(uniR);
        System.out.println(uniP);
        System.out.println(uniG);
        
        uniArrList.add(new University(uniN, uniR, uniP, uniG));
        
        uniListView.getItems().clear();
        
        for(University uni : uniArrList) {
            uniListView.getItems().add(uni);
        }
    }
}

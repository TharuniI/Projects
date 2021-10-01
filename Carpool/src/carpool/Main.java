package carpool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    Controller Make = new Controller();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        String firstName = "User";
        MainPage1(primaryStage, firstName);
    }

    public void MainPage1(Stage primaryStage, String firstName){
        Pane mainLayout = new Pane();
        Make.SetUpPage(primaryStage, mainLayout, "Main Page | Carpool Inc");
        Text welcome = Make.Titles("Hey " + firstName + "!", 50);
        mainLayout.getChildren().addAll(welcome);
        Make.Display(primaryStage, mainLayout);
    }
}

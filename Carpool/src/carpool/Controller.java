package carpool;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;

public class Controller {
    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();

    //                                        MAKE FUNCTIONS                                                          //
    //Page Setup//
    public void SetUpPage(Stage primaryStage, Pane mainLayout, String pageName){
        primaryStage.setTitle(pageName);
        BackgroundFill background_fill = new BackgroundFill(Color.rgb(227, 240, 216), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        mainLayout.setBackground(background);
    }

    //MakeButton//
    public Button Buttons(String text, double OFFSETX, double OFFSETY){
        Button button = new Button(text);
        button.setTextFill(Color.rgb(6, 72, 158));
        button.setScaleX(2);
        button.setScaleY(2);
        button.setLayoutX(primScreenBounds.getWidth()/2 - OFFSETX);
        button.setLayoutY(primScreenBounds.getHeight()/2 + OFFSETY);

        return button;
    }

    //MakeTtitle//
    public Text Titles(String text, int size){
        Text title = new Text(text);
        title.setFill(Color.rgb(6, 72, 158));
        title.setFont(Font.font("Verdana", FontWeight.BOLD, size));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setX(primScreenBounds.getWidth()/2 - title.getLayoutBounds().getWidth()/2);
        title.setY(title.getLayoutBounds().getHeight());

        return title;
    }

    //MakeTextFields//
    public TextField TextFields(String promptText){
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setPrefWidth(550);
        textField.setPrefHeight(50);

        return textField;
    }

    //MakePasswordField//
    public PasswordField PasswordFields(String text){
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(text);
        passwordField.setPrefWidth(550);
        passwordField.setPrefHeight(50);
        passwordField.setLayoutX(primScreenBounds.getWidth()/2 - passwordField.getPrefWidth()/2);

        return passwordField;
    }

    //MakeErrorPrompt//
    public Text ErrorPrompt(String errorMessage){
        Text errorPrompt = new Text(errorMessage);
        errorPrompt.setFill(Color.RED);
        errorPrompt.setFont(Font.font("Verdana", FontWeight.NORMAL, 25));
        errorPrompt.setX(primScreenBounds.getWidth()/2 - errorPrompt.getLayoutBounds().getWidth()/2);

        return errorPrompt;
    }

    //MakeDisplay//
    public void Display(Stage primaryStage, Pane layout){
        //sets size to the size of the user's screen
        Scene scene = new Scene(layout, primScreenBounds.getWidth(), primScreenBounds.getHeight());

        primaryStage.setScene(scene);
        primaryStage.show();

        //centers screen
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 4);
    }

    //                                     READING AND WRITING TO FILES                                               //
    //checks if the username exists
    public int InSystem(File file, String userName) throws FileNotFoundException {
        boolean inSystem = false;
        int count = 0;
        int finalCount = 0;

        if (file.exists()){
            Scanner scanRecords = new Scanner(file);
            //now we can read from the file if it opens
            String users = "";
            String[] userInfo;
            while(scanRecords.hasNext()){
                users = scanRecords.nextLine();
                userInfo = users.split(",");
                count++;

                if(userInfo[0].equals(userName)){
                    inSystem = true;
                    finalCount = count;
                }
            }
        }
        if(inSystem){
            return finalCount;
        }
        return -1;
    }
    //check if the user has inputted the correct password for the username they have inputted
    public boolean CorrectPass(File file, String userName, String password) throws FileNotFoundException {
        int count = InSystem(file, userName);
        try {
            if(count > -1){
                Scanner scanRecords = new Scanner(file);
                String users = "";
                String[] userInfo;

                for(int i = 0; i < count; i++){
                    users = scanRecords.nextLine();
                }
                userInfo = users.split(",");
                if(userInfo[1].equals(password)){
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    //gets specific information based on what the request(index number) is
    public String GetUserInfo(File file, String userName, int index) throws FileNotFoundException {
        int count = InSystem(file, userName);
        Scanner scanRecords = new Scanner(file);
        String users = "";
        String[] userInfo;

        for(int i = 0; i < count; i++){
            users = scanRecords.nextLine();
        }
        userInfo = users.split(",");

        return userInfo[index];
    }
    //add new user into records
    public void AddtoFile(File file, String userName, String password, String firstName, String lastName) throws IOException {
        FileWriter W = new FileWriter(new File(String.valueOf(file)), true);
        W.write("\n");
        W.write(userName + "," + password + "," + firstName + "," + lastName);
        W.close();
    }
}

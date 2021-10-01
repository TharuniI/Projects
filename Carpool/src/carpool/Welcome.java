package carpool;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Welcome extends Application {
    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
    Controller Make = new Controller();
    Controller Records = new Controller();
    File registerFile = new File("src\\carpool\\RegistrationRecords.txt");
    File carpoolFile = new File("src\\carpool\\CarpoolRecords.txt");
    File driverFile = new File("src\\carpool\\DriverRecords.txt");

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //WelcomePage(primaryStage);
        RegistrationPage2(primaryStage);
    }

    //                                             WELCOME PAGE                                                       //
    public void WelcomePage(Stage primaryStage){
        Pane mainLayout = new Pane();
        Make.SetUpPage(primaryStage, mainLayout,"Welcome Page | Carpool Inc");
        Text title = Make.Titles("Carpool Inc", 70);
        title.setY(primScreenBounds.getHeight()/2);

        //Buttons//
        Button loginButton = Make.Buttons("Login", (title.getLayoutBounds().getWidth() - 200), 100);
        Button registerButton = Make.Buttons("Register", (-1*title.getLayoutBounds().getWidth() + 200), 100);

        //Car//
        //link: https://images.vexels.com/media/users/3/145854/isolated/preview/22d6e1bc70e32d591db88c4ccc41addd-smart-car-side-view-by-vexels.png


        //Display//
        mainLayout.getChildren().addAll(title,registerButton, loginButton);
        Make.Display(primaryStage, mainLayout);

        //Button Action//
        loginButton.setOnAction(e -> {
            mainLayout.getChildren().removeAll();
            LoginPage1(primaryStage);
        });
        registerButton.setOnAction(e->{
            mainLayout.getChildren().removeAll();
            RegistrationPage1(primaryStage);
        });
    }

    //                                               LOGIN PAGE                                                       //
    public void LoginPage1(Stage primaryStage){
        //Page Setup//
        double OFFSETY = 100;
        Pane logLayout = new Pane();
        Make.SetUpPage(primaryStage, logLayout,"Login Page | Carpool Inc");
        Text welcome = Make.Titles("Welcome Back!", 50);
        Button backButton = new Button("<-Back");

        //username field
        TextField userNameField = Make.TextFields("Username");
        userNameField.setLayoutX(primScreenBounds.getWidth()/2 - userNameField.getPrefWidth()/2);
        userNameField.setLayoutY(primScreenBounds.getHeight()/2 - 150);

        //password field
        PasswordField passwordField = Make.PasswordFields("Password");
        passwordField.setLayoutY(primScreenBounds.getHeight()/2 - 50);

        //Buttons//
        Button logInButton = Make.Buttons("Login", 0, 200);

        //incorrect password or username!
        Text wrongUsernamePassText = Make.ErrorPrompt("Incorrect Username or Password.");
        wrongUsernamePassText.setY(primScreenBounds.getHeight()/2 + OFFSETY);


        //Display//
        logLayout.getChildren().addAll(welcome, backButton, userNameField, passwordField, logInButton);
        Make.Display(primaryStage,logLayout);

        //Button Action//
        backButton.setOnAction(e->{
            logLayout.getChildren().removeAll();
            WelcomePage(primaryStage);
        });
        logInButton.setOnAction(e->{
            logLayout.getChildren().removeAll(wrongUsernamePassText);
            try {
                //if this username exists and the password matches the username
                if(Records.CorrectPass(registerFile, userNameField.getText(), passwordField.getText())){
                    //only then can we move onto the main page
                    String firstName = Records.GetUserInfo(registerFile, userNameField.getText(), 2);
                    Main Main = new Main();
                    Main.MainPage1(primaryStage, firstName);
                } else {
                    //username doesnt exist
                    logLayout.getChildren().add(wrongUsernamePassText);
                }
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
    }

    //                                      REGISTRATION PAGE 1                                                       //
    public void RegistrationPage1(Stage primaryStage){
        Pane registerLayout = new Pane();
        Make.SetUpPage(primaryStage,registerLayout,"Registration Page | Part 1 | Carpool Inc");
        Text welcome = Make.Titles("Welcome!", 50);
        Button backButton = new Button("<-Back");

        //Register Info//
        double OFFSET = 100;
        double positionY = welcome.getLayoutBounds().getHeight() + OFFSET/2;
        //first name
        Text firstNameText = Make.Titles("First Name ", 25);
        TextField firstNameField = Make.TextFields("");
        Position(registerLayout, firstNameField, firstNameText, OFFSET, positionY);

        //last name
        positionY += OFFSET;
        Text lastNameText =Make.Titles("Last Name ", 25);
        TextField lastNameField = Make.TextFields("");
        Position(registerLayout,lastNameField, lastNameText, OFFSET, positionY);

        //username
        positionY += (OFFSET + OFFSET/2);
        Text userNameText =Make.Titles("User Name ", 25);
        TextField userNameField = Make.TextFields("");
        Position(registerLayout, userNameField, userNameText, OFFSET, positionY);

        //password
        positionY += OFFSET;
        Text passwordText =Make.Titles("Password ", 25);
        PasswordField passwordField1 = Make.PasswordFields("");
        Position(registerLayout, passwordField1, passwordText, OFFSET, positionY);

        //confirm password
        positionY += OFFSET;
        Text passwordText1 =Make.Titles("Confirm Password ", 25);
        PasswordField passwordField2 = Make.PasswordFields("");
        Position(registerLayout, passwordField2, passwordText1, OFFSET, positionY);

        //error prompts
        positionY += OFFSET;
        Text notUnique = Make.ErrorPrompt("This Username Already Exists.");
        notUnique.setY(positionY);
        Text noMatch = Make.ErrorPrompt("The Passwords Don't Match.");
        noMatch.setY(positionY);
        Text fillReq1 = Make.ErrorPrompt("Must Fill All Fields");
        fillReq1.setY(positionY);

        //next button
        positionY += OFFSET/2;
        Button nextButton = Make.Buttons("Next",0, 0);
        nextButton.setLayoutY(positionY);
        nextButton.setLayoutX(primScreenBounds.getWidth()/2 - nextButton.getWidth());

        //Button Action//
        backButton.setOnAction(e->{
            registerLayout.getChildren().removeAll();
            WelcomePage(primaryStage);
        });
        nextButton.setOnAction(e->{
            registerLayout.getChildren().removeAll(noMatch, notUnique, fillReq1);
            try {
                //if none of the fields are empty
                if (!(firstNameField.getText().equals("") || lastNameField.getText().equals("") || userNameField.equals("")
                        || passwordField1.getText().equals("") || passwordField2.equals(""))) {
                    //if the username is unique
                    if (Records.InSystem(registerFile, userNameField.getText()) < 0) {
                        //if the passwords do match
                        if ((passwordField1.getText().equals(passwordField2.getText()))) {
                            //if all this is true only then can we move onto part 2 of registration
                            Records.AddtoFile(registerFile, userNameField.getText(), passwordField1.getText(), firstNameField.getText(), lastNameField.getText());
                            registerLayout.getChildren().removeAll();
                            RegistrationPage2(primaryStage);
                        } else {
                            registerLayout.getChildren().add(noMatch);
                            passwordField1.setText("");
                            passwordField2.setText("");
                        }
                    } else {
                        registerLayout.getChildren().add(notUnique);
                    }
                } else {
                    registerLayout.getChildren().add(fillReq1);
                }
            }catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });


        //Display//
        registerLayout.getChildren().addAll(welcome, backButton, nextButton);
        Make.Display(primaryStage, registerLayout);
    }

    //                                      REGISTRATION PAGE 2                                                       //
    public void RegistrationPage2(Stage primaryStage){
        //Page Setup//
        Pane registerLayout2 = new Pane();
        Make.SetUpPage(primaryStage,registerLayout2,"Registration Page | Part 2 | Carpool Inc");
        Text welcome = Make.Titles("Welcome!", 50);
        Button backButton = new Button("<-Back");

        //Registration Info//
        double OFFSET = 100;
        double positionY = welcome.getLayoutBounds().getHeight() + OFFSET/2;

        //question 1: both
        Text questionText1 = Make.Titles("What do you want to register as?", 25);
        TextField dummyField = Make.TextFields("");
        Position(registerLayout2, dummyField, questionText1, OFFSET, positionY);
        registerLayout2.getChildren().remove(dummyField);

        positionY += (OFFSET/2);
        ChoiceBox<String> cd = new ChoiceBox<>();
        cd.getItems().addAll("Carpooler","Driver");
        cd.setLayoutX(questionText1.getLayoutX() + questionText1.getLayoutBounds().getWidth()/2);
        cd.setLayoutY(positionY);

        //question 2: both
        positionY += (OFFSET/2);
        Text questionText2 = Make.Titles("Where do you live?", 25);
        Position(registerLayout2, dummyField, questionText2, OFFSET, positionY);
        questionText2.setX(primScreenBounds.getWidth()/2 - questionText1.getLayoutBounds().getWidth()- dummyField.getWidth() - OFFSET);
        registerLayout2.getChildren().remove(dummyField);

        positionY += (OFFSET/2);
        ChoiceBox<String> home1 = new ChoiceBox<>();
        home1.getItems().addAll("Victoria", "Warden", "Birchmount", "Kennedy", "Midland", "Brimley", "McCowan", "Bellamy", "Markham", "Neilson", "Morningside");
        home1.setLayoutX(questionText1.getLayoutX() + questionText1.getLayoutBounds().getWidth()/2);
        home1.setLayoutY(positionY);

        ChoiceBox<String> home2 = new ChoiceBox<>();
        home2.getItems().addAll("Steeles", "McNicoll", "Finch", "McLevin", "Sheppard", "Milner", "Progress", "Ellesmere", "Brimorton", "Lawrence", "Eglinton");
        home2.setLayoutX(questionText1.getLayoutX() + questionText1.getLayoutBounds().getWidth()/2 + home1.getWidth() + OFFSET + OFFSET/2);
        home2.setLayoutY(positionY);

        //question 3: both
        positionY += (OFFSET/2);
        Text questionText3 = Make.Titles("Where is your desired location located?", 25);
        Position(registerLayout2, dummyField, questionText3, OFFSET, positionY);
        questionText3.setX(primScreenBounds.getWidth()/2 - questionText1.getLayoutBounds().getWidth()- dummyField.getWidth() - OFFSET);
        registerLayout2.getChildren().remove(dummyField);

        positionY += (OFFSET/2);
        ChoiceBox<String> place1 = new ChoiceBox<>();
        place1.getItems().addAll("Victoria", "Warden", "Birchmount", "Kennedy", "Midland", "Brimley", "McCowan", "Bellamy", "Markham", "Neilson", "Morningside");
        place1.setLayoutX(questionText1.getLayoutX() + questionText1.getLayoutBounds().getWidth()/2);
        place1.setLayoutY(positionY);

        ChoiceBox<String> place2 = new ChoiceBox<>();
        place2.getItems().addAll("Steeles", "McNicoll", "Finch", "McLevin", "Sheppard", "Milner", "Progress", "Ellesmere", "Brimorton", "Lawrence", "Eglinton");
        place2.setLayoutX(questionText1.getLayoutX() + questionText1.getLayoutBounds().getWidth()/2 + place1.getWidth() + OFFSET + OFFSET/2);
        place2.setLayoutY(positionY);


        //Display//
        registerLayout2.getChildren().addAll(welcome, backButton, cd, home1, home2, place1, place2);
        Make.Display(primaryStage, registerLayout2);

        //Button Action//
        backButton.setOnAction(e->{
            registerLayout2.getChildren().removeAll();
            RegistrationPage1(primaryStage);
        });
    }

    //format the textfields and texts of the registration pages
    public void Position(Pane layout, TextField field, Text text, double x, double y){
        field.setLayoutX(primScreenBounds.getWidth()/2 - field.getWidth() - x);
        field.setLayoutY((field.getHeight()/2) + y);
        text.setX(primScreenBounds.getWidth()/2 - text.getLayoutBounds().getWidth()- field.getWidth() - x);
        text.setY(text.getLayoutBounds().getHeight() + y);
        text.setFill(Color.BLACK);

        layout.getChildren().addAll(field, text);
    }
}

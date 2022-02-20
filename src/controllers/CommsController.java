package controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import txt.Txt;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import main.Main;

public class CommsController implements ControlledScreen {

    ScreenController myController;

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent;
    }

    public Txt txt = new Txt();
    public Vector<String> randomString = new Vector<>();


    @FXML
    public AnchorPane main_pain;
    @FXML
    private MenuButton choice_menu;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField commsField_output;

    @FXML
    public TextField wordsField_output;

    @FXML
    void initialize() {
        randomString = txt.read(0);
        commsField_output.setText("Please, 'space' to start...");
        wordsField_output.setText("enter 2 characters(example \"ам\")");
        wordsField_output.setEditable(false);
        commsField_output.autosize();
        keyHandler();
    }

    @FXML
    private void nextButton() {
        commsField_output.setText(txt.getRandomString(randomString));
        System.out.println("next");
    }

    @FXML
    private void switchToMixedFile() {
        randomString = txt.read(0);
        choice_menu.setText("mixed");
        commsField_output.setText(txt.getRandomString(randomString));
        System.out.println("switch to mixed file");
    }

    @FXML
    private void switchToFirstFile() {
        randomString = txt.read(1);
        choice_menu.setText("1)UtopD-side");
        commsField_output.setText(txt.getRandomString(randomString));
        System.out.println("switch to first file");
    }

    @FXML
    private void switchToSecondFile() {
        randomString = txt.read(2);
        choice_menu.setText("2)2UtopD-bottom");
        commsField_output.setText(txt.getRandomString(randomString));
        System.out.println("switch to second file");
    }

    @FXML
    private void switchToThirdFile() {
        randomString = txt.read(3);
        choice_menu.setText("3)D-bottomD-bottom");
        commsField_output.setText(txt.getRandomString(randomString));
        System.out.println("switch to third file");
    }
    @FXML
    public void switchToFourthFile() {
        randomString = txt.read(4);
        choice_menu.setText("4)D-sideD-side");
        commsField_output.setText(txt.getRandomString(randomString));
        System.out.println("switch to fourth file");
    }

    @FXML
    private void showWordsButton() {
        String letters = wordsField_output.getText();
        String message = txt.showWords(txt.txtToMatrix(txt.readFile()), letters);
        wordsField_output.replaceText(0, letters.length(), message);
        if (message.equals("Please, check input value"))
            wordsField_output.setStyle("-fx-background-color: red;");  // use css-style
        wordsField_output.setEditable(false);
        wordsField_output.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.BACK_SPACE) {
                clearWordsButton();
            }

            if (event.getCode() == KeyCode.SPACE) {
                nextButton();
            }
            // or can do this:
            // main_pain.requestFocus();

        });
        System.out.println("showWordsButton");
    }

    @FXML
    private void clearWordsButton() {
        wordsField_output.setEditable(true);
        wordsField_output.clear();
        wordsField_output.setStyle("-fx-background-color: white;");
        wordsField_output.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                //event.consume(); // important thing(if you have keyHandler event = ENTER,in this case)
                showWordsButton();
            }
        });
        System.out.println("clearWordsButton");
    }

    @FXML
    private void wordsFieldOutput() {
        clearWordsButton();
    }


    @FXML
    private void goTimer(/*ActionEvent actionEvent*/) throws IOException {
        /*Parent root = FXMLLoader.load(getClass().getResource("timer.fxml"));
        main_pain.getScene().setRoot(root)*/;

        myController.setScreen(Main.timerScreenID);
        System.out.println("go timer");

    }

    @FXML
    public void keyHandler() {
        main_pain.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                nextButton();
            }
        });
    }


}





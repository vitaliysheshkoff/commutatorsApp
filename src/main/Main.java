package main;
import controllers.ScreenController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    public static String commsScreenID = "comms";
    public static String commsScreenFile = "/fxml/comms.fxml";
    public static String timerScreenID = "timer";
    public static String timerScreenFile = "/fxml/timer.fxml";


    @Override
    public void start(Stage primaryStage)  {

        ScreenController mainContainer = new ScreenController();
        mainContainer.loadScreen(Main.commsScreenID,Main.commsScreenFile);
        mainContainer.loadScreen(Main.timerScreenID,Main.timerScreenFile);

        mainContainer.setScreen(Main.commsScreenID);
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root, 900, 400);
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/cube.png")));
        primaryStage.setTitle("commsApp");
        scene.setFill(Color.rgb(0, 0, 0));
        primaryStage.setScene(scene);



        primaryStage.show();
        primaryStage.setResizable(false);

        /*Parent root = FXMLLoader.load(getClass().getResource("comms.fxml"));
        primaryStage.setTitle("commutatorsApp");


        Scene scene = new Scene(root, 900, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package controllers;
import Kociemba_s_cubesolver.Search;
import Kociemba_s_cubesolver.Tools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import scramble.ScrambleImage;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Main;
import timer.Timer;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class TimerController implements ControlledScreen {

    @FXML
    public Rectangle _0, _1, _2, _3, _4, _5, _6, _7, _8, _9,
            _10, _11, _12, _13, _14, _15, _16, _17, _18, _19,
            _20, _21, _22, _23, _24, _25, _26, _27, _28, _29,
            _30, _31, _32, _33, _34, _35, _36, _37, _38, _39,
            _40, _41, _42, _43, _44, _45, _46, _47, _48, _49,
            _50, _51, _52, _53, _54;

    @FXML
    private Label delete_all_times, delete_current_time;
    @FXML
    private Pane go_commutators;

    @FXML
    private ListView<String> timesList;
    @FXML
    private Label current_time, current_mo3, current_avg5,
            current_avg12, current_avg25, current_avg50, current_avg100,
            best_time, best_mo3, best_avg5, best_avg12, best_avg25,
            best_avg50, best_avg100, mean;
    @FXML
    private TextField scramble_field;
    @FXML
    private Label milliseconds_label, second_label, minutes_label, hours_label;
    @FXML
    public AnchorPane timer_pane;


    ScreenController myController;
    Timer timer;
    boolean timer_is_start, catchKeyAfterPressed = false;
    String scramble, letterScramble;
    StringBuffer StringTime;
    int time = 0;
    int bestTime = Integer.MAX_VALUE,bestMo3 = Integer.MAX_VALUE, bestAvg5 = Integer.MAX_VALUE, bestAvg12 = Integer.MAX_VALUE, bestAvg25 = Integer.MAX_VALUE, bestAvg50 = Integer.MAX_VALUE, bestAvg100 = Integer.MAX_VALUE;
    Vector<Integer> times100;
    Rectangle[] stickers;
    // LinkedList<String> collection = null;
    ObservableList list;
    int numberOfSolve;

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent;
    }

    @FXML
    void initialize() {
        StringTime = new StringBuffer();
        timer = new Timer();
        timer.reset(hours_label, minutes_label, second_label, milliseconds_label);
        stickers = new Rectangle[]{_0, _1, _2, _3, _4, _5, _6, _7, _8, _9,
                _10, _11, _12, _13, _14, _15, _16, _17, _18, _19,
                _20, _21, _22, _23, _24, _25, _26, _27, _28, _29,
                _30, _31, _32, _33, _34, _35, _36, _37, _38, _39,
                _40, _41, _42, _43, _44, _45, _46, _47, _48, _49,
                _50, _51, _52, _53, _54};
        showNewScramble();
        keyHandler();
        times100 = new Vector<>();
        for (int i = 0; i < 100; i++) {
            times100.add(-1);
        }
        list = FXCollections.observableArrayList();
        // timesList.setMouseTransparent(true);
        timesList.setStyle("-fx-background-color: white;-fx-font-size:11.0;-fx-control-inner-background: FFFFFAF0;");
        numberOfSolve = 0;
    }

    private void goCommutators(/*ActionEvent actionEvent*/) {
        //Parent root = FXMLLoader.load(getClass().getResource("comms.fxml"));
        /*timer_pane.getScene().setRoot(root);*/

        myController.setScreen(Main.commsScreenID);
        System.out.println("go commutators");
        if (timer_is_start)
            stop();
        catchKeyAfterPressed = false;
    }

    private void keyHandler() {
        delete_current_time.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && !timer_is_start && list.size() > 0) {
                list.remove(0);
                times100.remove(times100.size() - 1);
                Collections.reverse(times100);
                times100.add(-1);
                Collections.reverse(times100);
                time = times100.lastElement();
                bestTime = Integer.MAX_VALUE;
                numberOfSolve--;
                showCurrentTime();
                showMo3();
                showAvg5();
                showAvg12();
                showAvg25();
                showAvg50();
                showAvg100();
                showMean(); // important showMean before showBestTimes(best times finding in showMean)
                showBestTimes();
            }
        });
        delete_all_times.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && !timer_is_start) {
                numberOfSolve = 0;
                list.clear();
                times100.clear();
                for (int i = 0; i < 100; i++) {
                    times100.add(-1);
                }
                time = -1;
                bestTime = Integer.MAX_VALUE;
                bestMo3 = Integer.MAX_VALUE;
                bestAvg5 = Integer.MAX_VALUE;
                bestAvg12 = Integer.MAX_VALUE;
                bestAvg25 = Integer.MAX_VALUE;
                bestAvg50 = Integer.MAX_VALUE;
                bestAvg100 = Integer.MAX_VALUE;
                showCurrentTime();
                showMo3();
                showAvg5();
                showAvg12();
                showAvg25();
                showAvg50();
                showAvg100();
                showMean(); // important showMean before showBestTimes(best times finding in showMean)
                showBestTimes();
            }
        });
        go_commutators.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown())
                goCommutators();
        });
        timer_pane.setOnKeyReleased(this::startTimer);
        timer_pane.setOnKeyPressed(this::stopTimer);
    }

    public void startTimer(KeyEvent event) {
        if (!catchKeyAfterPressed && event.getCode() == KeyCode.SPACE) {
            scramble_field.setMouseTransparent(true);
            scramble_field.deselect();
            timer_pane.requestFocus();
            setColorOnTime(255, 255, 255);
            timer.startTimer(hours_label, minutes_label, second_label, milliseconds_label);
            timer_is_start = true;
            System.out.print("Start!");
        }
        if (!timer_is_start && catchKeyAfterPressed) {
            System.out.print("Stop!"); // catch KeyReleased after keyPressed to stop timer!
            catchKeyAfterPressed = false;
        }
    }

    public void stopTimer(KeyEvent event) {
        stop();
    }

    private void stop() {
        if (timer_is_start) {
            timer.stopTimer();
            scramble_field.setMouseTransparent(false);
            timer_is_start = false;
            copyTimeFromTimeLabelsToInteger();
            if (list.size() == 100) {
                list.remove(99);
            }
            numberOfSolve++;
            list.add(0, (numberOfSolve + ") " + integerTimeToStringTime(time) + " " + letterScramble));
            timesList.setItems(list);
            showCurrentTime();
            showMo3();
            showAvg5();
            showAvg12();
            showAvg25();
            showAvg50();
            showAvg100();
            showMean(); // important showMean before showBestTimes(best times finding in showMean)
            showBestTimes();

            showNewScramble();
            catchKeyAfterPressed = true;
        }
    }

    private void copyTimeFromTimeLabelsToInteger() {
        time = Integer.parseInt(hours_label.getText()) * 360000 +
                Integer.parseInt(minutes_label.getText()) * 6000 +
                Integer.parseInt(second_label.getText()) * 100 +
                Integer.parseInt(milliseconds_label.getText());
        times100.remove(0);
        times100.add(time);
    }

    private String integerTimeToStringTime(int time) {
        StringTime.replace(0, StringTime.length(), "");
        //StringTime.append("current time: ");
        if (time < 0) {
            return "-";
        }
        int hours = (time / 360000) % 60,
                minutes = (time / 6000) % 60,
                seconds = (time / 100) % 60,
                milliseconds = time % 100;
        if (hours != 0) {
            if (hours < 10 && hours != 0) {
                StringTime.append("0" + hours + ":");
            } else {
                StringTime.append("" + hours + ":");
            }
        }
        if (minutes != 0) {
            if (minutes < 10) {
                StringTime.append("0" + minutes + ":");
            } else {
                StringTime.append("" + minutes + ":");
            }
        }
        if (seconds != 0) {
            if (seconds < 10) {
                StringTime.append("0" + seconds + ".");
            } else {
                StringTime.append("" + seconds + ".");
            }
        } else StringTime.append("0.");
        if (milliseconds < 10) {
            StringTime.append("0" + milliseconds);
        } else {
            StringTime.append("" + milliseconds);
        }
        return StringTime.toString();
    }

    private void showCurrentTime() {
        current_time.setText(integerTimeToStringTime(time));
    }

    private void showMo3() {
        int Mo3 = 0;
        boolean enough = true;
        for (int i = 97; i < times100.size(); i++) {
            if (times100.get(i) < 0)
                enough = false;
            Mo3 += times100.get(i);
        }
        Mo3 = (int) Math.round(Mo3 / 3.0);

        if (!enough)
            current_mo3.setText("-");
        else {
            current_mo3.setText(integerTimeToStringTime(Mo3));
        }
    }
    private int[] findAvgN(int n){
        int[] returnValue = new int[2];
        int enough = 1;
        int max = times100.get(times100.size() - n);
        int min = times100.get(times100.size() - n);
        int AvgN = 0;
        for (int i = times100.size() - n; i < times100.size(); i++) {
            if (times100.get(i) < 0)
                enough = 0;
            if (max < times100.get(i))
                max = times100.get(i);
            if (min > times100.get(i)) {
                min = times100.get(i);
            }
            AvgN += times100.get(i);
        }
        AvgN -= (max + min);
        AvgN = (int) Math.round(AvgN / (n - 2 + 0.0));
        returnValue[0] = enough;
        returnValue[1] = AvgN;
        return returnValue;
    }

    private void showAvg5() {
        int[] answer = findAvgN(5);
        if (answer[0]== 0)
            current_avg5.setText("-");
        else
            current_avg5.setText(integerTimeToStringTime(answer[1]));
    }

    private void showAvg12() {
        int[] answer = findAvgN(12);
        if (answer[0] == 0)
            current_avg12.setText("-");
        else {
            current_avg12.setText(integerTimeToStringTime(answer[1]));
        }
    }

    private void showAvg25() {
        int[] answer = findAvgN(25);
        if (answer[0] == 0)
            current_avg25.setText("-");
        else {
            current_avg25.setText(integerTimeToStringTime(answer[1]));
        }
    }

    private void showAvg50() {
        int[] answer = findAvgN(50);
        if (answer[0] == 0)
            current_avg50.setText("-");
        else {
            current_avg50.setText(integerTimeToStringTime(answer[1]));
        }
    }

    private void showAvg100() {
        int[] answer = findAvgN(100);
        if (answer[0] == 0)
            current_avg100.setText("-");
        else {
            current_avg100.setText(integerTimeToStringTime(answer[1]));
        }
    }

    private void showMean() { // also finding best time
        int Mean = 0;
        int counterSolves = 0;
        for (int i = 0; i < times100.size(); i++) {
            if (times100.get(i) >= 0) {
                if(bestTime > times100.get(i))                // here best time
                    bestTime = times100.get(i);
                counterSolves++;
                Mean += times100.get(i);
            }
        }

        Mean = (int) Math.round(Mean / Double.valueOf(counterSolves));
        if (counterSolves == 0)
            mean.setText("mean: -");
        else {
            mean.setText("mean: " + integerTimeToStringTime(Mean));
        }
    }

    /*private void findBestTimes() {
        int counterSolves = 0;
        Vector<Integer> vbestMo3 = new Vector<>();
        Vector<Integer> vbestAvg5 = new Vector<>();
        Vector<Integer> vbestAvg12 = new Vector<>();
        Vector<Integer> vbestAvg25 = new Vector<>();
        Vector<Integer> vbestAvg50 = new Vector<>();
        Vector<Integer> vbestAvg100 = new Vector<>();
        for (int i = 0; i < times100.size(); i++) {
            if (times100.get(i) > 0) {
                if (bestTime > times100.get(i))                // here best time
                    bestTime = times100.get(i);
                counterSolves++;
            }
        }
        int tmpMo3 = Integer.MAX_VALUE;
        int max = times100.get(times100.size() - 1 - counterSolves);
        int min = times100.get(times100.size() - 1 - counterSolves);
        int k = 0;
        for (int i = times100.size() - 1 - counterSolves; i < times100.size() ; i++) {
            k = i + 3;
            if (k < times100.size()) {
                for (int j =k; j >= i; j++) {
                    vbestMo3.add(times100.get(i));
                }
                *//*Collections.sort(bestMo3);
            bestMo3.remove(0);
            bestMo3.remove(bestMo3.size() - 2);*//*
            for(int e: vbestMo3){
                tmpMo3+=e;
            }
            tmpMo3 /= (int) Math.round(tmpMo3 / 3.0);
            if(bestMo3> tmpMo3)
                bestMo3 = tmpMo3;
        }
    }

}*/
    private void showBestTimes(){
        //findBestTimes();
        if(bestTime!= Integer.MAX_VALUE)
        best_time.setText(integerTimeToStringTime(bestTime));
        else best_time.setText("-");
        if(bestMo3!= Integer.MAX_VALUE)
        best_mo3.setText(integerTimeToStringTime(bestMo3));
        else best_mo3.setText("-");
        if(bestAvg5!= Integer.MAX_VALUE)
        best_avg5.setText(integerTimeToStringTime(bestAvg5));
        else best_avg5.setText("-");
        if(bestAvg12!= Integer.MAX_VALUE)
        best_avg12.setText(integerTimeToStringTime(bestAvg12));
        else best_avg12.setText("-");
        if(bestAvg25!= Integer.MAX_VALUE)
        best_avg25.setText(integerTimeToStringTime(bestAvg25));
        else best_avg25.setText("-");
        if(bestAvg50!= Integer.MAX_VALUE)
        best_avg50.setText(integerTimeToStringTime(bestAvg50));
        else best_avg50.setText("-");
        if(bestAvg100!= Integer.MAX_VALUE)
        best_avg100.setText(integerTimeToStringTime(bestAvg100));
        else best_avg100.setText("-");
    }
    private void showNewScramble() {
        scramble = Tools.randomCube();
        ScrambleImage.ConvertScrambleToImage(scramble, stickers);
        letterScramble = new Search().solution(scramble, 21, 100000000, 100, Search.INVERSE_SOLUTION);
        scramble_field.setText(letterScramble);
    }

    private void setColorOnTime(int r, int g, int b) {
        milliseconds_label.setTextFill(Color.rgb(r, g, b));
        second_label.setTextFill(Color.rgb(r, g, b));
        minutes_label.setTextFill(Color.rgb(r, g, b));
        hours_label.setTextFill(Color.rgb(r, g, b));
    }


}

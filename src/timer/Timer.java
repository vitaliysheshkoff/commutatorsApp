package timer;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Timer{

private Timeline timeline = null;
    public Timer() {
    }

    long time;
    long startTime;

    // BooleanProperty running = new SimpleBooleanProperty();
    final AnimationTimer timer = new AnimationTimer() {


        @Override
        public void start() {
          startTime = System.currentTimeMillis();
            //running.set(true);
            super.start();
        }

        @Override
        public void stop() {
            //running.set(false);
            super.stop();
        }

        @Override
        public void handle(long timestamp) {
            long now = System.currentTimeMillis();
            time = ((now - startTime - timestamp));
        }
    };

    public void startTimer(Label hours_label, Label minutes_label, Label seconds_label, Label milliseconds_label) {
        timer.start();
        timeline = new Timeline(
                new KeyFrame(Duration.millis(0.0),
                        e -> advanceDuration(hours_label, minutes_label, seconds_label, milliseconds_label)),
                new KeyFrame(Duration.millis(1))); // was 1
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void advanceDuration(Label hours_label, Label minutes_label, Label seconds_label, Label milliseconds_label) {

        timer.handle(0);
        if ((time / 10) % 100 < 10) {
            milliseconds_label.setText("0" + (time / 10) % 100);
        }
        else {
            milliseconds_label.setText(""+ (time / 10) % 100);
        }
        if ((time / 1000) % 60 < 10) {
            seconds_label.setText("0" + (time / 1000) % 60);
        }
        else {
            seconds_label.setText("" +(time / 1000) % 60);
        }
        if((time / 60000) % 60 < 10) {
            minutes_label.setText("0" + (time / 60000) % 60);
        }
        else{
            minutes_label.setText(""+(time / 60000) % 60);
        }
        if((time / 3600000) % 60 < 10) {
            hours_label.setText("0" + (time / 3600000) % 60);
        }
        else{
            hours_label.setText("" + (time / 3600000) % 60 );
        }
    }

    public void stopTimer()
    {
        timer.stop();
        timeline.stop();
    }

    public void reset(Label hours_label, Label minutes_label, Label seconds_label, Label milliseconds_label){
        hours_label.setText("00");
        minutes_label.setText("00");
        seconds_label.setText("00");
        milliseconds_label.setText("00");
    }

}


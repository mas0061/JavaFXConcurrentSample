package net.mas0061.javafx.sample.concurrent;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.mas0061.javafx.sample.child.ChildScreen;

public class ConcurrentSampleControl implements Initializable {

  @FXML
  private Label msgLabel;
  @FXML
  private Button pushButton;
  @FXML
  private Button startButton;
  @FXML
  private Button stopButton;
  @FXML
  private Button childButton;
  @FXML
  private ProgressBar sampleBar;
  @FXML
  private ProgressIndicator sampleIndicator;

  Task<Void> task;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
//    msgLabel.setTextFill(Color.RED);
//    msgLabel.setText("initialize.");
//    System.out.println("start -> " + startButton.focusedProperty().toString());
//    System.out.println("stop  -> " + stopButton.focusedProperty().toString());
//    System.out.println("push  -> " + pushButton.focusedProperty().toString());
  }

  @FXML
  protected void start(ActionEvent event) {
    msgLabel.setTextFill(Color.BLACK);
    msgLabel.setText("start");

    if (task == null || task.isCancelled()) {
      task = new Task<Void>() {
        @Override
        protected Void call() {
          try {
            for (double c = 1; c <= 100; c++) {
              if (isCancelled()) { break; }

              sampleBar.setProgress(c / 100);
              sampleIndicator.setProgress(c / 100);

              Thread.sleep(100);
            }
          } catch (InterruptedException e) {
          }

          return null;
        }
        @Override protected void cancelled() {
          super.cancelled();
          updateMessage("Cancelled!");
        }
      };

      Thread thread = new Thread(task);
      thread.setDaemon(true);
      thread.start();
    }
  }

  @FXML
  protected void stop(ActionEvent event) {
    if (task != null) {
      msgLabel.setTextFill(Color.BLACK);
      msgLabel.setText("stop");
      task.cancel();
    }
  }

  @FXML
  protected void push(ActionEvent event) {
    msgLabel.setTextFill(Color.BLACK);
    msgLabel.setText("push");
  }

  @FXML
  protected void child(ActionEvent event) {
	  ChildScreen childScreen = new ChildScreen();
	  Stage stage = new Stage();
	  childScreen.start(stage);
  }

  @FXML
  protected void releaseKey(KeyEvent event) {
	msgLabel.setText(event.getCode().toString());
  }

}

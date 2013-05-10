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
import javafx.scene.paint.Color;

public class ConcurrentSampleControl implements Initializable {

  @FXML
  private Label msgLabel;
  @FXML
  private Button startButton;
  @FXML
  private Button stopButton;
  @FXML
  private ProgressBar sampleBar;
  @FXML
  private ProgressIndicator sampleIndicator;

  Task<Void> task;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
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

}

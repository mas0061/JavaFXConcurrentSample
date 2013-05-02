package net.mas0061.javafx.sample.child;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ChildScreen {

	public void start(final Stage stage) {
		try {
			URL url = getClass().getResource("child.fxml");

			if (url == null) {
				System.out.println("URL is null.");
			} else {
				stage.setTitle("Child");

				Group group = new Group();
				AnchorPane pane = FXMLLoader.load(url);

				List<Node> childList = pane.getChildren();
				Button okButton = (Button) childList.get(0);
				okButton.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent e) {
						stage.close();
					}
				});

				group.getChildren().add(pane);

				Scene scene = new Scene(group, 300, 200);
				stage.setScene(scene);
				stage.show();
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}

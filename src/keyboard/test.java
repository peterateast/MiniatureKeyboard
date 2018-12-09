package keyboard;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button.*;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.scene.effect.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javafx.scene.media.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.shape.*;
import javafx.animation.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class test extends Application {
	Button button = new Button("Hello");
	Label text = new Label("hello");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		

		HBox hbox = new HBox();
		hbox.getChildren().addAll(button, text); // button will be left of text

		Image image = new Image(new File("images/testKey.png").toURI().toString());
		//ImageView iv1 = new ImageView();
		Label test = new Label();
		test.setGraphic(new ImageView(image));
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(test, hbox); // hbox with button and text on top of image view
		HBox root = new HBox();
		root.getChildren().add(stackPane);
		
		Stage main = new Stage();
		Scene test2 = new Scene(stackPane,500,500);
		main.setScene(test2);
		main.show();
	}
}

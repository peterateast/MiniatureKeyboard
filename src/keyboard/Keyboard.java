package keyboard;

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
import java.util.LinkedList;

import javafx.scene.media.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.shape.*;
import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*//pressing the A button
scene.setOnKeyPressed(e -> { 
					if (e.getCode().equals(KeyCode.A) && c.visibleProperty() == false) {	a.setVisible(true); } }

//releasing
scene.setOnKeyReleased(e -> { 
					if (e.getCode().equals(KeyCode.A) && c.visibleProperty() == true) {	a.setVisible(false); } }
					*/
public class Keyboard extends Application {

	private final int WIDTH = 1200;
	private final int HEIGHT = 800;
	private static Integer seconds = 0;
	private static Integer keyTime = seconds;
	private static Boolean stopKey = false;
	private static Boolean recordStatus = false;
	private static LinkedList<Label> keysPressed = new LinkedList<>();
	private static LinkedList<Double> durations = new LinkedList<>();
	private static LinkedList<Integer> lengths = new LinkedList<>();
	private static Long startTime;
	private static Long stopTime;
	private static Long milliseconds = (long) 500;
	private static int x;
	private static Boolean soundDone = false;
	private static ArrayList<MediaPlayer> recordingClone = new ArrayList<>();

	Button backInstructions = new Button("Back");
	Button backAbout = new Button("Back");
	Button play = new Button("Play");
	// images to be used:
	Image instructionsPicture = new Image(new File("images/Instructions.png").toURI().toString());
//	Image samplePiano = new Image(new File("images/piano keys.png").toURI().toString());
	Image instructMapPic = new Image(new File("images/Mappin.png").toURI().toString());
	Image aboutPic = new Image(new File("images/about.png").toURI().toString());

	// Main Screen:
	StackPane main = new StackPane();
	Button record = new Button("Record");
	Button instructions = new Button("Instructions");
	Button about = new Button("About");
	Button close = new Button("Close");
	Button done = new Button("Done Recording");

	// labels to put pictures in:
	Label testPiano = new Label("");
	Label instructLabel1 = new Label("");
	Label instructMapLabel = new Label("");
	Label aboutLabel = new Label("");
	Label note = new Label("Note: Record only works with key-presses.");
	// Labels for the piano keys
	Label c = new Label();
	Label cSharp = new Label();
	Label d = new Label();
	Label dSharp = new Label();
	Label e = new Label();
	Label f = new Label();
	Label fSharp = new Label();
	Label g = new Label();
	Label gSharp = new Label();
	Label a = new Label();
	Label aSharp = new Label();
	Label b = new Label();
	Label highC = new Label();
	Label highCSharp = new Label();
	Label highD = new Label();
	Label highDSharp = new Label();
	Label highE = new Label();
	Label highF = new Label();
	Label highFSharp = new Label();
	Label highG = new Label();
	Label highGSharp = new Label();
	Label highA = new Label();
	Label highASharp = new Label();
	Label highB = new Label();

	// Indivudal Keys for piano:
	Image cPic = new Image(new File("images/testKey.png").toURI().toString());
	Image cSharpPic = new Image(new File("images/C#.png").toURI().toString());
	Image dPic = new Image(new File("images/D.png").toURI().toString());
	Image dSharpPic = new Image(new File("images/D#.png").toURI().toString());
	Image ePic = new Image(new File("images/E.png").toURI().toString());
	Image fPic = new Image(new File("images/F.png").toURI().toString());
	Image fSharpPic = new Image(new File("images/F#.png").toURI().toString());
	Image gPic = new Image(new File("images/G.png").toURI().toString());
	Image gSharpPic = new Image(new File("images/G#.png").toURI().toString());
	Image aPic = new Image(new File("images/A.png").toURI().toString());
	Image aSharpPic = new Image(new File("images/A#.png").toURI().toString());
	Image bPic = new Image(new File("images/B.png").toURI().toString());
	Image highCPic = new Image(new File("images/highC.png").toURI().toString());
	Image highCSharpPic = new Image(new File("images/highC#.png").toURI().toString());
	Image highDPic = new Image(new File("images/highD.png").toURI().toString());
	Image highDSharpPic = new Image(new File("images/highD#.png").toURI().toString());
	Image highEPic = new Image(new File("images/highE.png").toURI().toString());
	Image highFPic = new Image(new File("images/highF.png").toURI().toString());
	Image highFSharpPic = new Image(new File("images/highF#.png").toURI().toString());
	Image highGPic = new Image(new File("images/highG.png").toURI().toString());
	Image highGSharpPic = new Image(new File("images/highG#.png").toURI().toString());
	Image highAPic = new Image(new File("images/highA.png").toURI().toString());
	Image highASharpPic = new Image(new File("images/highA#.png").toURI().toString());
	Image highBPic = new Image(new File("images/highB.png").toURI().toString());

	// Instructions Screen:
	StackPane instructPane = new StackPane();

	// About Screen:
	StackPane aboutPane = new StackPane();

	Media soundC = new Media(new File("sounds/0-C.mp3").toURI().toString());
	Media soundCSharp = new Media(new File("sounds/1-C#.mp3").toURI().toString());
	Media soundD = new Media(new File("sounds/2-D.mp3").toURI().toString());
	Media soundDSharp = new Media(new File("sounds/3-D#.mp3").toURI().toString());
	Media soundE = new Media(new File("sounds/4-E.mp3").toURI().toString());
	Media soundF = new Media(new File("sounds/5-F.mp3").toURI().toString());
	Media soundFSharp = new Media(new File("sounds/6-F#.mp3").toURI().toString());
	Media soundG = new Media(new File("sounds/7-G.mp3").toURI().toString());
	Media soundGSharp = new Media(new File("sounds/8-G#.mp3").toURI().toString());
	Media soundA = new Media(new File("sounds/9-A.mp3").toURI().toString());
	Media soundASharp = new Media(new File("sounds/10-A#.mp3").toURI().toString());
	Media soundB = new Media(new File("sounds/11-B.mp3").toURI().toString());
	Media soundHiC = new Media(new File("sounds/12-HighC.mp3").toURI().toString());
	Media soundHiCSharp = new Media(new File("sounds/13-HighC#.mp3").toURI().toString());
	Media soundHiD = new Media(new File("sounds/14-HighD.mp3").toURI().toString());
	Media soundHiDSharp = new Media(new File("sounds/15-HighD#.mp3").toURI().toString());
	Media soundHiE = new Media(new File("sounds/16-HighE.mp3").toURI().toString());
	Media soundHiF = new Media(new File("sounds/17-HighF.mp3").toURI().toString());
	Media soundHiFSharp = new Media(new File("sounds/18-HighF#.mp3").toURI().toString());
	Media soundHiG = new Media(new File("sounds/19-HighG.mp3").toURI().toString());
	Media soundHiGSharp = new Media(new File("sounds/20-HighG#.mp3").toURI().toString());
	Media soundHiA = new Media(new File("sounds/21-HighA.mp3").toURI().toString());
	Media soundHiASharp = new Media(new File("sounds/22-HighA#.mp3").toURI().toString());
	Media soundHiB = new Media(new File("sounds/23-HighB.mp3").toURI().toString());

	ArrayList<MediaPlayer> recording = new ArrayList();

	MediaPlayer noteC = new MediaPlayer(soundC);
	MediaPlayer noteCSharp = new MediaPlayer(soundCSharp);
	MediaPlayer noteD = new MediaPlayer(soundD);
	MediaPlayer noteDSharp = new MediaPlayer(soundDSharp);
	MediaPlayer noteE = new MediaPlayer(soundE);
	MediaPlayer noteF = new MediaPlayer(soundF);
	MediaPlayer noteFSharp = new MediaPlayer(soundFSharp);
	MediaPlayer noteG = new MediaPlayer(soundG);
	MediaPlayer noteGSharp = new MediaPlayer(soundGSharp);
	MediaPlayer noteA = new MediaPlayer(soundA);
	MediaPlayer noteASharp = new MediaPlayer(soundASharp);
	MediaPlayer noteB = new MediaPlayer(soundB);
	MediaPlayer noteHiC = new MediaPlayer(soundHiC);
	MediaPlayer noteHiCSharp = new MediaPlayer(soundHiCSharp);
	MediaPlayer noteHiD = new MediaPlayer(soundHiD);
	MediaPlayer noteHiDSharp = new MediaPlayer(soundHiDSharp);
	MediaPlayer noteHiE = new MediaPlayer(soundHiE);
	MediaPlayer noteHiF = new MediaPlayer(soundHiF);
	MediaPlayer noteHiFSharp = new MediaPlayer(soundHiFSharp);
	MediaPlayer noteHiG = new MediaPlayer(soundHiG);
	MediaPlayer noteHiGSharp = new MediaPlayer(soundHiGSharp);
	MediaPlayer noteHiA = new MediaPlayer(soundHiA);
	MediaPlayer noteHiASharp = new MediaPlayer(soundHiASharp);
	MediaPlayer noteHiB = new MediaPlayer(soundHiB);

	ArrayList<MediaPlayer> notes = new ArrayList<>();

	/*
	 * notes.add(noteC); notes.add(noteCSharp); notes.add(noteD);
	 * notes.add(noteDSharp); notes.add(noteE); notes.add(noteF);
	 * notes.add(noteFSharp); notes.add(noteG); notes.add(noteGSharp);
	 * notes.add(noteA); notes.add(noteASharp); notes.add(noteB);
	 * 
	 * notes.add(noteHiC); notes.add(noteHiCSharp); notes.add(noteHiD);
	 * notes.add(noteHiDSharp); notes.add(noteHiE); notes.add(noteHiF);
	 * notes.add(noteHiFSharp); notes.add(noteHiG); notes.add(noteHiGSharp);
	 * notes.add(noteHiA); notes.add(noteHiASharp); notes.add(noteHiB);
	 */

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Main screen setup:
		Stage mainScreen = new Stage();

		// Instructions Screen setup:
		Stage instructStage = new Stage();

		instructStage.setTitle("Instructions");
		Scene instructScene = new Scene(instructPane, 1200, 800);
		instructStage.setResizable(false);
		instructStage.setScene(instructScene);
		instructPane.getChildren().addAll(instructLabel1, instructMapLabel, backInstructions);
		instructPane.setMargin(instructLabel1, new Insets(0, 0, 325, 0));
		instructPane.setMargin(instructMapLabel, new Insets(400, 0, 0, 0));
		instructPane.setMargin(backInstructions, new Insets(700, 0, 0, 0));

		// instructStage.show();

		// About Screen setup:
		Stage aboutStage = new Stage();
		aboutStage.setTitle("About");
		Scene aboutScene = new Scene(aboutPane, 800, 600);
		aboutStage.setResizable(false);
		aboutStage.setScene(aboutScene);
		aboutPane.getChildren().addAll(aboutLabel, backAbout);
		aboutPane.setMargin(aboutLabel, new Insets(0, 0, 100, 0));
		aboutPane.setMargin(backAbout, new Insets(450, 0, 0, 0));

		// aboutStage.show();

		// Setting the piano key labels to images:
		c.setGraphic(new ImageView(cPic));
		cSharp.setGraphic(new ImageView(cSharpPic));
		d.setGraphic(new ImageView(dPic));
		dSharp.setGraphic(new ImageView(dSharpPic));
		e.setGraphic(new ImageView(ePic));
		f.setGraphic(new ImageView(fPic));
		fSharp.setGraphic(new ImageView(fSharpPic));
		g.setGraphic(new ImageView(gPic));
		gSharp.setGraphic(new ImageView(gSharpPic));
		a.setGraphic(new ImageView(aPic));
		aSharp.setGraphic(new ImageView(aSharpPic));
		b.setGraphic(new ImageView(bPic));
		highC.setGraphic(new ImageView(highCPic));
		highCSharp.setGraphic(new ImageView(highCSharpPic));
		highD.setGraphic(new ImageView(highDPic));
		highDSharp.setGraphic(new ImageView(highDSharpPic));
		highE.setGraphic(new ImageView(highEPic));
		highF.setGraphic(new ImageView(highFPic));
		highFSharp.setGraphic(new ImageView(highFSharpPic));
		highG.setGraphic(new ImageView(highGPic));
		highGSharp.setGraphic(new ImageView(highGSharpPic));
		highA.setGraphic(new ImageView(highAPic));
		highASharp.setGraphic(new ImageView(highASharpPic));
		highB.setGraphic(new ImageView(highBPic));
		/*
		 * noteC.setCycleCount(AudioClip.INDEFINITE);
		 * noteCSharp.setCycleCount(AudioClip.INDEFINITE);
		 * noteD.setCycleCount(AudioClip.INDEFINITE);
		 * noteDSharp.setCycleCount(AudioClip.INDEFINITE);
		 * noteE.setCycleCount(AudioClip.INDEFINITE);
		 * noteF.setCycleCount(AudioClip.INDEFINITE);
		 * noteFSharp.setCycleCount(AudioClip.INDEFINITE);
		 * noteG.setCycleCount(AudioClip.INDEFINITE);
		 * noteGSharp.setCycleCount(AudioClip.INDEFINITE);
		 * noteA.setCycleCount(AudioClip.INDEFINITE);
		 * noteASharp.setCycleCount(AudioClip.INDEFINITE);
		 * noteB.setCycleCount(AudioClip.INDEFINITE);
		 * noteHiC.setCycleCount(AudioClip.INDEFINITE);
		 * noteHiCSharp.setCycleCount(AudioClip.INDEFINITE);
		 * noteHiD.setCycleCount(AudioClip.INDEFINITE);
		 * noteHiDSharp.setCycleCount(AudioClip.INDEFINITE);
		 * noteHiE.setCycleCount(AudioClip.INDEFINITE);
		 * noteHiF.setCycleCount(AudioClip.INDEFINITE);
		 * noteHiFSharp.setCycleCount(AudioClip.INDEFINITE);
		 * noteHiG.setCycleCount(AudioClip.INDEFINITE);
		 * noteHiGSharp.setCycleCount(AudioClip.INDEFINITE);
		 * noteHiA.setCycleCount(AudioClip.INDEFINITE);
		 * noteHiASharp.setCycleCount(AudioClip.INDEFINITE);
		 * noteHiB.setCycleCount(AudioClip.INDEFINITE);
		 */
		notes.add(noteC);
		notes.add(noteCSharp);
		notes.add(noteD);
		notes.add(noteDSharp);
		notes.add(noteE);
		notes.add(noteF);
		notes.add(noteFSharp);
		notes.add(noteG);
		notes.add(noteGSharp);
		notes.add(noteA);
		notes.add(noteASharp);
		notes.add(noteB);

		notes.add(noteHiC);
		notes.add(noteHiCSharp);
		notes.add(noteHiD);
		notes.add(noteHiDSharp);
		notes.add(noteHiE);
		notes.add(noteHiF);
		notes.add(noteHiFSharp);
		notes.add(noteHiG);
		notes.add(noteHiGSharp);
		notes.add(noteHiA);
		notes.add(noteHiASharp);
		notes.add(noteHiB);
		// Testing to layer the piano keys:
		HBox hbox = new HBox();
		hbox.getChildren().addAll(c, d);

		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(e, f, hbox);

		Stage testScreen = new Stage();
		Scene testScene = new Scene(stackPane, 500, 500);
		testScreen.setScene(testScene);
		// testScreen.show();

		// Setting labels to images:
		// testPiano.setGraphic(new ImageView(samplePiano));
		instructLabel1.setGraphic(new ImageView(instructionsPicture));
		instructMapLabel.setGraphic(new ImageView(instructMapPic));
		aboutLabel.setGraphic(new ImageView(aboutPic));

		mainScreen.setTitle("Miniature Keyboard");
		Scene mainScene = new Scene(main, WIDTH, HEIGHT);
		mainScreen.setResizable(false);
		mainScreen.setScene(mainScene);
		mainScreen.show();

		// Placing the buttons:

		main.getChildren().addAll(instructions, about, record, close, c, d, e, f, g, a, b, highC, highD, highE, highF,
				highG, highA, highB, note, play, done);

		main.getChildren().addAll(cSharp, dSharp, fSharp, gSharp, aSharp, highCSharp, highDSharp, highFSharp,
				highGSharp, highASharp);

		main.setMargin(note, new Insets(-750, 0, 0, 0));
		main.setMargin(record, new Insets(300, 0, 0, 800));
		main.setMargin(instructions, new Insets(400, 0, 0, 800));
		main.setMargin(about, new Insets(500, 0, 0, 800));
		main.setMargin(close, new Insets(600, 0, 0, 800));
		main.setMargin(play, new Insets(250, 0, 0, 0));
		main.setMargin(done, new Insets(350, 0, 0, 0));
		// main.setMargin(testPiano, new Insets(0, 0, 200, 0));

		note.setVisible(false);
		// placing 24 keys:
		main.setMargin(c, new Insets(0, 1000, 200, 0));
		main.setMargin(cSharp, new Insets(0, 922, 344, 0));
		main.setMargin(d, new Insets(0, 845, 200, 0));
		main.setMargin(dSharp, new Insets(0, 770, 344, 0));
		main.setMargin(e, new Insets(0, 690, 200, 0));
		main.setMargin(f, new Insets(0, 540, 200, 0));
		main.setMargin(fSharp, new Insets(0, 460, 344, 0));
		main.setMargin(g, new Insets(0, 385, 200, 0));
		main.setMargin(gSharp, new Insets(0, 306, 344, 0));
		main.setMargin(a, new Insets(0, 228, 200, 0));
		main.setMargin(aSharp, new Insets(0, 153, 344, 0));
		main.setMargin(b, new Insets(0, 75, 200, 0));
		main.setMargin(highC, new Insets(0, -80, 200, 0));
		main.setMargin(highCSharp, new Insets(0, -158, 344, 0));
		main.setMargin(highD, new Insets(0, -235, 200, 0));
		main.setMargin(highDSharp, new Insets(0, -310, 344, 0));
		main.setMargin(highE, new Insets(0, -390, 200, 0));
		main.setMargin(highF, new Insets(0, -540, 200, 0));
		main.setMargin(highFSharp, new Insets(0, -618, 344, 0));
		main.setMargin(highG, new Insets(0, -695, 200, 0));
		main.setMargin(highGSharp, new Insets(0, -770, 344, 0));
		main.setMargin(highA, new Insets(0, -850, 200, 0));
		main.setMargin(highASharp, new Insets(0, -920, 344, 0));
		main.setMargin(highB, new Insets(0, -1000, 200, 0));

		play.setVisible(false);
		done.setVisible(false);
		// stylzing the buttons:
		done.setStyle("-fx-background-color: #ffffff; " + "-fx-pref-width: 150px; " + "-fx-pref-height: 20px; "
				+ "-fx-font-size: 12pt;" + "-fx-border-color:black;");
		play.setStyle("-fx-background-color: #ffffff; " + "-fx-pref-width: 150px; " + "-fx-pref-height: 20px; "
				+ "-fx-font-size: 12pt;" + "-fx-border-color:black;");

		record.setStyle("-fx-background-color: #ffffff; " + "-fx-pref-width: 150px; " + "-fx-pref-height: 20px; "
				+ "-fx-font-size: 12pt;" + "-fx-border-color:black;");

		instructions.setStyle("-fx-background-color: #ffffff; " + "-fx-pref-width: 150px; " + "-fx-pref-height: 20px; "
				+ "-fx-font-size: 12pt;" + "-fx-border-color:black;");

		about.setStyle("-fx-background-color: #ffffff; " + "-fx-pref-width: 150px; " + "-fx-pref-height: 20px; "
				+ "-fx-font-size: 12pt;" + "-fx-border-color:black;");

		close.setStyle("-fx-background-color: #ffffff; " + "-fx-pref-width: 150px; " + "-fx-pref-height: 20px; "
				+ "-fx-font-size: 12pt;" + "-fx-border-color:black;");

		backInstructions.setStyle("-fx-background-color: #ffffff; " + "-fx-pref-width: 150px; "
				+ "-fx-pref-height: 20px; " + "-fx-font-size: 12pt;" + "-fx-border-color:black;");

		backAbout.setStyle("-fx-background-color: #ffffff; " + "-fx-pref-width: 150px; " + "-fx-pref-height: 20px; "
				+ "-fx-font-size: 12pt;" + "-fx-border-color:black;");

		// Logic of the UI
		record.setOnMouseClicked((event) -> {
			if (record.getText().equals("Record")) {
				recording = new ArrayList();
				record.setText("Stop");
				note.setVisible(true);
				play.setVisible(false);
				recordStatus = true;
			} else if (record.getText().equals("Stop")) {
				record.setText("Record");
				note.setVisible(false);
				play.setVisible(true);
				done.setVisible(true);
				// printTest();
				recordStatus = false;
				// System.out.println(recording.get(0).toString()+"\n"+lengths.get(0));

			}
		});

		play.setOnMouseClicked((event) -> {
			/*
			 * for (x = 0; x < recording.size();) { // recording.get(x).play();
			 * 
			 * try { doTime(recording.get(x), lengths.get(x)); Thread.sleep(500); } catch
			 * (InterruptedException e1) { // TODO e1.printStackTrace(); }
			 * recording.get(x).stop(); x++;
			 * 
			 * }
			 */
			// if (soundDone == true) { System.out.println(lengths.get(x)); x++; }

			// System.out.println("Boolean check: "+soundDone); }

			// recordingClone = (ArrayList) recording.clone();

			for (MediaPlayer x : recording) {
				x.play();

				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) { // TODO
					e1.printStackTrace();
				}
				x.stop();
			}

			// System.out.println("test");
			// noteC.play();
			// recording = (ArrayList<MediaPlayer>) recordingClone.clone();
		});

		done.setOnMouseClicked((event) -> {
			play.setVisible(false);
			note.setVisible(false);
			done.setVisible(false);

		});
		instructions.setOnMouseClicked((event) -> {
			instructStage.show();
		});

		about.setOnMouseClicked((event) -> {
			aboutStage.show();
		});

		backInstructions.setOnMouseClicked((event) -> {
			instructStage.hide();
		});

		backAbout.setOnMouseClicked((event) -> {
			aboutStage.hide();
		});
		close.setOnAction(e -> Platform.exit());

		// importation of music

//ALL THE KEYS INTERACTIONS OH MY LORD
		mainScene.setOnKeyPressed(e -> {
			if ((e.getCode().equals(KeyCode.TAB)) && (c.getOpacity() == 1)) {
				c.setOpacity(.25);
				noteC.play();
				recording.add(noteC);
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.DIGIT1)) && (cSharp.getOpacity() == 1)) {
				cSharp.setOpacity(.25);
				noteCSharp.play();
				// recording.add(noteC);
				startTime = System.currentTimeMillis();
				recording.add(noteCSharp);
			} else if ((e.getCode().equals(KeyCode.Q)) && (d.getOpacity() == 1)) {
				d.setOpacity(.25);
				recording.add(noteD);
				noteD.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.DIGIT2)) && (dSharp.getOpacity() == 1)) {
				dSharp.setOpacity(.25);
				recording.add(noteDSharp);
				noteDSharp.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.W)) && (this.e.getOpacity() == 1)) {
				this.e.setOpacity(.25);
				recording.add(noteE);
				noteE.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.E)) && (f.getOpacity() == 1)) {
				f.setOpacity(.25);
				recording.add(noteF);
				noteF.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.DIGIT4)) && (fSharp.getOpacity() == 1)) {
				fSharp.setOpacity(.25);
				recording.add(noteFSharp);
				noteFSharp.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.R)) && (g.getOpacity() == 1)) {
				g.setOpacity(.25);
				recording.add(noteG);
				noteG.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.DIGIT5)) && (gSharp.getOpacity() == 1)) {
				gSharp.setOpacity(.25);
				recording.add(noteGSharp);
				noteGSharp.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.T)) && (a.getOpacity() == 1)) {
				a.setOpacity(.25);
				recording.add(noteA);
				noteA.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.DIGIT6)) && (aSharp.getOpacity() == 1)) {
				aSharp.setOpacity(.25);
				recording.add(noteASharp);
				noteASharp.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.Y)) && (b.getOpacity() == 1)) {
				b.setOpacity(.25);
				recording.add(noteB);
				noteB.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.U)) && (highC.getOpacity() == 1)) {
				highC.setOpacity(.25);
				recording.add(noteHiC);
				noteHiC.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.DIGIT8)) && (highCSharp.getOpacity() == 1)) {
				highCSharp.setOpacity(.25);
				recording.add(noteHiCSharp);
				noteHiCSharp.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.I)) && (highD.getOpacity() == 1)) {
				highD.setOpacity(.25);
				recording.add(noteHiD);
				noteHiD.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.DIGIT9)) && (highDSharp.getOpacity() == 1)) {
				highDSharp.setOpacity(.25);
				recording.add(noteHiDSharp);
				noteHiDSharp.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.O)) && (highE.getOpacity() == 1)) {
				highE.setOpacity(.25);
				recording.add(noteHiE);
				noteHiE.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.P)) && (highF.getOpacity() == 1)) {
				highF.setOpacity(.25);
				recording.add(noteHiF);
				noteHiF.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.MINUS)) && (highFSharp.getOpacity() == 1)) {
				highFSharp.setOpacity(.25);
				recording.add(noteHiFSharp);
				noteHiFSharp.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.OPEN_BRACKET)) && (highG.getOpacity() == 1)) {
				highG.setOpacity(.25);
				recording.add(noteHiG);
				noteHiG.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.EQUALS)) && (highGSharp.getOpacity() == 1)) {
				highGSharp.setOpacity(.25);
				recording.add(noteHiGSharp);
				noteHiGSharp.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.CLOSE_BRACKET)) && (highA.getOpacity() == 1)) {
				highA.setOpacity(.25);
				recording.add(noteHiA);
				noteHiA.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.BACK_SPACE)) && (highASharp.getOpacity() == 1)) {
				highASharp.setOpacity(.25);
				recording.add(noteHiASharp);
				noteHiASharp.play();
				startTime = System.currentTimeMillis();
			} else if ((e.getCode().equals(KeyCode.BACK_SLASH)) && (highB.getOpacity() == 1)) {
				highB.setOpacity(.25);
				recording.add(noteHiB);
				noteHiB.play();
				startTime = System.currentTimeMillis();
			}

		});

		c.setOnMouseClicked(e -> {
			// noteC.play();
			doTime(noteC, 1000);
			// seconds = keyTime;
		});
		cSharp.setOnMouseClicked(e -> {
			doTime(noteCSharp, 1000);
		});
		d.setOnMouseClicked(e -> {
			doTime(noteD, 1000);
		});
		dSharp.setOnMouseClicked(e -> {
			doTime(noteDSharp, 1000);
		});
		e.setOnMouseClicked(e -> {
			doTime(noteE, 1000);
		});
		f.setOnMouseClicked(e -> {
			doTime(noteF, 1000);
		});
		fSharp.setOnMouseClicked(e -> {
			doTime(noteFSharp, 1000);
		});
		g.setOnMouseClicked(e -> {
			doTime(noteG, 1000);
		});
		gSharp.setOnMouseClicked(e -> {
			doTime(noteGSharp, 1000);
		});
		a.setOnMouseClicked(e -> {
			doTime(noteA, 1000);
		});
		aSharp.setOnMouseClicked(e -> {
			doTime(noteASharp, 1000);
		});
		b.setOnMouseClicked(e -> {
			doTime(noteB, 1000);
		});
		highC.setOnMouseClicked(e -> {
			doTime(noteHiC, 1000);
			;
		});
		highCSharp.setOnMouseClicked(e -> {
			doTime(noteHiCSharp, 1000);
		});
		highD.setOnMouseClicked(e -> {
			doTime(noteHiD, 1000);
		});
		highDSharp.setOnMouseClicked(e -> {
			doTime(noteHiDSharp, 1000);
		});
		highE.setOnMouseClicked(e -> {
			doTime(noteHiE, 1000);
		});
		highF.setOnMouseClicked(e -> {
			doTime(noteHiF, 1000);
		});
		highFSharp.setOnMouseClicked(e -> {
			doTime(noteHiFSharp, 1000);
		});
		highG.setOnMouseClicked(e -> {
			doTime(noteHiG, 1000);
		});
		highGSharp.setOnMouseClicked(e -> {
			doTime(noteHiGSharp, 1000);
		});
		highA.setOnMouseClicked(e -> {
			doTime(noteHiA, 1000);
		});
		highASharp.setOnMouseClicked(e -> {
			doTime(noteHiASharp, 1000);
		});
		highB.setOnMouseClicked(e -> {
			doTime(noteHiB, 1000);
		});

		mainScene.setOnKeyReleased(e -> {
			if ((e.getCode().equals(KeyCode.TAB)) && (c.getOpacity() == .25)) {
				c.setOpacity(1);
				noteC.stop();
				stopTime = System.currentTimeMillis();
				// System.out.println((stopTime - startTime) / (double) 1000);
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;

			} else if ((e.getCode().equals(KeyCode.DIGIT1)) && (cSharp.getOpacity() == .25)) {
				cSharp.setOpacity(1);
				noteCSharp.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) ((stopTime - startTime) / (double) 1000));
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.Q)) && (d.getOpacity() == .25)) {
				d.setOpacity(1);
				noteD.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) ((stopTime - startTime) / (double) 1000));
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.DIGIT2)) && (dSharp.getOpacity() == .25)) {
				dSharp.setOpacity(1);
				noteDSharp.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) ((stopTime - startTime) / (double) 1000));
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.W)) && (this.e.getOpacity() == .25)) {
				this.e.setOpacity(1);
				noteE.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) ((stopTime - startTime) / (double) 1000));
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.E)) && (f.getOpacity() == .25)) {
				f.setOpacity(1);
				noteF.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.DIGIT4)) && (fSharp.getOpacity() == .25)) {
				fSharp.setOpacity(1);
				noteFSharp.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.R)) && (g.getOpacity() == .25)) {
				g.setOpacity(1);
				noteG.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.DIGIT5)) && (gSharp.getOpacity() == .25)) {
				gSharp.setOpacity(1);
				noteGSharp.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.T)) && (a.getOpacity() == .25)) {
				a.setOpacity(1);
				noteA.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.DIGIT6)) && (aSharp.getOpacity() == .25)) {
				aSharp.setOpacity(1);
				noteASharp.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.Y)) && (b.getOpacity() == .25)) {
				b.setOpacity(1);
				noteB.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.U)) && (highC.getOpacity() == .25)) {
				highC.setOpacity(1);
				noteHiC.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.DIGIT8)) && (highCSharp.getOpacity() == .25)) {
				highCSharp.setOpacity(1);
				noteHiCSharp.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.I)) && (highD.getOpacity() == .25)) {
				highD.setOpacity(1);
				noteHiD.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.DIGIT9)) && (highDSharp.getOpacity() == .25)) {
				highDSharp.setOpacity(1);
				noteHiDSharp.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.O)) && (highE.getOpacity() == .25)) {
				highE.setOpacity(1);
				noteHiE.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.P)) && (highF.getOpacity() == .25)) {
				highF.setOpacity(1);
				noteHiF.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.MINUS)) && (highFSharp.getOpacity() == .25)) {
				highFSharp.setOpacity(1);
				noteHiFSharp.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.OPEN_BRACKET)) && (highG.getOpacity() == .25)) {
				highG.setOpacity(1);
				noteHiG.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.EQUALS)) && (highGSharp.getOpacity() == .25)) {
				highGSharp.setOpacity(1);
				noteHiGSharp.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.CLOSE_BRACKET)) && (highA.getOpacity() == .25)) {
				highA.setOpacity(1);
				noteHiA.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.BACK_SPACE)) && (highASharp.getOpacity() == .25)) {
				highASharp.setOpacity(1);
				noteHiASharp.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			} else if ((e.getCode().equals(KeyCode.BACK_SLASH)) && (highB.getOpacity() == .25)) {
				highB.setOpacity(1);
				noteHiB.stop();
				stopTime = System.currentTimeMillis();
				durations.add((double) (stopTime - startTime) / (double) 1000);
				lengths.add((int) (stopTime - startTime));
				stopTime = (long) 0;
				startTime = (long) 0;
			}
		});

		// stopOther(noteC);

	}

	public void stopOther() {
		for (MediaPlayer y : notes) {
			y.stop();
		}
	}

	private void doTime(MediaPlayer sound, Integer duration) {

		sound.play();
		Timeline time = new Timeline();
		seconds = duration;
		// System.out.println("Duration: " + seconds);
		// System.out.print(seconds);
		KeyFrame frame = new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				seconds--;
				// System.out.println("test: " + seconds);
				// duration--;
				// System.out.print(seconds);
				// seconds+=(timeCounter-1);
				// timer2.setText("Seconds Left: "+seconds.toString());
				if (seconds <= 0) {
					// stopKey = true;
					// stopOther();
					// System.out.println("counter: " + x);
					// soundDone = true;
					sound.stop();
					time.stop();

				}

			}

		});

		// x++;
		time.setCycleCount(Timeline.INDEFINITE);
		time.getKeyFrames().add(frame);
		/*
		 * if(time!=null){ time.stop(); }
		 */

		time.play();
	}
	/*
	 * private void keyTime(MediaPlayer sound, Long duration) {
	 * System.out.println(x); x++; Timeline time = new Timeline(); //milliseconds =
	 * duration; //System.out.println(milliseconds); KeyFrame frame = new
	 * KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
	 * 
	 * @Override public void handle(ActionEvent event) { System.out.println("test");
	 * milliseconds--; if (milliseconds <= 0) {
	 * 
	 * sound.stop(); time.stop();
	 * 
	 * } }
	 * 
	 * }); }
	 * 
	 * 
	 * 
	 * class Label {
	 * 
	 * @Override public string toString() {
	 * 
	 * } }
	 * 
	 * public void printTest() { /* for (Label x: keysPressed) {
	 * System.out.println(x); }
	 * 
	 * for (Double y : durations) { System.out.println(y); } //
	 * System.out.println(c); }
	 */

}
/* Need the opacity code, opinion on buttons for keys, hashmap for record */
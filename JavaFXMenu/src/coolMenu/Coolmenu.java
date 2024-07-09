package coolMenu;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Coolmenu extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("start");
		BorderPane root = new BorderPane();

		Menu dateMenu = new Menu("Print");
		Menu fileMenu = new Menu("Write");
		Menu colorMenu = new Menu("Change Color");
		Menu exitMenu = new Menu("Exit Options");

		MenuItem dateItem = new MenuItem("Date/Time");
		MenuItem fileItem = new MenuItem("Save to Text File");
		MenuItem colorItem = new MenuItem("Color");
		MenuItem exitItem = new MenuItem("Exit");

		Label label1 = new Label("Text Box");
		TextField textField = new TextField();
		HBox hb = new HBox();
		hb.getChildren().addAll(label1, textField);
		hb.setSpacing(10);

		dateItem.setOnAction(e -> {
			System.out.println("Date Item Selected");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentDateTime = dateFormat.format(new Date());
			System.out.println(currentDateTime);
			textField.setText(currentDateTime);
		});

		fileItem.setOnAction(e -> {
			System.out.println("Text File Selected");
			String currentDateTime = textField.getText();
			try {
				FileWriter log = new FileWriter("log.txt");
				log.write(currentDateTime);
				log.close();
				System.out.println("File Successfully Written");
			} catch (IOException e1) {
				System.out.println("Error Writing File");
				e1.printStackTrace();
			}
		});

		colorItem.setOnAction(e -> {
			System.out.println("Color Change");
			Random rand = new Random();
			float r = rand.nextFloat();
			float g = 1;
			float b = rand.nextFloat();
			float o = 1;
			root.setBackground(new Background(new BackgroundFill(new Color(r, g, b, o), null, null)));
		});

		exitItem.setOnAction(e -> {
			System.out.println("Exit");
			Platform.exit();
		});

		dateMenu.getItems().addAll(dateItem);
		fileMenu.getItems().addAll(fileItem);
		colorMenu.getItems().addAll(colorItem);
		exitMenu.getItems().addAll(exitItem);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(dateMenu, fileMenu, colorMenu, exitMenu);

		root.setTop(menuBar);
		root.setCenter(hb);
		Scene scene = new Scene(root, 500, 300);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		System.out.println("Main");
		Application.launch(args);
	}

}

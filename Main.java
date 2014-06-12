package proj;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Scene scene;
	private BorderPane root;
	private Button reloadButton, goButton;
	private TextField addressField;
	private WebView webView;
	private WebEngine webEngine;
	
	@Override
	public void start(Stage stage) throws Exception {
		System.out.println(Thread.currentThread().getName());
		
		//place navigation in vertical box
		HBox hBox = new HBox(5);
		hBox.setAlignment(Pos.CENTER);
		
		//buttons for navigation
		reloadButton = new Button("Reload");
		goButton = new Button("go");
		
		//add listeners to buttons
		reloadButton.setOnAction(reload);
		goButton.setOnAction(go);
		
		//textfield for entering web addresses
		addressField = new TextField("Enter web address here...");
		addressField.setPrefColumnCount(50);
		
		//add all navigation nodes to the vbox
		hBox.getChildren().addAll(reloadButton, goButton, addressField);
		
		//our webview that displays the page
		webView = new WebView();
		
		//engine to manage our pages.
		webEngine = webView.getEngine();
		webEngine.setJavaScriptEnabled(true);
		webEngine.load("http://www.yahoo.com");
		
		//our main app layout with 5 regions
		BorderPane root = new BorderPane();
		root.setPrefSize(1024, 768);
		
		//add every node to the borderpane
		root.setTop(hBox);
		root.setCenter(webView);
		
		//set the scene
		scene = new Scene(root);
		
		//the stage manages the scene.
		stage.setTitle("The JavaFX Web Browser");
		stage.setScene(scene);
		stage.show();
		
	}
	
	//event handlers for navigation
	private EventHandler<ActionEvent> reload = new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			webEngine.reload();
		}
	};
	
	private EventHandler<ActionEvent> go = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			System.out.println(addressField.getText());
			webEngine.load("http://" + addressField.getText());
		}		
	};
	
	public static void main(String[] args) {
		launch(args);
	}

}
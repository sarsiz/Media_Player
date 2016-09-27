package application;
	
import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
/*
 * I have made a media player which can play mp3 and mp4 formats only(till now)
 * More functionalities will be soon added to make it better than the others
 */

public class Main extends Application {
	
	Player player;
	FileChooser fileChooser;
	
	public void start(final Stage primaryStage) {
		//Now, I'm creating menu bar, to allow the users to open the file which they want to play
		MenuItem open = new MenuItem("Open");
		Menu file = new Menu("File");
		MenuBar menu = new MenuBar();
		
		//now we need to these to each other
		file.getItems().add(open);  //now I will add file to the menu
		menu.getMenus().add(file);  //now I need to add the menu to the player soo...go on :)
		
		fileChooser = new FileChooser();
		
		open.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				player.player.pause();  /* I made an object player outside the start function...
										   so that this function can reach the player */
				//now I need to choose the files...
				File file = fileChooser.showOpenDialog(primaryStage);  //now I added final in the start function
				if(file!=null){
					try {
						player = new Player(file.toURI().toURL().toExternalForm());
						Scene scene = new Scene(player, 1080, 720, Color.BLACK);  //make a new scene
						primaryStage.setScene(scene);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		String path = "C:\\Users\\DELL\\workspace\\Saz_MediaPlayer\\moviemusic\\ChahuMainYaaNaa.mp4";
		//Media media = new Media(new File(path).toURI().toString());
		
		//converted the string to url type
		player = new Player(new File(path).toURI().toString());  //first I created a player..
		player.setTop(menu);  //this will add the menu to the top...now we will set the action for menu ^^
		Scene scene = new Scene(player, 1080, 720, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

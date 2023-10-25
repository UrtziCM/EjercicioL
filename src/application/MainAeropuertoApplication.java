package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainAeropuertoApplication extends Application {

    @Override
    public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(this.getClass().getResource("/fxml/listadoAeropuertos.fxml"));
	    	Scene scene = new Scene( root );
	    	
	        stage.setTitle("PERSONAS");
	        stage.setScene(scene);
	        stage.setResizable(false);
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public static void main(String[] args) {
		launch(args);
	}
}

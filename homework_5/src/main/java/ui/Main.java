package ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import database.Sql;
import javafx.stage.WindowEvent;
import manage.Searcher;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);


    }





    public static Sql s=new Sql();
    @Override
    public void start(Stage primaryStage) {


        try {
            URL url = getClass().getClassLoader().getResource("Main.fxml");
            //System.out.print(url.getPath());
            Parent root = FXMLLoader.load(url);

            Scene scene = new Scene(root);

            primaryStage.setTitle("学校资讯");
            primaryStage.setScene(scene);


            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.exit(0);
                }
            });

            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

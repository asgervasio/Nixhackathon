import edu.ycp.cs320.cteichmann.persist.NixMain;
import edu.ycp.cs320.cteichmann.persist.Tile;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.image.SampleModel;


public class javaFX extends Application{

    Button button;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        NixMain nx = new NixMain();

        primaryStage.setTitle("CarChecker");


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label sampleSwatchLabel = new Label("Sample Location:");
        GridPane.setConstraints(sampleSwatchLabel, 0 ,1);

        TextField fileInput = new TextField("Test");
        GridPane.setConstraints(fileInput, 1, 1);

        Tile sampleTile = nx.readSampleSwatch("ye_colordata.csv");
        Rectangle sampleSwatch = new Rectangle(500,500);
        sampleSwatch.setFill(sampleTile.getColor());

        grid.getChildren().add(new Rectangle(50,50, sampleTile.getColor()));


        grid.getChildren().addAll(sampleSwatchLabel, fileInput);

        button = new Button();
        button.setText("Submit");
        button.setOnAction(e -> System.out.println(fileInput.getText()));
        GridPane.setConstraints(button, 0, 2);
        grid.getChildren().add(button);

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        layout.getChildren().add(sampleSwatch);

        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}

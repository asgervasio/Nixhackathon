import edu.ycp.cs320.cteichmann.persist.NixMain;
import edu.ycp.cs320.cteichmann.persist.Tile;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.image.SampleModel;


public class javaFX extends Application{
    

    Scene homeScene, scene2;

    @Override
    public void start(Stage primaryStage) {
        NixMain nx = new NixMain();
        primaryStage.setTitle("CarChecker");
        BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        border.setTop(hbox);


        Tile sampleTile = nx.readSampleSwatch("ye_colordata.csv");
        Rectangle sampleSwatch = new Rectangle(500,500);
        sampleSwatch.setFill(sampleTile.getColor());


        /**************HOME SCENE************************************/
         GridPane grid = new GridPane();
         grid.setAlignment(Pos.CENTER);
         grid.setHgap(10);
         grid.setVgap(10);
         grid.setPadding(new Insets(25, 25, 25, 25));

         Text scenetitle = new Text("Grease Thief");
         scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
         grid.add(scenetitle, 0, 0, 2, 1);

         Label locLabel = new Label("Sample Location:");
         grid.add(locLabel, 0, 1);

         TextField locField = new TextField();
         grid.add(locField, 1, 1);


         Label standardLabel = new Label("Standards:");
         grid.add(standardLabel, 0, 2);

         TextField standardField = new TextField();
         grid.add(standardField, 1, 2);

         Button submitButton = new Button("Submit");
         HBox hbButton = new HBox(10);
         hbButton.setAlignment(Pos.BOTTOM_RIGHT);
         hbButton.getChildren().add(submitButton);
         grid.add(hbButton, 1, 4);


        Scene homeScene = new Scene(grid, 300, 275);

        /*****************************************************/

        primaryStage.setScene(homeScene);
        primaryStage.show();


        /*******************SCENE 2************************************/
        Button button2 = new Button("GO BACK");
        Label fileLocLabel = new Label();
        Label standardLocLabel = new Label();

        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(button2,fileLocLabel,standardLocLabel);
        scene2 = new Scene(layout2, 600, 300);

        /**************************************************************/

        submitButton.setOnAction(e -> {
            fileLocLabel.setText(locField.getText());
            standardLocLabel.setText(standardField.getText());
            primaryStage.setScene(scene2);
        });

        button2.setOnAction(e -> primaryStage.setScene(homeScene));

    }

    public HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Label sampleSwatchLabel = new Label("Sample Location:");
        sampleSwatchLabel.setPrefSize(100,30);

        TextField fileInput = new TextField("Test");
        fileInput.setPrefSize(100,30);

        hbox.getChildren().addAll(sampleSwatchLabel, fileInput);

        return hbox;
    }
}

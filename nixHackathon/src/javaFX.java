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
import java.util.List;


public class javaFX extends Application{


    Scene homeScene, scene2;
    NixMain nix = new NixMain();
    Tile sampleTile;
    List<Tile> standardList;
    private Text sceneTitle         = new Text();
    Label sampleLocLable            = new Label();
    TextField sampleLocTextField    = new TextField();
    Label standardLocLabel          = new Label();
    TextField standardLocTextField  = new TextField();
    Button submitButton             = new Button();

    Rectangle sampleSwatch = new Rectangle(50,50);



    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        NixMain nx = new NixMain();
        primaryStage.setTitle("CarChecker");

        //Tile sampleTile = nx.readSampleSwatch("ye_colordata.csv");
        //Rectangle sampleSwatch = new Rectangle(500,500);
        //sampleSwatch.setFill(sampleTile.getColor());


        /**************HOME SCENE************************************/
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        sceneTitle.setText("Grease Thief");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        sampleLocLable.setText("Sample Location:");
        grid.add(sampleLocLable, 0, 1);

        grid.add(sampleLocTextField, 1, 1);


        standardLocLabel.setText("Standards:");
        grid.add(standardLocLabel, 0, 2);

        grid.add(standardLocTextField, 1, 2);

        submitButton.setText("Submit");
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
        BorderPane border = new BorderPane();
        Label fileLocLabel = new Label();
        Label standardLocLabel = new Label();
        VBox layout2 = new VBox(20);

        /**************************************************************/
/*
        submitButton.setOnAction(e -> {
            setSampleTile(sampleLocTextField.getText());

            //fileLocLabel.setText(locField.getText());
            //standardLocLabel.setText(standardField.getText());
            primaryStage.setScene(scene2);
            //sampleTile = loadTile(locField.getText());

        });
        //Text sampleText = new Text("Grease Thief");
        //scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        //Rectangle sampleSwatch = new Rectangle(50,50, sampleTile.getColor());

        button2.setOnAction(e -> primaryStage.setScene(homeScene));

        layout2.getChildren().addAll(sampleSwatch,button2,fileLocLabel,standardLocLabel);
        scene2 = new Scene(layout2, 600, 300);

    }

    public void setSampleTile(String loc){
        sampleTile = nix.readSampleSwatch(loc);
        sampleSwatch.setFill(sampleTile.getColor());
        System.out.println(sampleTile.getR());
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

    public Tile loadTile(String loc){
        sampleTile = nix.readSampleSwatch(loc);
        return sampleTile;
    }

    public List<Tile> loadStandards(String loc){
        standardList = nix.loadGreaseStandards();
        return standardList;
    }
}

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
    Tile standardTile;
    List<Tile> standardList;
    private Text sceneTitle                 = new Text();
    private Label sampleLocLable            = new Label();
    private TextField sampleLocTextField    = new TextField();
    private Label standardLocLabel          = new Label();
    private TextField standardLocTextField  = new TextField();
    private Button submitButton             = new Button();

<<<<<<< HEAD
    Rectangle sampleSwatch = new Rectangle(50,50);
    Rectangle standardSwatchWhite = new Rectangle(50,50);
    Rectangle standardSwatchGrey = new Rectangle(50,50);
    Rectangle standardSwatchTan = new Rectangle(50,50);
    Rectangle standardSwatchBrown = new Rectangle(50,50);
    Rectangle standardSwatchDarkBrown = new Rectangle(50,50);
    Rectangle standardSwatchBlack = new Rectangle(50,50);
=======
    private Text sampleText                 = new Text();
    private Rectangle sampleSwatch          = new Rectangle(50,50);
    private Label sampleRGB                 = new Label();
>>>>>>> dfa168fe2082b92f3c460c9e57d0634c8d539d4e



    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        NixMain nx = new NixMain();
<<<<<<< HEAD
        primaryStage.setTitle("Grease Thief");
=======
        primaryStage.setTitle("CarChecker");


>>>>>>> dfa168fe2082b92f3c460c9e57d0634c8d539d4e
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

        VBox layout2 = new VBox(20);
        HBox bar = new HBox(10);

        /**************************************************************/

        submitButton.setOnAction(e -> {
            setSampleTile(sampleLocTextField.getText());
<<<<<<< HEAD
            setStandardTile(standardLocTextField.getText());
            primaryStage.setScene(scene2);
        });

        button2.setOnAction(e -> primaryStage.setScene(homeScene));
        layout2.getChildren().addAll(sampleSwatch,button2,fileLocLabel,standardLocLabel);
        bar.getChildren().addAll(standardSwatchWhite, standardSwatchGrey, standardSwatchTan, standardSwatchBrown, standardSwatchDarkBrown, standardSwatchBlack);
        scene2 = new Scene(bar, 600, 300);
=======

            primaryStage.setScene(scene2);

        });

        button2.setOnAction(e -> primaryStage.setScene(homeScene));
        sampleText.setText("Sample");
        sampleText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        layout2.getChildren().addAll(sampleText,sampleSwatch, sampleRGB, button2);
        border.setLeft(layout2);
        scene2 = new Scene(border, 600, 300);
>>>>>>> dfa168fe2082b92f3c460c9e57d0634c8d539d4e

    }

    public void setSampleTile(String loc){
        sampleTile = nix.readSampleSwatch(loc);
        sampleSwatch.setFill(sampleTile.getColor());
        sampleRGB.setText("R: "+ sampleTile.getR() + " G: " + sampleTile.getG() + " B: " + sampleTile.getB());
        System.out.println(sampleTile.getR());
    }
<<<<<<< HEAD
    public void setStandardTile(String loc){
        standardTile = nix.loadGreaseStandards().get(0);
        standardSwatchWhite.setFill(standardTile.getColor());
        System.out.println(standardTile.getR());
        standardTile = nix.loadGreaseStandards().get(1);
        standardSwatchGrey.setFill(standardTile.getColor());
        standardTile = nix.loadGreaseStandards().get(2);
        standardSwatchTan.setFill(standardTile.getColor());
        standardTile = nix.loadGreaseStandards().get(3);
        standardSwatchBrown.setFill(standardTile.getColor());
        standardTile = nix.loadGreaseStandards().get(4);
        standardSwatchDarkBrown.setFill(standardTile.getColor());
        standardTile = nix.loadGreaseStandards().get(5);
        standardSwatchBlack.setFill(standardTile.getColor());
    }
        public Tile loadTile(String loc){
=======

    public Tile loadTile(String loc){
>>>>>>> dfa168fe2082b92f3c460c9e57d0634c8d539d4e
        sampleTile = nix.readSampleSwatch(loc);
        return sampleTile;
    }

    public List<Tile> loadStandards(String loc){
        standardList = nix.loadGreaseStandards();
        return standardList;
    }
}

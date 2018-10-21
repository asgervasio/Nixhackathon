import edu.ycp.hackathon.greaseThief.NixMain;
import edu.ycp.hackathon.greaseThief.Tile;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;


public class javaFX extends Application{
    Scene homeScene, scene2;
    NixMain nix = new NixMain();
    Tile sampleTile;
    List<Tile> standardList;
    private Text sceneTitle                 = new Text();
    private Label sampleLocLable            = new Label();
    private TextField sampleLocTextField    = new TextField();
    private Label standardLocLabel          = new Label();
    private TextField standardLocTextField  = new TextField();
    private Button submitButton             = new Button();

    private Text sampleText                 = new Text();
    private Rectangle sampleSwatch          = new Rectangle(50,50);
    private Rectangle matchSwatch           = new Rectangle(50, 50);
    private Label sampleRGB                 = new Label();
    private Label percentMatch              = new Label();
    private Label ironContent               = new Label();
    private Label description               = new Label();


    private Rectangle standardSwatchWhite       = new Rectangle(50,50);
    private Rectangle standardSwatchGrey        = new Rectangle(50,50);
    private Rectangle standardSwatchTan         = new Rectangle(50,50);
    private Rectangle standardSwatchBrown       = new Rectangle(50,50);
    private Rectangle standardSwatchDarkBrown   = new Rectangle(50,50);
    private Rectangle standardSwatchBlack       = new Rectangle(50,50);


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        NixMain nx = new NixMain();
        primaryStage.setTitle("Grease Thief");


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
        border.setStyle("-fx-background-color: #9EEB71;");
        VBox leftVBox = new VBox(20);
        HBox gradient = new HBox(10);
        gradient.setAlignment(Pos.CENTER);
        gradient.setPadding(new Insets(25,0,10,0));

        VBox rightVBox = new VBox(20);


        /**************************************************************/

        submitButton.setOnAction(e -> {
            Tile sample = setSampleTile(sampleLocTextField.getText());
            List<Tile> standards = setStandardTile(standardLocTextField.getText());
            Tile closest = sample.compareTile(standards);
            matchSwatch.setFill(closest.getColor());

            primaryStage.setScene(scene2);

        });

        button2.setOnAction(e -> primaryStage.setScene(homeScene));
        sampleText.setText("Sample");
        sampleText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        leftVBox.getChildren().addAll(sampleText,sampleSwatch, sampleRGB, button2);
        gradient.getChildren().addAll(standardSwatchWhite,standardSwatchGrey,standardSwatchTan,standardSwatchBrown,standardSwatchDarkBrown,standardSwatchBlack);
        rightVBox.getChildren().add(matchSwatch);
        border.setTop(gradient);
        border.setLeft(leftVBox);
        border.setRight(rightVBox);
        scene2 = new Scene(border, 600, 300);
    }

    public Tile setSampleTile(String loc){
        sampleTile = nix.readSampleSwatch(loc);
        sampleSwatch.setFill(sampleTile.getColor());
        sampleRGB.setText("R: "+ sampleTile.getR() + " G: " + sampleTile.getG() + " B: " + sampleTile.getB());
        return sampleTile;
    }

    public List<Tile> setStandardTile(String loc){
        standardList = nix.loadGreaseStandards(loc);

        standardSwatchWhite     .setFill(standardList.get(0).getColor());
        standardSwatchGrey      .setFill(standardList.get(1).getColor());
        standardSwatchTan       .setFill(standardList.get(2).getColor());
        standardSwatchBrown     .setFill(standardList.get(3).getColor());
        standardSwatchDarkBrown .setFill(standardList.get(4).getColor());
        standardSwatchBlack     .setFill(standardList.get(5).getColor());

        return standardList;
    }

}

import edu.ycp.hackathon.greaseThief.NixMain;
import edu.ycp.hackathon.greaseThief.Tile;
import javafx.application.Application;
import javafx.geometry.HPos;
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
import javafx.scene.text.TextAlignment;
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
    private Text matchText                  = new Text();
    private Rectangle sampleSwatch          = new Rectangle(150,150);
    private Rectangle matchSwatch           = new Rectangle(150, 150);
    private Label sampleRGB                 = new Label();
    private Label matchRGB                  = new Label();
    private Label deltaMatch                = new Label();
    private Label status                = new Label();
    private Label description               = new Label();
    private Label descriptionBody           = new Label();
    private Label gradientLabel             = new Label();


    private Rectangle standardSwatchWhite       = new Rectangle(100,100);
    private Rectangle standardSwatchGrey        = new Rectangle(100,100);
    private Rectangle standardSwatchTan         = new Rectangle(100,100);
    private Rectangle standardSwatchBrown       = new Rectangle(100,100);
    private Rectangle standardSwatchDarkBrown   = new Rectangle(100,100);
    private Rectangle standardSwatchBlack       = new Rectangle(100,100);


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
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
        sceneTitle.setTextAlignment(TextAlignment.CENTER);
        grid.add(sceneTitle, 0, 0, 2, 1);
        GridPane.setHalignment(sceneTitle, HPos.CENTER);

        sampleLocLable.setText("Sample Location: ");
        sampleLocLable.setFont(Font.font("Tahoma", FontWeight.LIGHT, 30));
        grid.add(sampleLocLable, 0, 1);


        grid.add(sampleLocTextField, 1, 1);


        standardLocLabel.setText("Standards: ");
        standardLocLabel.setFont(Font.font("Tahoma", FontWeight.LIGHT, 30));
        GridPane.setHalignment(standardLocLabel, HPos.RIGHT);

        grid.add(standardLocLabel, 0, 2);

        grid.add(standardLocTextField, 1, 2);

        submitButton.setText("Submit");
        HBox hbButton = new HBox(10);
        hbButton.setAlignment(Pos.BOTTOM_RIGHT);
        hbButton.getChildren().add(submitButton);
        grid.add(hbButton, 1, 4);


        Scene homeScene = new Scene(grid, 1500, 750);

        /*****************************************************/

        primaryStage.setScene(homeScene);
        primaryStage.show();

        /*******************SCENE 2************************************/
        Button button2 = new Button("NEW SAMPLE");
        BorderPane border = new BorderPane();
        border.setStyle("-fx-background-color: #9EEB71;");
        VBox topVBox = new VBox(10);
        VBox leftVBox = new VBox(10);
        VBox rightVBox = new VBox(10);
        VBox centerVBox = new VBox(10);
        HBox gradient = new HBox(10);
        gradient.setAlignment(Pos.CENTER);
        gradient.setPadding(new Insets(20,0,0,0));



        /**************************************************************/

        submitButton.setOnAction(e -> {
            Tile sample = setSampleTile(sampleLocTextField.getText());
            List<Tile> standards = setStandardTile(standardLocTextField.getText());
            Tile closest = sample.compareTile(standards);
            matchSwatch.setFill(closest.getColor());
            matchRGB.setText("R: "+ closest.getR() + " G: " + closest.getG() + " B: " + closest.getB());
            matchRGB.setFont(Font.font("Tahoma", FontWeight.LIGHT, 30));
            deltaMatch.setText("DeltaMatch: "    + String.valueOf(Math.round(sample.getDeltaMatch() * 100) / 10000.0));
            deltaMatch.setFont(Font.font("Tahoma", FontWeight.LIGHT, 20));
            status.setText("Status: " + closest.getIronContent());
            status.setFont(Font.font("Tahoma", FontWeight.LIGHT, 20));
            description.setText("Recommendation");
            description.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

            descriptionBody.setText(closest.getDescription());
            descriptionBody.setFont(Font.font("Tahoma", FontWeight.LIGHT, 20));
            descriptionBody.setWrapText(true);
            descriptionBody.setTextAlignment(TextAlignment.CENTER);
            description.setAlignment(Pos.CENTER);
            primaryStage.setScene(scene2);

        });

        button2.setOnAction(e -> primaryStage.setScene(homeScene));

        gradientLabel.setText("Standard Set");
        gradientLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
        gradient.setPadding(new Insets(10,0,0,0));



        sampleText.setText("Sample");
        sampleText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));


        matchText.setText("Match");
        matchText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
        gradient.getChildren().addAll(standardSwatchWhite,standardSwatchGrey,standardSwatchTan,standardSwatchBrown,standardSwatchDarkBrown,standardSwatchBlack);
        topVBox.getChildren().addAll(gradientLabel, gradient);
        leftVBox.getChildren().addAll(sampleText,sampleSwatch, sampleRGB);
        leftVBox.setPadding(new Insets(0,20,0,20));
        centerVBox.getChildren().addAll(deltaMatch, status, description, descriptionBody, button2);
        rightVBox.getChildren().addAll(matchText,matchSwatch, matchRGB);
        rightVBox.setPadding(new Insets(0,20,0,20));
        topVBox.setAlignment(Pos.CENTER);
        leftVBox.setAlignment(Pos.CENTER);
        centerVBox.setAlignment(Pos.CENTER);
        rightVBox.setAlignment(Pos.CENTER);

        centerVBox.setMaxWidth(500);



        border.setTop(topVBox);
        border.setLeft(leftVBox);
        border.setCenter(centerVBox);
        border.setRight(rightVBox);
        scene2 = new Scene(border, 1500, 750);
    }

    public Tile setSampleTile(String loc){
        sampleTile = nix.readSampleSwatch(loc);
        sampleSwatch.setFill(sampleTile.getColor());
        sampleRGB.setText("R: "+ sampleTile.getR() + " G: " + sampleTile.getG() + " B: " + sampleTile.getB());
        sampleRGB.setFont(Font.font("Tahoma", FontWeight.LIGHT, 30));

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

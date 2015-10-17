import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene sceneMain;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Label mainTop = new Label("Welcome to Walmart, please choose an option below to continue");
        mainTop.setTextAlignment(TextAlignment.CENTER);
        mainTop.setWrapText(true);
        Label mainCenter = new Label("< Walmart Associates only || Customers please click >");
        mainCenter.setTextAlignment(TextAlignment.CENTER);
        mainCenter.setWrapText(true);
        Label mainBot = new Label("Walmart API demo by Hongda Zeng at Boilermake");
        mainBot.setTextAlignment(TextAlignment.CENTER);
        mainBot.setWrapText(true);

        Button saveItem = new Button("Save an item");
        Button findItem = new Button("Find a product");

        VBox layoutTop = new VBox(20);
        layoutTop.getChildren().addAll(mainTop);
        layoutTop.setPadding(new Insets(20, 20, 20, 20));
        layoutTop.setAlignment(Pos.CENTER);

        HBox layoutCenter = new HBox(20);
        layoutCenter.getChildren().addAll(saveItem, mainCenter, findItem);
        layoutCenter.setPadding(new Insets(20, 20, 20, 20));
        layoutCenter.setAlignment(Pos.CENTER);

        VBox layoutBot = new VBox(20);
        layoutBot.getChildren().addAll(mainBot);
        layoutBot.setPadding(new Insets(20, 20, 20, 20));
        layoutBot.setAlignment(Pos.CENTER);

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(layoutTop);
        mainPane.setCenter(layoutCenter);
        mainPane.setBottom(layoutBot);

        sceneMain = new Scene(mainPane, 750, 300);

        window.setScene(sceneMain);
        window.setTitle("Walmart Store Helper");
        window.show();

    }

}

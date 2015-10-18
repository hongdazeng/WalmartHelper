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

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    Stage window;
    Scene sceneMain;
    ArrayList<ItemLoc> mainList = new ArrayList<>();

    Double price;
    String itemName;

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
        APIcaller dealCaller = new APIcaller("Cake");
        dealCaller.dealCall();
        String dealName = dealCaller.getNamedeal();
        double dealPrice = dealCaller.getPricedeal();

        Label dealBot = new Label("Deal of the day: " + dealName + " for just $" + dealPrice);
        Label mainBot = new Label("Walmart API demo by Hongda Zeng at Boilermake");
        mainBot.setTextAlignment(TextAlignment.CENTER);
        mainBot.setWrapText(true);

        Button saveItem = new Button("Save an item");
        Button findItem = new Button("Find a product");

        saveItem.setOnAction(e -> {
            PopupBox newBox = new PopupBox();
            newBox.storeLogin();
            if (newBox.getAnswer()) {
                newBox.storeEntry();
                if (newBox.getStatus()) {
                    ItemLoc a = new ItemLoc(newBox.getUPC(), newBox.getLoc());
                    mainList.add(a);
                    PopupBox.displaySimple("Item Added", "Your item have been saved");
                }
            } else {
                PopupBox.displaySimple("Bad Password", "Please enter a valid password");
            }

        });

        findItem.setOnAction(e -> {

            boolean found = false;
            PopupBox newBox = new PopupBox();
            newBox.customerEntry();
            String entry = newBox.getUserEntry();
            APIcaller newCall = new APIcaller(entry);
            double upc = 0;
            try {
                upc = newCall.getUPC();
                price = newCall.getPrice();
                itemName = newCall.getName();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
                upc = -1;
            }
            for (ItemLoc b : mainList) {
                if (b.getUPC() == upc) {
                    PopupBox.displaySimple("Item found", "Best match for your search is: " + itemName +
                            "\nIt can be found at " + b.getLoc() + "\nfor $" + price);
                    found = true;
                }
            }
            if (!found) {
                PopupBox.displaySimple("Item not found", "We are unable to find such an item in this store. " +
                        "\nPlease consider the following item from Walmart.com" +
                        "\n" + itemName + " - $" + price);
            }
        });

        VBox layoutTop = new VBox(20);
        layoutTop.getChildren().addAll(mainTop);
        layoutTop.setPadding(new Insets(20, 20, 20, 20));
        layoutTop.setAlignment(Pos.CENTER);

        HBox layoutCenter = new HBox(20);
        layoutCenter.getChildren().addAll(saveItem, mainCenter, findItem);
        layoutCenter.setPadding(new Insets(20, 20, 20, 20));
        layoutCenter.setAlignment(Pos.CENTER);

        VBox layoutBot = new VBox(20);
        layoutBot.getChildren().addAll(dealBot, mainBot);
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

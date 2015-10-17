import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Hongda Zeng on 10/17/2015!
 */
public class PopupBox {

    boolean answer;

    double itemUPC;
    String itemLOC;

    public static void displaySimple(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(300);
        window.setHeight(200);

        Label label = new Label();
        label.setWrapText(true);
        label.setText(message);
        label.setTextAlignment(TextAlignment.CENTER);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void storeLogin() {
        Stage window = new Stage();
        Button button;
        String password = "walmart1";

        Label label = new Label("Please enter the store password");

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Store Login");
        window.setWidth(300);
        window.setHeight(200);

        final TextField passWordInput = new TextField();
        button = new Button("Enter");
        button.setOnAction(e -> {
            String entry = passWordInput.getText();
            if (entry.equalsIgnoreCase(password)) {
                answer = true;
                window.close();
            } else {
                answer = false;
                window.close();
            }
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, passWordInput, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void storeEntry() {
        Stage window = new Stage();
        Button button;

        Label label = new Label("Please enter the item UPC");
        Label label2 = new Label("Please enter the item's location");

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Save an item");
        window.setWidth(300);
        window.setHeight(400);

        final TextField upcInput = new TextField();
        final TextField locationInput = new TextField();
        button = new Button("Save");
        button.setOnAction(e -> {
            itemUPC = Double.parseDouble(upcInput.getText());
            itemLOC = locationInput.getText();
            window.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, upcInput, label2, locationInput, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public boolean getAnswer() {
        return answer;
    }

    public double getUPC() {
        return itemUPC;
    }

    public String getLoc() {
        return itemLOC;
    }
}

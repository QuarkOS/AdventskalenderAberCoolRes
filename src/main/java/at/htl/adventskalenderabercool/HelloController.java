package at.htl.adventskalenderabercool;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    private Button Door1;
    @FXML
    private Button Door10;
    @FXML
    private Button Door11;
    @FXML
    private Button Door12;
    @FXML
    private Button Door13;
    @FXML
    private Button Door14;
    @FXML
    private Button Door15;
    @FXML
    private Button Door16;
    @FXML
    private Button Door17;
    @FXML
    private Button Door18;
    @FXML
    private Button Door19;
    @FXML
    private Button Door2;
    @FXML
    private Button Door20;
    @FXML
    private Button Door21;
    @FXML
    private Button Door22;
    @FXML
    private Button Door23;
    @FXML
    private Button Door24;
    @FXML
    private Button Door3;
    @FXML
    private Button Door4;
    @FXML
    private Button Door5;
    @FXML
    private Button Door6;
    @FXML
    private Button Door7;
    @FXML
    private Button Door8;
    @FXML
    private Button Door9;
    @FXML
    private Label Heading;
    @FXML
    private ImageView backgroundImage;
    @FXML
    private Pane backgroundPane;

    private List<Door> doors = new ArrayList<>();

    private final InputStream backgroundurl = HelloController.class.getResourceAsStream("images/background.jpg");
    private final Image backgroundImg = new Image(backgroundurl);
    private Border border;

    // NormalGreenBackground
    private BackgroundFill backgroundFill = new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY);
    private final Background doorBackground = new Background(backgroundFill);

    @FXML
    private void initialize() {
        border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        Heading.setText("Casino Advent Calendar");
        backgroundImage.setImage(backgroundImg);
        initDoors();
    }

    private void initDoors() {
        for (int i = 1; i <= 24; i++) {
            try {
                Button door = (Button) getClass().getDeclaredField("Door" + i).get(this);
                final int doorNumber = i;
                final Button doorButton = door;

                doorButton.setText(String.valueOf(i));
                doorButton.setBorder(border);
                doorButton.setBackground(doorBackground);
                doorButton.setOnAction(event -> openDoor(doorButton, doorNumber));
                doors.add(new Door(i, "Content of door " + i));

                // Set image to corresponding ImageView
//                findCorrespondingImageToDoor(i);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void findCorrespondingImageToDoor(int doorNumber) {
        String imageFileName = "images/door" + doorNumber + ".jpg";
        InputStream imageUrl = HelloController.class.getResourceAsStream(imageFileName);
        Image doorImage = new Image(imageUrl);

        try {
            ImageView imageView = (ImageView) getClass().getDeclaredField("image" + doorNumber).get(this);
            imageView.setImage(doorImage);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }


    private void openDoor(Button door, int doorNumber) {
        Door currentDoor = doors.get(doorNumber - 1);

        if (!currentDoor.isOpened()) {
            // Create a scaling animation to simulate the door growing bigger
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), door);
            scaleTransition.setByX(1.2);
            scaleTransition.setByY(1.2);
            scaleTransition.setCycleCount(1);
            scaleTransition.setAutoReverse(false);

            // Create a fade out animation to make the door disappear
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), door);
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);
            fadeTransition.setCycleCount(1);
            fadeTransition.setAutoReverse(false);

            SequentialTransition sequentialTransition = new SequentialTransition(scaleTransition, fadeTransition);
            sequentialTransition.play();

            System.out.println("Opened door " + doorNumber + ": " + currentDoor.getContent());
            currentDoor.setOpened(true);
        } else {
            System.out.println("Door " + doorNumber + " is already opened.");
        }
    }


}

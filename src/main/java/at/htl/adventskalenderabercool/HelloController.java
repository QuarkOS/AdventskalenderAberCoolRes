package at.htl.adventskalenderabercool;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;

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

    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image5;
    @FXML
    private ImageView image6;
    @FXML
    private ImageView image7;
    @FXML
    private ImageView image8;
    @FXML
    private ImageView image9;
    @FXML
    private ImageView image10;
    @FXML
    private ImageView image11;
    @FXML
    private ImageView image12;
    @FXML
    private ImageView image13;
    @FXML
    private ImageView image14;
    @FXML
    private ImageView image15;
    @FXML
    private ImageView image16;
    @FXML
    private ImageView image17;
    @FXML
    private ImageView image18;
    @FXML
    private ImageView image19;
    @FXML
    private ImageView image20;
    @FXML
    private ImageView image21;
    @FXML
    private ImageView image22;
    @FXML
    private ImageView image23;
    @FXML
    private ImageView image24;

    private List<Door> doors = new ArrayList<>();

    @FXML
    private void initialize() {
        Heading.setText("Fish Advent Calendar");
        initDoors();
        setAllImagesNotVisible();
    }

    private void initDoors() {
        for (int i = 1; i <= 24; i++) {
            try {
                Button door = (Button) getClass().getDeclaredField("Door" + i).get(this);
                final int doorNumber = i;
                final Button doorButton = door;

                doorButton.setText(String.valueOf(i));
                doorButton.setOnAction(event -> openDoor(doorButton, doorNumber));
                doors.add(new Door(i, "Content of door " + i));

                // Set image to corresponding ImageView
                findCorrespondingImageToDoor(i);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void setAllImagesNotVisible() {
        for (int i = 1; i <= 24; i++) {
            try {
                ImageView imageView = (ImageView) getClass().getDeclaredField("image" + i).get(this);
                imageView.setVisible(false);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void findCorrespondingImageToDoor(int doorNumber) {
        String imageFileName = "/at/htl/adventskalenderabercool/images/door" + doorNumber + ".jpg";
        InputStream imageStream = getClass().getResourceAsStream(imageFileName);

        if (imageStream == null) {
            System.err.println("Bilddatei nicht gefunden: " + imageFileName);
            return;
        }

        Image doorImage = new Image(imageStream);

        try {
            ImageView imageView = (ImageView) getClass().getDeclaredField("image" + doorNumber).get(this);
            System.out.println("ImageView: " + imageView + " -> " + doorNumber);
            imageView.setImage(doorImage);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }


    private void openDoor(Button door, int doorNumber) {
        Door currentDoor = doors.get(doorNumber - 1);

        if (!currentDoor.isOpened()) {
            System.out.println("Opened door " + doorNumber + ": " + currentDoor.getContent());
            currentDoor.setOpened(true);
            openAnimation(door, doorNumber);
        } else {
            System.out.println("Door " + doorNumber + " is already opened.");
        }
    }

    private void setImageAtIndexToVisible(int index) {
        try {
            ImageView imageView = (ImageView) getClass().getDeclaredField("image" + index).get(this);
            imageView.setVisible(true);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private void openAnimation(Button door, int doorNumber) {
    try {
        ImageView imageView = (ImageView) getClass().getDeclaredField("image" + doorNumber).get(this);
        // Create a TranslateTransition for the door
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), door);
        transition.setByX(100); // Move the door 100 pixels to the right
        transition.setOnFinished(event -> imageView.setVisible(true)); // Show image when animation is finished
        // Start the animation
        transition.play();
    } catch (IllegalAccessException | NoSuchFieldException e) {
        throw new RuntimeException(e);
    }
}}

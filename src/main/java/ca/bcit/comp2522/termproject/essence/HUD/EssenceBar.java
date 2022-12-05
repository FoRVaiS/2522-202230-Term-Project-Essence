package ca.bcit.comp2522.termproject.essence.HUD;

import ca.bcit.comp2522.termproject.essence.Layers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The User Interface to display essence bar(EXP).
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */
public class EssenceBar implements Initializable {

    /**
     * Instantiation of BigDecimal to help with accuracy in rounding to .2f.
     */
    BigDecimal progress = new BigDecimal(String.format("%.2f", 0.0));
    @FXML
    private ProgressBar essenceBar = new ProgressBar();
    @FXML
    private Label essenceLabel = new Label();

    /**
     * Initialize a color for the bar.
     * @param arg0 of a URL
     * @param arg1 of a ResourceBundle
     */

    @Override
    public void initialize(final URL arg0, final ResourceBundle arg1) {
        essenceBar.setStyle("-fx-accent: #0FFFF0;");
    }

    /**
     * Increase progress bar based on collection of essence.
     * @param essence as an int
     * @param capacity as an int
     */
    public void increaseProgress(final int essence, final int capacity) {
        if (progress.doubleValue() < 1) {
            progress = new BigDecimal(String.format("%.2f", progress.doubleValue() + essence / capacity));
            essenceBar.setProgress(progress.doubleValue());
            essenceLabel.setText((int) Math.round(progress.doubleValue() * 100) + "%");
        }
    }

    /**
     * Resets the progress bar once the entity levels up.
     */
    public void reset() {
        essenceBar.setProgress(0);
    }

    /**
     * Render the interfaces.
     */
    public void render() {
        try {
            final Parent root = FXMLLoader.load(getClass().getResource("/essenceBar.fxml"));
            Layers.UI_LAYER.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

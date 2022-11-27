package ca.bcit.comp2522.termproject.essence.userInterface;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

// attempt progress bar implementation.
public class EssenceAccumulator implements Initializable {

    //The BigDecimal helps with rounding behavior.
    /**
     * Instantiation of BigDecimal to help with accuracy in rounding to .2f.
     */
    BigDecimal progress = new BigDecimal(String.format("%.2f", 0.0));
    @FXML
    public ProgressBar essenceBar;
    @FXML
    private Button button;
    @FXML
    private Label essenceLabel;


    @Override
    public void initialize(final URL arg0, final ResourceBundle arg1) {

        essenceBar.setStyle("-fx-accent: #77CCFF;");

    }

    /**
     * Increase progress bar via button press.
     */

    public void increaseProgress() {

        if(progress.doubleValue() < 1) {
            progress = new BigDecimal(String.format("%.2f", progress.doubleValue() + 0.1));
            essenceBar.setProgress(progress.doubleValue());
            essenceLabel.setText((int) Math.round(progress.doubleValue() * 100) + "%");
        }
    }
}



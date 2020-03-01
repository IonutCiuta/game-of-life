package gof.gui;

import gof.implementation.Board;
import gof.core.IBoard;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    
    private final int DEFAULT_SIZE = 15;

    @FXML
    private FlowPane base;

    @FXML
    private HBox presetBox;

    @FXML
    private Button openButton, saveButton, openPresetBtn;

    @FXML
    private Button runButton, stopButton, clearButton;

    @FXML
    private HBox rootBox;

    private IBoard board;

    private JavaFXDisplayDriver display;

    private Timeline loop = null;
    
    private int windowWidth = 750;
    private int cellSizePx = 30;

    private PresetHandler presetHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presetHandler = new PresetHandler();
        AnchorPane anchor = presetHandler.loadPresets(base);
        presetBox.getChildren().add(anchor);

        // Setup board
        this.board = new Board();
        this.board.setup(DEFAULT_SIZE);
        createDisplay();
        
        attachResizeListener();
    }

    @FXML
    private void onRun(Event evt) {
        toggleButtons(false);

        loop = new Timeline(new KeyFrame(Duration.millis(300), e -> {
            board.update();
            display.displayBoard(board);
        }));

        loop.setCycleCount(100);
        loop.play();
    }

    @FXML
    private void onStop(Event evt) {
        toggleButtons(true);
        loop.stop();
    }

    @FXML
    private void onClear(Event evt) {
        this.board.setup(DEFAULT_SIZE);
        createDisplay();
    }
    
    @FXML
    private void onPresetOpen(Event evt) {
        board = presetHandler.openCurrentPreset(DEFAULT_SIZE);
        createDisplay();
    }

    @FXML
    private void onOpen(Event evt) {
        Board newBoard = FileHandler.openFromFile(DEFAULT_SIZE);
        if (newBoard != null) {
            board = newBoard;
            createDisplay();
        }
    }

    @FXML
    private void onSave(Event evt) {
        FileHandler.saveToFile(board);
    }
    
    private void toggleButtons(boolean enable) {
        presetBox.setDisable(!enable);
        openButton.setDisable(!enable);
        openPresetBtn.setDisable(!enable);
        saveButton.setDisable(!enable);
        runButton.setDisable(!enable);
        clearButton.setDisable(!enable);
        stopButton.setDisable(enable);
    }
    
    private void createDisplay() {
        display = new JavaFXDisplayDriver(board.getWidth(), cellSizePx, board);

        base.getChildren().clear();
        base.getChildren().add(new Group(display.getPane()));        
    }
    
    private void attachResizeListener() {
        ChangeListener<Number> sizeListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int newWidth = newValue.intValue();
                if (newWidth > 250 && Math.abs(newWidth - windowWidth) >= 50) {
                    windowWidth = newWidth;
                    cellSizePx = newWidth / 25;
                    createDisplay();
                }
            }
        };
        rootBox.widthProperty().addListener(sizeListener);
    }
}
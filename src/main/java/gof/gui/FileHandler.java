package gof.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import gof.core.IBoard;
import gof.core.IBoardProvider;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileHandler {

    public static IBoard openFromFile(int defaultSize, IBoardProvider boardProvider) {
        File file = askForOpenFile();
        if (file == null) {
            return null;
        }
        
        return loadFromFile(file, defaultSize, boardProvider);
    }
    
    public static IBoard loadFromFile(File file, int defaultSize, IBoardProvider boardProvider) {
        StringBuilder input = new StringBuilder();
        int size = defaultSize;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().replaceAll("\\s+","");
                input.append(line);
                size = line.length();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open file to create board!", e);
        }

        return boardProvider.getBoardFromString(input.toString(), size);
    }
    
    public static void saveToFile(IBoard board) {
        File file = askForSaveFile();
        if (file == null) {
            return;
        }
        
        String output = ""; // string of numbers from board
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                output += board.getState(i, j) ? 1 : 0;
            }
            if (i != board.getHeight() - 1){
                output += "\n";
            }
        }

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(output);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private static File askForSaveFile() {
        return getFileChooser().showSaveDialog(new Stage());
    }
    
    private static File askForOpenFile() {
        return getFileChooser().showOpenDialog(new Stage());
    }
    
    private static FileChooser getFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Game of Life Board File");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("GOFB files (*.gofb)", "*.gofb"));
        
        return fileChooser;
    }
}

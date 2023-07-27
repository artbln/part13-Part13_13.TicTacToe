package ticTacToe;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TicTacToeApplication extends Application {

    @Override
    public void start(Stage window) throws Exception {
        // create the layout
        BorderPane borderPane = new BorderPane();
        Label topLabel = new Label("Turn: X");
        topLabel.setFont(Font.font("Monospace", 40));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        ArrayList<String> listX = new ArrayList<>();
        ArrayList<String> listO = new ArrayList<>();

        for (int x = 1; x <= 3; x++) {
            for (int y = 1; y <= 3; y++) {
                Button btn = new Button("  ");
                btn.setFont(Font.font("Monospace", 40));
                btn.setPrefSize(80, 80);
                gridPane.add(btn, x, y);

                // get the count (overcome the lambda limitation), & setId() 
                int z = 1;
                if (x == 1) {
                    z = y;
                } else if (x == 2) {
                    z = y + 3;
                } else if (x == 3) {
                    z = y + 6;
                }
                btn.setId(String.valueOf(z));
                
                btn.setOnMouseClicked((event) -> {
                    if (topLabel.getText().equals("Turn: X")) {
                        if (btn.getText().equals("  ")) {
                            btn.setText("X");
                            topLabel.setText("Turn: O");
                            listX.add(btn.getId());
                            if (checkForEnd(listX) || listX.size() == 5) {
                                topLabel.setText("The end!");
                            }
                        }
                    }

                    if (topLabel.getText().equals("Turn: O")) {
                        if (btn.getText().equals("  ")) {
                            btn.setText("O");
                            topLabel.setText("Turn: X");
                            listO.add(btn.getId());
                            if (checkForEnd(listO)) {
                                topLabel.setText("The end!");
                            }
                        }
                    }
                });
            }
        }

        borderPane.setTop(topLabel);
        borderPane.setCenter(gridPane);

        // set scene, show window
        Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
    }

    public boolean checkForEnd(ArrayList<String> list) {
        return (list.contains("1") && list.contains("2") && list.contains("3"))
                || (list.contains("4") && list.contains("5") && list.contains("6"))
                || (list.contains("7") && list.contains("8") && list.contains("9"))
                || (list.contains("1") && list.contains("4") && list.contains("7"))
                || (list.contains("2") && list.contains("5") && list.contains("8"))
                || (list.contains("3") && list.contains("6") && list.contains("9"))
                || (list.contains("1") && list.contains("5") && list.contains("9"))
                || (list.contains("3") && list.contains("5") && list.contains("7"));
    }
    
}

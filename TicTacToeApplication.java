package ticTacToe;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToeApplication extends Application {

    private boolean xTurn;
    private Button[] buttons;
    private Label turnLabel;
    private boolean gameEnded;

    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
    }

    @Override
    public void start(Stage stage) {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(300, 300);


        turnLabel = new Label("Turn: X");
        layout.setTop(turnLabel);


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        layout.setCenter(gridPane);
        buttons = new Button[9];
        for (int i = 0; i < 9; i++) {
            Button button = new Button();
            button.setPrefSize(80, 80);
            button.setOnAction(e -> {
                if (!gameEnded && button.getText().isEmpty()) {
                    if (xTurn) {
                        button.setText("O");
                        turnLabel.setText("Turn: X");
                    } else {
                        button.setText("X");
                        turnLabel.setText("Turn: O");
                    }
                    xTurn = !xTurn;
                    checkGameEnd();
                }
            });
            buttons[i] = button;
            gridPane.add(button, i % 3, i / 3);
        }

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    private void checkGameEnd() {

        for (int i = 0; i < 3; i++) {
            if (!buttons[i * 3].getText().isEmpty() && buttons[i * 3].getText().equals(buttons[i * 3 + 1].getText())
                    && buttons[i * 3].getText().equals(buttons[i * 3 + 2].getText())) {
                endGame();
                return;
            }
        }


        for (int i = 0; i < 3; i++) {
            if (!buttons[i].getText().isEmpty() && buttons[i].getText().equals(buttons[i + 3].getText())
                    && buttons[i].getText().equals(buttons[i + 6].getText())) {
                endGame();
                return;
            }
        }


        if (!buttons[0].getText().isEmpty() && buttons[0].getText().equals(buttons[4].getText())
                && buttons[0].getText().equals(buttons[8].getText())) {
            endGame();
            return;
        }
        if (!buttons[2].getText().isEmpty() && buttons[2].getText().equals(buttons[4].getText())
                && buttons[2].getText().equals(buttons[6].getText())) {
            endGame();
            return;
        }


        boolean tie = true;
        for (Button button : buttons) {
            if (button.getText().isEmpty()) {
                tie = false;
                break;
            }
        }
        if (tie) {
            endGame();
        }
    }

    private void endGame() {
        gameEnded = true;
        turnLabel.setText("The end!");
    }
}
                                                                                                                                       
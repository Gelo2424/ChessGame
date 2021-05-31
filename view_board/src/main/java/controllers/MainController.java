package controllers;

import app.App;
import player.enums.ChessboardStyle;
import player.enums.GameMode;
import player.enums.Pace;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import player.manager.LoginManager;

import java.io.IOException;

public class MainController {
    public AnchorPane mainAnchorPane;
    public ChoiceBox<Pace> paceChoiceBox;
    public ChoiceBox<GameMode> modeChoiceBox;
    public Button showProfileButton;
    public Button logInButton;

    public void initialize() {
        showProfileButton.setDisable(LoginManager.getLoggedUser() == null);
        //choice box do rodzaju gry
        ObservableList<GameMode> mode = FXCollections.observableArrayList(GameMode.values());
        modeChoiceBox.setItems(mode);
        modeChoiceBox.setValue(GameMode.GraczGracz);

        //choice box do tempa gry
        ObservableList<Pace> pace = FXCollections.observableArrayList(Pace.values());
        paceChoiceBox.setItems(pace);
        paceChoiceBox.setValue(Pace.menu);

        setLoginButton();
    }

    public void setLoginButton() {
        if(LoginManager.getLoggedUser() == null) {
            logInButton.setOnAction(this::logIn);
            logInButton.setText("Zaloguj sie");
        } else {
            logInButton.setOnAction(this::logOut);
            logInButton.setText("Wyloguj sie");
        }
    }

    public void logIn(ActionEvent actionEvent) {
        App.changeScene(mainAnchorPane,"loginWindow");
    }

    public void logOut(ActionEvent actionEvent) {
        LoginManager.logout();
        setLoginButton();
        StyleManager.setBoardColor(ChessboardStyle.classic);
    }

    public void showProfile(ActionEvent actionEvent) throws IOException {
        App.changeScene(mainAnchorPane,"profileWindow");
    }

    public void closeApp(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void newGame(ActionEvent actionEvent) throws IOException {
        App.changeScene(mainAnchorPane,"gameWindow");
        GameController.wts.start();
    }

    public void setPace(ActionEvent actionEvent) {

        switch (paceChoiceBox.getValue().getPace()) {
            case "bullet 1 | 0" -> {
                GameController.wts.setTime(1);
                GameController.wts.setIncrement(0);
                GameController.bts.setTime(1);
                GameController.bts.setIncrement(0);
            }
            case "bullet 1 | 1" -> {
                GameController.wts.setTime(1);
                GameController.wts.setIncrement(1);
                GameController.bts.setTime(1);
                GameController.bts.setIncrement(1);
            }
            case "blitz 3 | 0" -> {
                GameController.wts.setTime(3);
                GameController.wts.setIncrement(0);
                GameController.bts.setTime(3);
                GameController.bts.setIncrement(0);
            }
            case "blitz 5 | 0" -> {
                GameController.wts.setTime(5);
                GameController.wts.setIncrement(0);
                GameController.bts.setTime(5);
                GameController.bts.setIncrement(0);
            }
            case "blitz 5 | 5" -> {
                GameController.wts.setTime(5);
                GameController.wts.setIncrement(5);
                GameController.bts.setTime(5);
                GameController.bts.setIncrement(5);
            }
            case "szybkie 10 | 0" -> {
                GameController.wts.setTime(10);
                GameController.wts.setIncrement(0);
                GameController.bts.setTime(10);
                GameController.bts.setIncrement(0);;
            }
        }
    }
}

package edu.examenRecup.controller;

import edu.examenRecup.dao.UserDAO;
import edu.examenRecup.model.User;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

import java.util.List;

public class UserViewController {

    @FXML
    protected FlowPane cardsContainer;
    protected UserDAO userDAO;

    @FXML
    public void initializa() {
        userDAO = new UserDAO();
        loadCards();
    }

    protected void loadCards() {
        cardsContainer.getChildren().clear();
        List<User> users = userDAO.getAllUsers();


    }

    protectd VBox buildCard(User, user boolenan isAdmin) {

        buildCard(User user) {
            VBox card = new VBox(6);
            card.setPrefSize(200, 190);
            card.setAlignment(Pos.TOP_LEFT);
            card.setPadding(new Insets(12));
    }




    }
}
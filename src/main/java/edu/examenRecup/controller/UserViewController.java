package edu.examenRecup.controller;

import edu.examenRecup.dao.UserDAO;
import edu.examenRecup.model.Role;
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
        // cardsContainer.getAdmin().clear();}
    }
}

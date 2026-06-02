package edu.examenRecup.controller;

import edu.examenRecup.dao.UserDAO;
import edu.examenRecup.model.Role;
import edu.examenRecup.model.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class AdminViewController extends UserViewController {

    @FXML private FlowPane cardsContainer;
    @FXML private TextField idField;
    @FXML private TextField nombreField;
    @FXML private TextField nicknameField;
    @FXML private TextField emailField;
    @FXML private TextField edadField;
    @FXML private TextField passwordField;
    @FXML private ComboBox<String> roleComboBox;
    @FXML private Label errorLabel;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private Button clearButton;

    @FXML
    public void initialize() {
        userDAO = new UserDAO();

        roleComboBox.setItems(FXCollections.observableArrayList("user", "admin"));
        roleComboBox.setValue("user");

        loadCards();

        addButton.setOnAction(event -> handleAdd());
        updateButton.setOnAction(event -> handleUpdate());
        deleteButton.setOnAction(event -> handleDelete());
        clearButton.setOnAction(event -> handleClear());
    }

    private boolean validarCampos() {
        String nombre = nombreField.getText().trim();
        String nickname = nicknameField.getText().trim();
        String email = emailField.getText().trim();
        String edadText = edadField.getText().trim();
        String password = passwordField.getText().trim();

        if (nombre.isEmpty() || nickname.isEmpty() || email.isEmpty()
                || edadText.isEmpty() || password.isEmpty()) {
            mostrarError("Todos los campos son obligatorios.");
            return false;
        }

        try {
            Integer.parseInt(edadText);
        } catch (NumberFormatException e) {
            mostrarError("La edad debe ser un número.");
            return false;
        }

        return true;
    }

    private void handleAdd() {
        if (!validarCampos()) return;

        User user = new User();
        user.setNombre(nombreField.getText().trim());
        user.setNickname(nicknameField.getText().trim());
        user.setEmail(emailField.getText().trim());
        user.setEdad(Integer.parseInt(edadField.getText().trim()));
        user.setPassword(passwordField.getText().trim());
        user.setRole(Role.valueOf(roleComboBox.getValue()));

        boolean ok = userDAO.createUser(user);
        if (ok) {
            handleClear();
            loadCards();
        } else {
            mostrarError("Error: nickname o email ya existen.");
        }
    }

    private void handleUpdate() {
        if (idField.getText().trim().isEmpty()) {
            mostrarError("Selecciona un usuario para actualizar.");
            return;
        }
        if (!validarCampos()) return;

        User user = new User();
        user.setId(Integer.parseInt(idField.getText().trim()));
        user.setNombre(nombreField.getText().trim());
        user.setNickname(nicknameField.getText().trim());
        user.setEmail(emailField.getText().trim());
        user.setEdad(Integer.parseInt(edadField.getText().trim()));
        user.setPassword(passwordField.getText().trim());
        user.setRole(Role.valueOf(roleComboBox.getValue()));

        boolean ok = userDAO.updateUser(user);
        if (ok) {
            handleClear();
            loadCards();
        } else {
            mostrarError("Error: nickname o email ya existen.");
        }
    }

    private void handleDelete() {
        if (idField.getText().trim().isEmpty()) {
            mostrarError("Selecciona un usuario para eliminar.");
            return;
        }
        int id = Integer.parseInt(idField.getText().trim());
        userDAO.deleteUser(id);
        handleClear();
        loadCards();
    }

    private void handleClear() {
        idField.clear();
        nombreField.clear();
        nicknameField.clear();
        emailField.clear();
        edadField.clear();
        passwordField.clear();
        roleComboBox.setValue("user");
        errorLabel.setVisible(false);
        errorLabel.setManaged(false);
    }

    private void mostrarError(String mensaje) {
        errorLabel.setText(mensaje);
        errorLabel.setVisible(true);
        errorLabel.setManaged(true);
    }

    @Override
    protected VBox buildCard(User user, boolean isYoungest) {
        VBox card = super.buildCard(user, isYoungest);

        card.setOnMouseClicked(event -> {
            idField.setText(String.valueOf(user.getId()));
            nombreField.setText(user.getNombre());
            nicknameField.setText(user.getNickname());
            emailField.setText(user.getEmail());
            edadField.setText(String.valueOf(user.getEdad()));
            passwordField.setText(user.getPassword());
            roleComboBox.setValue(user.getRole().name());
            errorLabel.setVisible(false);
            errorLabel.setManaged(false);
        });

        return card;
    }
}

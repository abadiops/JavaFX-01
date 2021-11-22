package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.Pessoa;
import util.Alerts;
import util.Constraints;

public class ViewController implements Initializable {

	@FXML
	private Button btSomar;
	
	@FXML
	private Button btAllPessoas;

	@FXML
	private TextField txtNumero1;

	@FXML
	private TextField txtNumero2;

	@FXML
	private Label labelResultado;

	@FXML
	private ComboBox<Pessoa> comboBoxPessoa;
	
	private ObservableList<Pessoa> obsList;

	@FXML
	public void onBtAllAction() {
		
		for (Pessoa person : comboBoxPessoa.getItems()) {
			System.out.println(person);
		}
	}

	@FXML
	public void onButtonSomar() {

		try {

			Locale.setDefault(Locale.ENGLISH);

			double numero1 = Double.parseDouble(txtNumero1.getText());
			double numero2 = Double.parseDouble(txtNumero2.getText());
			double soma = numero1 + numero2;

			labelResultado.setText(String.format("%.2f", soma));

		}
		catch (NumberFormatException e) {

			Alerts.showAlert("Error", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	@FXML
	public void onComboBoxPersonAction() {
		
		Pessoa pessoa = comboBoxPessoa.getSelectionModel().getSelectedItem();
		System.out.println(pessoa);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		Constraints.setTextFieldDouble(txtNumero1);
		Constraints.setTextFieldDouble(txtNumero2);

		List<Pessoa> list = new ArrayList<>();
		
		list.add(new Pessoa(1, "Maria", "maria@gmail.com"));
		list.add(new Pessoa(2, "Alex", "alex@gmail.com"));
		list.add(new Pessoa(3, "Box", "bob@gmail.com"));

		obsList = FXCollections.observableArrayList(list);
		comboBoxPessoa.setItems(obsList);

		Callback<ListView<Pessoa>, ListCell<Pessoa>> factory = lv -> new ListCell<Pessoa>() {
			@Override
			protected void updateItem(Pessoa item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getName());
			}
		};

		comboBoxPessoa.setCellFactory(factory);
		comboBoxPessoa.setButtonCell(factory.call(null));
	}
}

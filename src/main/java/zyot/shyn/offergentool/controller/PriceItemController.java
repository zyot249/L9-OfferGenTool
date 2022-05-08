package zyot.shyn.offergentool.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import zyot.shyn.offergentool.pojo.MyComboBoxItem;

import java.net.URL;
import java.util.ResourceBundle;

public class PriceItemController implements Initializable {
    @FXML
    private ComboBox<MyComboBoxItem<String>> payTypeCbBox;
    @FXML
    private ComboBox<MyComboBoxItem<String>> currencyCbBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<MyComboBoxItem<String>> payTypeList = FXCollections.observableArrayList();
        payTypeList.add(new MyComboBoxItem<>("sms", "SMS"));
        payTypeList.add(new MyComboBoxItem<>("ewallet", "Ewallet"));
        payTypeList.add(new MyComboBoxItem<>("iap", "IAP"));
        payTypeCbBox.setItems(payTypeList);
        payTypeCbBox.getSelectionModel().selectFirst();

        ObservableList<MyComboBoxItem<String>> currencyList = FXCollections.observableArrayList();
        currencyList.add(new MyComboBoxItem<>("PHP", "PHP"));
        currencyList.add(new MyComboBoxItem<>("USD", "USD"));
        currencyCbBox.setItems(currencyList);
        currencyCbBox.getSelectionModel().selectFirst();
    }
}

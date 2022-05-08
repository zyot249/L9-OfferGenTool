package zyot.shyn.offergentool.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import zyot.shyn.offergentool.pojo.MyComboBoxItem;

import java.net.URL;
import java.util.ResourceBundle;

public class GiftItemController implements Initializable {
    @FXML
    private ComboBox<MyComboBoxItem<String>> itemNameCbBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<MyComboBoxItem<String>> itemNameList = FXCollections.observableArrayList();
        itemNameList.add(new MyComboBoxItem<>("gold", "Gold"));
        itemNameList.add(new MyComboBoxItem<>("diamond", "Diamond"));
        itemNameList.add(new MyComboBoxItem<>("hammer", "Búa Đập Trứng"));
        itemNameList.add(new MyComboBoxItem<>("candle", "Nến Lucky Parol"));
        itemNameList.add(new MyComboBoxItem<>("ticket", "Vé Board Trip"));
        itemNameList.add(new MyComboBoxItem<>("xuquayso", "Xu Spin Ball"));
        itemNameList.add(new MyComboBoxItem<>("catchfishxu", "Năng lượng Câu Cá"));
        itemNameList.add(new MyComboBoxItem<>("goldCoin", "Xu Vàng Vòng Quay Đôi"));
        itemNameList.add(new MyComboBoxItem<>("silverCoin", "Xu Bạc Vòng Quay Đôi"));
        itemNameList.add(new MyComboBoxItem<>("collectioncardbox_1", "Pack Xám"));
        itemNameList.add(new MyComboBoxItem<>("collectioncardbox_2", "Pack Xanh Lá"));
        itemNameList.add(new MyComboBoxItem<>("collectioncardbox_3", "Pack Xanh Dương"));
        itemNameList.add(new MyComboBoxItem<>("collectioncardbox_4", "Pack Vàng"));
        itemNameCbBox.setItems(itemNameList);
        itemNameCbBox.getSelectionModel().selectFirst();
    }
}

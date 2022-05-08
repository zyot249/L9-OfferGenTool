package zyot.shyn.offergentool.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import zyot.shyn.offergentool.AppState;
import zyot.shyn.offergentool.ToolApplication;
import zyot.shyn.offergentool.offer.GiftObject;
import zyot.shyn.offergentool.offer.OfferObject;
import zyot.shyn.offergentool.offer.OfferOpenType;
import zyot.shyn.offergentool.pojo.MyComboBoxItem;
import zyot.shyn.offergentool.util.TimeUtil;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.ResourceBundle;

public class GenOfferController implements Initializable {
    @FXML
    private Label notifyLabel;
    private OfferObject offerObject;

    // basic info
    @FXML
    private TextField offerIdTxt;
    @FXML
    private TextField priorityTxt;
    @FXML
    private TextField offerNameTxt;
    @FXML
    private TextField displayNameTxt;
    @FXML
    private TextField endowTxt;
    @FXML
    private TextField limitBuyTxt;
    @FXML
    private TextField showHourTxt;
    @FXML
    private TextField countDownBuyHourTxt;
    @FXML
    private TextField countDownNotBuyHourTxt;

    @FXML
    private TextArea offerContentTxt;

    @FXML
    private ComboBox<MyComboBoxItem<Integer>> guiStyleCbBox;
    private ObservableList<MyComboBoxItem<Integer>> guiStyleList;
    @FXML
    private ComboBox<MyComboBoxItem<String>> openTypeCbBox;
    private ObservableList<MyComboBoxItem<String>> openTypeList;
    @FXML
    private ComboBox<MyComboBoxItem<String>> locationCbBox;
    private ObservableList<MyComboBoxItem<String>> locationList;

    @FXML
    private DatePicker openDatePicker;
    @FXML
    private DatePicker endDatePicker;

    // price & gift
    @FXML
    private VBox priceList;
    private int priceNodeNum;

    @FXML
    private VBox giftList;
    private int giftNodeNum;

    // condition
    @FXML
    private TextField purchasedRubyMinTxt;
    @FXML
    private TextField purchasedRubyMaxTxt;
    @FXML
    private TextField gameAgeMinTxt;
    @FXML
    private TextField gameAgeMaxTxt;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComboBox();
        initDatePicker();
        initPricePane();
        initGiftPane();

        offerObject = AppState.getInstance().getSelectedOffer();
        offerIdTxt.setEditable(false);

        initDataOfferInfo();
        initDataOfferRequirement();
        initDataOfferPriceAndGifts();
    }

    private void initDataOfferPriceAndGifts() {
        priceList.getChildren().clear();
        priceNodeNum = 0;
        for (Map.Entry<String, Integer> entry : offerObject.info.priceByTypes.entrySet()) {
            try {
                Node node = FXMLLoader.load(ToolApplication.class.getResource("price-item.fxml"));
                AnchorPane pane = (AnchorPane) node;
                String payType = entry.getKey().split("_")[1];
                String currency = entry.getKey().split("_")[0];
                int cost = entry.getValue();
                for (Node node1 : pane.getChildren()) {
                    if (node1.getId().equals("payTypeCbBox")) {
                        ComboBox<MyComboBoxItem<String>> comboBox = (ComboBox<MyComboBoxItem<String>>) node1;
                        for (MyComboBoxItem<String> item : comboBox.getItems()) {
                            if (item.getId().equals(payType)) {
                                comboBox.getSelectionModel().select(item);
                                break;
                            }
                        }
                    } else if (node1.getId().equals("currencyCbBox")) {
                        ComboBox<MyComboBoxItem<String>> comboBox = (ComboBox<MyComboBoxItem<String>>) node1;
                        for (MyComboBoxItem<String> item : comboBox.getItems()) {
                            if (item.getId().equals(currency)) {
                                comboBox.getSelectionModel().select(item);
                                break;
                            }
                        }
                    } else if (node1.getId().equals("costTxt")) {
                        TextField textField = (TextField) node1;
                        textField.setText(String.valueOf(cost));
                    }
                }

                priceList.getChildren().add(node);
                priceNodeNum++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        giftList.getChildren().clear();
        giftNodeNum = 0;
        for (GiftObject giftObject : offerObject.info.giftList) {
            try {
                Node node = FXMLLoader.load(ToolApplication.class.getResource("gift-item.fxml"));
                AnchorPane pane = (AnchorPane) node;
                String itemName = giftObject.itemId;
                int num = giftObject.num;
                for (Node node1 : pane.getChildren()) {
                    if (node1.getId().equals("itemNameCbBox")) {
                        ComboBox<MyComboBoxItem<String>> comboBox = (ComboBox<MyComboBoxItem<String>>) node1;
                        for (MyComboBoxItem<String> item : comboBox.getItems()) {
                            if (item.getId().equals(itemName)) {
                                comboBox.getSelectionModel().select(item);
                                break;
                            }
                        }
                    } else if (node1.getId().equals("giftNumTxt")) {
                        TextField textField = (TextField) node1;
                        textField.setText(String.valueOf(num));
                    }
                }

                giftList.getChildren().add(node);
                giftNodeNum++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void initDataOfferRequirement() {
        try {
            String[] purchaseRequire = offerObject.requirement.purchasedRuby.split("_");
            if (purchaseRequire.length != 2)
                return;
            String[] gameAgeRequire = offerObject.requirement.gameAge.split("_");
            if (gameAgeRequire.length != 2)
                return;

            int purchaseMin = Integer.parseInt(purchaseRequire[0]);
            int purchaseMax = Integer.parseInt(purchaseRequire[1]);
            int gameAgeMin = Integer.parseInt(gameAgeRequire[0]);
            int gameAgeMax = Integer.parseInt(gameAgeRequire[1]);

            purchasedRubyMinTxt.setText(String.valueOf(purchaseMin));
            purchasedRubyMaxTxt.setText(String.valueOf(purchaseMax));
            gameAgeMinTxt.setText(String.valueOf(gameAgeMin));
            gameAgeMaxTxt.setText(String.valueOf(gameAgeMax));
        } catch (Exception e) {

        }
    }

    private void initDataOfferInfo() {
        offerIdTxt.setText(String.valueOf(offerObject.id));
        priorityTxt.setText(String.valueOf(offerObject.priority));
        offerNameTxt.setText(offerObject.info.name);
        displayNameTxt.setText(offerObject.info.displayName);
        endowTxt.setText(offerObject.info.endow);
        offerContentTxt.setText(offerObject.info.content);
        limitBuyTxt.setText(String.valueOf(offerObject.info.limitPerUser));
        showHourTxt.setText(String.valueOf(offerObject.info.showHour));
        countDownBuyHourTxt.setText(String.valueOf(offerObject.info.buyCountDownHour));
        countDownNotBuyHourTxt.setText(String.valueOf(offerObject.info.notBuyCountDownHour));
        if (offerObject.info.startTime > 0)
            openDatePicker.setValue(TimeUtil.getLocalDate(String.valueOf(offerObject.info.startTime)));
        if (offerObject.info.endTime > 0)
            endDatePicker.setValue(TimeUtil.getLocalDate(String.valueOf(offerObject.info.endTime)));

        for (MyComboBoxItem<Integer> item : guiStyleList) {
            if (item.getId() == offerObject.info.guiStyle) {
                guiStyleCbBox.getSelectionModel().select(item);
                break;
            }
        }
        for (MyComboBoxItem<String> item : openTypeList) {
            if (item.getId().equals(offerObject.openType.name())) {
                openTypeCbBox.getSelectionModel().select(item);
                break;
            }
        }
        for (MyComboBoxItem<String> item : locationList) {
            if (item.getId().equals(offerObject.info.location)) {
                locationCbBox.getSelectionModel().select(item);
                break;
            }
        }

    }

    private void initGiftPane() {
        giftNodeNum = 0;
        onAddGiftButtonClick();
    }

    private void initPricePane() {
        priceNodeNum = 0;
        onAddPriceButtonClick();
    }

    private void initComboBox() {
        guiStyleList = FXCollections.observableArrayList();
        guiStyleList.add(new MyComboBoxItem<>(1, "GUI Midnight"));
        guiStyleList.add(new MyComboBoxItem<>(2, "GUI Jeepney"));
        guiStyleCbBox.setItems(guiStyleList);
        guiStyleCbBox.getSelectionModel().selectFirst();

        openTypeList = FXCollections.observableArrayList();
        openTypeList.add(new MyComboBoxItem<>("OPEN_AT_LOGIN", "Mở lúc user login"));
        openTypeList.add(new MyComboBoxItem<>("OPEN_WHEN_GOLD_LOW", "Mở lúc user hết gold"));
        openTypeCbBox.setItems(openTypeList);
        openTypeCbBox.getSelectionModel().selectFirst();

        locationList = FXCollections.observableArrayList();
        locationList.add(new MyComboBoxItem<>("INT,PH", "Global & Philippines"));
        locationList.add(new MyComboBoxItem<>("PH", "Philippines"));
        locationList.add(new MyComboBoxItem<>("INT", "Global"));
        locationCbBox.setItems(locationList);
        locationCbBox.getSelectionModel().selectFirst();
    }

    private void initDatePicker() {
        openDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            endDatePicker.setDayCellFactory(d -> new DateCell() {
                @Override
                public void updateItem(LocalDate localDate, boolean b) {
                    super.updateItem(localDate, b);
                    setDisable(localDate.isBefore(newValue));
                }
            });
        });
    }

    @FXML
    protected void onAddGiftButtonClick() {
        try {
            Node node = FXMLLoader.load(ToolApplication.class.getResource("gift-item.fxml"));
            giftList.getChildren().add(node);
            giftNodeNum++;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onRemoveGiftButtonClick() {
        if (giftNodeNum > 1) {
            giftList.getChildren().remove(giftNodeNum - 1);
            giftNodeNum--;
        }
    }

    @FXML
    protected void onAddPriceButtonClick() {
        try {
            Node node = FXMLLoader.load(ToolApplication.class.getResource("price-item.fxml"));
            priceList.getChildren().add(node);
            priceNodeNum++;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onRemovePriceButtonClick() {
        if (priceNodeNum > 1) {
            priceList.getChildren().remove(priceNodeNum - 1);
            priceNodeNum--;
        }
    }

    @FXML
    protected void onSubmitButtonClick() {
        if (!updateOfferInfo())
            return;
        if (!updateOfferRequirement())
            return;
        if (!updatePriceAndGifts())
            return;

        AppState.getInstance().updateOfferInfo(offerObject);
        AppState.getInstance().closeSecondWindow();
    }

    private boolean updatePriceAndGifts() {
        for (Node node : priceList.getChildren()) {
            AnchorPane pane = (AnchorPane) node;
            String payType = "";
            String currency = "";
            int cost = 0;
            for (Node node1 : pane.getChildren()) {
                if (node1.getId().equals("payTypeCbBox")) {
                    ComboBox<MyComboBoxItem<String>> comboBox = (ComboBox<MyComboBoxItem<String>>) node1;
                    payType = comboBox.getValue().getId();
                } else if (node1.getId().equals("currencyCbBox")) {
                    ComboBox<MyComboBoxItem<String>> comboBox = (ComboBox<MyComboBoxItem<String>>) node1;
                    currency = comboBox.getValue().getId();
                } else if (node1.getId().equals("costTxt")) {
                    TextField textField = (TextField) node1;
                    cost = Integer.parseInt(textField.getText());
                }
            }
            offerObject.info.priceByTypes.put(currency + "_" + payType, cost);
        }

        for (Node node : giftList.getChildren()) {
            AnchorPane pane = (AnchorPane) node;
            String itemName = "";
            int num = 0;
            for (Node node1 : pane.getChildren()) {
                if (node1.getId().equals("itemNameCbBox")) {
                    ComboBox<MyComboBoxItem<String>> comboBox = (ComboBox<MyComboBoxItem<String>>) node1;
                    itemName = comboBox.getValue().getId();
                } else if (node1.getId().equals("giftNumTxt")) {
                    TextField textField = (TextField) node1;
                    num = Integer.parseInt(textField.getText());
                }
            }
            offerObject.info.giftList.add(new GiftObject(itemName, num));
        }

        return true;
    }

    private boolean updateOfferInfo() {
        try {
            int priority = Integer.parseInt(priorityTxt.getText());
            String offerName = offerNameTxt.getText();
            String displayName = displayNameTxt.getText();
            String endow = endowTxt.getText();
            String content = offerContentTxt.getText();
            int guiStyle = guiStyleCbBox.getValue().getId();
            String openType = openTypeCbBox.getValue().getId();
            String location = locationCbBox.getValue().getId();
            int limitBuy = Integer.parseInt(limitBuyTxt.getText());
            int showHour = Integer.parseInt(showHourTxt.getText());
            int countDownBuyHour = Integer.parseInt(countDownBuyHourTxt.getText());
            int countDownNotBuyHour = Integer.parseInt(countDownNotBuyHourTxt.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            int openDate = Integer.parseInt(openDatePicker.getValue().format(formatter));
            int endDate = Integer.parseInt(endDatePicker.getValue().format(formatter));

            if (offerName.equals("") || displayName.equals("") || endow.equals("") || content.equals(""))
                return false;

            offerObject.openType = OfferOpenType.getTypeOrDefault(openType, OfferOpenType.OPEN_AT_LOGIN);
            offerObject.priority = priority;
            offerObject.active = true;
            offerObject.info.name = offerName;
            offerObject.info.displayName = displayName;
            offerObject.info.content = content;
            offerObject.info.endow = endow;
            offerObject.info.guiStyle = guiStyle;
            offerObject.info.startTime = openDate;
            offerObject.info.endTime = endDate;
            offerObject.info.showHour = showHour;
            offerObject.info.buyCountDownHour = countDownBuyHour;
            offerObject.info.notBuyCountDownHour = countDownNotBuyHour;
            offerObject.info.limitPerUser = limitBuy;
            offerObject.info.location = location;

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean updateOfferRequirement() {
        try {
            int purchaseMin = Integer.parseInt(purchasedRubyMinTxt.getText());
            int purchaseMax = Integer.parseInt(purchasedRubyMaxTxt.getText());
            int gameAgeMin = Integer.parseInt(gameAgeMinTxt.getText());
            int gameAgeMax = Integer.parseInt(gameAgeMaxTxt.getText());

            offerObject.requirement.purchasedRuby = String.format("%d_%d", purchaseMin, purchaseMax);
            offerObject.requirement.gameAge = String.format("%d_%d", gameAgeMin, gameAgeMax);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @FXML
    protected void onCancelButtonClick() {
        AppState.getInstance().closeSecondWindow();
    }
}
package zyot.shyn.offergentool.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import zyot.shyn.offergentool.AppState;
import zyot.shyn.offergentool.ToolApplication;
import zyot.shyn.offergentool.offer.OfferObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {
    @FXML
    private ListView<OfferObject> offerListView;
    @FXML
    private TextArea jsonText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        offerListView.setItems(AppState.getInstance().getOfferList());
        jsonText.setWrapText(true);
//        jsonText.setEditable(false);
        jsonText.setOnMouseClicked(event -> {
                jsonText.selectAll();
        });
        offerListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OfferObject>() {
            @Override
            public void changed(ObservableValue<? extends OfferObject> observableValue, OfferObject offerObject, OfferObject t1) {

            }
        });
    }

    @FXML
    public void onAddOfferButtonClick() {
        TextInputDialog td = new TextInputDialog("enter offer id");
        td.setTitle("Add new offer");
        Optional<String> result = td.showAndWait();
        if (result.isPresent()) {
            try {
                int newOfferId = Integer.parseInt(result.get());
                OfferObject offerObject = new OfferObject(Integer.parseInt(String.valueOf(newOfferId)));
                offerObject.init();
                AppState.getInstance().addNewOffer(offerObject);
            } catch (NumberFormatException e) {

            }
        }
    }

    @FXML
    public void onEditOfferButtonClick() {
        OfferObject offerObject = offerListView.getSelectionModel().getSelectedItem();
        if (offerObject == null)
            return;

        AppState.getInstance().setSelectedOffer(offerObject);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ToolApplication.class.getResource("gen-offer-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1024, 720);
            Stage newWindow = AppState.getInstance().getSecondWindow();
            newWindow.setTitle("Offer Info");
            newWindow.setScene(scene);
            newWindow.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onRemoveOfferButtonClick() {
        OfferObject offerObject = offerListView.getSelectionModel().getSelectedItem();
        AppState.getInstance().removeOffer(offerObject);
    }

    @FXML
    public void onGenJsonButtonClick() {
        try {
            HashMap<Integer, OfferObject> offerList = new HashMap<>();
            for (OfferObject offerObject : AppState.getInstance().getOfferList()) {
                offerList.put(offerObject.id, offerObject);
            }
            Gson gson = new Gson();
            jsonText.setText(gson.toJson(offerList));
        } catch (Exception e) {
            jsonText.setText(e.getMessage());
        }

    }

    @FXML
    public void onParseJsonButtonClick() {
        HashMap<Integer, OfferObject> offerList = new HashMap<>();
        String json = jsonText.getText();
        if (json == null || json.equals(""))
            return;

        Type typeToken = new TypeToken<HashMap<String, OfferObject>>() {
        }.getType();
        Gson gson = new Gson();
        offerList = gson.fromJson(json, typeToken);
        if (offerList != null) {
            AppState.getInstance().setOfferList(new ArrayList<>(offerList.values()));
        }
    }
}

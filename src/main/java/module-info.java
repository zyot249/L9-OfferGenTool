module zyot.shyn.offergentool {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    requires gson;
    requires java.sql;

    opens zyot.shyn.offergentool to javafx.fxml;
    exports zyot.shyn.offergentool;
    exports zyot.shyn.offergentool.controller;
    opens zyot.shyn.offergentool.controller to javafx.fxml;
}
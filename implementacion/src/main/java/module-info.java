module com.oregontrail {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.oregontrail to javafx.fxml;
    opens com.oregontrail.controllers to javafx.fxml;
    opens com.oregontrail.models to javafx.base;
    
    exports com.oregontrail;
    exports com.oregontrail.controllers;
    exports com.oregontrail.models;
    exports com.oregontrail.utils;
}

package utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NavegacionUtil {
    
    public void Navegacion(AnchorPane anchorpane, String url){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) anchorpane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            root.requestFocus();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
}
    
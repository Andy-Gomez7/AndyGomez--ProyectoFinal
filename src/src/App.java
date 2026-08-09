import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage PrimaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/inGame.fxml"));

        Scene scene = new Scene(loader.load());
        scene.getRoot().requestFocus();
        PrimaryStage.setTitle("Flappy Bird");
        PrimaryStage.setScene(scene);
        PrimaryStage.show();
    }
}
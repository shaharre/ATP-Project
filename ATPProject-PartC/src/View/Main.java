package View;

import Model.IModel;
import Model.MyModel;
import ViewModel.MyViewModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyView.fxml"));
//        Parent root = fxmlLoader.load();
//        MyViewController viewController = fxmlLoader.getController();
//        viewController.initialize();

        IModel model = new MyModel();
        model.startServers();
        MyViewModel viewModel = new MyViewModel(model);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyView.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Bunny World");
        Scene scene = new Scene(root, 850, 600);
        primaryStage.setScene(scene);
        MyViewController viewController = fxmlLoader.getController();
        viewController.setResizeEvent(scene);
        viewController.setViewModel(viewModel);
        viewModel.addObserver(viewController);
        viewController.initialize();
        primaryStage.setScene(scene);
        SetStageCloseEvent(primaryStage, model);
        primaryStage.show();
    }
    public void SetStageCloseEvent(Stage primaryStage, IModel model) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent windowEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to exit?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    model.stopServers();
                    primaryStage.close();
                    Platform.exit();
                    System.exit(0);
                } else {
                    windowEvent.consume();
                }
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}

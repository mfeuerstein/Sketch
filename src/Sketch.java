import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class Sketch extends Application {
   public void start(Stage primaryStage) {
      SketchPane pane = new SketchPane();
      Scene scene = new Scene(pane, 400.0D, 400.0D);
      pane.setOnKeyPressed((e) -> {
         if (e.getCode() == KeyCode.DELETE) {
            pane.getChildren().clear();
         }
         if (e.getCode() == KeyCode.Z && e.isControlDown()) {
            pane.undo();
         }
         if (e.getCode() == KeyCode.Y && e.isControlDown()) {
            pane.redo();
         }
         if (e.getCode() == KeyCode.UP && e.isControlDown()) {
            pane.adjustLength(5D);
         } else if (e.getCode() == KeyCode.DOWN && e.isControlDown()) {
            pane.adjustLength(-5D);
         } else {
            if (e.getCode() == KeyCode.UP) {
               pane.sketchLine(0, -1);
            } else if (e.getCode() == KeyCode.DOWN) {
               pane.sketchLine(0, 1);
            } else if (e.getCode() == KeyCode.LEFT) {
               pane.sketchLine(-1, 0);
            } else if (e.getCode() == KeyCode.RIGHT) {
               pane.sketchLine(1, 0);
            }
         }
      });

      primaryStage.setTitle("Sketch");
      primaryStage.setScene(scene);
      primaryStage.show();
      pane.requestFocus();
   }

   public static void main(String[] args) {
      launch(args);
   }
}
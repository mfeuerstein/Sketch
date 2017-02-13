import java.util.Stack;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

@SuppressWarnings("restriction")
public class SketchPane extends Pane {
   private double x = 200.0D;
   private double y = 200.0D;
   private double lineRadius = 5.0D;
   private Stack<Line> undoStack = new Stack<Line>();
   private Stack<Line> redoStack = new Stack<Line>();

   public void sketchLine(int xMod, int yMod) {
      double xTemp = this.x;
      double yTemp = this.y;
      this.moveCursor(xMod, yMod);
      Line sketch = new Line(xTemp, yTemp, this.x, this.y);
      undoStack.push(sketch);
      redoStack.clear();
      this.getChildren().add(sketch);
   }

   public void moveCursor(int xMod, int yMod) {
      this.x += this.lineRadius * (double) xMod;
      this.y += this.lineRadius * (double) yMod;
   }

   public void undo() {
      if (!undoStack.isEmpty()) {
         Line sketch = undoStack.pop();
         redoStack.push(sketch);
         setCurStart(sketch);
         this.getChildren().remove(sketch);
      }
   }

   public void redo() {
      if (!redoStack.isEmpty()) {
         Line sketch = redoStack.pop();
         undoStack.push(sketch);
         setCurEnd(sketch);
         this.getChildren().add(sketch);
      }
   }

   boolean setCurStart(Line line) {
      if (line != null) {
         this.x = line.getStartX();
         this.y = line.getStartY();
         return true;
      } else
         return false;
   }

   boolean setCurEnd(Line line) {
      if (line != null) {
         this.x = line.getEndX();
         this.y = line.getEndY();
         return true;
      } else
         return false;
   }

   public boolean adjustLength(double i) {
      if (i >= -50 && i <= 50) {
         if (lineRadius + i > 0 && lineRadius + i <= 100) {
            lineRadius += i;
            return true;
         }
      }
      return false;
   }
}
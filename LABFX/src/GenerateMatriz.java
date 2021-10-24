import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GenerateMatriz extends JavaFXapp {
	public static GridPane gridpane = new GridPane();
	
	public void execute(Stage primaryStage){
		gridpane.setAlignment(Pos.CENTER);

//		int sizeY = 61;
//		int sizeX = 91;

//		int matriz[][] = new int[sizeY][sizeX];
		for (int y = 0; y < sizeY; y++) {
			int rectAntX = 0;
			for (int x = 0; x < sizeX; x++) {
//        	 try { Thread.sleep (1000); } catch (InterruptedException ex) {}
				Random rnd = new Random();
				int indexNumber = 1;
				Rectangle retanguloTemplete = new Rectangle(5, 5);

				if (x == 0 || y == 0 || y == (sizeY - 1) || (x == (sizeX - 1))) { // setar cor barreira
					retanguloTemplete.setFill(Color.rgb(0, 188, 212));
					indexNumber = 0;
				} else {

					if (x % 2 == 1) {
						if (y % 2 == 1) {
							retanguloTemplete.setFill(Color.WHITE);
							indexNumber = 0;
						} else {

							retanguloTemplete.setFill(Color.rgb(0, 0, 0, 0.1));
							indexNumber = 0;
						}
					} else {
						retanguloTemplete.setFill(Color.rgb(0, 0, 0, 0.1));
						indexNumber = 0;

					}

				}
				if (y == 1 && x == 0) { // if paredes
					retanguloTemplete.setFill(Color.WHITE);
					indexNumber = 0;
				}
				if ((y == (sizeY - 2)) && (x == (sizeX - 1)) || (y == (sizeY - 2)) && (x == (sizeX - 2))
						|| (y == 1) && (x == 1)) { // if entrada e saída
					retanguloTemplete.setFill(Color.WHITE);
					indexNumber = 0;
				}

				matriz[y][x] = indexNumber;
//				System.out.println(matriz[y][x]);
				gridpane.setRowIndex(retanguloTemplete, y);
				gridpane.setColumnIndex(retanguloTemplete, x);
				gridpane.getChildren().add(retanguloTemplete);
			}
		}

//    Button botao1 = new Button("Clique em mim! (Tratador externo)");
//    gridpane.getChildren().addAll( botao1);

		Scene scene = new Scene(gridpane, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Labirinto");
		primaryStage.show();
	}
}

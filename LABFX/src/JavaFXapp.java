/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.*;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.event.Event;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Tauz
 */

public class JavaFXapp extends Application {

	public int sizeRows = 5;
	public int sizeCollumns = 5;

	private Random rnd = new Random();
	private GridPane gridpane = new GridPane();

	public String choose_Orientation(int rows, int collumns) {

		if (rows < collumns) {
			return "VERTICAL";
		} else if (collumns < rows) {
			return "HORIZONTAL";
		} else {
			return rnd.nextInt(2) == 0 ? "HORIZONTAL" : "VERTICAL";
		}

	}

	public void create_Walls(int matriz_grid[][], int rowInitial, int collumnInitial, int rows, int collumns, String orientation) {
		if (collumns < 2 || rows < 2) {
			return;
		}

		boolean isHorizontal = (orientation == "HORIZONTAL");

		// achar coluna aleatória e definir as coordenas X ou Y se for ou não um corte
		// horizontal ou vertical
		int randomCoordRow = rowInitial + (isHorizontal ? rnd.nextInt(rows  - 2) + 1 : 0);
		int randomCoordCollumn = collumnInitial + (isHorizontal ? 0 : rnd.nextInt(collumns - 2) + 1);
		System.out.println("randomCoordRow : " + randomCoordRow + "\nrandomCoordCollumn: " + randomCoordCollumn);
//		int directionGuidanceX = isHorizontal ? 1 : 0;
//		int directionGuidanceY = isHorizontal ? 0 : 1;
System.out.println("collumns: " + collumns);
		int length = isHorizontal ? collumns : rows;
		int squareWhite = isHorizontal ? rnd.nextInt(collumns - 2) + 1 : rnd.nextInt(rows - 2) + 1;
//		char perpendicularDirection = isHorizontal ? 'S' : 'N';
		for (int i = 0; i < length; i++) {

			// Criar rentangulo ciano;
			Rectangle retanguloTemplete = new Rectangle(5, 5);
			retanguloTemplete.setFill(Color.rgb(0, 188, 212));

			// variavel para matriz binaria para uma classe futura
			int indexNumber = 1;

			// Se a coordenada predominante(maior) for a Row...
			if ((randomCoordRow - rowInitial) > (randomCoordCollumn - collumnInitial)) {

				matriz_grid[randomCoordRow][i] = indexNumber;

				// if para criar o quadrado branco no meio
				if (squareWhite == i) {
					retanguloTemplete.setFill(Color.WHITE);
					matriz_grid[randomCoordRow][i] = 0;
				}

				gridpane.setRowIndex(retanguloTemplete, randomCoordRow);
				gridpane.setColumnIndex(retanguloTemplete, i +  collumnInitial);
				gridpane.getChildren().add(retanguloTemplete);
			} else {

				matriz_grid[i][randomCoordCollumn] = indexNumber;

				if (squareWhite == i) {
					retanguloTemplete.setFill(Color.WHITE);
					matriz_grid[i][randomCoordCollumn] = 0;
				}
				gridpane.setRowIndex(retanguloTemplete, i + rowInitial);
				gridpane.setColumnIndex(retanguloTemplete, randomCoordCollumn );
				gridpane.getChildren().add(retanguloTemplete);
			}

		}

//		int nCollumn = isHorizontal ? randomCoordRow - x + 1 : collumns;
//		int nRow = isHorizontal ? rows : randomCoordCollumn - y + 1;

//		create_Walls(matriz_grid, randomCoordRow, randomCoordCollumn, sizeX - randomCoordRow, sizeY, choose_Orientation(sizeX, sizeY));
		if (isHorizontal) {
			System.out.println("hori");
//			create_Walls(matriz_grid, 0, 0, sizeRows - (sizeRows - randomCoordRow -1), sizeCollumns, choose_Orientation(sizeRows - (sizeRows - randomCoordRow -1),sizeCollumns));
//			create_Walls(matriz_grid, 0, 0, sizeRows - (sizeRows - randomCoordRow - 1), sizeCollumns, choose_Orientation(sizeRows - randomCoordCollumn + 1, sizeCollumns));
//			create_Walls(matriz_grid, randomCoordRow + 1, 0, (sizeRows - randomCoordRow - 1), sizeCollumns, choose_Orientation(sizeRows - randomCoordRow + 1, sizeCollumns ));
		} else {
			System.out.println("vert");
			create_Walls(matriz_grid, 0, 0, sizeRows, sizeCollumns - (sizeCollumns - randomCoordCollumn -1), choose_Orientation(sizeRows, sizeCollumns - randomCoordCollumn + 1));
			create_Walls(matriz_grid, 0, randomCoordCollumn + 1, sizeRows, (sizeCollumns - randomCoordCollumn -1), choose_Orientation(sizeRows, sizeCollumns - randomCoordCollumn + 1));
//			create_Walls(matriz_grid, randomCoordCollumn, randomCoord, sizeX , sizeY - randomCoordCollumn , choose_Orientation(sizeX , sizeY - randomCoordCollumn ));

		}

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		int matriz[][] = new int[sizeRows][sizeCollumns];
		gridpane.setAlignment(Pos.CENTER);

		for (int y = 0; y < sizeRows; y++) {
			int rectAntX = 0;
			for (int x = 0; x < sizeCollumns; x++) {
				Rectangle retanguloTemplete = new Rectangle(5, 5);
				int indexNumber = 1;

				if (x == 0 || y == 0 || y == (sizeRows - 1) || (x == (sizeCollumns - 1))) { // setar cor barreira
					retanguloTemplete.setFill(Color.rgb(0, 188, 212));
					indexNumber = 0;
				} else {

					// TODO

				}
				if (y == 1 && x == 0) { // if paredes
					retanguloTemplete.setFill(Color.WHITE);
					indexNumber = 0;
				}
				if ((y == (sizeRows - 2)) && (x == (sizeCollumns - 1))
						|| (y == (sizeRows - 2)) && (x == (sizeCollumns - 2)) || (y == 1) && (x == 1)) { // if
																												// entrada
																												// e
																												// saída
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
		create_Walls(matriz, 0, 0, sizeRows, sizeCollumns, choose_Orientation(sizeRows, sizeCollumns));

		Scene scene = new Scene(gridpane, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Labirinto");
		primaryStage.show();
	}

	public static void main(String[] args) {

		launch(args);
	}

}
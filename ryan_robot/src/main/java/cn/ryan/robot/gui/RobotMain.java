package cn.ryan.robot.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RobotMain extends Application {

	double x1;
	double y1;
	double x_stage;
	double y_stage;

	@Override
	public void start(final Stage primaryStage) throws Exception {
		Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
			}
		});
		StackPane root = new StackPane();
		root.getChildren().add(btn);

		root.setStyle("-fx-background:transparent;-fx-background-image: url(\"img/main_bg.png\")");
		Scene scene = new Scene(root, 150, 240);
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent m) {
				// 计算
				primaryStage.setX(x_stage + m.getScreenX() - x1);
				primaryStage.setY(y_stage + m.getScreenY() - y1);
			}
		});
		scene.setOnDragEntered(null);
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent m) {
				// 按下鼠标后，记录当前鼠标的坐标
				x1 = m.getScreenX();
				y1 = m.getScreenY();
				x_stage = primaryStage.getX();
				y_stage = primaryStage.getY();
			}
		});

		scene.setFill(null);
		// primaryStage.setTitle("Hello World!");
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

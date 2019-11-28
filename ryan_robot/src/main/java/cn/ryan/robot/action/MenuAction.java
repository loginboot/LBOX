package cn.ryan.robot.action;

import cn.ryan.robot.RobotLogin;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;

public class MenuAction implements EventHandler<Event> {

    private String type;

    public MenuAction(String type) {

    }

    public void handle(Event event) {
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                  
                    new RobotLogin().show();
                } catch (Exception e) {

                }
            }
        });

    }

}

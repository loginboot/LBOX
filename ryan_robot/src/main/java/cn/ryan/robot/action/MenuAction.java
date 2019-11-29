package cn.ryan.robot.action;

import cn.ryan.robot.Robot;
import cn.ryan.robot.RobotLogin;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class MenuAction implements EventHandler<Event> {

    private String idType;

    public MenuAction(String idType) {
        this.idType = idType;
    }

    public void handle(Event event) {
        if ("LOGIN".equals(idType)) {
            Platform.runLater(new Runnable() {
                public void run() {
                    try {
                        Stage stg = Robot.stgMap.get(RobotLogin.class.getSimpleName());
                        if (stg == null) {
                            RobotLogin rl = new RobotLogin();
                            rl.show();
                        } else {
                            stg.show();
                            System.out.println("Windows show.");
                        }
                    } catch (Exception e) {

                    }
                }
            });
        } else if ("EXIT".equals(idType)) {
            System.out.println("Eixt system.");
            System.exit(0);
        }

    }

}

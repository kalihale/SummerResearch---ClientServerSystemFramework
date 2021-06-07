package BasicClientServer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ServerGUI extends Application
{
    private Server server;
    private final String BTGRAY = "-fx-background-color: #cbccd1";
    private boolean serverRunning = false;

    @Override
    public void start(Stage primaryStage)
    {

        GridPane serverPane = new GridPane();
        serverPane.setBackground(new Background(new BackgroundFill(Color.BISQUE, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(serverPane);
        primaryStage.setMinHeight(550);
        primaryStage.setMinWidth(550);

        //  <@  Set up text area
        TextArea taDisplay = new TextArea();

        //  <@  Buttons
        Button btStartServer = new Button("Start server");
        Button btUserReg = new Button("Number of users registered");
        Button btUserLog = new Button("Number of users logged in");
        Button btUCon = new Button("Number of users connected");
        Button btWhichCon = new Button("Which users are logged in");
        Button btWhichLock = new Button("Which users are locked out");
        Button btCloseServer = new Button("Shut Down Server");

        btStartServer.setStyle(BTGRAY);
        btUserReg.setStyle(BTGRAY);
        btUserLog.setStyle(BTGRAY);
        btUCon.setStyle(BTGRAY);
        btWhichCon.setStyle(BTGRAY);
        btWhichLock.setStyle(BTGRAY);
        btCloseServer.setStyle(BTGRAY);

        btStartServer.setOnAction(actionEvent ->
                {
                    if (serverRunning == false) {
                        server = new Server();
                        server.start();
                        serverRunning = true;
                    }
                    else{
                        System.out.println("Servers already running stoopid");;
                    }
                }
            );
        btUserReg.setOnAction(actionEvent -> taDisplay.appendText("Number of users registered: " + UserDataBase.getRegisteredUsers() + "\n"));
        btUserLog.setOnAction(actionEvent ->
                taDisplay.appendText("Number of users logged in: " + UserDataBase.getLoggedInNum() + "\n"));
        btUCon.setOnAction(actionEvent -> taDisplay.appendText("Number of users connected: " + server.getConnectedUsers() + "\n"));
        btWhichCon.setOnAction(actionEvent ->
        {
            String users = UserDataBase.getLoggedInUsers();
            String[] userArr = users.split("/o/");
            taDisplay.appendText("Which users are logged in:\n");
            for (String s : userArr)
            {
                taDisplay.appendText(s + "\n");
            }
        });
        btWhichLock.setOnAction(actionEvent ->
        {
            String users = UserDataBase.getLockedUsers();
            String[] userArr = users.split("/o/");
            taDisplay.appendText("Which users are locked out:\n");
            for (String s : userArr)
            {
                taDisplay.appendText(s + "\n");
            }
        });
        btCloseServer.setOnAction(actionEvent ->{
            //server.stop();
            //serverRunning = false;
                }
                /*  <@  Add server end here*/);
        ColumnConstraints column0 = new ColumnConstraints();
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        ColumnConstraints column3 = new ColumnConstraints();
        ColumnConstraints column4 = new ColumnConstraints();
        column0.setPercentWidth(20);
        column1.setPercentWidth(20);
        column2.setPercentWidth(20);
        column3.setPercentWidth(20);
        column4.setPercentWidth(20);
        serverPane.getColumnConstraints().addAll(column0, column1, column2, column3, column4);

        RowConstraints row0 = new RowConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        row0.setPercentHeight(15);
        row1.setPercentHeight(78);
        row2.setPercentHeight(7);
        serverPane.getRowConstraints().addAll(row0, row1, row2);

        serverPane.add(btUserReg, 0, 0);
        serverPane.add(btUserLog, 1, 0);
        serverPane.add(btUCon, 2, 0);
        serverPane.add(btWhichCon, 3, 0);
        serverPane.add(btWhichLock, 4, 0);
        serverPane.add(taDisplay, 1, 1, 3, 1);
        serverPane.add(btStartServer, 0, 2);
        serverPane.add(btCloseServer, 4, 2);

        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

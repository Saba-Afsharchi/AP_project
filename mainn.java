import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sun.misc.Cleaner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;




public class mainn extends Application{
    private ArrayList<Coordinate> player1history = new ArrayList<Coordinate>();
    private ArrayList<Coordinate> player2history = new ArrayList<Coordinate>();
    private static final int MAX_POSITIONS = 1000;
    private int Index_to_be_picked_plyr1;
    private int index_to_be_picked_plyr2;
    Rectangle player1, player2;
    AnimationTimer gameLoop;
    Set<KeyCode> input = new HashSet();
    Pane gameBoard;


    @Override
    public void start(Stage primaryStage) throws Exception {

        System.out.println("welcome!")

        player1 = new Rectangle(200,150,20,20);
        player1.setFill(Color.CORAL);


        final double[][] positionsplayer1 = new double[MAX_POSITIONS][2];
        final double[][] positionsplayer2 = new double[MAX_POSITIONS][2];

        final int[] positionIndexplayer1 = {0};
        positionsplayer1[positionIndexplayer1[0]][0] = player1.getX();
        positionsplayer1[positionIndexplayer1[0]][1] = player1.getY();



        player2 = new Rectangle(100,100,20,20);
        player2.setFill(Color.AQUA);

        final int[] positionIndexplayer2 = {0};
        positionsplayer2[positionIndexplayer2[0]][0] = player2.getX();
        positionsplayer2[positionIndexplayer2[0]][1] = player2.getY();


        gameBoard = new Pane(player1 , player2);
        VBox.setVgrow(gameBoard, Priority.ALWAYS);
        gameBoard.setOnKeyPressed(event -> input.add(event.getCode()));
        gameBoard.setOnKeyReleased(event -> input.remove(event.getCode()));
        gameLoop = new AnimationTimer()
        {
            @Override
            public void handle(long l)
            {
                if (input.contains(KeyCode.UP)) {
                    player1.setY(player1.getY() - 10);
                    if (player1.getY() < 0) {
                        player1.setY(0);
                    }
                }
                else if (input.contains(KeyCode.DOWN)) {
                    player1.setY(player1.getY() + 10);
                    if (player1.getY() + player1.getHeight() > gameBoard.getHeight()) {
                        player1.setY(gameBoard.getHeight() - player1.getHeight());
                    }
                }
                else if (input.contains(KeyCode.RIGHT)) {
                    player1.setX(player1.getX() + 10);
                    if (player1.getX() + player1.getHeight() > gameBoard.getHeight()) {
                        player1.setX(gameBoard.getHeight() - player1.getHeight());
                    }
                }
                else if (input.contains(KeyCode.LEFT)) {
                    player1.setX(player1.getX() - 10);
                    if (player1.getX() + player1.getHeight() > gameBoard.getHeight()) {
                        player1.setX(gameBoard.getHeight() - player1.getHeight());
                    }
                }


                if (input.contains(KeyCode.D)) {
                    player2.setY(player2.getY() + 10);
                    if (player2.getY() < 0) {
                        player2.setY(0);
                    }
                }
                else if (input.contains(KeyCode.E)) {
                    player2.setY(player2.getY() - 10);
                    if (player2.getY() + player2.getHeight() > gameBoard.getHeight()) {
                        player2.setY(gameBoard.getHeight() - player2.getHeight());
                    }
                }
                else if (input.contains(KeyCode.F)) {
                    player2.setX(player2.getX() + 10);
                    if (player2.getX() + player2.getHeight() > gameBoard.getHeight()) {
                        player2.setX(gameBoard.getHeight() - player2.getHeight());
                    }
                }
                else if (input.contains(KeyCode.S)) {
                    player2.setX(player2.getX() - 10);
                    if (player2.getX() + player2.getHeight() > gameBoard.getHeight()) {
                        player2.setX(gameBoard.getHeight() - player2.getHeight());
                    }
                }
                //Player 1
                if (input.contains(KeyCode.LEFT) || input.contains(KeyCode.RIGHT) || input.contains(KeyCode.UP) || input.contains(KeyCode.DOWN)) {
                    positionIndexplayer1[0]++;
                    if (positionIndexplayer1[0] < MAX_POSITIONS) {
                        positionsplayer1[positionIndexplayer1[0]][0] = player1.getX();
                        positionsplayer1[positionIndexplayer1[0]][1] = player1.getY();
                    }
                    int x = (int) player1.getX();
                    int y = (int) player1.getY();
                    Rectangle player1fp = new Rectangle(x, y, 20, 20);
                    player1fp.setFill(player1.getFill());
                    gameBoard.getChildren().add(player1fp);

                    Coordinate player1coordinates = new Coordinate(x, y);
                    if (containListplayer1(x,y)) {
                        javafx.scene.shape.Polygon player1polygon = new Polygon();
                        for (int i = Index_to_be_picked_plyr1; i <= player1history.size() - 1; i++) {
                            Coordinate coordinates = player1history.get(i);
                            ObservableList<Double> points1 = player1polygon.getPoints();
                            for (Coordinate vector : player1history) {
                                points1.addAll((double) coordinates.getX(), (double) coordinates.getY());
                            }

                        }

                        player1polygon.setFill(player1.getFill());
                        gameBoard.getChildren().add(player1polygon);
                        System.out.println("player1 points : " + calculateShoelaceFormula(positionsplayer1,true));
                    }
                    player1history.add(player1coordinates);
                }

                //player2
                if(input.contains(KeyCode.E) || input.contains(KeyCode.S) || input.contains(KeyCode.D) || input.contains(KeyCode.F)){

                    positionIndexplayer2[0]++;
                    if (positionIndexplayer2[0] < MAX_POSITIONS) {
                        positionsplayer2[positionIndexplayer2[0]][0] = player2.getX();
                        positionsplayer2[positionIndexplayer2[0]][1] = player2.getY();
                    }
                    int x = (int) player2.getX();
                    int y = (int) player2.getY();
                    Rectangle player2fp = new Rectangle(x, y, 20, 20);
                    player2fp.setFill(player2.getFill());
                    gameBoard.getChildren().add(player2fp);

                    Coordinate player2coordinates = new Coordinate(x, y);
                    if (containlistplayer2(x,y)) {
                        Polygon player2polygon = new Polygon();
                        for (int i = index_to_be_picked_plyr2; i <= player2history.size() - 1; i++) {
                            Coordinate coordinates = player2history.get(i);
                            ObservableList<Double> points2 = player2polygon.getPoints();
                            for (Coordinate vector : player2history) {
                                points2.addAll((double) coordinates.getX(), (double) coordinates.getY());
                            }

                        }

                        player2polygon.setFill(player2.getFill());
                        gameBoard.getChildren().add(player2polygon);
                        System.out.println("player2 points : " + calculateShoelaceFormula(positionsplayer2,true));

                    }
                    player2history.add(player2coordinates);

                }





            }


        };




        Button btnStartGame = new Button("Play");
        btnStartGame.setMaxWidth(Double.MAX_VALUE);
        btnStartGame.setOnAction((event) -> {
            gameBoard.requestFocus();
            gameLoop.start();
            btnStartGame.setDisable(true);
        });
        VBox root = new VBox(gameBoard,btnStartGame);
        Scene scene = new Scene(root, 500, 500);

        primaryStage.setTitle("welcome to paint i.o. !");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    private boolean containListplayer1(float x, float y) {
        for(Coordinate player1coordinates : player1history){
            Index_to_be_picked_plyr1 = player1history.indexOf(player1coordinates);
            if(player1coordinates.getX()==x & player1coordinates.getY()==y){
                return true;
            }
        }
        return false;
    }
    private boolean containlistplayer2(float q, float z) {
        for(Coordinate player2coordinates : player2history){
            index_to_be_picked_plyr2 = player2history.indexOf(player2coordinates);
            if(player2coordinates.getX()==q & player2coordinates.getY()==z){
                return true;
            }
        }
        return false;
    }
    public static double calculateShoelaceFormula(double[][] polygonBoundary,boolean absoluteValue) {
        int nbCoordinates = polygonBoundary.length;
        int nbSegment = nbCoordinates - 1;

        double[] l = new double[nbSegment];

        for (int i = 0; i < nbSegment; i++) {
            l[i] = (polygonBoundary[i + 1][0] - polygonBoundary[i][0]) * (polygonBoundary[i + 1][1] + polygonBoundary[i][1]);
        }

        double sum = 0.0;
        for (double value : l) {
            sum += value;
        }

        if (absoluteValue) {
            return (int) Math.abs(sum / 2.0);
        } else {
            return (int) (sum / 2.0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
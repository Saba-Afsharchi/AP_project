import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

import static javax.swing.UIManager.get;


public class ThePlayerClass extends Application {

    private  ArrayList<Coordinate> history = new ArrayList<Coordinate>();

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int MAX_POSITIONS = 1000;
    private static int[] intArray;
    private static int[][] intMatrix;
    private int index_to_be_picked;



    @Override
    public void start(Stage primaryStage) throws Exception {

        final Rectangle rectangle = new Rectangle(20,20,Color.BLUE);
        rectangle.setTranslateX(10);
        rectangle.setTranslateY(10);

        final Pane root = new Pane();
        root.getChildren().add(rectangle);
        Scene scene = new Scene(root, 300, 600);

        final double[][] positions = new double[MAX_POSITIONS][2];



        final int[] positionIndex = {0};
        positions[positionIndex[0]][0] = rectangle.getTranslateX();
        positions[positionIndex[0]][1] = rectangle.getTranslateY();






        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                System.out.println(e.getCode());
                if (e.getCode() == KeyCode.LEFT)
                    rectangle.setTranslateX(rectangle.getTranslateX() - 5);

                else if (e.getCode() == KeyCode.RIGHT)
                    rectangle.setTranslateX(rectangle.getTranslateX() + 5);
                else if (e.getCode() == KeyCode.UP)
                    rectangle.setTranslateY(rectangle.getTranslateY() - 5);
                else if (e.getCode() == KeyCode.DOWN) {
                    rectangle.setTranslateY(rectangle.getTranslateY() + 5);
                }


                positionIndex[0]++;
                if (positionIndex[0] < MAX_POSITIONS) {
                    positions[positionIndex[0]][0] = rectangle.getTranslateX();
                    positions[positionIndex[0]][1] = rectangle.getTranslateY();
                }

//                rectangle.translateXProperty().addListener((observable, oldValue, newValue) -> {
//                    if (rectangle.getTranslateX() != oldValue.doubleValue()) {
//                        positionIndex[0]++;
//                        if (rectangle.getTranslateX() == oldValue.doubleValue()) {
//
//                            Polygon polygon = new Polygon();
//                            for (int i = 0; i <= positionIndex[0]; i++) {
//                                for (int j=0 ; j<=positionIndex[1];j++)
//                                {
//                                    polygon.getPoints().addAll(positions[i][j]);
//                                }
//                            }
//                            root.getChildren().add(polygon);
//                        }
//                    }
//                });
                Rectangle rectangle2 = new Rectangle(20,20,Color.BLUE);

                rectangle2.setX(rectangle.getTranslateX());
                rectangle2.setY(rectangle.getTranslateY());
                root.getChildren().add(rectangle2);

                int x = (int) rectangle.getTranslateX();
                int y = (int) rectangle.getTranslateY();


                Coordinate c1 = new Coordinate(x,y);
                if (containList(x,y)) {
//                    System.out.println("didammmmmmmweqqwfrfe34223mmmmmmmm.........");
                    Polygon polygon = new Polygon();
//                    System.out.println("didammmmmmmmmmmmmmm........wqewqeqweqw.");
//                    System.out.println(index_to_be_picked);
//                    System.out.println(history.size());
//                    System.out.println("111111111111111111111111111111111111111.........");
                    for (int i=index_to_be_picked;i<=history.size()-1;i++) {
//                        System.out.println(i);
                        Coordinate coordinates = history.get(i);
                        ObservableList<Double> points = polygon.getPoints();
                        for (Coordinate vector : history) {
                            points.addAll((double) coordinates.getX(), (double) coordinates.getY());
                        }

                    }



                    polygon.setFill(Color.BLUE);
                    root.getChildren().add(polygon);

                }
                history.add(c1);
//                System.out.println(history.size());




//                System.out.println("Shape position: (" + rectangle.getTranslateX() + ", " + rectangle.getTranslateY() + ")");
//                System.out.println("points : " + calculateShoelaceFormula(positions,true));
            }
        });

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
//                System.out.println("All positions:");
                for (int i = 0; i < positionIndex[0]; i++) {
//                    System.out.println(rectangle);
                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean containList(float x, float y) {
        for(Coordinate c1 : history){
            index_to_be_picked = history.indexOf(c1);
            if(c1.getX()==x & c1.getY()==y){
                return true;
            }
        }
        return false;
    }
    @Override
    public void stop() throws Exception {
        // leave this method empty
    }

    @Override
    public void init() throws Exception {
        // leave this method empty
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

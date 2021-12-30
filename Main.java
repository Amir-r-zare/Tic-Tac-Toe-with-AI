import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {


    public static void main(String[] args) throws InterruptedException {
        Game bord = new Game();
        Random rand = new Random();
        Show s = new Show();
        s.start();
        System.out.println();
        s.welcom();
        System.out.println();
        Thread.sleep(500);
        s.guidance();
        System.out.println();
        TimeUnit.SECONDS.sleep(1);
        s.environment();
        bord.ShowTheBord();
        TimeUnit.SECONDS.sleep(1);
        System.out.println();
        s.firstGamer();
        int choice = bord.input.nextInt();
        if (choice == 1) {
            Point point = new Point(rand.nextInt(3), rand.nextInt(3));
            bord.move(point, 1);
            bord.ShowTheBord();
        }
        while (!bord.isGameOver()) {
            System.out.println("Choose location : ");
            Point userMove = new Point(bord.input.nextInt(), bord.input.nextInt());
            bord.move(userMove, 2);
            bord.ShowTheBord();
            if (bord.isGameOver()) break;
            bord.miniMaxAlgorithm(0, 1);
            bord.move(bord.computersMove, 1);
            bord.ShowTheBord();
        }
        if (bord.hasXWon())
            System.out.println("YOU LOST -_- ");
        else if (bord.Win())
            System.out.println("YOU Wiiiiiiin ^_^ ");
        else
            System.out.println("Game finished with out Winner !");
    }
}


class Show {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    void guidance() throws InterruptedException {
        System.out.print(ANSI_WHITE+"Guidance : "+ANSI_RESET);
        Thread.sleep(500);
        System.out.print(". ");
        Thread.sleep(500);
        System.out.print(". ");
        Thread.sleep(500);
        System.out.print(". ");
        System.out.println("");
        Thread.sleep(500);
        System.out.println(ANSI_PURPLE + "0 is Free state" + ANSI_RESET);
        Thread.sleep(500);
        System.out.println(ANSI_YELLOW + "How choose the free state ? " + ANSI_RESET);
        Thread.sleep(500);
        System.out.print("choose the row   ");
        Thread.sleep(500);
        System.out.print("-->   ");
        Thread.sleep(500);
        System.out.print("Press Enter   ");
        Thread.sleep(500);
        System.out.print("-->   ");
        Thread.sleep(500);
        System.out.print("choose the col");
        System.out.println();
        Thread.sleep(500);
        System.out.println("OR");
        Thread.sleep(500);
        System.out.print("choose the row   ");
        Thread.sleep(500);
        System.out.print("-->   ");
        Thread.sleep(500);
        System.out.print("Press Space   ");
        Thread.sleep(500);
        System.out.print("-->   ");
        Thread.sleep(500);
        System.out.print("choose the col");
        System.out.println();
        Thread.sleep(500);
    }

    void start() throws InterruptedException {
        System.out.print("Starting ");
        Thread.sleep(500);
        System.out.print(". ");
        Thread.sleep(500);
        System.out.print(". ");
        Thread.sleep(500);
        System.out.print(". ");
        Thread.sleep(500);
        System.out.println();
    }

    void environment() throws InterruptedException {
        System.out.print("Environment of Game : ");
        Thread.sleep(500);
        System.out.print(". ");
        Thread.sleep(500);
        System.out.print(". ");
        Thread.sleep(500);
        System.out.print(". ");
        System.out.println();
        Thread.sleep(500);
    }

    void firstGamer() throws InterruptedException {
        System.out.print(ANSI_BLUE+"Who should start?  "+ANSI_RESET);
        Thread.sleep(500);
        System.out.print(ANSI_BLUE+"Computer:1   "+ANSI_RESET);
        Thread.sleep(500);
        System.out.print(ANSI_BLUE+"User:2"+ANSI_RESET);
        System.out.println();
        Thread.sleep(500);
    }

    void welcom() throws InterruptedException {
        System.out.println(ANSI_CYAN+" *** Welcome to TIC-TOC-TOE ***"+ANSI_RESET);
        Thread.sleep(500);
    }
}

class Game {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    List<Point> freepoints;
    Scanner input = new Scanner(System.in);
    int[][] TTT_game = new int[3][3];

    public Game() {
    }

    public boolean isGameOver() {
        return (hasXWon() || Win() || getFreeState().isEmpty());
    }

    public boolean hasXWon() {
        if ((TTT_game[0][0] == TTT_game[1][1] && TTT_game[0][0] == TTT_game[2][2] && TTT_game[0][0] == 1) ||
                (TTT_game[0][2] == TTT_game[1][1] && TTT_game[0][2] == TTT_game[2][0] && TTT_game[0][2] == 1)) {
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((TTT_game[i][0] == TTT_game[i][1] && TTT_game[i][0] == TTT_game[i][2] && TTT_game[i][0] == 1)
                    || (TTT_game[0][i] == TTT_game[1][i] && TTT_game[0][i] == TTT_game[2][i] && TTT_game[0][i] == 1))) {
                return true;
            }
        }
        return false;
    }

    public boolean Win() {
        if ((TTT_game[0][0] == TTT_game[1][1] && TTT_game[0][0] == TTT_game[2][2] && TTT_game[0][0] == 2) ||
                (TTT_game[0][2] == TTT_game[1][1] && TTT_game[0][2] == TTT_game[2][0] && TTT_game[0][2] == 2)) {
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((TTT_game[i][0] == TTT_game[i][1] && TTT_game[i][0] == TTT_game[i][2] && TTT_game[i][0] == 2)
                    || (TTT_game[0][i] == TTT_game[1][i] && TTT_game[0][i] == TTT_game[2][i] && TTT_game[0][i] == 2)) {
                return true;
            }
        }
        return false;
    }

    public List<Point> getFreeState() {
        freepoints = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (TTT_game[i][j] == 0) {
                    freepoints.add(new Point(i, j));
                }
            }
        }
        return freepoints;
    }

    public void move(Point point, int gamer) {
        TTT_game[point.i][point.j] = gamer;
    }

    void getUserChoose() {
        System.out.println(ANSI_YELLOW+"Your choose: "+ANSI_RESET);
        int row = input.nextInt();
        int col = input.nextInt();
        Point point = new Point(row, col);
        move(point, 2);
    }

    public void ShowTheBord() {
        System.out.println();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.print(ANSI_PURPLE+TTT_game[i][j]+ANSI_RESET);
                if (j != 2)
                    System.out.print(ANSI_PURPLE+" | "+ANSI_RESET);
            }
            System.out.println();
            if (i != 2)
                System.out.println("---------");
        }
    }

    Point computersMove;

    public int miniMaxAlgorithm(int depth, int t) {
        if (hasXWon() == true)
            return +1;
        if (Win() == true)
            return -1;
        List<Point> pointsAvailable = getFreeState();
        if (pointsAvailable.isEmpty() == true)
            return 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < pointsAvailable.size(); ++i) {
            Point point = pointsAvailable.get(i);
            if (t == 1) {
                move(point, 1);
                int currentScore = miniMaxAlgorithm(depth + 1, 2);
                max = Math.max(currentScore, max);
                if (depth == 0)
                    System.out.println("Score for location " + (i + 1) + " = " + currentScore);
                if (currentScore >= 0) {
                    if (depth == 0) computersMove = point;
                }
                if (currentScore == 1) {
                    TTT_game[point.i][point.j] = 0;
                    break;
                }
                if (i == pointsAvailable.size() - 1 && max < 0) {
                    if (depth == 0) computersMove = point;
                }
            } else if (t == 2) {
                move(point, 2);
                int currentScore = miniMaxAlgorithm(depth + 1, 1);
                min = Math.min(currentScore, min);
                if (min == -1) {
                    TTT_game[point.i][point.j] = 0;
                    break;
                }
            }
            TTT_game[point.i][point.j] = 0;
        }
        return t == 1 ? max : min;
    }
}

class Point {
    int i, j;

    public Point(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        return "[" + i + ", " + j + "]";
    }
}

class PointAndScore {
    int score;
    Point point;

    PointAndScore(int score, Point point) {
        this.score = score;
        this.point = point;
    }
}





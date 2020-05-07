package pl.pagowski.mikolaj;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("set first player name: ");
        String player1 = sc.nextLine();
        System.out.println("Welcome in game: " + player1);
        System.out.println(" ------ ");

        System.out.println("set second player name: ");
        String player2 = sc.nextLine();
        System.out.println("Welcome in game: " + player2);
        System.out.println(" ------ ");

        int[][] player1Board = new int[10][10];
        int[][] player2Board = new int[10][10];

        // loop for set ship location
        System.out.println("how to set properly location: 'x y'");
        for (int i = 0; i < 5; i++) {
            Position p1 = ask4ShipPosition(player1, sc, player1Board, "set location: ", true);
            player1Board[p1.getX()][p1.getY()] = 1;
        }
        drawBoard(player1Board);

        System.out.println("how to set properly location: 'x y'");
        for (int i = 0; i < 5; i++) {
            Position p2 = ask4ShipPosition(player2, sc, player2Board, "set location", true);
            player2Board[p2.getX()][p2.getY()] = 1;
        }
        drawBoard(player2Board);

        letsPlay();

        // you've got only 5 ships
        while (true) {
            int numberOfShipsPlayer1 = 5;
            int numberOfShipsPlayer2 = 5;


            while (true) {
                Position position = ask4ShipPosition(player1, sc, player1Board, "shooting position: ", false);
                if (player2Board[position.getX()][position.getY()] == 0) {
                    System.out.println(" you've MISSED your shot");
                    drawBoard(player2Board);
                    break;
                } else if (player2Board[position.getX()][position.getY()] == 1) {
                    System.out.println("HIT! Nice, you got one shot left");
                    player2Board[position.getX()][position.getY()] = 2;
                    numberOfShipsPlayer2--;
                    System.out.println(numberOfShipsPlayer2 + " ships left to sunk!");
                    drawBoard(player2Board);
                } else {
                    System.out.println("you've just hit the same ship..");
                }
                if (numberOfShipsPlayer2 == 0) {
                    System.out.println("END OF TE GAME, YOU WIN");
                    break;
                }
            }


            while (true) {
                Position position = ask4ShipPosition(player2, sc, player2Board, "shooting position: ", false);
                if (player1Board[position.getX()][position.getY()] == 0) {
                    System.out.println(" you've MISSED your shot");
                    drawBoard(player1Board);
                    break;
                } else if (player1Board[position.getX()][position.getY()] == 1) {
                    System.out.println("HIT! Nice, you got one more shot");
                    player1Board[position.getX()][position.getX()] = 2;
                    numberOfShipsPlayer1--;
                    System.out.println(numberOfShipsPlayer1 + " ships left to sunk!");
                    drawBoard(player1Board);
                } else {
                    System.out.println("you've just hit the same ship..");
                }
                if (numberOfShipsPlayer1 == 0) {
                    System.out.println("END OF TE GAME, YOU WIN");
                    break;
                }
            }
        }
    }

    private static void letsPlay() {
        System.out.println(" -------- ");
        System.out.println(" LET THE GAME BEGIN ");
        System.out.println(" -------- ");
    }

    private static Position ask4ShipPosition(String player, Scanner sc, int[][] playerBoard, String message, boolean ship) {
        Position position = null;
        while (position == null) {
            System.out.println(player + " " + message);
            String shipPosition = sc.nextLine();
            String[] location = shipPosition.split(" ");
            if (location.length != 2) {
                System.out.println("Wrong position!");
            } else {
                int line = Integer.parseInt(location[0]);
                int column = Integer.parseInt(location[1]);

                // first = 0;
                // last = 9;
                boolean wrongLineAndColumn = line < 0 || line > 9 || column < 0 || column > 9;
                if (wrongLineAndColumn) {
                    System.out.println("You've selected wrong position!");
                } else if (ship && playerBoard[line][column] != 0) {
                    System.out.println("You've selected wrong position!");
                } else {
                    position = new Position(line, column);
                }
            }
        }
        return position;
    }

    // 0 - ship field
    // 1 - ship
    // 2 - hit & sink

    private static void drawBoard(int[][] player1Board) {
        for (int i = 0; i < player1Board.length; i++) {
            for (int j = 0; j < player1Board.length; j++) {
                System.out.print(player1Board[i][j] == 0 ? "0" : (player1Board[i][j] == 1 ? "1" : "2"));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}

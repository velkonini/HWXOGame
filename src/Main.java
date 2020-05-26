import java.util.Random;
import java.util.Scanner;

public class Main {

    static final int SIZE = 3;
    static final int TO_WIN = 3;

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;

    private static char[][] field = new char[SIZE][SIZE];

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true){
            humanTurn();
            printMap();

            if(checkWin(DOT_X)){
                System.out.println("Вы победили!");
                break;
            }

            if(isFull()){
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();

            if(checkWin(DOT_O)){
                System.out.println("Компьютер выиграл!");
                break;
            }

            if(isFull()){
                System.out.println("�����");
                break;
            }
        }
    }
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void setSym(int y, int x, char sym) {
        field[y][x] = sym;
    }

    public static void humanTurn() {
        int x, y;

        do {
            System.out.println("Введите координаты Вашего хода X Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(y, x));
        map[y][x] = DOT_X;
    }

    public static void aiTurn() {
        int x, y;

        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(y, x));
        map[y][x] = DOT_O;
    }



    public static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static boolean isFull(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(map[i][j] == DOT_EMPTY){
                    return false;
                }
            }
        }
        return true;
    }


    private static boolean checkWin(char sym) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkLine(i, j, 0, 1,  sym)) return true;
                if (checkLine(i, j, 1, 1,  sym)) return true;
                if (checkLine(i, j, 1, 0,  sym)) return true;
                if (checkLine(i, j, -1, 1, sym)) return true;
            }
        }
        return false;
    }

    private static boolean checkLine(int y, int x, int wy, int wx, char sym) {
        int wayX = x + (TO_WIN - 1) * wx;
        int wayY = y + (TO_WIN - 1) * wy;
        if (wayX < 0 || wayY < 0 || wayX > SIZE - 1 || wayY > SIZE - 1) return false;
        for (int i = 0; i < TO_WIN; i++) {
            int itemY = y + i * wy;
            int itemX = x + i * wx;
            if (field[itemY][itemX] != sym) return false;
        }
        return true;
    }



}



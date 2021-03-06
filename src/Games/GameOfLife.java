package Games;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameOfLife {
    private char[][] grid;
    File file;
    int gridWidth;
    int gridLength;

    public GameOfLife(){
        this.file = new File("src/Games/input.txt");
        this.gridWidth = 0;
        this.gridLength = 0;
    }

    public void play(){
        Scanner input = new Scanner(System.in);
        int generation = 1;

        System.out.println("Press enter to generate generations");
        String showNextGeneration = input.nextLine();

        grid = getGridSize(declareScanner(file));
        grid = setValues(declareScanner(file));

        while (showNextGeneration.equals("")){
            System.out.println("Generation number " + (generation) + ":");
            printArray(grid);
            grid = createNewGeneration(grid);
            System.out.println("Press enter for new generation (press any other key to end the game)");
            showNextGeneration = input.nextLine();
            generation++;
        }
    }

    private char[][] setValues(Scanner scanner) {
        String line;
        char[] array;
        int length = 0;

        while (scanner.hasNextLine()){
            line = scanner.nextLine();
            array = line.toCharArray();
            grid[length] = array;
            length++;
        }
        return grid;
    }

    private char[][] getGridSize(Scanner scanner) {
        while (scanner.hasNextLine()){
            String firstLine = scanner.nextLine();
            gridWidth = firstLine.length();
            gridLength++;
        }
        return new char[gridLength][gridWidth];
    }

    public Scanner declareScanner(File file){
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return scanner;
    }

    private void printArray(char[][] arrayToPrint) {
        for (char[] chars : arrayToPrint) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
        System.out.println();
    }

    private char[][] createNewGeneration(char[][] oldGeneration){
        char[][] newArray = new char[gridLength][gridWidth];

        for (int i = 0; i < oldGeneration.length; i++) {
            for (int j = 0; j < oldGeneration[i].length; j++) {
                int surroundingLiving = 0;

                // cell before
                if (j > 0){
                    if (oldGeneration[i][j-1] == '#'){
                        surroundingLiving++;
                    }
                }

                // cell after
                if (j < 99){
                    if (oldGeneration[i][j+1] == '#'){
                        surroundingLiving++;
                    }
                }

                // cells above
                if (i > 0){
                    if (j > 0){
                        if (oldGeneration[i-1][j-1] == '#'){
                            surroundingLiving++;
                        }
                    }
                    if (oldGeneration[i-1][j] == '#'){
                        surroundingLiving++;
                    }
                    if (j < 99){
                        if (oldGeneration[i-1][j+1] == '#'){
                            surroundingLiving++;
                        }
                    }
                }

                // cells beneath
                if (i < 99){
                    if (j > 0){
                        if (oldGeneration[i + 1][j - 1] == '#') {
                            surroundingLiving++;
                        }
                    }
                    if (oldGeneration[i + 1][j] == '#') {
                        surroundingLiving++;
                    }
                    if (j < 99) {
                        if (oldGeneration[i + 1][j + 1] == '#') {
                            surroundingLiving++;
                        }
                    }
                }

                // fill new char[][]
                if (surroundingLiving == 2 || surroundingLiving == 3){
                    newArray[i][j] = '#';
                } else {
                    newArray[i][j] = '.';
                }
            }
        }
        return newArray;
    }
}

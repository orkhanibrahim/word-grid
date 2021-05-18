package com.orxanibrahim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Grid {
    private final int gridSize;
    char[][] contents;
    private final List<Coordinate> coordinates = new ArrayList<>();

    private class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private enum Direction{
        HORIZONTAL,
        VERTICAL,
        DIAGONAL
    }

    public Grid(int gridSize) {
        contents = new char[gridSize][gridSize];
        this.gridSize = gridSize;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                coordinates.add(new Coordinate(i, j));
                contents[i][j] = '_';
            }
        }
    }

    public void fillGrid(List<String> words) {
        Collections.shuffle(coordinates);
        for (String word : words) {
            for (Coordinate coordinate : coordinates) {
                int x = coordinate.x;
                int y = coordinate.y;
                Direction selectedDirection = getDirectionForFit(word,coordinate);
                if (selectedDirection != null) {
                    switch (selectedDirection){
                        case HORIZONTAL:
                            for (char c : word.toCharArray()) {
                                contents[x][y++] = c;
                            }
                            break;
                        case VERTICAL:
                            for (char c : word.toCharArray()) {
                                contents[x++][y] = c;
                            }
                            break;
                        case DIAGONAL:
                            for (char c : word.toCharArray()) {
                                contents[x++][y++] = c;
                            }
                            break;
                    }
                    break;
                }

            }
        }
    }

    public void displayGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(contents[i][j] + " ");
            }
            System.out.println("");
        }
    }

    private Direction getDirectionForFit(String word, Coordinate coordinate) {
        List<Direction> directionList = Arrays.asList(Direction.values());
        Collections.shuffle(directionList);
        for(Direction direction:directionList){
            if(doesFit(word,coordinate,direction)){
                return direction;
            }
        }
        return null;
    }

    private boolean doesFit(String word, Coordinate coordinate,Direction direction){
        int wordLength = word.length();

        switch (direction){
            case HORIZONTAL:
                if(coordinate.y+ wordLength >gridSize)return false;
                for(int i=0;i< wordLength;i++){
                    if(contents[coordinate.x][coordinate.y+i]!='_')return false;
                }
                break;
            case VERTICAL:
                if(coordinate.x+ wordLength >gridSize)return false;
                for(int i=0;i< wordLength;i++){
                    if(contents[coordinate.x+i][coordinate.y]!='_')return false;
                }
                break;
            case DIAGONAL:
                if(coordinate.y+ wordLength >gridSize||coordinate.x+ wordLength >gridSize)return false;
                for(int i=0;i< wordLength;i++){
                    if(contents[coordinate.x+i][coordinate.y+i]!='_')return false;
                }
                break;
        }
        return true;
    }
}

package com.orxanibrahim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
        for (String word : words) {
            Collections.shuffle(coordinates);

            for (Coordinate coordinate : coordinates) {
                int x = coordinate.x;
                int y = coordinate.y;
                if (doesFit(word,coordinate)) {
                    for (char c : word.toCharArray()) {
                        contents[x][y++] = c;
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

    private boolean doesFit(String word, Coordinate coordinate) {
        if (coordinate.y + word.length() < gridSize) {
            for (int i = 0; i < word.length(); i++) {
                if (contents[coordinate.x][coordinate.y + i] != '_') return false;
            }
            return true;
        }
        return false;
    }
}

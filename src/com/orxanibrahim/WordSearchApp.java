package com.orxanibrahim;

import java.util.Arrays;
import java.util.List;

public class WordSearchApp {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("one","two","three","four");
        Grid grid = new Grid(10);
        grid.fillGrid(words);
        grid.displayGrid();



    }
}

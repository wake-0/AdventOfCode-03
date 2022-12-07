package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.run();
    }

    record Rucksack(String firstCompartment, String secondCompartment, String thirdCompartment) {

        int getValue() {
            for (char c : firstCompartment.toCharArray()) {
                if (secondCompartment.indexOf(c) != -1 && thirdCompartment.indexOf(c) != -1) {
                    // Char found
                    int ascii = c;
                    return Character.isUpperCase(c) ? ascii - 65 + 27 : ascii - 97 + 1;
                }
            }

            throw new UnsupportedOperationException();
        }
    }

    final String path = "TODO";

    final List<Rucksack> rucksacks = new ArrayList<>();

    void run() throws IOException {
        this.readRucksacks();
        this.printValues();
    }

    void readRucksacks() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path + "input.txt"))) {
            String line;
            String first = null;
            String second = null;
            while ((line = br.readLine()) != null) {

                if (first == null) {
                    first = line;
                    continue;
                }

                if (second == null) {
                    second = line;
                    continue;
                }

                Rucksack rucksack = new Rucksack(first, second, line);
                rucksacks.add(rucksack);

                // reset compartments
                first = null;
                second = null;
            }
        }
    }

    void printValues() {
        System.out.println(this.rucksacks.stream().map(Rucksack::getValue).reduce(Integer::sum).get());


    }
}
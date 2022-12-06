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

    record Rucksack(String content) {

        String getFirstCompartment() {
            int length = content.length();
            return content.substring(0, length / 2);
        }

        String getSecondCompartment() {
            int length = content.length();
            return content.substring(length / 2, length);
        }

        int getValue() {
            String firstCompartment = this.getFirstCompartment();
            String secondCompartment = this.getSecondCompartment();

            for (char c : firstCompartment.toCharArray()) {
                if (secondCompartment.indexOf(c) != -1) {
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
            while ((line = br.readLine()) != null) {
                rucksacks.add(new Rucksack(line));
            }
        }
    }

    void printValues() {
        System.out.println(this.rucksacks.stream().map(Rucksack::getValue).reduce(Integer::sum).get());


    }
}
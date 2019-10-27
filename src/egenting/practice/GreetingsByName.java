/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egenting.practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Tan Chek Wei
 */
public class GreetingsByName {

    static ArrayList<String> boyNames = new ArrayList<>();
    static ArrayList<String> girlNames = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String nameFile = args[0];
        String boyNameFile = args[1];
        String girlNameFile = args[2];

        try (BufferedReader br = new BufferedReader(new FileReader(boyNameFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                boyNames.add(line);
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(girlNameFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                girlNames.add(line);
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(nameFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String fullName = capitalize(line);

                int indexOfOpenBracket = line.indexOf("[");
                int indexOfCloseBracket = line.indexOf("]");
                String surname = line.substring(indexOfOpenBracket + 1, indexOfCloseBracket);
                surname = capitalize(surname);
                
                System.out.println("To: " + fullName);
                System.out.print("Wishing you a Merry Christmas, ");
                if(gender(fullName).equals("boy")){
                    System.out.print("Mr " + surname + ".\n");
                } else if (gender(fullName).equals("girl")){
                    System.out.print("Ms " + surname + ".\n");
                } else {
                    System.out.print("Undefined " + surname + ".\n");
                }
            }
        }
    }

    public static String capitalize(String line) {
        line = line.replace("[", "").replace("]", "");
        String[] words = line.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }
        return String.join(" ", words);
    }

    public static String gender(String capitalizedLine) {
        String[] words = capitalizedLine.split(" ");
        for (String word : words) {
            if (boyNames.contains(word)) {
                return "boy";
            } else if (girlNames.contains(word)){
                return "girl";
            }
        }
        return "undefined";
    }
}

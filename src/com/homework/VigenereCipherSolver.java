package com.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VigenereCipherSolver {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) throws IOException {
        List<Character> alphabet = ALPHABET.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input encrypted text");
        String encryption = reader.readLine();

        Map<Character, Double> commonFrequency = getCommonLetterFrequencies();

        List<Integer> valuesPlace = separateEncryption(encryption, 5).values().stream()
                .map(separatedValue ->
                        calculateFrequencySum(alphabet, findFrequency(countLetters(separatedValue), separatedValue.length()), commonFrequency))
                .map(entryset -> findMaxValue(entryset).getKey()).collect(Collectors.toList());

        StringBuilder key = new StringBuilder();
        for (int keyElement : valuesPlace) {
            key.append(alphabet.get(keyElement));
        }

        System.out.println(key.toString());
        decrypt(encryption, key.toString());

        putGapsIntoText(decrypt(encryption, key.toString()));
    }

    public static void findCommon(String first, String second) {
        int sameLetter = 0;
        for (int i = 0; i < first.length(); i++) {
            if (i < second.length() && first.charAt(i) == second.charAt(i)) {
                System.out.println("" + first.charAt(i) + second.charAt(i));
                ++sameLetter;
            }
        }
        System.out.println(sameLetter);
    }

    public static Map<Character, Integer> countLetters(String code) {
        Map<Character, Integer> letters = new HashMap<>();
        for (char c : code.toCharArray()) {
            if (letters.containsKey(c)) {
                letters.put(c, letters.get(c) + 1);
            } else {
                letters.put(c, 1);
            }
        }
        System.out.println(letters);
        return letters;
    }

    public static Map<Character, Double> findFrequency(Map<Character, Integer> letters, double length) {
        Map<Character, Double> frequency = new HashMap<>();
        for (char key : letters.keySet()) {
            frequency.put(key, letters.get(key) / length);
        }
        System.out.println(frequency);
        return frequency;
    }

    public static Map<Integer, Double> calculateFrequencySum(List<Character> alphabet, Map<Character, Double> codeFrequency,
                                                             Map<Character, Double> commonFrequency) {
        Map<Integer, Double> sums = new HashMap<>();
        double sum = 0.0;
        List<Character> movingList = new ArrayList<>(alphabet);
        int position = 0;
        do {
            for (int i = 0; i < alphabet.size(); i++) {
                char alphabetLetter = alphabet.get(i);
                if (!codeFrequency.containsKey(alphabetLetter)) {
                    codeFrequency.put(alphabetLetter, 0.0);
                }
                sum = sum + (codeFrequency.get(movingList.get(i)) * commonFrequency.get(alphabet.get(i)));
            }

            sums.put(position, sum);
            sum = 0;
            position++;
            movingList.add(movingList.get(0));
            movingList.remove(0);
        } while (movingList.get(0) != "A".charAt(0));
        System.out.println(sums);
        return sums;
    }

    public static String decrypt(String code, String key) {
        StringBuilder message = new StringBuilder();
        int charPosition = 0;
        for (char ch : code.toCharArray()) {
            char toAdd = (char) ((ch - key.charAt(charPosition) + 26) % 26 + 65);
            message.append(toAdd);
            charPosition = ++charPosition % key.length();
        }
        System.out.println(message.toString());
        return message.toString();
    }

    public static Map.Entry<Integer, Double> findMaxValue(Map<Integer, Double> value) {
        Map.Entry<Integer, Double> maxEntry = null;

        for (Map.Entry<Integer, Double> entry : value.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }

        System.out.println("Max Entry: " + maxEntry);
        return maxEntry;
    }

    public static Map<Integer, String> separateEncryption(String encryption, int keyLength) {
        Map<Integer, String> values = new HashMap<>();
        for (int i = 0; i < encryption.length(); i++) {
            if (i * keyLength <= encryption.length() - 1) {
                for (int y = 0; y < keyLength; y++) {
                    char valueChar = encryption.substring(y).charAt(i * keyLength);
                    if (values.containsKey(y)) {
                        values.put(y, values.get(y) + valueChar);
                    } else {
                        values.put(y, Character.toString(valueChar));
                    }
                }
            } else {
                break;
            }
        }
        System.out.println(values);
        return values;
    }

    public static String putGapsIntoText(String word) {
        String val = "5";
        String result = word.replaceAll("(.{" + val + "})", "$1 ").trim();
        System.out.println(result);
        return result;
    }

    public static Map<Character, Double> getCommonLetterFrequencies() {
        Map<Character, Double> commonFrequency = new HashMap<>();
        commonFrequency.put("E".charAt(0), 12.02);
        commonFrequency.put("T".charAt(0), 9.10);
        commonFrequency.put("A".charAt(0), 8.12);
        commonFrequency.put("O".charAt(0), 7.68);
        commonFrequency.put("I".charAt(0), 7.31);
        commonFrequency.put("N".charAt(0), 6.95);
        commonFrequency.put("S".charAt(0), 6.28);
        commonFrequency.put("R".charAt(0), 6.02);
        commonFrequency.put("H".charAt(0), 5.92);
        commonFrequency.put("D".charAt(0), 4.32);
        commonFrequency.put("L".charAt(0), 3.98);
        commonFrequency.put("U".charAt(0), 2.88);
        commonFrequency.put("C".charAt(0), 2.71);
        commonFrequency.put("M".charAt(0), 2.61);
        commonFrequency.put("F".charAt(0), 2.30);
        commonFrequency.put("Y".charAt(0), 2.11);
        commonFrequency.put("W".charAt(0), 2.09);
        commonFrequency.put("G".charAt(0), 2.03);
        commonFrequency.put("P".charAt(0), 1.82);
        commonFrequency.put("B".charAt(0), 1.49);
        commonFrequency.put("V".charAt(0), 1.11);
        commonFrequency.put("K".charAt(0), 0.69);
        commonFrequency.put("X".charAt(0), 0.17);
        commonFrequency.put("Q".charAt(0), 0.11);
        commonFrequency.put("J".charAt(0), 0.10);
        commonFrequency.put("Z".charAt(0), 0.07);

        return commonFrequency;
    }
}

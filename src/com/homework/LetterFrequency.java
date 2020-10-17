package com.homework;

public enum LetterFrequency {
    E(12.02, "E"),
    T(9.10, "T"),
    A(8.12, "A"),
    O(7.68, "O"),
    I(7.31, "I"),
    N(6.95, "N"),
    S(6.28, "S"),
    R(6.02, "R"),
    H(5.92, "H"),
    D(4.32, "D"),
    L(3.98, "L"),
    U(2.88, "U"),
    C(2.71, "C"),
    M(2.61, "M"),
    F(2.30, "F"),
    Y(2.11, "Y"),
    W(2.09, "W"),
    G(2.03, "G"),
    P(1.82, "P"),
    B(1.49, "B"),
    V(1.11, "V"),
    K(0.69, "K"),
    X(0.17, "X"),
    Q(0.11, "Q"),
    J(0.10, "J"),
    Z(0.07, "Z");

    private double frequency;
    private char letter;

    LetterFrequency(double frequency, String letter) {
        this.frequency = frequency;
        this.letter = letter.charAt(0);
    }

    public double getFrequency() {
        return frequency;
    }

    public char getLetter() {
        return letter;
    }

}

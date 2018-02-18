package com.edgeboard.edgeboard.drawing.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Krystian on 2017-12-16.
 */
public enum Alphabet {
    A(Arrays.asList(CornerType.BOT_LEFT, CornerType.TOP_LEFT, CornerType.BOT_RIGHT), 'a'),
    B(Arrays.asList(CornerType.TOP_LEFT, CornerType.BOT_LEFT, CornerType.BOT_RIGHT, CornerType.BOT_LEFT), 'b'),
    C(Arrays.asList(CornerType.TOP_RIGHT, CornerType.BOT_LEFT, CornerType.BOT_RIGHT), 'c'),
    D(Arrays.asList(CornerType.TOP_RIGHT, CornerType.BOT_RIGHT, CornerType.BOT_LEFT, CornerType.BOT_RIGHT), 'd'),
    E(Arrays.asList(CornerType.TOP_RIGHT, CornerType.TOP_LEFT, CornerType.BOT_LEFT, CornerType.BOT_RIGHT), 'e'),
    F(Arrays.asList(CornerType.TOP_RIGHT, CornerType.TOP_LEFT, CornerType.BOT_LEFT), 'f'),
    G(Arrays.asList(CornerType.TOP_RIGHT, CornerType.TOP_LEFT, CornerType.BOT_LEFT, CornerType.BOT_RIGHT, CornerType.BOT_LEFT), 'g'),
    H(Arrays.asList(CornerType.TOP_LEFT, CornerType.BOT_LEFT, CornerType.TOP_RIGHT, CornerType.BOT_RIGHT), 'h'),
    I(Arrays.asList(CornerType.TOP_LEFT, CornerType.BOT_LEFT), 'i'),
    J(Arrays.asList(CornerType.TOP_RIGHT, CornerType.BOT_RIGHT, CornerType.BOT_LEFT), 'j'),
    K(Arrays.asList(CornerType.TOP_LEFT, CornerType.BOT_LEFT, CornerType.TOP_RIGHT, CornerType.BOT_LEFT, CornerType.BOT_RIGHT), 'k'),
    L(Arrays.asList(CornerType.TOP_LEFT, CornerType.BOT_LEFT, CornerType.BOT_RIGHT), 'l'),
    M(Arrays.asList(CornerType.BOT_LEFT, CornerType.TOP_LEFT, CornerType.BOT_RIGHT, CornerType.TOP_RIGHT, CornerType.BOT_RIGHT), 'm'),
    N(Arrays.asList(CornerType.BOT_LEFT, CornerType.TOP_LEFT, CornerType.BOT_RIGHT, CornerType.TOP_RIGHT), 'n'),
    O(Arrays.asList(CornerType.BOT_LEFT, CornerType.TOP_LEFT, CornerType.TOP_RIGHT, CornerType.BOT_RIGHT, CornerType.BOT_LEFT), 'o'),
    P(Arrays.asList(CornerType.BOT_LEFT, CornerType.TOP_LEFT, CornerType.TOP_RIGHT, CornerType.BOT_LEFT), 'p'),
    Q(Arrays.asList(CornerType.TOP_RIGHT, CornerType.TOP_LEFT, CornerType.BOT_LEFT, CornerType.BOT_RIGHT, CornerType.TOP_RIGHT, CornerType.BOT_RIGHT), 'q'),
    R(Arrays.asList(CornerType.BOT_LEFT, CornerType.TOP_LEFT, CornerType.TOP_RIGHT), 'r'),
    S(Arrays.asList(CornerType.TOP_RIGHT, CornerType.TOP_LEFT, CornerType.BOT_RIGHT, CornerType.BOT_LEFT), 's'),
    T(Arrays.asList(CornerType.TOP_LEFT, CornerType.TOP_RIGHT, CornerType.BOT_RIGHT), 't'),
    U(Arrays.asList(CornerType.TOP_LEFT, CornerType.BOT_LEFT, CornerType.BOT_RIGHT, CornerType.TOP_RIGHT), 'u'),
    V(Arrays.asList(CornerType.TOP_LEFT, CornerType.BOT_LEFT, CornerType.TOP_RIGHT), 'v'),
    W(Arrays.asList(CornerType.TOP_LEFT, CornerType.BOT_LEFT, CornerType.TOP_RIGHT, CornerType.BOT_RIGHT, CornerType.TOP_RIGHT), 'w'),
    X(Arrays.asList(CornerType.TOP_LEFT, CornerType.BOT_RIGHT, CornerType.TOP_RIGHT, CornerType.BOT_LEFT), 'x'),
    Y(Arrays.asList(CornerType.TOP_LEFT, CornerType.BOT_RIGHT, CornerType.TOP_RIGHT, CornerType.BOT_RIGHT), 'y'),
    Z(Arrays.asList(CornerType.TOP_LEFT, CornerType.TOP_RIGHT, CornerType.BOT_LEFT, CornerType.BOT_RIGHT), 'z'),

    SPACE(Arrays.asList(CornerType.TOP_LEFT, CornerType.TOP_RIGHT), ' '),
    DOT(Collections.singletonList(CornerType.BOT_RIGHT), '.'),
    COMMA(Collections.singletonList(CornerType.BOT_LEFT), ','),

    NUM_ONE(Arrays.asList(CornerType.TOP_RIGHT, CornerType.BOT_RIGHT), '1'),
    NUM_TWO(Arrays.asList(CornerType.BOT_LEFT, CornerType.TOP_RIGHT, CornerType.BOT_LEFT, CornerType.BOT_RIGHT), '2'),
    NUM_THREE(Arrays.asList(CornerType.TOP_LEFT, CornerType.TOP_RIGHT, CornerType.BOT_RIGHT, CornerType.BOT_LEFT), '3'),
    NUM_FOUR(Arrays.asList(CornerType.TOP_RIGHT, CornerType.BOT_LEFT, CornerType.BOT_RIGHT, CornerType.TOP_RIGHT, CornerType.BOT_RIGHT), '4'),
    NUM_FIVE(Arrays.asList(CornerType.TOP_RIGHT, CornerType.TOP_LEFT, CornerType.TOP_RIGHT, CornerType.BOT_RIGHT, CornerType.BOT_LEFT), '5'),
    NUM_SIX(Arrays.asList(CornerType.TOP_RIGHT, CornerType.BOT_LEFT, CornerType.BOT_RIGHT, CornerType.BOT_LEFT), '6'),
    NUM_SEVEN(Arrays.asList(CornerType.TOP_LEFT, CornerType.TOP_RIGHT, CornerType.BOT_LEFT), '7'),
    NUM_EIGHT(Arrays.asList(CornerType.TOP_RIGHT, CornerType.TOP_LEFT, CornerType.BOT_RIGHT, CornerType.BOT_LEFT, CornerType.TOP_RIGHT), '8'),
    NUM_NINE(Arrays.asList(CornerType.TOP_RIGHT, CornerType.TOP_LEFT, CornerType.TOP_RIGHT, CornerType.BOT_RIGHT), '9'),
    NUM_ZERO(Arrays.asList(CornerType.TOP_RIGHT, CornerType.TOP_LEFT, CornerType.BOT_LEFT, CornerType.BOT_RIGHT, CornerType.TOP_RIGHT), '0'),

    NOT_A_LETTER(Collections.singletonList(CornerType.NONE), ' '),
    BACK_SPACE(Arrays.asList(CornerType.TOP_RIGHT, CornerType.TOP_LEFT), ' '),
    CLEAR(Arrays.asList(CornerType.TOP_RIGHT, CornerType.BOT_LEFT), ' '),
    CAPITALIZE(Collections.singletonList(CornerType.TOP_LEFT), ' '),
    SPEAK(Collections.singletonList(CornerType.TOP_RIGHT), ' '),
    RESET_LEARNING_INDEX(Arrays.asList(CornerType.BOT_LEFT, CornerType.BOT_RIGHT), ' '),
    NEXT_MODE(Arrays.asList(CornerType.BOT_RIGHT, CornerType.TOP_RIGHT), ' ');


    private final List<CornerType> sequence;
    private final char letter;

    Alphabet(List<CornerType> list, char letter) {
        this.sequence = list;
        this.letter = letter;
    }

    public List<CornerType> getSequence() { return sequence; }

    public char getLetter() { return letter; }

    public static Alphabet fromValue(List<CornerType> list) {
        boolean match;
        for(Alphabet a : Alphabet.values()) {
            if(a.getSequence().size() != list.size()) {
                continue;
            }
            match = true;
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i) != a.getSequence().get(i)) {
                    match = false;
                }
            }
            if(match) {
                return a;
            }
        }
        return NOT_A_LETTER;
    }

    public static Alphabet getByLetter(char letter) {
        for(Alphabet a : Alphabet.values()) {
            if (a.getLetter() == letter) {
                return a;
            }
        }
        return NOT_A_LETTER;
    }

    public static char getPangramCharByIndex(int index) {
        String s = "the quick brown fox jumps over the lazy dog";
        return s.charAt(index);
    }

    public static String getPangramString() {
        return "the quick brown fox jumps over the lazy dog";
    }

    public static char getAlphabetCharByIndex(int index) {
        String s = "abcdefghijklmnopqrstuvwxyz";
        return s.charAt(index);
    }

    public static String getAlphabethString() {
        return "abcdefghijklmnopqrstuvwxyz";
    }

}

package com.pai.hairdresser.util;

import java.util.Random;

public class CodeGenerator {
    public static final String CHARACTERS_SET = "ABCDEFGHIJKLMNOPRQSTUWVXYZ123456789@#$";

    public static String generateCode(int codeLength) {
        if (codeLength <= 0)
            return null;
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i=0; i<codeLength; ++i) {
            int index = random.nextInt(CHARACTERS_SET.length());
            code.append(CHARACTERS_SET.charAt(index));
        }
        return code.toString();
    }
}

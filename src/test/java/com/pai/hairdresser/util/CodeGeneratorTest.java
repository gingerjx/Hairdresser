package com.pai.hairdresser.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeGeneratorTest {
  @Test
  void generate() {
    String code = CodeGenerator.generateCode(5);
    assertNotNull(code);
    assertEquals(5, code.length());

    code = CodeGenerator.generateCode(8);
    assertNotNull(code);
    assertEquals(8, code.length());

    code = CodeGenerator.generateCode(11);
    assertNotNull(code);
    assertEquals(11, code.length());

    code = CodeGenerator.generateCode(-11);
    assertNull(code);

    code = CodeGenerator.generateCode(0);
    assertNull(code);
  }
}
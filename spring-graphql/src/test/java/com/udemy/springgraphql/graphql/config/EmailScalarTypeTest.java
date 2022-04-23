package com.udemy.springgraphql.graphql.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailScalarTypeTest {

    private final EmailScalarType testee;

    public EmailScalarTypeTest() {
        testee = new EmailScalarType();
    }

    @Test
    public void givenCorrectEmail_whenIsEmailType_itShouldReturnTrue() {
        boolean valid = testee.isEmailType("tomas@gmail.com");
        assertTrue(valid);
    }

    @Test
    public void givenInorrectEmail1_whenIsEmailType_itShouldReturnFalse() {
        boolean valid = testee.isEmailType("tomas @gmail.com");
        assertFalse(valid);
    }

    @Test
    public void givenInorrectEmail2_whenIsEmailType_itShouldReturnFalse() {
        boolean valid = testee.isEmailType("tomas@@gmail.com");
        assertFalse(valid);
    }

    @Test
    public void givenInorrectEmail3_whenIsEmailType_itShouldReturnFalse() {
        boolean valid = testee.isEmailType("tom as@gmail.com");
        assertFalse(valid);
    }

    @Test
    public void givenInorrectEmail4_whenIsEmailType_itShouldReturnFalse() {
        boolean valid = testee.isEmailType("tomas@gmailcom");
        assertFalse(valid);
    }

    @Test
    public void givenInorrectEmail5_whenIsEmailType_itShouldReturnFalse() {
        boolean valid = testee.isEmailType("tomas@.gmailcom");
        assertFalse(valid);
    }
}

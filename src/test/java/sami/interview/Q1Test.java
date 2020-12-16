package sami.interview;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Sami on 21:42 - 30/03/17.
 *
 * This test case served as an alternative unit testing as it has in
 * the class simple assert and logging result testing in its main method
 *
 * This test case uses jUnit 4, hence it doesn't need to extend
 * {@linkplain junit.framework.TestCase}
 */
public class Q1Test {

    @Test
    public void shouldReturnTrueForUppercaseStrings() throws Exception {
        assertTrue(Q1.isFirstCharUppercase("Java"));
        assertTrue(Q1.isFirstCharUppercase("Jvm"));
        assertTrue(Q1.isFirstCharUppercase("Github"));
        assertTrue(Q1.isFirstCharUppercase("Sami"));
        assertTrue(Q1.isFirstCharUppercase("Sami"));
    }

    @Test
    public void shouldReturnFalseForLowercaseStrings() throws Exception {
        assertFalse(Q1.isFirstCharUppercase("java"));
        assertFalse(Q1.isFirstCharUppercase("jvm"));
        assertFalse(Q1.isFirstCharUppercase("github"));
        assertFalse(Q1.isFirstCharUppercase("sami"));
    }

    @Test
    public void firstTwoShouldTrueThenFalseResults() throws Exception{
        assertTrue(Q1.isFirstCharUppercase("ABC"));
        assertTrue(Q1.isFirstCharUppercase("ZYX"));
        assertFalse(Q1.isFirstCharUppercase("abc"));
        assertFalse(Q1.isFirstCharUppercase("zyx"));
    }

}
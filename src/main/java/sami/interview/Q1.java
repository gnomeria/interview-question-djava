package sami.interview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sami on 21:22 - 30/03/17.
 *
 ********************************
 *  Question 1:                 *
 ********************************
 * Write a method that takes a String and returns a Boolean.
 * It should return True if the first character is an uppercase.
 * You cannot use String APIs.
 *
 * Although this class contains a logging activity as a test might
 * not be necessary since it's already covered in the unit test,
 * it serves just as a demonstration.
 */
public class Q1 {
    private static final Logger logger = LoggerFactory.getLogger(Q1.class);

    public static void main(String[] args) {
        String firstTest = "Java";
        String secondTest = "Github";
        String thirdTest = "factory";
        String outputFormat = "Expected result from string ('{}') is {}. Result from method: {}";

        logger.info(outputFormat, firstTest, true, isFirstCharUppercase(firstTest));
        logger.info(outputFormat, secondTest, true, isFirstCharUppercase(secondTest));
        logger.info(outputFormat, thirdTest, false, isFirstCharUppercase(thirdTest));
    }

    /**
     * This method uses the decimal value representation of ASCII character to
     * test whether the first character is within the valid capital letter
     * range
     * @param str
     * @return
     */
    static Boolean isFirstCharUppercase(CharSequence str) {
        return str.charAt(0) >= 65 && str.charAt(0) <= 90;
    }

}

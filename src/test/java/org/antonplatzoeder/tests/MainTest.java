package org.antonplatzoeder.tests;

import org.antonplatzoeder.ranking.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
private final String newline = System.getProperty("line.separator");

    @Test
    void InvalidFilePath(){
        var filepath = "src/../NoSuchFolderName/input1.txt";
        String output = Main.Runner(new String[] {filepath});
        assertEquals("File [%s] was not found.".formatted(filepath), output);
    }

    @Test
    void InputSet1() {
        String expected = "1. Tarantulas, 6 pts" + newline +
                "2. Lions, 5 pts" + newline +
                "3. FC Awesome, 1 pts" + newline +
                "3. Snakes, 1 pts" + newline +
                "5. Grouches, 0 pts" + newline;

        var output = Main.Runner(new String[] { "src/../testfiles/input1.txt" });
        assertEquals(expected, output);
    }

    @Test
    void MissingScore() {
        String output = Main.Runner(new String[] { "src/../testfiles/inputWithMissingScore.txt" });
        assertEquals("No TeamResult could be parsed from [Tarantulas]", output);
    }

    @Test
    void NumberInTeamName() {
        String expected = "1. Tarantulas 4, 3 pts" + newline +
                "2. Lions, 2 pts" + newline +
                "3. FC Awesome, 1 pts" + newline +
                "3. Snakes, 1 pts" + newline;

        String output = Main.Runner(new String[] { "src/../testfiles/inputWithNumberInName.txt" });
        assertEquals(expected, output);
    }
}
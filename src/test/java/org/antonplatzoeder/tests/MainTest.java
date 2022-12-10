package org.antonplatzoeder.tests;

import org.antonplatzoeder.ranking.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private final String testfilesFolder = "src/../testfiles";
    private final String newline = System.getProperty("line.separator");

    @Test
    void invalidParameter_NoFilePath(){
        String output = Main.run(new String[0]);
        assertEquals("Expected a filepath as parameter", output);
    }

    @Test
    void invalidParameter_InvalidFilePath(){
        var filepath = "src/../NoSuchFolderName/validInput.txt";
        String output = Main.run(new String[] {filepath});
        assertEquals("File [%s] was not found.".formatted(filepath), output);
    }

    @Test
    void validInput() {
        String expected = "1. Tarantulas, 6 pts" + newline +
                "2. Lions, 5 pts" + newline +
                "3. FC Awesome, 1 pts" + newline +
                "3. Snakes, 1 pts" + newline +
                "5. Grouches, 0 pts" + newline;

        var output = Main.run(new String[] { "%s/validInput.txt".formatted(testfilesFolder) });
        assertEquals(expected, output);
    }

    @Test
    void emptyDataSet() {
        var output = Main.run(new String[] { "%s/empty.data".formatted(testfilesFolder) });
        assertEquals("No matches were played. No ranking could be calculated.", output);
    }

    @Test
    void missingScore() {
        String output = Main.run(new String[] { "%s/inputWithMissingScore.txt".formatted(testfilesFolder) });
        assertEquals("Error in match [Tarantulas, FC Awesome 0]: No TeamResult could be parsed from [Tarantulas].", output);
    }

    @ParameterizedTest
    @CsvSource(
        value = {
            "inputWithScoreNotInteger.txt:Tarantulas 1, FC Awesome:Awesome",
            "inputWithScoreAsDouble.txt:Tarantulas 1, FC Awesome 200.2:200.2"
        },
        delimiter = ':')
    void scoreIsNotInteger(String filename, String expectedInvalidMatch, String expectedInvalidScore) {
        String output = Main.run(new String[] { "%s/%s".formatted(testfilesFolder, filename) });
        assertEquals(
                "Error in match [%s]: Cannot parse score from \"%s\".".formatted(expectedInvalidMatch, expectedInvalidScore),
                output,
                "Importing file %s should have failed.".formatted(filename));
    }

    @Test
    void invalidTeamNameWithNumber() {
        String expected = "1. Tarantulas 4, 3 pts" + newline +
                "2. Lions, 2 pts" + newline +
                "3. FC Awesome, 1 pts" + newline +
                "3. Snakes, 1 pts" + newline;

        String output = Main.run(new String[] { "%s/inputWithNumberInName.txt".formatted(testfilesFolder) });
        assertEquals(expected, output);
    }

    @Test
    void nameInDifferentCase() {
        String expected = "1. Tarantulas, 6 pts" + newline +
                "2. Lions, 5 pts" + newline +
                "3. FC Awesome, 1 pts" + newline +
                "3. SnAKes, 1 pts" + newline +
                "5. Grouches, 0 pts" + newline;

        String output = Main.run(new String[] { "%s/inputDifferentCase.txt".formatted(testfilesFolder) });
        assertEquals(expected, output);
    }

    @Test
    void invalidNumberOfTeams() {
        String output = Main.run(new String[] { "%s/inputWithMoreThanTwoTeams.txt".formatted(testfilesFolder) });
        assertEquals("Error in match [Tarantulas 1, FC Awesome 0,a]: Failed to parse two teams.", output);
    }
}
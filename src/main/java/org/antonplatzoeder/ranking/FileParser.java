package org.antonplatzoeder.ranking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FileParser {

    public static Match[] parse(String filepath) throws Exception {
        List<Match> matches = new ArrayList<>();
        Scanner reader = null;

        try {
            File input = new File(filepath);
            reader = new Scanner(input);

            while (reader.hasNextLine()) {
                Match match = parseMatch(reader.nextLine());
                matches.add(match);
            }
        } catch (FileNotFoundException ex) {
        /*
            Rethrowing exception here to provide a consistent message across platforms (on windows the folder
            delimiter was changed from '/' to '\') as well as a more succinct message.
        */
            throw new Exception("File [%s] was not found.".formatted(filepath), ex);
        } finally {
            if (reader != null)
                reader.close();
        }

        return matches.toArray(new Match[0]);
    }

    private static Match parseMatch(String matchAsString) throws Exception {
        try {
            String[] results = matchAsString.split(",( *)");

            if (results.length != 2)
                throw new Exception("Failed to parse two teams.");

            TeamResult team1, team2;

            team1 = parseTeamResult(results[0].trim());
            team2 = parseTeamResult(results[1].trim());

            return new Match(team1, team2);
        } catch (Exception ex)
        {
            throw new Exception("Error in match [%s]: %s".formatted(matchAsString, ex.getMessage()), ex);
        }
    }

    private static TeamResult parseTeamResult(String resultAsString) throws Exception {
        /*
            A team name could possibly have a space in it. Account for the space.
        */
        var delimiterIndex = resultAsString.lastIndexOf(" ");
        if (delimiterIndex < 0)
            throw new Exception("No TeamResult could be parsed from [%s].".formatted((resultAsString)));

        var teamName = resultAsString.substring(0, delimiterIndex);
        int score = parseScore(resultAsString.substring(delimiterIndex + 1));

        return new TeamResult(teamName, score);
    }

    private static int parseScore(String scoreAsString) throws Exception {
        try {
            return Integer.parseInt(scoreAsString);
        } catch (NumberFormatException ex) {
            throw new Exception("Cannot parse score from \"%s\".".formatted(scoreAsString), ex);
        }
    }
}

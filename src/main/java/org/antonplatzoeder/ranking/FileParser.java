package org.antonplatzoeder.ranking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FileParser {
    public static Match[] parse(String filepath) throws Exception {
        List<Match> matches = new ArrayList<>();

        try {
            File input = new File(filepath);
            Scanner reader = new Scanner(input);
            while (reader.hasNextLine()) {
                Match match = parseMatch(reader.nextLine());
                matches.add(match);
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            // Rethrowing exception here to provide a more succinct message and expected message.
            throw new Exception("File [%s] was not found.".formatted(filepath), ex);
        }

        return matches.toArray(new Match[0]);
    }

    private static Match parseMatch(String matchAsString) throws Exception {
        String[] results = matchAsString.split(",( *)");

        if (results.length != 2)
            throw new Exception("Failed to parse two teams from [%s]".formatted(matchAsString));

        var team1 = parseTeamResult(results[0].trim());
        var team2 = parseTeamResult(results[1].trim());
        return new Match(team1, team2);
    }

    private static TeamResult parseTeamResult(String resultAsString) throws Exception {
        /*
            A team name could possibly have a space in it. Account for the space.
        */
        var delimiterIndex = resultAsString.lastIndexOf(" ");
        if (delimiterIndex < 0)
            throw new Exception("No TeamResult could be parsed from [%s]".formatted((resultAsString)));

        var teamName = resultAsString.substring(0, delimiterIndex);
        int score = Integer.parseInt(resultAsString.substring(delimiterIndex + 1));

        return new TeamResult(teamName, score);
    }
}

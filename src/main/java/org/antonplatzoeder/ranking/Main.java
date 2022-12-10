package org.antonplatzoeder.ranking;

import java.util.List;

public class Main {
    public static void main(String[] args)  {
        System.out.println(run(args));
    }

    public static String run(String[] args) {
        try
        {
            validateArguments(args);
            Match[] matches = FileParser.parse(args[0]);
            RankingTable rankingTable = new RankingTable(List.of(matches));
            return rankingTable.toString();
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }
    }

    private static void validateArguments(String[] args) throws Exception {
        if (args.length < 1)
            throw new Exception("Expected a filepath as parameter");
    }
}
package org.antonplatzoeder.ranking;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

class RankingTable {
    private final SortedSet<Map.Entry<String, Integer>> ranking;
    public RankingTable(Iterable<Match> matches)
    {
        this.ranking = new TreeSet<>(new RankingTableComparator());

        for (Match match: matches) {
            rank(match);
        }
    }

    public String toString()
    {
        StringBuilder output = new StringBuilder();

        int index = 1;
        int previousPoints = -1;
        int currentPosition= 0;

        for (Map.Entry<String, Integer> map: ranking) {
            currentPosition = map.getValue() == previousPoints ? currentPosition : index;

            output.append(String.format("%s. %s, %s pts%s", currentPosition, map.getKey(), map.getValue(), System.getProperty("line.separator")));

            previousPoints = map.getValue();
            index++;
        }

        return output.toString();
    }

    private void rank(Match match) {
        var winner = match.getWinningTeam();

        String[] teams = {match.getTeam1Result().getTeamName(), match.getTeam2Result().getTeamName()};
        for (String teamName : teams) {
            var rankingPoint = winner == null
                    ? 1
                    : teamName.equals(winner) ? 3 : 0;

            assignPoints(teamName, rankingPoint);
        }
    }

    private void assignPoints(String teamName, int point)
    {
        var rankEntry = findRanking(teamName);
        if (rankEntry == null)
            ranking.add(Map.entry(teamName, point));
        else {
            ranking.remove(rankEntry);
            ranking.add(Map.entry(teamName, rankEntry.getValue() + point));
        }
    }

    private Map.Entry<String, Integer> findRanking(String teamName)
    {
        for (Map.Entry<String, Integer> map: ranking)
        {
            if (map.getKey().equals(teamName))
                return map;
        }

        return null;
    }
}

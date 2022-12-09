package org.antonplatzoeder.ranking;

class Match {
    private final TeamResult team1Result;
    private final TeamResult team2Result;

    public Match(TeamResult result1, TeamResult result2)
    {
        this.team1Result = result1;
        this.team2Result = result2;
    }

    public TeamResult getTeam1Result() {
        return team1Result;
    }

    public TeamResult getTeam2Result() {
        return team2Result;
    }

    public String getWinningTeam() {
        if (team1Result.getScore() > team2Result.getScore())
            return team1Result.getTeamName();
        else if (team1Result.getScore() < team2Result.getScore())
            return team2Result.getTeamName();

        return null;  //is draw
    }
}

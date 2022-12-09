package org.antonplatzoeder.ranking;

class TeamResult {
    private final String teamName;
    private final int score;

    public TeamResult(String teamName, int score)
    {
        this.teamName = teamName;
        this.score = score;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getScore() {
        return score;
    }
}

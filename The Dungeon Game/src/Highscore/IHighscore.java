package Highscore;

import java.util.Collection;

public interface IHighscore
{
    public void addScore(String name, int score);
    public Collection<HighscoreEntry> getTopTen();
}

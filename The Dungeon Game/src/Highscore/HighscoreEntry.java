package Highscore;

public class HighscoreEntry implements Comparable<HighscoreEntry>
{
    private final String name;
    private final int score;

    public HighscoreEntry(String name, int score)
    {
        this.name = name;
        this.score = score;
    }

    public String getName()
    {
        return name;
    }

    public int getScore()
    {
        return score;
    }

    @Override
    public int compareTo(HighscoreEntry other)
    {
        //We want high scores before low scores...
        int c = other.score - score;
        if(c != 0) return c;
        return name.compareTo(other.name);
    }
    
}

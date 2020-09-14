package Highscore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHighscore implements IHighscore
{
    private final File file;

    public FileHighscore(String filename)
    {
        this.file = new File(filename);
    }
    
    
    @Override
    public void addScore(String name, int score)
    {
        TreeSet<HighscoreEntry> set = load();
        set.add(new HighscoreEntry(name, score));
        save(set);
    }

    @Override
    public Collection<HighscoreEntry> getTopTen()
    {
        return load();
    }
    
    private TreeSet<HighscoreEntry> load()
    {
        TreeSet<HighscoreEntry> res = new TreeSet<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line = br.readLine();
            while(line != null)
            {
                String[] pair = line.split(";");
                if(pair.length == 2)
                {
                    String name = pair[0];
                    int points = Integer.parseInt(pair[1]);
                    HighscoreEntry entry = new HighscoreEntry(name, points);
                    res.add(entry);
                }
                line = br.readLine();
            }
        }
        catch(FileNotFoundException e)
        {
            return res;
        }
        catch(IOException ex)
        {
            Logger.getLogger(FileHighscore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    private void save(TreeSet<HighscoreEntry> set)
    {
        BufferedWriter writer = null;
        try
        {
            int count = 0;
            writer = new BufferedWriter(new FileWriter(file));
            for(HighscoreEntry entry : set)
            {
                writer.write(entry.getName());
                writer.write(';');
                writer.write("" + entry.getScore());
                writer.write(System.lineSeparator());
                ++count;
                //Make sure we only write 10 lines.
                if(count == 10) break;
            }
        }
        catch(IOException ex)
        {
            Logger.getLogger(FileHighscore.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                if(writer != null)
                {
                    writer.close();
                }
            }
            catch(Exception e)
            {
                Logger.getLogger(FileHighscore.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
}

package IO;

import java.io.IOException;

public class TextIO implements ITextIO
{
    private final ISimpleTextIO io;

    public TextIO(ISimpleTextIO io)
    {
        this.io = io;
    }
    
    @Override
    public int getInteger(int min, int max)
    {
        String input = io.get();
        boolean ok = false;
        int res = 0;
        while(!ok)
        {
            ok = true;
            try
            {
                res = Integer.parseInt(input);
                if(res < min || res > max)
                {
                    ok = false;
                    io.put("\nYou must enter an integer between " + min + " and " + max + ", both inclusive.");
                    input = get();
                }
            }
            catch(NumberFormatException exp)
            {
                ok = false;
                io.put("\nYou must enter an integer!");
                input = io.get();
            }
        }
        return res;
    }
    
    @Override
    public int select(String header, Iterable<String> choices, String footer)
    {
        // This is an example of where Java has got it wrong (historic reasons, long story).
        // Arrays do not implement Iterable<>, so we need to have the exact same code twice
        // in two almost identical methods (except one parameter type), if we want to support
        // both arrays and collections :(
        put(header);
        int count = 0;
        for(String s : choices)
        {
            io.put("\n" + (++count) + " - " + s);
        }
        io.put("\n" + footer);
        return getInteger(1, count)-1;
    }
    
    @Override
    public int select(String header, String[] choices, String footer)
    {
        // This is an example of where Java has got it wrong (historic reasons, long story).
        // Arrays do not implement Iterable<>, so we need to have the exact same code twice
        // in two almost identical methods (except one parameter type), if we want to support
        // both arrays and collections :(
        put(header);
        int count = 0;
        for(String s : choices)
        {
            io.put("\n" + (++count) + " - " + s);
        }
        io.put("\n" + footer);
        return getInteger(1, count)-1;
    }

    @Override
    public void put(String s)
    {
        io.put(s);
    }

    @Override
    public void clear()
    {
        io.clear();
    }

    @Override
    public String get()
    {
        return io.get();
    }

    @Override
    public void close() throws IOException
    {
        io.close();
    }    
}

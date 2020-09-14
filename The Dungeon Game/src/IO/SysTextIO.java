package IO;

import java.io.IOException;
import java.util.Scanner;

public class SysTextIO implements ISimpleTextIO
{

    private final static Scanner keyboard = new Scanner(System.in);
    
    @Override
    public void put(String str)
    {
        System.out.print(str);
    }

    @Override
    public String get()
    {
        System.out.print("\n>");
        return keyboard.nextLine();
    }

    @Override
    public void clear()
    {
        for(int i = 0; i < 100; ++i)
        {
            System.out.println("");
        }
    }

    @Override
    public void close() throws IOException
    {
        System.out.println("\n\n\nGoodbye!\n\n\n");
    }
    
}

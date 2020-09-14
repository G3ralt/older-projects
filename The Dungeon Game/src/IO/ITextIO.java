package IO;

public interface ITextIO extends ISimpleTextIO
{
    public int getInteger(int min, int max);
    public int select(String header, Iterable<String> choices, String footer);
    public int select(String header, String[] choices, String footer);
}

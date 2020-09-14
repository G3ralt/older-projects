package IO;

import java.io.Closeable;

public interface ISimpleTextIO extends Closeable
{
    public void put(String s);
    public void clear();
    public String get();
}

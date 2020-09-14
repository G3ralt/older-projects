package IO;

public class TextIOFactory
{
    private static ITextIO sysIO = null;
    
    public static ITextIO getSysTextIO()
    {
        if(sysIO == null)
        {
            sysIO = new TextIO(new SysTextIO());
        }
        return sysIO;
    }
    
    public static ITextIO newGUITextIO()
    {
        return new TextIO(GUITextIO.createGUI());
    }
}

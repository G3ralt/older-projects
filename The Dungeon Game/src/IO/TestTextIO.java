package IO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestTextIO
{
    public static void main(String[] args) throws IOException
    {
        
        ITextIO io1 = TextIOFactory.newGUITextIO();
        ITextIO io2 = TextIOFactory.newGUITextIO();
        ITextIO io3 = TextIOFactory.getSysTextIO();
        
        
        io1.put("Hello GUI 1\n");
        io2.put("Hello GUI 2\n");
        io3.put("Hello systext\n");
        List<String> choices = new ArrayList();
        choices.add("Red");
        choices.add("Green");
        choices.add("Blue");
        choices.add("Yellow");
        choices.add("White");
        choices.add("Black");
        String ch1 = choices.get(io1.select("You must choose a color for your hat:", choices, "Which color do you prefer?\n"));
        io1.clear();
        io1.put("You have choosen a " + ch1 + " hat. A good choice!\n");
        io3.put("GUI 1 have choosen a " + ch1 + " hat.\n");
        io2.put("The person using the other GUI have selected a " + ch1 + " hat. Can you do better?\n");
        String ch2 = choices.get(io2.select("You must choose a color for your hat:", choices, "Which color do you prefer?\n"));
        io2.clear();
        io2.put("You have choosen a " + ch2 + " hat. A good choice!\n");
        io3.put("GUI 2 have choosen a " + ch2 + " hat.\n");
        io1.put("The person using the other GUI have selected a " + ch2 + " hat. So there is that!\n");
        io1.put("Press ENTER to quit ");
        io1.get();
        io1.close();
        io2.put("Press ENTER to quit ");
        io2.get();
        io2.close();
        io3.put("Everyone has gone away...");
        io3.close();
    }
   
}

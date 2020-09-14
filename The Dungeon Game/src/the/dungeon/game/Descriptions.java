package the.dungeon.game;

public class Descriptions {
    Player player;
    
    public Descriptions() {
    }
    
    public String rules() {
        return ("\nYou are about to enter /!\\ The Witcher`s Dungeon /!\\.\nIt was part of an old witcher fortress destroyed"
                + " in the year 1268.\nThe dungeon is divied into rooms that have up to 4 possible exits.\n"
                + "Each room has it`s own characteristics, weapons or monsters.\nThe objective is to reach the end of the maze."
                + "\nYou need energy to progress and you have a total of 100 at the start of the game.\n"
                + "Each move will cost you 1 energy and each trap you find will take 10 energy to disable.\n"
                + "NB! If you loose all your energy, the Dungeon Keeper will collect your soul and the game will end with your death."
                + "\nThe Dungeon Keeper is an entity protecting the gold of the witchers. You can buy potions from him"
                + "\nAt any moment you can type 'help' to see all available commands.\nEnjoy the game!");
    }
        
    public String Start(){
        return ("\n*** No.Turning.Back. ***\nThe Gate that led you into the dungeon shuts behind you."
                + "\nIt seems to be locked. As father always said: “Never look back!”\nYou’re introduced to a sign saying: "
                + "“The determination of true champions starts here!”.");
    }
    
    public String desc2(){
        return ("\n*** Determination Room ***\nThe Gravity-pull seems to ease up, you feel lighter yet fragile, lost but collected."
                + "\nThe dark void that surrounds you disappears and transit into glimmering holy beams that strike the ground before you."
                + "\nYou’re presented by an ol’ rusty and beaten up handy dandy chest filled with nothing else but golden chocolate coins.");
    }
    
    public String desc3(){
        return ("\n*** The Window Room ***\nRed and orange beams of light hit the surface you’re standing on.\n"
                + "Flames surround you in an unbearable hot inferno. You notice an entrance blocked off by a molten lava wall."
                + "\nWithout the ability to move further you start to think that any more time spent in here "
                + "will leave you crucially wounded.\n\"Definitely not going in there again!\", you say to yourself gasping after"
                + " your breath.\nThis place is not as abandoned as you had thought.");
    }
            
    public String desc4(){
        return ("\n*** The Hallway ***\nIt’s a very dank hallway, actually it’s both a hall and a way.");
    }
    
    public String desc5(){
        return ("\n*** The Mirror Room ***\nAs you enter, the room looks bland, cold and empty.\n"
                + "On the wall furthest away from you a mysterious mirror hangs on the wall.");
    }
    
    public String desc6(){
        return ("\n*** Connector ***\nYou see a room with a sign “Rush B!” and a terrorist running to plant the bomb.");
    }
    
    public String desc7(){
        return ("\n*** The Library ***\nAs dust enters your airways, your eyes reveal thousands of books.\n"
                + "May they come in handy at any point? As you skimmer around the library, you spot an opening…A hidden door perhaps?"
                + "\nYou start to hear strange noises outside the library, maybe it’s just your sanity.");
    }
    
    public String desc8(){
        return ("\n*** The Shattered Window ***\nFinally, a breath of fresh air. You are welcomed by a magical surrounding "
                + "entirely from glass.\nA purplish light enlightens the room. As you step into the beam of light\n"
                + "Your blood starts to run thinner in your veins\nYou begin to feel stronger and more energized. "
                + "This might come in handy, you note.");
    }
    
    public String desc9(){
        return ("\n*** Paradise? ***\nAs you enter the next area, you see a stone staircase leading up to a Garden.\n"
                + "The Garden is riddled with gravestones and encased from top to bottom.\nThe cold and shattering atmosphere"
                + " gives you goosebumps through your ripped clothing.\n\"No one is escaping from here, not even death.” "
                + "You say to yourself.\nNearby you see a demolished church-house to the west and a mold-riddled Shack to the east."
                + "\nAt last smirking your eyes: You spot what looks like a Mausoleum far north in the distance….”"
                + " Treasure or Trouble?\" You ask yourself…\nA strong wind hits you from behind pushing you"
                + " forwards…Where do you proceed?");
    }
    
    public String desc10(){
        return ("\n*** Shacklers Shack ***\nThe light is just sufficient to capture your surroundings.\n"
                + "The shack is intact, everything in order apart from a strangely placed Human-Skeleton laying"
                + " up against one of the barrels inside, guarding a Silver Sword and Wooden Shield.\n“Treasure” you think. "
                + "Having Goblins as your best friends, has not come in handy for you yet.\nQuickly!"
                + " You dash over to grab the “shinies!” but get stopped in your path by the guarding Skeleton."
                + "\nYou wrestle your way around and manage to snatch the shield! With a quick blow you "
                + "dismantle the Skeleton.\nPesky rattle-box ain’t got nothing on you, slamming the door as you leave.");
    }
    
    public String desc11(){
        return ("\n*** St. Gregory's Church ***\nWorryingly you decide to proceed through the church house."
                + "\nOn the facade of the structure you read “St. Gregory’s Church”.\nUnable to identify your location, "
                + "you begin to question if you’re equipped well enough for whatever you might encounter inside,"
                + " tightening your fist.\nAs you enter: “This might not be wise…” you think to yourself."
                + " Inside you find yet another stairway facing downwards.\nSeems like an entrance back into "
                + "the dungeon. Do you proceed down?");
    }
    
    public String desc12(){
        return ("\n*** Everec’s Mausoleum ***\nYou clear your mind, and go straight for the Mausoleum. "
                + "It says “Everec’s Mausoleum” as you go down the crypt.\nYou foresee danger ahead. Do you proceed?");
    }
    
    public String desc13(){
        return ("\n*** The Gift Shop ***\nYou enter a well decorated room, but see a Spectral Gremlin struggle a lot."
                + "\nHe seems to be enchained by the arms and legs.\nHe tells you that you will be able to browse"
                + " his wares if you help letting him loose.\nYou spot a Spectral Chest linked to his legs. Do you trust him?");
    }
    
    public String desc14(){
        return ("\n*** The Coinflip ***\nThe hall consists of walls riddled with torches and banners. At the end a "
                + "gigantic Dungeon Keeper wearing Plate Armor appears infant of you. He is guarding a big gate."
                + "\nHe speaks with monotonously deep-pitched voice “I cannot predict what lies ahead, "
                + "but if you wish to proceed there’s a fee that you must apply to pay” You let out a *sigh*."
                + "\nYou look around, not seeing alternative paths to go. Do you accept the keepers offer or not?"); 
    }
    
    public String desc15(){
        return ("\n*** Keepers Rest ***\nYou bribed some wares from the Dungeon Keeper, including a mail armor piece,"
                + " helmet and an Icy Enhanced Sword that has been dipped in Spectral Oil.\nYou wonder, "
                + "“What’s that for?” You lose your hearing as you enter the next room.\nAn army of Skeletons along "
                + "with two Banshees and a Witch appear.\nYou prepare yourself for combat, letting out a "
                + "War Cry and tightening your grip of your sword.");
    }
    
    public String desc16(){
        return ("\n*** Goldies! ***\nYou managed to beat the challenge the Dungeon Keeper had given you."
                + " This room features a golden chest.\nA huge turn of events as you open your prize."
                + " Victorious but wounded you leave the room after having collected your reward.");
    }
    
    public String desc17(){
        return ("\n*** Preparation ***\nYou find yourself in a long hall again. You hear a distant orchestra playing."
                + " The adrenaline within your body is starting to release.\nLooking around you see"
                + " moon light being casted infant of you as you proceed down the hallway.\nThe hall"
                + " continues like a never ending stream until a huge gate before your eyes.\nIt’s labeled"
                + " “Boos Room” … Are you ready for your final challenge?\nYou hear a distant cheer, reminding "
                + "you of your father. Your time is now.");
    }
    
    public String desc18(){
        return ("\n*** The Dead Witcher`s Room ***\nYou should really reevaluate your life at this point, cause "
                + "as you enter the room it starts filling with thick dark smoke.\nIt stings your eyes as you"
                + " try to open them.\nIt is actually the darkest room as you can possibly imagine at this point.\n"
                + "When the smoke lays off, it is to your surprise, Dick Kick’em in all of his glory.\nYou scream out"
                + " loud: “It’s time to chew dick and kick butt, and I’m ALL OUT OF DICK! You let out a Tarzan War"
                + " Cry, as you leap into battle!");
    }
    
    public String desc19(){
        return ("\n*** Blessing of the Knights ***\nYou find yourself in a church-like surrounding.\nAn altar "
                + "is staring you down infant of you, along the path in the middle are church benches aligned "
                + "in a perfectly symmetrical manner.\nYellowish light fills the room as you proceed up the alter."
                + "\nOn top of a small pillar, a book is placed. It radiates with light.\nTouching it doesn’t seem wise,"
                + " but on the other hand …");
    }   
    
    public String exitMessage() {
       return ("\nThank you for playing our game!\nIt was an honor having you in The Witcher`s Dungeon!"
               + "\nWe hope you enjoyed yourself and you are very welcome to play again!\nBest of wishes,\nAlex & Max");
    }
    
    public String outOfEnergy() {
        return "\nUnfortunately you ran out of Energy and your soul will be forever trapped in The Witcher`s Dungeon!";
    }
    
    public String endGame() {
        return "\nYou see a light at the end of tunnel.\nAs you walk the air gets fresh.\n"
                + "Soon you see an exit and you run as fast as you can.\nYou see the Sun for the first time in ages.\n"
                + "The sound of the birds singing is music for your ears.";
    }    
    
    public String key1() {
        return "You’ve found a Bronze Key. What could be it’s use? Anyway It’s likely to snap so I would be careful!";
    }
    
    public String key2() {
        return "A Silver key appears! Seems beaten up but still usable!";
    }
    
    public String key3() {
        return "Bling bling!";
    }
    
    public String key4() {
        return "More keys?";
    }
    
    public String key5() {
        return "Ooooh shiny! Nevermind. From now on I like keys!";
    }
    
    public String key6() {
        return "I should make this a hobby!";
    }
    
    public String trap1() {
        return "You get a pleaseantly warm presentation, when the spikes penetrate you get a burning sensation, getting closer to God in a tight situation.";
    }
    
    public String trap2() {
        return "You fell down a snake pit. Dozens of snakes pierce your skin and flesh injecting poisonous venom as you crawl your way back to the surface. With your ripped skin and bleeding body you start feeling nauseous.";
    }
    
    public String trap3() {
        return "Ouch! Spikes, Spikes everywhere. STOP POKING ME!";
    }
    
    public String trap4() {
        return "You’ve stepped into a Draining Trap! Do you feel …. sleepy yet?";
    }
    
    public String trap5() {
        return "Suffocation and lungs filled with water, what a pleasant story. Must suck to be you … Sooo how long can you hold your breath?";
    }
    
    public String dungeonKeeper() {
        return "\nThe Dungeon Keeper takes a quick peek at you, armored up and standing tall while mumbling to himself."
                + "\nAfter a while he agrees to give his attention to you."
                + "\n“I have valuables that will help you in your journey, traveller. But for a price of course.”";
    }
    
    public String monster1() {
        return "You get welcomed by an angry look, as you eyes clear up you realize that you’re facing Hillary Clinton in person."
                + "\nShe appears mad and ready to attack you at any given moment."
                + "\nYour first thought is to … Grab her by the ... never mind. Just TRUMP her!.";
    }
    
    public String monster2() {
        return "A face that can’t look any more smug, yes that is the face of Mr. Donald Trump that we know so well. He will try to grab you by the ...";
    }
    
    public String monster3() {
        return "Whisper quiet, translucent and deadly a white shadow swirls around you. It seems afraid of your presence. "
                + "\nYou start feeling uncomfterable grabbing firmly around your weapon of choice.";
    }
    
    public String monster4() {
        return "Loyal and protecting. He stands tall and does not acknowledge your existence."
                + "\nProbably because he already imagined wiping you from the face of the earth."
                + "\nBut you know what they say … ”The bigger they are, the harder they fall!";
    }
    
    public String monster5() {
        return "Once a necromancer, he was dragged into a void teleport crafted from Obsidian "
                + "\nonly to embrace his power to an extend, that he soon ruled over the Kingdom of the Dark."
                + "\nNow he walks the earth, with a raging temper. only wishing to get his normal life back.";
    }
    
    public String monster6() {
        return "A thunderous tornado shaped creature stands tall and looks down upon you ..."
                + "\nSlamming his Trident into the ground, screaming: "
                + "\n“Eartling! You shouldn’t have Trumped Hillary!” as he bitch-slaps you across the room.";
    }
    
    public String monster7() {
        return "What would you get if you DNA Transplanted Juggernaut genes into a Troll?"
                + "\nBasically a Troll Hybrid named Jikaos, the Brutal. Ruler of the Dungeon, Leader of the Tribes."
                + "\nOne tonne of pure muscle, skin as tough as titanium and Stone Armor from top to toe!"
                + "\nOh! Did I mention the double sided blades that accounts as his arms made from pure Topaz? Yeah, you’re screwed.";
    }
    
    public String staff() {
        return "Not for the faint hearted. Beware of bruises!";
    }
    
    public String sword() {
        return "This torching sword is absurdly long and heavy, but should get the work done.";
    }
    
    public String axe() {
        return "Infinite rule to those who bear these arms!";
    }
    
    public String spear() {
        return "The legends speak true and wise that the tip of this legendary weapon has pierces the heart of many legends. It is told that if thrown, it will transform into a gust of wind piercing anything in its path.";
    }
    
    public String hammer() {
        return "This is the definition of destruction. Molded by the substance within the core of the Earth itself, this mace-like weapon was structured to obliterate even the very atoms that formed any substance known to man.";
    }
    
    public String pike() {
        return "Equipped and used by the old greek god of the Sea himself, you now can rule the oceans!";
    }
    
    public String boots() {
        return "No worries for cold feet again!";
    }
    
    public String helmet() {
        return "Protect your nugget!";
    }
    
    public String pants() {
        return "You’ll have to wear these if you skip leg day!";
    }
    
    public String cape() {
        return "Makes every aspect of the Dungeon render 50% faster, but still it’s only 30 FPS! LoL";
    }
    
    public String shield() {
        return "Put this in front of your face if you’re facing danger!";
    }
    
    public String bodyarmor() {
        return "Protect your beautiful breasts! For completing the hole set of Dungeon armor you get a bonus armor!";
    }
    
    
}

package developersudhanshu.com.javajokeslibrary;

import java.util.ArrayList;
import java.util.Random;

public class RandomProgrammingJokes {

    private static int lastJokeIndex = -1;

    private ArrayList<String> jokes = new ArrayList<String>(){{
        add("What's a computer hardware? A part of a computer that you can kick.");
        add("\"Debugging\" is like being the detective in a crime drama where you are also the murderer");
        add("An optimist says \"The glass is half full.\"\n" +
                "A pessimist says \"The glass is half empty.\"\n" +
                "A programmer says \"The glass is twice as large as necessary.\"");
        add("A programmer puts two glasses on his bedside table before going to sleep." +
                " A full one, in case he gets thirsty, and an empty one, in case he doesn’t.");
        add("The best thing about a Boolean is that even if you are wrong, you are only off by a bit.");
        add("Why do programmers always mix up Christmas and Halloween?\n" +
                "Because Dec 25 is Oct 31.");
        add("An SQL query goes into a bar, walks up to two tables and asks: \"Can I join you?\"");
    }};

    public String serveJokes(){
        Random rand = new Random();

        int jokeIndex = rand.nextInt(jokes.size());

        if(lastJokeIndex == jokeIndex){
            jokeIndex += 1;
        }
        lastJokeIndex = jokeIndex;

        return jokes.get(jokeIndex);
    }
}

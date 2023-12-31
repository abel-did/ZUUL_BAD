 /**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2019.09.25
 * 
 * @author DIDOUH Abel
 * @date   04/01/2023
 * @version Exercice 7.43
 */
public class CommandWords
{
    // a constant array that will hold all valid command words
    private final String[] aValidCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        this.aValidCommands = new String[10];
        this.aValidCommands[0] = "go";
        this.aValidCommands[1] = "help";
        this.aValidCommands[2] = "quit";
        this.aValidCommands[3] = "look";
        this.aValidCommands[4] = "eat";
        this.aValidCommands[5] = "back";
        this.aValidCommands[6] = "test";
        this.aValidCommands[7] = "drop";
        this.aValidCommands[8] = "take";
        this.aValidCommands[9] = "inventory";
    } // CommandWords()
    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand( final String pString )
    {
        for ( int vI=0; vI<this.aValidCommands.length; vI++ ) {
            if ( this.aValidCommands[vI].equals( pString ) )
                return true;
        } // for
        // if we get here, the string was not found in the commands :
        return false;
    } // isCommand()
    /**
     * Affiche toutes les commandes valides.
     * Modification Exercice 7.18
     * @return chaine
     */
    public String getCommandList()
    {
        String chaine = new String();
        for(String command : aValidCommands)
        {
            chaine = chaine + (command + " ");
        }
        return chaine;
    }//showAll
} // CommandWords
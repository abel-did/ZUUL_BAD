import java.util.StringTokenizer;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2013.09.15
 * 
 * @author DIDOUH Abel
 * @date   04/01/2023
 * @version Exercice 7.43
 */
public class Parser 
{
    private CommandWords aValidCommands;  // (voir la classe CommandWords)
    
    /**
     * Constructeur par defaut qui cree les 2 objets prevus pour les attributs
     */
    public Parser() 
    {
        this.aValidCommands = new CommandWords();
        
        // System.in designe le clavier, comme System.out designe l'ecran
    } // Parser()
    /**
     * @return The next command from the user.
     */
    public Command getCommand(final String pInputLine) 
    {
        String vWord1 = null;
        String vWord2 = null;
        
        // cherche jusqu'a 2 mots dans la ligne tapee
        StringTokenizer vTokenizer = new StringTokenizer( pInputLine );
        
        if ( vTokenizer.hasMoreTokens() ) 
             vWord1 = vTokenizer.nextToken();      // recupere le premier mot
        else 
            vWord1 = null;
        
        if( vTokenizer.hasMoreTokens() )
            vWord2 = vTokenizer.nextToken();       // recupere le second mot
        else
            vWord2 = null;
            

        if ( this.aValidCommands.isCommand( vWord1 ) )
            return new Command( vWord1, vWord2 );
        else
            return new Command( null, vWord2 );
    } // getCommand()
    /**
     * Affiche une liste des commandes valides.
     */
    public String getCommandString()
    {
         return this.aValidCommands.getCommandList();
    }//showCommands
} // Parser

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 *  This class is part of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.
 *  
 *  This class creates all rooms, creates the parser and starts
 *  the game.  It also evaluates and executes the commands that 
 *  the parser returns.
 *
 * @author DIDOUH Abel
 * @date   04/01/2023
 * @version Exercice 7.43
 */
public class GameEngine
{
    /**
     * Attributs (private)
     */
    private Parser              aParser;
    private UserInterface       aGui;
    private Player              aPlayer;
    
    private int                 aNumberOfAction;
    /**
     * Constructeur d'objets pour la classe GameEngine
     */
    public GameEngine()
    {
        this.aParser = new Parser();
        this.createRooms();
        this.aNumberOfAction = 0;
    }//GameEngine
    /**
     * Procedure qui creer l'interface utilisateur
     * @param pUserInterface UserInterface
     */
    public void setGUI( final UserInterface pUserInterface)
    {
        this.aGui = pUserInterface;
        this.aPlayer.setGUI(this.aGui);
        
        this.printWelcome();
    }//setGui
    /**
     * Procédure privée printWelcome
     */
    private void printWelcome()
    {
        //Message de bienvenue
        this.aGui.println("Welcome to the Castle");
        this.aGui.println("You are a student and you must");
        this.aGui.println("find a scroll in the castle !");
        this.aGui.println("Type 'help' if you need help");
        this.aGui.println(" ");
        
        printLocationInfo();
    }//printWelcome
    /**
     * Procedure privée createRooms
     * Creer les zones du jeu
     */
    private void createRooms()
    {
       //Création des pieces
       Room vBasseCour      = new Room("courtyard"  ,"in the courtyard",      "IMAGE/BasseCours.jpg");
       Room vChapelle       = new Room("chapel"     ,"in the chapel",         "IMAGE/Chapelle.jpg");
       Room vCrypte         = new Room("crypt"      ,"in the crypt",          "IMAGE/Crypte.jpg");
       Room vCuisineRoyales = new Room("kitchens"   ,"in the royal kitchens", "IMAGE/CuisinesRoyales.jpg");
       Room vDonjon         = new Room("dungeon"    ,"in the dungeon",        "IMAGE/Donjon.jpg");
       Room vEcuries        = new Room("stable"     ,"in the stable",         "IMAGE/Ecuries.jpg");
       Room vMusee          = new Room("museum"     ,"in the museum",         "IMAGE/Musee.jpg");
       Room vPontLevis      = new Room("levis bridge"   ,"on the levis bridge",   "IMAGE/PontLevis.jpg");
       Room vRemparts       = new Room("fortress walls" ,"on fortress walls",     "IMAGE/Remparts.jpg");
       Room vSalleDesGardes = new Room("guard room"     ,"in the guard room",     "IMAGE/SalleDesGardes.jpg");
       Room vSalleMusique   = new Room("music room"     ,"in the music room",     "IMAGE/SalleMusique.jpg");
       Room vJardin         = new Room("garden"         ,"in the garden",         "IMAGE/Jardin.jpg");
       Room vSentier         = new Room("trail"         ,"on the trail",         "IMAGE/Sentier.jpg");
       
       //Definition des sorties
       vPontLevis.setExit("east", vBasseCour);
       
       vBasseCour.setExit("north", vChapelle);
       vBasseCour.setExit("east", vSalleDesGardes);
       vBasseCour.setExit("west", vPontLevis);
       vBasseCour.setExit("up", vRemparts);
       
       vChapelle.setExit("south", vBasseCour);
       
       vJardin.setExit("south", vSalleDesGardes);
       
       vSentier.setExit("south", vEcuries);
       
       vSalleDesGardes.setExit("north", vJardin);
       vSalleDesGardes.setExit("east", vEcuries);
       vSalleDesGardes.setExit("west", vBasseCour);
       vSalleDesGardes.setExit("south", vCuisineRoyales);
       
       vEcuries.setExit("north", vSentier);
       vEcuries.setExit("west", vSalleDesGardes);
       
       vCuisineRoyales.setExit("north", vSalleDesGardes);
       //TrapDoor
       //vCuisineRoyales.setExit("up", vMusee);
       
       vRemparts.setExit("down", vBasseCour);
       vRemparts.setExit("south", vDonjon);
              
       vDonjon.setExit("north", vRemparts);
       vDonjon.setExit("down", vCrypte);
       vDonjon.setExit("south", vSalleMusique);
       vDonjon.setExit("east", vMusee);
       
       vCrypte.setExit("up", vDonjon);
       
       vSalleMusique.setExit("north", vDonjon);
       
       vMusee.setExit("west", vDonjon);
       vMusee.setExit("down", vCuisineRoyales);       
       
       //Definition des Items
       Item vLunettesNocturnes      = new Item("goggles", 400, "Glasses to see in the dark");
       Item vCasseroles             = new Item("pan", 750, "A pan for cooking food");
       Item vTicket                 = new Item("ticket" , 1, "a ticket");
       Item vLampe                  = new Item("light", 200, "a light");
       Item vBottes                 = new Item("boot", 1600, "a boot");
       Item vCookie                 = new Item("cookie", 20000, "a cookie");
       Item vCle                    = new Item("key", 150, "key");
       Item vGateau                 = new Item("cake", 500, "a chocolate cake");
       Item vCarte                  = new Item("map", 153, "a map");
       Item vParchemin1             = new Item("scroll", 6, "You ...");
       Item vParchemin2             = new Item("parchment", 6 ,"...win !");
       Item vInstruction            = new Item("rules", 1, "The scroll are in the dungeon and the crypt"); 
       //Affectation des Items
       vSalleDesGardes.addItem(vLunettesNocturnes);
       vSalleDesGardes.addItem(vLampe);
       vCuisineRoyales.addItem(vCasseroles);
       vCuisineRoyales.addItem(vCookie);
       vCuisineRoyales.addItem(vGateau);
       vPontLevis.addItem(vTicket);
       vBasseCour.addItem(vBottes);
       vRemparts.addItem(vCarte);
       vDonjon.addItem(vParchemin1);
       vCrypte.addItem(vParchemin2);
       vChapelle.addItem(vInstruction);
       vBasseCour.addItem(vCle);
       //Creation du personnage
       this.aPlayer = new Player("Abel", 1000);
       //Definition du point de depart
       this.aPlayer.setCurrentRoom(vPontLevis);
       this.aPlayer.addRoomOnStack(vPontLevis);
    }//createRooms
    /**
     * Procédure printHelp
     */
    private void printHelp()
    {
        this.aGui.println("You are lost. You are alone");
        this.aGui.println("You wander around at the castle.");
        this.aGui.println("");
        this.aGui.println("Your command words are: " + aParser.getCommandString());
    }//printHelp
    /**
     * Procédure privée goRoom
     * @param pCommand 
     */
    private void goRoom(final Command pCommand)
    {
        if ( ! pCommand.hasSecondWord() )
        {
            this.aGui.println( "Go where ?" );
            return;
        }
        String vDirection = pCommand.getSecondWord();
        
        Room vNextRoom = this.aPlayer.getCurrentRoom().getExit(vDirection);

        if (vNextRoom != null)
        {
            this.aPlayer.goRoomPlayer(vNextRoom);
            this.printLocationInfo();
        }
        else
            this.aGui.println( "There is no door !" );
    }//goRoom(Command pDir)
    /**
     *Procedure qui affiche les informations liées à la situation courante.
     */
    public void printLocationInfo()
    {
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());    
    }//printLocationInfo
    /**
     * Fonction booléenne privée processCommand : appeler la bonne méthode 
     * en fonction de la commande passée en paramètre.
     * @param pCommandLine
     */
    public void interpretCommand(final String pCommandLine)
    {
        this.aGui.println(">" + pCommandLine);
        Command vCommand = this.aParser.getCommand( pCommandLine );
        
        if ( vCommand.isUnknown() )
        {
            this.aGui.println("I don't know what you mean ...");
            return;
        }
        
        String vCommandWord = vCommand.getCommandWord();
        String vSecondWord = vCommand.getSecondWord();
        
        if( vCommandWord.equals( "help" ))
        {
            if(vSecondWord == null)
            {
                this.printHelp();
            }
            else if( vSecondWord.equals("go"))
            {
                this.aGui.println("go : this is a simple action to move.");
            }
            else
            {
                this.aGui.println("What is your problem ?");
            }
        }
            
            
        else  if ( vCommandWord.equals( "go" ))
        {
            this.goRoom( vCommand );
        }
            
            
        else  if ( vCommandWord.equals( "look" ))
        {
            if(vSecondWord == null)
            {
                this.look();
            }
            else
            {
                if(this.aPlayer.getCurrentRoom().itemInHashMap(vSecondWord)) 
                {
                    this.aGui.println(this.aPlayer.getCurrentRoom().allInformationItem(vSecondWord));
                }
                else
                {
                    this.aGui.println("This item is not in the room !");
                }
            }
        }
        
        else  if ( vCommandWord.equals( "eat" ))
        {
            if(vSecondWord == null)
            {
                this.eat();
            }
            else if (vSecondWord.equals("cookie"))
            {
                this.magicCookie();
            }
            else
            {
                this.aGui.println("What do you want ?");
            }
        }
            
        
        else if ( vCommandWord.equals( "back" ))
        {
            if(vSecondWord == null)
            {
                this.back();
            }
            else
            {
                this.aGui.println("Just write  : back");
            }
        }
        
        else if (vCommandWord.equals( "test" ))
        {
            this.test(vSecondWord);
        }
        
        else if (vCommandWord.equals( "drop"))
        {
            this.drop(vSecondWord);
        }
        else if (vCommandWord.equals( "take" ))
        {
            this.take(vSecondWord);
        }
        else if(vCommandWord.equals( "inventory"))
        {
            this.inventory();
        }
            
            
        else if ( vCommandWord.equals( "quit" ))
        {
            if ( vCommand.hasSecondWord() )
                this.aGui.println("Quit what ?");
            else
                this.endGame();
        }
        
        this.timeLimit();
    }//processCommand
    /**
     * Commande look()
     */
    private void look()
    {
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
    }//look
    /**
     * Commande eat()
     */
    private void eat()
    {
        this.aGui.println("You have eaten now and you are not hungry any more.");
    }//eat
    /**
     * Commande qui retourne dans la pièce précédente
     */
    private void back()
    {
        this.aPlayer.backPlayer();
        this.printLocationInfo();
    }//back
    /**
     * Procedure de fin du jeu
     */
    private void endGame()
    {
        this.aGui.println("Thank you for playing. Good bye.");
        this.aGui.enable(false);
    }//endGame
    /**
     * Fonction test executant successivement toutes les commandes lues dans ce fichier de texte
     * @param pNomFichier nom du fichier
     */
    public void test(final String pNomFichier)
    {
        if(pNomFichier == null)
        {
            this.aGui.println("Please enter the name of the file.");
        }
        else
        {
            Scanner vSc;
            
            java.io.InputStream vIS = this.getClass().getResourceAsStream( "/test/" + pNomFichier + ".txt" );             
            if(vIS == null)
            {
                this.aGui.println("This file does not exist");
            }
            else
            {
                vSc = new Scanner(vIS);
                while ( vSc.hasNextLine() ) 
                {
                    String vLigne = vSc.nextLine();
                    this.interpretCommand(vLigne);
                }
            }
        }
    }//test
    /**
     * Procedure take qui prends un objet se trouvant dans la salle où se trouve le joueur.
     * @param pString nom de l'objet
     */
    public void take(final String pString)
    {
        if(pString == null)
        {
            this.aGui.println("Take what ?");
        }
        else
        {
            if(!this.aPlayer.take(pString))
            {
                this.aGui.println("You can't take this item");
            }
            else
            {
                this.aGui.println("You take an item.");
            }
        }   
    }//take
    /**
     * Procedure drop qui depose un objet dans la salle où se trouve le joueur.
     * @param pString nom de l'objet
     */
    public void drop(final String pString)
    {
        if(pString == null)
        {
            this.aGui.println("Drop what ?");
        }
        else
        {
            if(!this.aPlayer.drop(pString))
            {
                this.aGui.println("This item does not exist !");
            }
            else
            {
                this.aGui.println("You drop an item.");
            }
        }        
    }//drop
    /**
     * Procedure qui affiche l'inventaire du joueur. 
     */
    public void inventory()
    {
        this.aGui.println(this.aPlayer.getItemList().inventory());
        this.aGui.println("Total weight : " + this.aPlayer.getCurrentWeightInventory() + " / " + this.aPlayer.getMaxWeightInventory());
    }//inventory
    /**
     * Procedure magicCookie qui double le poids maximal de l'inventaire du joueur. 
     */
    public void magicCookie()
    {
        Item vItem = this.aPlayer.getCurrentRoom().getItem("cookie");
        if(vItem != null)
        {
            int weight = this.aPlayer.getMaxWeightInventory();
            this.aPlayer.setMaxWeightInventory(2*weight);
            this.aGui.println("You ate a magic cookie. \n Your maximum inventory weight has just increased.");
            this.aPlayer.getCurrentRoom().removeItem("cookie");
        }
    }//magicCookie
    /**
     * Accesseur sur l'attribut aNumberOfAction;
     */
    public int getANumberOfAction()
    {
        return this.aNumberOfAction;
    }//getANumberOfAction
    /**
     * Modificateur sur l'attribut aNumberOfAction;
     * @param pNumberOfAction nombre d'action possible
     */
    public void setANumberOfAction(final int pNumberOfAction)
    {
        this.aNumberOfAction = pNumberOfAction;   
    }//setANumberOfAction
    /**
     * Procedure time limit
     */
    public void timeLimit()
    {
        int vNbrOfAction = this.getANumberOfAction();
        if(vNbrOfAction == 20)
        {
            this.endGame();
        }
        else
        {
            vNbrOfAction++; 
            this.setANumberOfAction(vNbrOfAction);        
        }
    }//timeLimit
}
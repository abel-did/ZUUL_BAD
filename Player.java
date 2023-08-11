import java.util.Stack;
import java.util.HashMap;
/**
 * La classe Player - un joueur du jeu Welcome to the Castle
 *
 * @author DIDOUH Abel
 * @date   04/01/2023
 * @version Exercice 7.43
 */
public class Player
{
    /**
     * Attributs 
     */
    private String aName;
    private Room aCurrentRoom;
    private Stack<Room> aStack;
    private UserInterface aGuiPlayer;   
    private ItemList aItemList;
    //v7.32
    private int aMaxWeightInventory;
    private int aCurrentWeightInventory;    
    /**
     * Constructeur naturel
     * @param pName         Name 
     * @param pMaxWeight    Weight
     */
    public Player(final String pName, final int pMaxWeight)
    {
        this.aName = pName;
        this.aStack = new Stack<Room>();
        //7.31.1
        this.aItemList = new ItemList();
        //7.32
        this.aMaxWeightInventory = pMaxWeight;
        this.aCurrentWeightInventory = 0;
    }//Player(.)
    /**
     * Procedure qui modifie l'attribut aCurrentRoom
     * @param pCurrentRoom salle actuelle
     */
    public void setCurrentRoom(final Room pCurrentRoom)
    {
        this.aCurrentRoom = pCurrentRoom;
    }//setCurrentRoom
    /**
     * Procedure qui modifie l'attribut aGuiPlayer
     * @param pGui l'interface utilisateur
     */
    public void setGUI(final UserInterface pGui)
    {
        this.aGuiPlayer = pGui;
    }//setGUI
    /**
     * Procedure qui ajoute le poids de l'objet passé en paramétre au poids total
     * @param pItemWeight poids de l'objet
     */
    public void addCurrentWeight(final int pItemWeight)
    {
        this.aCurrentWeightInventory = this.aCurrentWeightInventory + pItemWeight;
    }//addCurrentWeight
    /**
     * Procedure qui retire le poids de l'objet passé en paramétre au poids total
     * @param pItemWeight poids de l'objet
     */
    public void lessCurrentWeight(final int pItemWeight)
    {
        this.aCurrentWeightInventory = this.aCurrentWeightInventory - pItemWeight;
    }//lessCurrentWeight
    /**
     * Accesseur sur l'attribut aCurrentRoom
     * @return Room aCurrentRoom
     */
    public Room getCurrentRoom()
    {
        return this.aCurrentRoom;
    }//getCurrentRoom
    /**
     * Accesseur sur l'attribut aName
     * @return String aName
     */
    public String getName()
    {
        return this.aName;
    }//getName
    /**
     * Accesseur sur l'attribut aMaxWeightInventory
     * @return int aMaxWeightInventory
     */    
    public int getMaxWeightInventory()
    {
        return this.aMaxWeightInventory;
    }//getMaxWeightInventory
    /**
     * Accesseur sur l'attribut aCurrentWeightInventory
     * @return int aCurrentWeightInventory
     */ 
    public int getCurrentWeightInventory()
    {
        return this.aCurrentWeightInventory;
    }//getCurrentWeightInventory
    /**
     * Accesseur sur l'attribut aItemList
     * @return ItemList aItemList
     */
    public ItemList getItemList()
    {
        return this.aItemList;
    }//getItemList
    /**
     * Definit un poids maximal pour l'inventaire
     * @param pMaxWeightInventory poids maximal de l'inventaire
     */
    public void setMaxWeightInventory(final int pMaxWeightInventory)
    {
        this.aMaxWeightInventory = pMaxWeightInventory;
    }//setMaxWeightInventory
    /**
     * Procedure goRoomPlayer
     * @param pNextRoom prochaine chambre
     */
    public void goRoomPlayer(final Room pNextRoom)
    {
        this.addRoomOnStack(pNextRoom);
        this.aCurrentRoom = pNextRoom;  
    }//goRoomPlayer
    /**
     * Accesseur sur l'attribut aStack
     * @return Stack<Room> aStack
     */
    public Stack<Room> getRoomStack()
    {
        return this.aStack;
    }//getRoomStack
    /**
     * Procedure qui ajoute la salle à une pile.
     * @param pRoom Salle a ajouter
     */
    public void addRoomOnStack(final Room pRoom)
    {
        this.aStack.push(pRoom);
    }//addRoomOnStack
    /**
     * Procedure qui supprime la salle de la pile.
     */
    public Room removeRoomOnStack()
    {
        return this.aStack.pop();
    }//removeRoomOnStack
    /**
     * Procedure qui ramene le joueur dans la salle precedente.
     */
    public void backPlayer()
    {
        if(this.getRoomStack().empty() != true && this.aStack.size() > 1)
        {
            Room vPreviousRoom = this.removeRoomOnStack();
            Room vCurrentRoom = this.getRoomStack().peek();
            this.setCurrentRoom(vCurrentRoom);            
            
            //v7.43
            if(!vPreviousRoom.isExit(vCurrentRoom))
            {
                this.getRoomStack().clear(); 
                this.setCurrentRoom(vPreviousRoom);
                this.addRoomOnStack(vPreviousRoom);
            }
        }
        else
        {
            this.setCurrentRoom(this.getRoomStack().peek());
        }
    }//backPlayer
    /**
     * Procedure take qui prends un objet se trouvant dans la salle où se trouve le joueur.
     * @param pItemName nom de l'objet
     */
    public boolean take(final String pItemName)
    {    
        Item vItem = this.aCurrentRoom.getItem(pItemName);
        if(vItem != null)
        {
            this.addCurrentWeight(vItem.getWeight());
            if(this.aCurrentWeightInventory <= this.aMaxWeightInventory)
            {
                this.aItemList.putItem(pItemName, vItem);
                this.aCurrentRoom.removeItem(pItemName);
    
                return true;
            }
            else
            {
                this.lessCurrentWeight(vItem.getWeight());               
            }
        }
        return false;
    }//take
    /**
     * Procedure drop qui depose un objet dans la salle où se trouve le joueur.
     * @param pItemName nom de l'objet
     */
    public boolean drop(final String pItemName)
    {
        Item vItem = this.aItemList.getItem(pItemName);
        if(vItem != null)
        {
            this.aCurrentRoom.addItem(vItem);
            this.aItemList.removeItem(pItemName);
            this.lessCurrentWeight(vItem.getWeight());     
            return true;
        }
        return false;
    }//drop
}

import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author DIDOUH Abel
 * @date   04/01/2023
 * @version Exercice 7.43
 */
public class Room
{
    /**
     * Attributs (private)
     */
    private String aDescription;
    private String aNameRoom;
    private HashMap<String, Room> aExits;
    private String aImageName;
    private ItemList aItemList;   
    /**
     * Cree une piece decrite par la chaine "description", la chaine pImage est le chemin relatif
     * @param pDescription Description de la piece
     * @param pImage
     */
    public Room(final String pNameRoom,final String pDescription, final String pImage)
    {
        this.aNameRoom = pNameRoom;
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        //v 22
        this.aImageName = pImage;
        this.aItemList = new ItemList();
    }//Room()
    /**
     * Renvoie la piece atteinte si nous nous deplacons dans la direction.
     * @param pDirection Direction de la piece
     * @return Room
     */
    public Room getExit(final String pDirection)
    {
        return this.aExits.get(pDirection);
    }//getExit
    /**
     * Accesseur sur l'attribut aNameRoom
     * @return String
     */
    public String getNameRoom()
    {
        return this.aNameRoom;
    }
    /**
     *Accesseur sur l'attribut aDescription
     *@return String
     */
    public String getDescription()
    {
        return this.aDescription;
    }
    /**
     * Definit une sortie pour cette piece
     * @param pDirection la direction de la sortie
     * @param pNeighbor La salle dans la direction donnee
     */
    public void setExit(final String pDirection, final Room pNeighbor)
    {
        this.aExits.put(pDirection, pNeighbor);
    }//setExit
    /**
     * Renvoie une description des sorties de la piece.
     * Exercice 7.7
     * Exercice 7.9
     * @return les sorties disponibles
     */
    public String getExitString()
    {
        String returnString = "Exits : ";
        Set<String> keys = aExits.keySet();
        for(String aExits : keys)
        {
            returnString += " " + aExits;
        }
        return returnString;
    }//getExitString 
    /**
     * Renvoie une description détaillée de cette pièce
     * @return une description de la piece, avec les sorties
     */
    public String getLongDescription()
    {
        return "You are " + this.getDescription() + "\n" +this.getExitString() + "\n" + this.getItemsString();
    }//getLongDescription
    /**
     * Renvoie une chaine décrivant le nom de l'image de la pièce.
     * @return String aImageName
     */
    public String getImageName()
    {
        return this.aImageName;
    }//getImageName
    /**
     * Procedure qui affecte un Item à une Room
     * @param pItem objet
     */
    public void addItem(final Item pItem)
    {
        this.aItemList.putItem(pItem.getName(), pItem);
    }//addItem
    /**
     * Renvoie une description des Items de la piece
     * @return String vReturnString
     */
    public String getItemsString()
    {
        return this.aItemList.getItemsString();
    }//getItemsString
    /**
     * Renvoie un Item 
     * @param pItem Chaine nom de l'Item
     * @return Item 
     */
    public Item getItem(final String pItemName)
    {
        return this.aItemList.getItem(pItemName);
    }//getItem
    /**
     * Fonction booleen qui renvoie Vrai si un item est dans la Hashmap, faux sinon.
     * @param pItem Chaine nom de l'Item
     * @return Boolean
     */
    public boolean itemInHashMap(final String pItemName)
    {
        return this.aItemList.itemInList(pItemName);
    }//itemInHashMap
    /**
     * Renvoie une description détaillé de l'item de la piece 
     * @param pItem Chaine nom de l'Item
     * @return String Item.getLongDescription()
     */
    public String allInformationItem(final String pItemName)
    {
        return this.aItemList.getItem(pItemName).getLongDescription();
    }//allInformationItem
    /**
     * Procedure qui supprime un Item de la piece
     * @param pItemName nom de l'objet
     */
    public void removeItem(final String pItemName)
    {
        this.aItemList.removeItem(pItemName);
    }//removeItem
    /**
     * Fonction booléenne qui renvoie Vrai si la Room en parametre est une sortie.
     * @param pRoom nom de la Room 
     * @return Boolean
     */
    public boolean isExit(final Room pRoom)
    {
        Set<String> keys = aExits.keySet();
        List<String> vListNameRoom =new ArrayList<String>();  
       
        for(String aExits : keys)
        {
            vListNameRoom.add(this.getExit(aExits).getNameRoom());
        }
        
        return vListNameRoom.contains(pRoom.getNameRoom());
    }
} // Room

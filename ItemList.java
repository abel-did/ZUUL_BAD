import java.util.HashMap;
import java.util.Set;
/**
 * Classe ItemList - Les items du jeu
 *
 * @author DIDOUH Abel
 * @date   04/01/2023
 * @version Exercice 7.43
 */
public class ItemList
{
    /**
     * Attributs (private)
     */
    private HashMap<String, Item> aItems;
    /**
     * Constructeur par défaut
     */
    public ItemList()
    {
        this.aItems = new HashMap<String, Item>();
    }
    /**
     * Accesseur sur l'attribut aItems
     * @param pName nom de l'objet
     * @return Item aItems
     */
    public Item getItem(final String pName)
    {
        return this.aItems.get(pName);
    }
    /**
     * Procedure qui ajoute un item
     * @param pName nom de l'objet
     * @param pItem objet
     */
    public void putItem(final String pName, final Item pItem)
    {
        this.aItems.put(pName, pItem);
    }
    /**
     * Procedure qui supprime un item
     * @param pName nom de l'objet
     */
    public void removeItem(final String pName)
    {
        this.aItems.remove(pName);
    }
    /**
     * Fonction qui renvoie une chaine comprennant les items
     */
    public String getItemsString()
    {
        if(this.aItems.isEmpty())
        {
            return "No item here.";
        }
        else
        {
            String vReturnString = "Items : ";
            Set<String> keys = aItems.keySet();
            for(String aItems : keys)
            {
                vReturnString += " " + aItems;
            }
            return vReturnString;
        }
    }
    /**
     * Procedure qui verifie si l'item est dans la liste
     * @param pItemName nom de l'objet
     */
    public boolean itemInList(final String pItemName)
    {
        if(this.aItems.containsKey(pItemName))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Fonction qui retourne l'inventaire du joueur.
     */
    final String inventory()
    {
        String vReturnString = "Items : \n";
        Set<String> keys = aItems.keySet();
        
        if(!this.aItems.isEmpty())
        {
            for(String aItems : keys)
            {
                vReturnString += "\t" + aItems + " \n"; 
            }
            return vReturnString;
        }
        else
        {
            return ("No items in the inventory.\n");
        }
    }//Inventory
}

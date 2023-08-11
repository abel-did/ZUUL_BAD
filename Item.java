/**
 * Classe Item - un Item du jeu Welcome to the Castle
 *
 * @author DIDOUH Abel
 * @date   04/01/2023
 * @version Exercice 7.43
 */
public class Item
{
    /**
     * Attributs (private)
     */
    private String aName;
    private int aWeight;
    private String aDescription;
    /**
     * Constructeur naturel de la classe Item
     * @param pName     Name 
     * @param pWeight   Weight
     * @param pDescription Description
     */
    public Item(final String pName,final int pWeight, final String pDescription)
    {
        this.aName = pName;
        this.aWeight = pWeight;
        this.aDescription = pDescription;
    }//Items()
    /**
     * Accesseur sur l'attribut aWeight
     * @return int aWeight
     */
    public int getWeight()
    {
        return this.aWeight;
    }//getWeight
    /**
     * Accesseur sur l'attribut aDescription
     * @return String aDescription
     */
    public String getDescription()
    {
        return this.aDescription;
    }//getDescription
    /**
     * Accesseur sur l'attribut aName
     * @return String aName
     */
    public String getName()
    {
        return this.aName;
    }//getName
    /**
     * Fonction qui renvoie la description (Name - Weight - Description) d'un item
     * @return String Name Weight Description
     */
    public String getLongDescription()
    {
        return  "Name : " + this.getName() + "\n" +
                "Weight : " + this.getWeight() + "\n" +
                "Description : " + this.getDescription();
    }//getLongDescription
}

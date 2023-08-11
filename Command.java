/**
 * Classe Command - une commande du jeu d'aventure Zuul.
 *
 * @author DIDOUH Abel
 * @date   04/01/2023
 * @version Exercice 7.43
 */
public class Command
{
    /**
     * Attributs (private)
     */
    private String aCommandWord;
    private String aSecondWord;
    
    /**
     * Constructeur naturel
     * @param pCommandWord chaine pour initialiser aCommandWord
     * @param pSecondWord chaine pour initialiser aSecondWord
     */
    public Command(final String pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord  = pSecondWord; 
    }//Command()
    /**
     * Accesseur aCommandWord
     * @return String aCommandWord
     */
    public String getCommandWord()
    {
        return this.aCommandWord;
    }//getCommandWord
    /**
     * Accesseur aSecondWord
     * @return String aSecondWord
     */
    public String getSecondWord()
    {
        return this.aSecondWord;
    }//getSecondWord
    /**
     * Fonction booléenne qui permettra de vérifier qu'un second 
     * mot a bien été tapé.
     * @return boolean
     */
    public boolean hasSecondWord()
    {
        return this.aSecondWord != null;
    }//hasSecondWord
    /**
     * Fonction booléenne qui retourne vrai si le premier mot est null.
     * @return boolean
     */
    public boolean isUnknown()
    {
        return this.aCommandWord == null;
    }//isUnknown
} // Command

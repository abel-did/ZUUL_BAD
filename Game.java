/**
 * Classe Game - le moteur du jeu : Welcome to the Castle
 * 
 * @author DIDOUH Abel
 * @date   04/01/2023
 * @version Exercice 7.43
 */
public class Game
{
    /**
     * Attributs (private)
     */
    private UserInterface aGui;
    private GameEngine aEngine;
    /**
     * Creer le jeu
     */
    public Game()
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface( this.aEngine );
        this.aEngine.setGUI( this.aGui );
    }
}//Game

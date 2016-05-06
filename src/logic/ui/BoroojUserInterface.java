package logic.ui;

import logic.controllers.World;

/**
 * game interaction logic with the gui
 */
public interface BoroojUserInterface {
    /**
     * Updates the view for the ui using world
     */
    void display();

    /**
     * Sets the current world for the ui
     *
     * @param world the world to be set
     */
    void setWorld(World world);

    /**
     * it is called on game over
     */
    void onGameOver();

    /**
     * it is called on defeat
     */
    void onLose();

    /***
     * it is called on win
     */
    void onWin();
}

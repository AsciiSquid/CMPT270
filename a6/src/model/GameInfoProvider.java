package model;

import java.util.List;


/**
 * The information provided by the GameModel for outside access.
 */
public interface GameInfoProvider {
    void addObserver(GameObserver observer);

    List<GameObject> getGameObjects();

    boolean isOver();

    boolean isPaused();

    int getLevel();

    int getPlayerScore();

    int getPlayerLives();

    int getTick();

    void setInvaderSynchronizationObject(Object reference);
}

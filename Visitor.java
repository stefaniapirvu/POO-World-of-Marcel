package GAME.player;

public interface Visitor <T extends Entity>{
    void visit (T entity);
}

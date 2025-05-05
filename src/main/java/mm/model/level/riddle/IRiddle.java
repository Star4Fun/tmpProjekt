package mm.model.level.riddle;

import mm.model.physics.GameUpdateLoop;
import mm.model.physics.GameWorld;

public interface IRiddle {

    /** Initialisiert das Rätsel (Physikobjekte, Szene etc.) */
//    TODO: Rausfinden, wie die Sachen übergeben werden
    void initialize(GameWorld welt);

    /** Pro Frame aufrufen: Physik aktualisieren, Objekte bewegen etc. */
//    TODO: Kontrollieren, ob GameUpdateLoop.update() aufgerufen wird oder wie das geht
    void update(float zeitDelta);

    /** Gibt true zurück, wenn die Bedingung für das gelöste Rätsel erfüllt ist */
    boolean isSolved();

    /** Setzt das Rätsel zurück in den Anfangszustand */
    void reset();
}
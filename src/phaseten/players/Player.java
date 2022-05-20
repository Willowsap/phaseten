package phaseten.players;

import phaseten.Card;
import phaseten.Hand;

public abstract class Player {
    protected Hand hand;

    public abstract Card takeTurn();
}

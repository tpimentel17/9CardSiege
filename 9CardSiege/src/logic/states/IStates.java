package logic.states;

import java.io.Serializable;

public interface IStates extends Serializable {
    IStates startGame();    //AwaitBeginning -> AwaitTopCardToBeDrawn
    IStates drawTopCard();  //AwaitTopCardToBeDrawn -> AwaitPlayerActionSelection
                            //AwaitTopCardToBeDrawn -> AwaitGameFinish
}

package logic.states;

import java.io.Serializable;

public interface IStates extends Serializable {
    IStates startGame();        //AwaitBeginning -> AwaitTopCardToBeDrawn
    IStates drawTopCard();      //AwaitTopCardToBeDrawn -> AwaitPlayerActionSelection
                                //AwaitTopCardToBeDrawn -> AwaitGameFinish
    IStates archersAttack();    //AwaitPlayerActionSelection -> AwaitTrackSelection
    IStates attackSelectedTrack(String action, String selectedTrack);   //AwaitTrackSelection -> AwaitPlayerActionSelection
                                                                        //AwaitTrackSelection -> AwaitTopCardToBeDrawn
                                                                        //AwaitTrackSelection -> AwaitGameFinish
    IStates boilingWaterAttack();   //AwaitPlayerActionSelection -> AwaitTrackSelection
    IStates restartGame();          //AwaitGameFinish -> AwaitGameBegining
}

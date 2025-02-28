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
    IStates restartGame();      //AwaitGameFinish -> AwaitGameBegining
    IStates closeCombat();      //AwaitPlayerActionSelection -> AwaitTrackSelection
    IStates coupure();      //AwaitPlayerActionSelection -> AwaitPlayerActionSelection
                            //AwaitPlayerActionSelection -> AwaitTopCardToBeDrawn
                            //AwaitPlayerActionSelection -> AwaitGameFinish
    IStates rallyTroopsOptions();   //AwaitPlayerActionSelection -> AwaitOptionSelection
    IStates rallyTroops(boolean drm);   //AwaitOptionSelection -> AwaitPlayerActionSelection
                                        //AwaitOptionSelection -> AwaitTopCardToBeDrawn
                                        //AwaitOptionSelection -> AwaitGameFinish
    IStates tunnelMovement();   //AwaitPlayerActionSelection -> AwaitMovementTypeSelection
    IStates moveSoldiers(int movementType);     //AwaitMovementTypeSelection -> AwaitPlayerActionSelection
                                                //AwaitMovementTypeSelection -> AwaitTopCardToBeDra//AwaitMovementTypeSelection -> AwaitPlayerActionSelectionwn
                                                //AwaitMovementTypeSelection -> AwaitGameFinish
    IStates supplyRaid();   //AwaitPlayerActionSelection -> AwaitPlayerActionSelection
                            //AwaitPlayerActionSelection -> AwaitTopCardToBeDrawn
                            //AwaitPlayerActionSelection -> AwaitGameFinish
    IStates sabotage();     //AwaitPlayerActionSelection -> AwaitPlayerActionSelection
                            //AwaitPlayerActionSelection -> AwaitTopCardToBeDrawn
                            //AwaitPlayerActionSelection -> AwaitGameFinish
    IStates additionalActionPointsSelected();   //AwaitPlayerActionSelection -> AwaitAdditionalActionPointsSelected
    IStates additionalActionPoint(int point);    //AwaitAdditionalActionPointsSelected -> AwaitPlayerActionSelection
}

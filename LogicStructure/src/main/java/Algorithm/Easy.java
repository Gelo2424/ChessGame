package Algorithm;



import Piece.Move;
import Piece.PieceSet;

import java.util.Random;

public class Easy extends Algorithm{

    Random random = new Random();

    @Override
    public void makeMove() {
        int index = random.nextInt(gameSet.getPossibleMoves().size());

        Move nextMove =  gameSet.getPossibleMoves().get(index);



       for(Move possibleMove : gameSet.getPossibleMoves()) {
           while(possibleMove.isDestination(nextMove)) {
               gameSet.getPossibleMoves().remove(index);
               index = random.nextInt(gameSet.getPossibleMoves().size());
               nextMove =  gameSet.getPossibleMoves().get(index);
           }
       }

      // gameSet.move(nextMove);


    }
}

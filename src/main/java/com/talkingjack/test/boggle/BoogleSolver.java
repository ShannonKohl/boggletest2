package com.talkingjack.test.boggle;

import java.util.ArrayList;
import java.util.List;

import com.talkingjack.util.DictionaryTrie;
import com.talkingjack.util.DictionaryTrie.WordValidator;

public class BoogleSolver {
	private DictionaryTrie dictionary;
	public BoogleSolver( String[] dictionaryArray ){
		for( String word : dictionaryArray ){
			dictionary.add( word );
		}
	}
	public Iterable<String> getAllValidWords( BoggleBoard board ){
		ArrayList<String> allWords = new ArrayList<>();
		for( int col = 0; col < board.cols(); col++ ){
			for( int row = 0; row < board.rows(); row++ ){
				Position current = new Position( row, col );
				String word = checkWord( current, new NeighborVisitor( current, board.rows(), board.cols()), dictionary.startEvaluation(), board, allWords );
				if( word != null ){
					allWords.add( word );
				}
			}
		}
		return allWords;
	}
	
	private String checkWord( Position current, NeighborVisitor neighbors, WordValidator validator, BoggleBoard board, List<String> foundWords ){
//		boolean done = false;
//		String word = null;
//		char letter = board.getLetter( current );
//		while( !done ){
//			if( validator.nextLetter( letter )){
//				Position next = neighbors.next();
//				word = checkWord( next, new NeighborVisitor( next, board.rows(), board.cols()), validator, board );
//				if( word != null ){
//					foundWords.add( word );
//					//proceed to next neighbor
//				}
//			}else{
//				
//			}
//		}
		return null;
	}
	/**
	 * This class implements a walk around a position on a game board, starting at the 1:30 position if directly above is a 12 o'clock.
	 * @author shannonkohl
	 *
	 */
	class NeighborVisitor {
		private int maxRow;
		private int maxCol;
		private Position location;
		private int count = 0;
		private boolean isRow = true;
		private boolean isIncrement = true;
		public NeighborVisitor( Position center, int maxRow, int maxCol ){
			location = center;
			location.decrementRow();
			location.incrementCol();
			this.maxRow = maxRow;
			this.maxCol = maxCol;
			//TODO: handle invalid
		}

		public Position next(){
			if( count == 0 ){
				count++;
				return location;
			}
			if( count % 3 == 0 ){
				isRow = !isRow;
				isIncrement = !isIncrement;
			}
			count++;
			if( isRow ){
				if( isIncrement ){
					location.incrementRow();
				}else{
					location.decrementRow();
				}
			}else{//is col
				if( isIncrement ){
					location.incrementCol();
				}else{
					location.decrementCol();
				}				
			}
			if( !isValid()){
				return next();
			}
			return location;
		}
		private boolean isValid(){
			return location.getRow() >= 0 && location.getRow() <= maxRow && location.getCol() >= 0 && location.getCol() <= maxCol;
		}
	}
}

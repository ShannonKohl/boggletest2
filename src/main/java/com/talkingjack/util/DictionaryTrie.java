package com.talkingjack.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//ideally this should implement an interface
public class DictionaryTrie {
	private final TrieNode root = new TrieNode();
	
	public void add( String toAdd ){
		TrieNode currNode = root;
		for( int i = 0; i < toAdd.length(); i++ ){
			char curr = toAdd.charAt( i );
			if( currNode.hasChild( curr )){
				currNode = currNode.getChild( curr );
			}else{
				TrieNode child = new TrieNode();
				child.key = curr;
				child.parent = currNode;
				currNode.children.put( curr, child );
				currNode = child;
			}
		}
		currNode.valid = true;
	}
	
	public boolean isWord( String word ){
		TrieNode currNode = root;
		for( int i = 0; i < word.length(); i++ ){
			currNode = currNode.getChild( word.charAt( i ));
			if( currNode == null ){
				return false;
			}
		}
		return currNode.isValid();
	}
	
	public WordValidator startEvaluation(){ return new WordValidator(); }//Maybe interface instead
	
	public class WordValidator {
		private TrieNode current = root;
		public boolean nextLetter( char letter ){
			if( current.hasChild( letter )){
				current = current.getChild( letter );
				return true;
			}
			return false;
		}
		public boolean isValidWord(){ return current.isValid(); }
	}
	
	//TODO: make final for a one writer, many reader trie, will need rewrite logic and a ReadWriteLock. May not be worth it since tree is only added to, never removed from in current usecase
	private class TrieNode {//maybe s/b generic? But I think Tries are primarily used for strings? But could allow for compressing like "tr"
		private Character key;
		private Map<Character, TrieNode> children = new HashMap<>();
		private TrieNode parent;
		
		private boolean valid = false;//whether a valid word or not since a word that is a prefix of another word will not be a leaf node
		
		public String getValue(){
			StringBuilder sb = new StringBuilder( key );
			TrieNode curr = parent;
			while( curr.parent != null ){
				sb.append( curr.key );
				curr = curr.parent;
			}
			return sb.reverse().toString();
		}
		public boolean isValid(){ return valid; }
		public Character getKey(){ return key; }
		public TrieNode getChild( char key ){ return children.get( key ); }
		public boolean hasChild( char key ){ return children.containsKey( key ); }
		
		@Override
		public boolean equals( Object other ){ return key.equals( ((TrieNode)other).key ); }
		@Override
		public int hashCode(){ return key.hashCode(); }
	}
}

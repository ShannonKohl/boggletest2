package com.talkingjack.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.Callable;

public class DictionaryLoader implements Callable<DictionaryTrie> {
	private String dictionaryPath;

	@Override
	public DictionaryTrie call() throws Exception {
		try( BufferedReader in = new BufferedReader( new FileReader( dictionaryPath ))){
			DictionaryTrie dt = new DictionaryTrie();
			String word = in.readLine();
			while( word != null ){
				dt.add( word );
			}
			return dt;
		}
	}
	
}

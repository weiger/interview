package com.interview.basics.model.tree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Trie {

	private TrieNode root;
	
	public Trie() {
		this.root = new TrieNode();
	}
	
	public void addWord(String word) {
		TrieNode node = this.root;
		for(char c : word.toCharArray()){
			node = node.addChild(c);
			if (node == null) 
				return;
		}
		node.setWord(true);
	}
	
	public boolean isWord(String s) {
		TrieNode node = this.root;
		for(char c : s.toCharArray()){
			node = node.get(c);
			if(node == null)
				return false;
		}
		return node.isWord();
	}
	
	public TrieNode match(String s) {
		TrieNode node = this.root;
		for(char c : s.toCharArray()){
			node = node.get(c);
			if(node == null)
				return null;
		}
		
		return node;
	}

    public static Trie loadDictionary(String dictionary){
        Trie trie = new Trie();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(dictionary));
            String line = null;
            while( (line = reader.readLine()) != null) {
                String word = line.trim().toLowerCase();
                trie.addWord(word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return trie;
    }

}

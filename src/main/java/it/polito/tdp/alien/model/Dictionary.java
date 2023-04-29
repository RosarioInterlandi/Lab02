package it.polito.tdp.alien.model;

import java.util.LinkedList;
import java.util.TreeMap;

public class Dictionary {
	TreeMap<String, LinkedList<String>> alienDictionary = new TreeMap<String, LinkedList<String>>();

	public boolean addWord(String alienWord, String translation) {
		if (this.checkWords(alienWord) && this.checkWords(translation)) {
			if (alienDictionary.containsKey(alienWord.toLowerCase())) {
				alienDictionary.get(alienWord.toLowerCase()).add(translation.toLowerCase());
			} else {
				LinkedList<String> temporaryList = new LinkedList<String>();
				temporaryList.add(translation.toLowerCase());
				alienDictionary.put(alienWord.toLowerCase(), temporaryList);
			}
			return true;

		} else {
			return false;
		}
	}

	public String searchTranslation(String alienWord) {
		if (!this.checkWords(alienWord))
			return "Word is not valid\n";
		if (alienDictionary.containsKey(alienWord.toLowerCase())) {
			String translations = "";
			for (String translation : alienDictionary.get(alienWord.toLowerCase())) {
				if (translations.compareTo("") != 0)
					translations += "\n";
				translations += translation;
			}
			return translations;
		} else {
			return "Word not found in dictionary\n";
		}
	}

	public boolean checkWords(String s) {
		char[] chars = s.toCharArray();
		boolean flagCountQuestionMark = false;

		for (char c : chars) {
			if (!Character.isLetter(c) && Character.compare(c, '?') != 0) {
				return false;
			}
			if (Character.compare(c, '?') == 0) {
				if (flagCountQuestionMark == false) {
					flagCountQuestionMark = true;
				} else {
					return false;
				}
			}
		}

		return true;
	}

	public String translateWordWildCard(String alienWildCard) {

		// Utilizzo le regular expression di Java (posso usare stringa.matches())
		// Sostituisco "?" con "."
		// "." nelle regex indica un qualsiasi carattere
		alienWildCard = alienWildCard.replaceAll("\\?", ".");
		int counter =0;
		for (Character c: alienWildCard.toCharArray()) {
			if (c.equals('.'))
				counter++;
		}
		if (counter>1)
			return "Only one question mark for wild card";
		
		LinkedList<String> wordsBinded = new LinkedList<String>();
		for (String keySearch : alienDictionary.keySet()) {
			if (keySearch.matches(alienWildCard)) {
				wordsBinded.add(keySearch);
			}
		}
		
		String translations = "";
		for (String keyTranslate: wordsBinded) {
			translations+= keyTranslate+ ":";
			for (String translation : alienDictionary.get(keyTranslate.toLowerCase())) {
				if (translations.compareTo("") != 0)
					translations += "\n";
				translations += translation;
			}
		}
		return translations;

	}

}

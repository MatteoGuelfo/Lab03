package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Dictionary {
	
	private List<String> dizionario;
	private List<RichWord> parole;
	private Map<String, RichWord> paroleRicevute;
	
	
	public Dictionary() {
		this.dizionario = new LinkedList<>();
		this.parole = new LinkedList<>(); 
		this.paroleRicevute = new TreeMap<>();
	}


	public void loadDictionary(String lingua) {
		int passi=0;
		 
		try {
			FileReader fr = null;
			passi++;
			if(lingua.equals("Italiano"))
				fr = new FileReader("Italian.txt");
			else if(lingua.equals("English")) 
				fr = new FileReader("English.txt");
			
			passi++;
			BufferedReader br = new BufferedReader(fr);
			passi++;
			String parola; 
			while((parola=br.readLine()) != null) {
				dizionario.add(parola);
				passi++;
			}
			
			br.close();
			fr.close();		
			
		}catch(IOException e){
			System.out.print("Errore nella lettura del file"+ passi);
			
		}
	}
	
	public List<RichWord> spellChecker (List<String> inputTextList){
		List<RichWord> ritorno =new LinkedList<>();
		
		//aggiungo tutte le parole alla lista e alla mappa
//		for(String i: inputTextList) {
//			if(!paroleRicevute.containsKey(i)) {
//				RichWord parola= new RichWord(i);
//				ritorno.add(parola);
//				parole.add(parola); 
//				paroleRicevute.put(i,parola);
//		}
				
//		}
		
		for(String s:inputTextList) {
			for(String str: dizionario) {
				if(str.equals(s)) {
//					paroleRicevute.get(s).setCorretta(true);
					ritorno.add(new RichWord(s,true));
				}else {
					ritorno.add(new RichWord(s,false));
				}
					
			}
		}
		
		return ritorno;
		
 	}
	
	

}

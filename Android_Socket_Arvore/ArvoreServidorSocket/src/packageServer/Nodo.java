package packageServer;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;

public class Nodo implements Serializable {
	
	public static final long  serialVersionUID = 1L;
	
	Vector filhos;
	Nodo pai;
	String nome;
	
	public Nodo(String s) {
		filhos = new Vector(4);
		nome = s;
		}
	
	public void addFilho(Nodo n) {
		//Filhos.addElement(n);
		filhos.addElement(n);
		n.pai = this; 
	}
	
	public Integer sizeFilhos() {
		return filhos.size();
	}
	
	public String getFilho(Integer i) {
		return filhos.get(i).toString();
	}
	
//	public void getFilhos() {
//		Iterator iter1 = filhos.iterator();
//		while(iter1.hasNext()){
//		        System.out.println(iter1.next());
//		}
//	}
	
}
package packageClient;

import java.io.ObjectOutputStream;
import java.net.Socket;

import packageServer.Nodo;

public class ClientMain {
	
	public static void main(String[] args) {
		try {

			Socket cliente = new Socket("localhost", 4444);
			System.out.println("Cliente rodando");
			
			Nodo top = new Nodo("topo");
			top.addFilho(new Nodo("filho1"));
			top.addFilho(new Nodo("filho2"));
			
			//log
			System.out.println(top.sizeFilhos());
			System.out.println(top.toString());
			for(int i=0;i<top.sizeFilhos();i++){
				System.out.println(top.getFilho(i));
			}
			
			//top.getFilhos();
			
			ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
			out.writeObject(top);
			out.flush();
			cliente.close();
			
		}
		catch (Exception e) {  
            e.getStackTrace();  
        }
    }

}

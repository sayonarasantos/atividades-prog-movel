package packageServer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	
	public static void main(String[] args) {
		try {
			
			ServerSocket server= new ServerSocket(4444);
			System.out.println("Servidor rodando");
			Socket socket = server.accept();
			
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			Nodo n = (Nodo)in.readObject();
			n.addFilho(new Nodo("servidor"));
			
			//log
			for(int i=0;i<n.sizeFilhos();i++){
				System.out.println(n.getFilho(i));
			}
			
			//n.getFilhos();
			
			out.writeObject(n);
			out.flush();
			socket.close();
			server.close();
			
			}
		catch (Exception e) {  
            e.getStackTrace();  
        }
    } 

}
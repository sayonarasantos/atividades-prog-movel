package appsocket.clientsocket;

/**
* Created by Sayonara on 06/06/2017.
*/

import android.os.AsyncTask;
import android.util.Log;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends AsyncTask<Void, Void, Void> {

    String dstAddress;

    Client(String addr) {
        dstAddress = addr;
    }

    @Override
    protected Void doInBackground (Void... params){
        try {
            Socket cliente = new Socket(dstAddress, 4444);
            //System.out.println("Cliente rodando");
            Log.i("TESTE", "Cliente rodando");

            Nodo top = new Nodo("topo");
            top.addFilho(new Nodo("filho1"));
            top.addFilho(new Nodo("filho2"));

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
            Log.e("ERRO", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute (Void result){
        super.onPostExecute(result);
    }
}



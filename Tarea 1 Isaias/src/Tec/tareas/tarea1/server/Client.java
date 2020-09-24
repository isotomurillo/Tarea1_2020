package Tec.tareas.tarea1.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
    final String dis;
    final String dos;

    public Client(String dis, String dos) {
        this.dis=dis;
        this.dos=dos;
    }

    public static void main (String[] args) throws IOException{
        try{
            Scanner scn = new Scanner(System.in);

            InetAddress ip = InetAddress.getByName("localhost");

            Socket s = new Socket(ip, 5056);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            while(true){
                System.out.println(dis.readUTF());
                String tosend = scn.nextLine();
                dos.writeUTF(tosend);

                if (tosend.equals("Salir")){
                    System.out.println("Cerrando conección");
                    s.close();
                    System.out.println("Se cerró");
                    break;
                }

                String recived = dis.readUTF();
                System.out.println(recived);
            }
            scn.close();
            dis.close();
            dos.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

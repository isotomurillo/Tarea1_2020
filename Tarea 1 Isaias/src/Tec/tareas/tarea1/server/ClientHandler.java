package Tec.tareas.tarea1.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler extends Thread {
    DateFormat fordate = new SimpleDateFormat("yyy/MMM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");

    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos){
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run(){
        String recived;
        String toreturn;
        while (true){
            try{
                dos.writeUTF("Qué informacion quiere?[Fecha | Hora]...\n" + "Escriba salir para terminar");

                recived = dis.readUTF();

                if (recived.equals("Salir")){
                    System.out.println(this.s + " quiere salir");
                    this.s.close();
                    System.out.println("se cerró");
                    break;
                }

                Date date = new Date();

                switch (recived){
                    case "Fecha":
                        toreturn=fordate.format(date);
                        dos.writeUTF(toreturn);
                        break;
                    case "Hora":
                        toreturn=fortime.format(date);
                        dos.writeUTF(toreturn);
                        break;
                    default:
                        dos.writeUTF("entrada inválida");
                        break;


                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try{
            this.dis.close();
            this.dos.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}

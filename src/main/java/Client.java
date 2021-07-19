//import io.netty.channel.unix.Socket;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        byte[] buf = new byte[8192];
        int i = 0;
        int j = 0;

        MyMessage msgServer = new MyMessage();

        try (Socket socket = new Socket("localhost", 8189);
             ObjectEncoderOutputStream oeos = new ObjectEncoderOutputStream(socket.getOutputStream()); //поток на сервер
             ObjectDecoderInputStream odis = new ObjectDecoderInputStream(socket.getInputStream());
             FileInputStream in = new FileInputStream("C:\\Stalker_-_Antologia_1.iso");
             FileInputStream in2 = new FileInputStream("C:\\Stalker_-_Antologia_1.iso")) {

            while (in.read(buf) > 0) { //поискать метод проверки количества пакетов
                j=j+1;
            }
            in.close();

            while (in2.read(buf) > 0) {
                i=i+1;

                msgServer.setCount(i);//номер конкретного пакета
                msgServer.setCountR(j);//сколько вообще пакетов
                msgServer.setBuf(buf);//кусочек файла

                oeos.writeObject(msgServer);//отправляем в поток объект с заполненными полями
                oeos.flush();
            }
            oeos.close();
            in2.close();
            System.out.println("Передача завершена.");

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import sun.java2d.pipe.SpanShapeRenderer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

public class ServerHandler extends SimpleChannelInboundHandler<MyMessage> {

    FileOutputStream out = new FileOutputStream("C:\\Пример\\Stalker_-_Antologia_1.iso");//нужно как-то через отдельный поток
    int percent = 0;
    String str = "";

    public ServerHandler() throws FileNotFoundException {
        //System.out.println("Файл не найден");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client connected...");
        System.out.println(LocalDateTime.now());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client disconnected...");
        System.out.println(LocalDateTime.now());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {//нужно как-то через отдельный поток
        out.write(msg.getBuf());

        if (percent<((msg.getCount()*100) / msg.getCountR())){
            percent = ((msg.getCount()*100) / msg.getCountR());
            str = str + "*";
            System.out.print("\r"+ str + " " + percent + " %");

        }

        if (msg.getCount() == msg.getCountR()){
            System.out.println();
            System.out.println("поток записи файла завершен");
            out.close();
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
package server.com;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class studenns {
    HttpServer server;

    public static void main(String[] args){
        System.out.println("Server has started");

        studenns stud = new studenns();
        stud.init();
    }
    public  void  init() {
        try{
            server= HttpServer.create(new InetSocketAddress("localhost", 8001),0);
            server.createContext("/postgres", new studennsHttp());
            server.start();
        }catch(IOException ex) {
            System.out.println("Server error" + ex);

        }
    }
}

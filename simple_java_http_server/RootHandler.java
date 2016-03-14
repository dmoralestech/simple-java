//package simple_java_http_server;
//
//import javax.xml.ws.spi.http.HttpExchange;
//import javax.xml.ws.spi.http.HttpHandler;
//import java.io.IOException;
//import java.io.OutputStream;
//
///**
// * Created by dmorales on 14/03/2016.
// */
//public class RootHandler implements HttpHandler {
//
//    @Override
//
//    public void handle(HttpExchange he) throws IOException {
//        String response = "<h1>Server start successif you see this message</h1>" + "<h1>Port: " + port + "</h1>";
//        he.sendResponseHeaders(200, response.length());
//        OutputStream os = he.getResponseBody();
//        os.write(response.getBytes());
//        os.close();
//    }
//}
package server.com;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class studennsHttp implements HttpHandler {
    public void handle(HttpExchange httpExchange) {
        System.out.println("Server connected with the handle");
        handleResponse(httpExchange);// method
    }

    private void handleResponse(HttpExchange httpExchange) {
        connectDB dbConnect = new connectDB();
        dbConnect.DbConnect();
        String resp = dbConnect.getStudentsInfo();//method
        dbConnect.close();

        try {
            OutputStream outputStream = httpExchange.getResponseBody();
            StringBuilder createHtml = new StringBuilder(); //html builder method
            createHtml.append("<html>")
                    .append("<body>")
                    .append("<h1>")
                    .append("Student Records")
                    .append("<h1>")
                    .append(resp)
                    .append("</body>")
                    .append("</html>");
            String htmlResponse = createHtml.toString();

            httpExchange.sendResponseHeaders(200, htmlResponse.length());
            outputStream.write(htmlResponse.getBytes());
            outputStream.flush();
            outputStream.close();


        } catch (IOException ex) {
            System.out.println("Server error" + ex);

        }
    }
}

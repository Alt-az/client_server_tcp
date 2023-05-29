import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GetHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange arg) throws IOException {
        try {
            String[] numbers = arg.getRequestURI().toString().replace("/", "").split("_");
            List<File> files = new ArrayList<>();
            for (String num : numbers) {
                files.add(new File(num + ".txt"));
            }

            StringBuilder builder = new StringBuilder();
            for (File file : files) {
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    builder.append(data);
                }
                myReader.close();
            }
            StringBuilder htmlBuilder = new StringBuilder();
            Pattern pattern = Pattern.compile("<td>(\\d+)</td>");
            Matcher matcher = pattern.matcher(builder.toString());
            Integer suma = 0;
            while (matcher.find()) {
                suma += Integer.parseInt(matcher.group(1));
            }
            // https://dzone.com/articles/simple-http-server-in-java htmlBuilder
            htmlBuilder.append("<html>").append("<head>").append("<style>")
                    .append("table, th, td {border: 1px solid black;}").append("</style>").append("</head")
                    .append("<body>")
                    .append("<table>")
                    .append(builder)
                    .append("</table>")
                    .append("<p>Suma: " + suma + "</p>")
                    .append("</body>")
                    .append("</html>");
            String response = htmlBuilder.toString().replace(" ", "\n");
            arg.sendResponseHeaders(200, response.length());
            OutputStream os = arg.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (IOException e) {
            StringBuilder htmlBuilder = new StringBuilder();
            htmlBuilder.append("<html>").append("<head>").append("<style>")
                    .append("table, th, td {border: 1px solid black;}").append("</style>").append("</head")
                    .append("<body>")
                    .append("Magazyn nie istnieje lub wpisales blednie magazyn! Wpisz np. http://localhost:8080/1")
                    .append("<p>Suma: " + 0 + "</p>")
                    .append("</body>")
                    .append("</html>");
            String response = htmlBuilder.toString();
            arg.sendResponseHeaders(200, response.length());
            OutputStream os = arg.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}

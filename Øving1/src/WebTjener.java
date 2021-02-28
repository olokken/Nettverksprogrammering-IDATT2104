import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

public class WebTjener {
    final static int PORTNR = 80;

    public static void main(String[] args) throws IOException {
        ServerSocket tjener = new ServerSocket(PORTNR);
        System.out.println("Venter på en forbindelse...");
        Socket forbindelse = tjener.accept();
        System.out.println("Du har nå en forbindelse");
        BufferedReader in = new BufferedReader(new InputStreamReader(forbindelse.getInputStream()));
        String alt = "<ul>";
        String inputLine;
        while (!(inputLine = in.readLine()).equals("")) {
            alt += "<li>" + inputLine + "</li>";
        }
        alt += "</ul>";
        PrintWriter out = new PrintWriter(forbindelse.getOutputStream(), true);
        System.out.println("Skriv inn velkomstmeldingen til klienten i terminalen");
        //Scanner sc = new Scanner(System.in);
        //String velkomst = sc.nextLine();
        out.println("HTTP/1.0 200 OK");
        out.println("Content-Type: text/html; charset=utf-8 (linjeskift)");
        out.println("\r\n");
        out.println("<h1>" + "Heisann og hoppsann" + "</h1>");
        out.println(alt);

        forbindelse.close();
        tjener.close();
        out.close();
        in.close();


    }
}


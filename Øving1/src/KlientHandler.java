import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class KlientHandler implements Runnable{
    private Socket klient;
    private BufferedReader in;
    private PrintWriter out;

    public KlientHandler (Socket klientSocket) throws IOException {
        this.klient = klientSocket;
        in = new BufferedReader(new InputStreamReader(klient.getInputStream()));
        out = new PrintWriter(klient.getOutputStream(), true);
    }
    @Override
    public void run() {
        try {
            /* Sender innledning til klienten */
            out.println("Hei, du har kontakt med tjenersiden!");
            out.println("Skriv regnestykket på formatet: tall +/- tall");

            /* Mottar data fra klienten */
            String linje = in.readLine();  // mottar en linje med tekst
            while (linje != null) {  // forbindelsen på klientsiden er lukkes
                System.out.println("En klient skrev: " + linje);
                String[] tall = linje.split(" ");

                try {
                    int x = Integer.parseInt(tall[0]);
                    int y = Integer.parseInt(tall[2]);
                    String sum = SocketTjener.valg(tall[1], x, y);
                    out.println(sum);
                } catch (Exception e){
                    out.println("Du har ikke skrevet regnestykket på rett format");
                }
                linje = in.readLine();  // mottar en linje med tekst
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.close();
            System.out.println("Alt lukkes på tjenersiden");
        }
    }
}

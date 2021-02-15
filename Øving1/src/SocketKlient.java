import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.security.auth.kerberos.KeyTab;
import javax.sound.sampled.Line;

class SocketKlient {
  public static void main(String[] args) throws IOException {
    final int PORTNR = 1250;

    /* Bruker en scanner til å lese fra kommandovinduet */
    Scanner leserFraKommandovindu = new Scanner(System.in);
    System.out.print("Oppgi navnet adressen på maskinen der tjenerprogrammet kjører: ");
    String tjenermaskin = leserFraKommandovindu.nextLine();

    /* Setter opp forbindelsen til tjenerprogrammet */
    Socket forbindelse = new Socket(tjenermaskin, PORTNR);
    System.out.println("Forbindelsen er opprettet");

    /* Åpner en forbindelse for kommunikasjon med tjenerprogrammet */
    BufferedReader in = new BufferedReader(new InputStreamReader(forbindelse.getInputStream()));
    PrintWriter out = new PrintWriter(forbindelse.getOutputStream(), true);
    try {
      /* Leser innledning fra tjeneren og skriver den til kommandovinduet */
      System.out.println(in.readLine());
      System.out.println(in.readLine());

      /* Leser tekst fra kommandovinduet (brukeren) */
      String linje = leserFraKommandovindu.nextLine();
      while (!linje.equals("")) {
        out.println(linje);  // sender teksten til tjeneren
        System.out.println("Svaret : ");// mottar respons fra tjeneren
        System.out.println(in.readLine());
        linje = leserFraKommandovindu.nextLine();
      }
    } catch (Exception e) {
      System.err.println(e);
    } finally {
      /* Lukker forbindelsen */
      in.close();
      out.close();
      forbindelse.close();
      System.out.println("Alt lukkes på klientsiden");
    }
  }
}

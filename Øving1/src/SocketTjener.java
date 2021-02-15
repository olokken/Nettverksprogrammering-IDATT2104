import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SocketTjener {
  final static int PORTNR = 1250;
  private static ArrayList<KlientHandler> klienter = new ArrayList<>();
  private static ExecutorService pool = Executors.newFixedThreadPool(4);

  public static void main(String[] args) throws IOException {
    ServerSocket tjener = new ServerSocket(PORTNR);
    int antall = 0;
    try {
      while (true) {
        System.out.println("Logg for tjenersiden. Nå venter vi...");
        Socket forbindelse = tjener.accept();
        antall++; // venter inntil noen tar kontakt
        System.out.println("Du har mottatt " + antall +" forbindelser");
        KlientHandler kh = new KlientHandler(forbindelse);
        klienter.add(kh);
        pool.execute(kh);
      }
    } catch (Exception e){
      System.err.println(e);
    } finally {
      tjener.close();
    }

  }

  static String valg(String operator, int x, int y){
    if (operator.equals("+")) {
      return ("Sum: " + addisjon(x, y));
    } else if (operator.equals("-")) {
      return ("Sum: " + substraksjon(x, y));
    } else {
      return ("Ugyldig operator");
    }
  }

  private static int addisjon(int x, int y) {
    return x + y;
  }

  private static int substraksjon(int x, int y) {
    return x - y;
  }
}

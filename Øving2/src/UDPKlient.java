import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPKlient {
    private static final int PORTNR = 1250;

    public static void main(String[] args) throws IOException {
        DatagramSocket klient = new DatagramSocket();
        Scanner sc = new Scanner(System.in);
        System.out.println("Skriv inn et regnestykke på formen tall +- tall, eller skriv exit om du vil avslutte programmet");
        String regnestykke = sc.nextLine();
        while (!regnestykke.equals("exit")) {
            send(regnestykke, 1250, klient);
            String svar = motta(klient);
            System.out.println(svar);
            regnestykke = sc.nextLine();
        }
        send(regnestykke, 1250, klient);
        System.out.println("Avslutter klienten...");
        klient.close();
    }

    static void send(String regnestykke, int portnr, DatagramSocket klient) throws IOException {
        byte[] b1 = regnestykke.getBytes();
        DatagramPacket svar = new DatagramPacket(b1, b1.length, InetAddress.getLocalHost(), portnr);
        klient.send(svar);
    }

    static String motta(DatagramSocket server) throws IOException {
        byte[] buf = new byte[1024];
        DatagramPacket motta = new DatagramPacket(buf, 0, buf.length);
        server.receive(motta);
        return new String(motta.getData()).replaceAll("\u0000.*", "");
    }
}

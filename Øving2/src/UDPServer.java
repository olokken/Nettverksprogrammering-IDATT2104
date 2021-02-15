import java.io.IOException;
import java.net.*;

public class UDPServer {
    private static final int PORTNR = 1250;
    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(PORTNR);
        DatagramPacket motta = motta(server);
        String regnestykke = new String(motta.getData()).replaceAll("\u0000.*", "");
        while (!regnestykke.equals("exit")){
            System.out.println("En klient skrev: " + regnestykke);
            String[] tall = regnestykke.split(" ");
            try {
                int x = Integer.parseInt(tall[0]);
                int y = Integer.parseInt(tall[2]);
                String sum = valg(tall[1], x, y);
                send(sum, motta.getPort(), server);
            } catch (Exception e){
                send("Du har skrevet regnestykket på feil format", motta.getPort(), server);
            }
            motta = motta(server);
            regnestykke = new String(motta.getData());
            regnestykke= regnestykke.replaceAll("\u0000.*", "");
        }
        System.out.println("Avsluttter serveren...");
        server.close();
    }

    static void send(String sum, int portnr, DatagramSocket server) throws IOException {
        byte[] b1 = sum.getBytes();
        DatagramPacket svar = new DatagramPacket(b1, b1.length, InetAddress.getLocalHost(), portnr);
        server.send(svar);
    }

    static DatagramPacket motta(DatagramSocket server) throws IOException {
        byte[] buf = new byte[1024];
        DatagramPacket motta = new DatagramPacket(buf, 0, buf.length);
        server.receive(motta);
        return motta;
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





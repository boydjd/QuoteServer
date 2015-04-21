import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class QuoteServer {
    public static void main(String[] args) {
        try {
            ServerSocket sock = new ServerSocket(6017);

            while (true) {
                Socket client = sock.accept();
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);

                pout.println(QuoteServer.getQuote());
                client.close();
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    protected static String getQuote() {
        try {
            /**
             * Quote file retrieved from http://www.shlomifish.org/humour/fortunes/shlomif-factoids
             **/
            Scanner quoteFile = new Scanner(new File("quotes"));
            ArrayList<String> quoteArray = new ArrayList<String>();
            String quote;
            Random random = new Random();

            quoteFile.useDelimiter("%");

            while (quoteFile.hasNext()) {
                quoteArray.add(quoteFile.next());
            }

            quoteFile.close();

            return quoteArray.get(random.nextInt(quoteArray.size()));
        } catch (IOException ioe) {
            System.err.println(ioe);
            return "";
        }
    }
}

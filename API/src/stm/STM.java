package stm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*; // IOException
import java.util.*; // Scanner
import jssc.*;

public class STM {

    private static SerialPort serialPort;

    public static void main(String[] args) throws IOException,
            NoSuchAlgorithmException, InvalidKeyException, InterruptedException {
        Coin bitcoin = new Coin("BTC");
        Coin ethereum = new Coin("ETH");
        Coin xrp = new Coin("XRP");
        Coin bitcoinCash = new Coin("BCH");
        Coin eos = new Coin("EOS");
        Coin stellar = new Coin("XLM");
        Coin X0 = new Coin("ZRX");
        Coin neo = new Coin("NEO");
        Coin zilliqa = new Coin("ZIL");
        Coin monero = new Coin("XMR");
        final int time = 5000;
        while(true){
        
        System.out.println(bitcoin.GETAPI());
        Thread.sleep(time);
        
        System.out.println(ethereum.GETAPI());
        Thread.sleep(time);
        
        System.out.println(xrp.GETAPI());
        Thread.sleep(time);
        
        System.out.println(bitcoinCash.GETAPI());
        Thread.sleep(time);
        
        System.out.println(eos.GETAPI());
        Thread.sleep(time);
        
        System.out.println(stellar.GETAPI());
        Thread.sleep(time);
        
        System.out.println(X0.GETAPI());
        Thread.sleep(time);
        
        System.out.println(neo.GETAPI());
        Thread.sleep(time);
        
        System.out.println(zilliqa.GETAPI());
        Thread.sleep(time);
        
        System.out.println(monero.GETAPI());
        Thread.sleep(time);
        }
    }
    
    public void SentToPort() {
        String[] portNames = SerialPortList.getPortNames();

        if (portNames.length == 0) {
            System.out.println("Brak wolnych portów.");
            try {
                System.in.read();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return;
        }

        System.out.println("Dostępne porty:");
        for (int i = 0; i < portNames.length; i++) {
            System.out.println(portNames[i]);
        }
        System.out.println("Wpisz nazwę portu i wciśnij ENTER");
        Scanner in = new Scanner(System.in);
        String portName = in.next();

        // writing to port
        serialPort = new SerialPort(portName);
        try {
            // opening port
            serialPort.openPort();

            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);

            // writing string to port
            serialPort.writeString("newResponse");

            System.out.println("Wysłano wiadomość");
        } catch (SerialPortException ex) {
            System.out.println("Błąd podczas wysyłania: " + ex);
        }
    }
}

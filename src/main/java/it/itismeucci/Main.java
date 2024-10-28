package it.itismeucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
         System.out.println("Il client e' partito");
        Socket mySocket = new Socket("localhost", 3000);
        System.out.println("il client si e' collegato");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        DataOutputStream out = new DataOutputStream(mySocket.getOutputStream());
        
        Scanner sc = new Scanner(System.in);

        String scelta;

        do {
            System.out.println("---------------------------------");
            System.out.println("1) Memorizza una nuova nota");
            System.out.println("2) lista note salvate");
            System.out.println("3) Esci");
            System.out.println("---------------------------------");
            System.out.println("inserire il numero dell'operazione che si vuol compiere");
            scelta = sc.nextLine();

            switch(scelta){
                case "1":
                System.out.println("inserire stringa: ");
                String stringa = sc.nextLine();
                out.writeBytes(stringa + "\n");
                String risposta =  in.readLine();
                if (risposta.equals("OK")){
                    System.out.println("Nota salvata");
                }
                break;


                case "2":
                out.writeBytes("?" + "\n");
            
                System.out.println("Lista:");
                String stringaRicevuta;
                do{
                    stringaRicevuta = in.readLine();
                    if (!stringaRicevuta.equals("@")){
                    System.out.println(stringaRicevuta);
                    }
                    
                }while(!stringaRicevuta.equals("@"));
                break;

                case "3":
                out.writeBytes("!" + "\n");
                System.out.println("connessione terminata!!");
                break;


            }


        }while (!scelta.equals("3"));

        mySocket.close();

    }
}
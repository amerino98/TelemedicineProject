/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BITalino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Álvaro Merino
 */
public class Server {

    public static void main(String args[]) throws IOException {
        List<Patient> toma = new ArrayList();
        ServerSocket serverSocket = new ServerSocket(9009);
        Socket socket = serverSocket.accept();
        System.out.println("Connection Patient created");
        Patient patient = new Patient();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        System.out.println("Text Received:\n");
        String line;
        patient.setName(bufferedReader.readLine());
        patient.setDNI(bufferedReader.readLine());
        patient.setQ3(TrueFalse(bufferedReader.readLine()));
        patient.setQ4(TrueFalse(bufferedReader.readLine()));
        patient.setQ5(TrueFalse(bufferedReader.readLine()));
        patient.setQ6(TrueFalse(bufferedReader.readLine()));
        patient.setQ7(TrueFalse(bufferedReader.readLine()));
        patient.setQ8(TrueFalse(bufferedReader.readLine()));
        for (int j = 0; j < 10000000; j++) {
            for (int i = 0; i < 10; i++) {
                int ecg = Integer.parseInt(bufferedReader.readLine());
                patient.ECG.add(ecg);
            }

        }

        releaseResources(bufferedReader, socket, serverSocket);
        toma.add(patient);
        System.out.println(toma);

    }

    private static boolean TrueFalse(String q) {
        if (q.equals("Y") == true) {
            return true;
        } else {
            return false;
        }
    }

    private static void releaseResources(BufferedReader bufferedReader,
            Socket socket, ServerSocket socketServidor) {
        try {
            bufferedReader.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        try {
            socketServidor.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}

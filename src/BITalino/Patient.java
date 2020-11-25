/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BITalino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.RemoteDevice;

/**
 *
 * @author Álvaro Merino
 */
public class Patient {

    String name;
    String DNI;
    String date;
    String time;

    boolean q3;
    boolean q4;
    boolean q5;
    boolean q6;
    boolean q7;
    boolean q8;
    List<Integer> ECG = new ArrayList();
    List<Integer> EDA = new ArrayList();

    public Patient(String name, String DNI, String date, String time, boolean q1, boolean q2, boolean q3, boolean q4, boolean q5, boolean q6, boolean q7, boolean q8) {
        this.name = name;
        this.DNI = DNI;
        this.date = date;
        this.time = time;

        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        this.q6 = q6;
        this.q7 = q7;
        this.q8 = q8;
    }

    @Override
    public String toString() {
        return "Patient{" + "name=" + name + ", DNI=" + DNI + ", date=" + date + ", time=" + time + ", q3=" + q3 + ", q4=" + q4 + ", q5=" + q5 + ", q6=" + q6 + ", q7=" + q7 + ", q8=" + q8 + ", ECG=" + ECG + ", EDA=" + EDA + '}';
    }

    public Patient() {
    }

    public String getName() {
        return name;
    }

    public String getDNI() {
        return DNI;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public boolean isQ3() {
        return q3;
    }

    public boolean isQ4() {
        return q4;
    }

    public boolean isQ5() {
        return q5;
    }

    public boolean isQ6() {
        return q6;
    }

    public boolean isQ7() {
        return q7;
    }

    public boolean isQ8() {
        return q8;
    }

    public List<Integer> getECG() {
        return ECG;
    }

    public List<Integer> getEDA() {
        return EDA;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setQ3(boolean q3) {
        this.q3 = q3;
    }

    public void setQ4(boolean q4) {
        this.q4 = q4;
    }

    public void setQ5(boolean q5) {
        this.q5 = q5;
    }

    public void setQ6(boolean q6) {
        this.q6 = q6;
    }

    public void setQ7(boolean q7) {
        this.q7 = q7;
    }

    public void setQ8(boolean q8) {
        this.q8 = q8;
    }

    public void setECG(List<Integer> ECG) {
        this.ECG = ECG;
    }

    public void setEDA(List<Integer> EDA) {
        this.EDA = EDA;
    }

    public static void main(String[] args) throws IOException {

        String response;
        String Q1 = "Complete name of the patient:";
        String Q2 = "DNI of the patient:";
        String Q3 = "Has the patient suffered a stressful situation?";
        String Q4 = "What frequency of situations of anxiety or tension has the patient had today?";
        String Q5 = "Has the patient suffered excessive sweating or perspiration?";
        String Q6 = "Has the patient suffered from chest pain or similar symptoms?";
        String Q7 = "Has the patient suffered from dizziness or vertigo?";
        String Q8 = "Has the patient have any comment or obserbation.please explain it ";

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Socket socket = new Socket("localhost", 9009);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        System.out.println(Q1);

        response = bufferedReader.readLine();

        printWriter.println(response);
        System.out.println(Q2);

        response = bufferedReader.readLine();

        printWriter.println(response);
        System.out.println("Use \"Y\" to respond yes and \"N\" to respond no ");

        System.out.println(Q3);

        response = bufferedReader.readLine();
        while (response.equals("Y") == false && response.equals("N") == false) {
            System.out.println("iNTRODUCE AGAIN");
            response = bufferedReader.readLine();
        }
        printWriter.println(response);
        System.out.println(Q4);

        response = bufferedReader.readLine();
        while (response.equals("Y") == false && response.equals("N") == false) {
            System.out.println("iNTRODUCE AGAIN");
            response = bufferedReader.readLine();
        }
        printWriter.println(response);
        System.out.println(Q5);

        response = bufferedReader.readLine();
        while (response.equals("Y") == false && response.equals("N") == false) {
            System.out.println("iNTRODUCE AGAIN");
            response = bufferedReader.readLine();
        }
        printWriter.println(response);
        System.out.println(Q6);

        response = bufferedReader.readLine();
        while (response.equals("Y") == false && response.equals("N") == false) {
            System.out.println("iNTRODUCE AGAIN");
            response = bufferedReader.readLine();
        }
        printWriter.println(response);
        System.out.println(Q7);

        response = bufferedReader.readLine();
        while (response.equals("Y") == false && response.equals("N") == false) {
            System.out.println("iNTRODUCE AGAIN");
            response = bufferedReader.readLine();
        }
        printWriter.println(response);
        System.out.println(Q8);

        response = bufferedReader.readLine();
        printWriter.println(response);
        BITalino b = BitalinoConection();

        closeBitalino(b);

        releaseResources(printWriter, socket);
    }

    private static void releaseResources(PrintWriter printWriter, Socket socket) {
        printWriter.close();

        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private static BITalino BitalinoConection() {

        BITalino bitalino = null;
        Frame[] frame;
        try {
            bitalino = new BITalino();
            // Code to find Devices
            //Only works on some OS
            Vector<RemoteDevice> devices = bitalino.findDevices();
            System.out.println(devices);

            //You need TO CHANGE THE MAC ADDRESS
            //You should have the MAC ADDRESS in a sticker in the Bitalino
            String macAddress = "98:D3:11:FD:1E:CC";

            //Sampling rate, should be 10, 100 or 1000
            int SamplingRate = 10;
            bitalino.open(macAddress, SamplingRate);
            return bitalino;

        } catch (BITalinoException ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);

        } catch (InterruptedException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Frame[] reading(BITalino b) {
        Frame[] frame;
        int[] channelsToAcquire = {1, 5};
        try {
            b.start(channelsToAcquire);

            //Each time read a block of 10 samples
            int block_size = 10;
            frame = b.read(block_size);
            return frame;

        } catch (Throwable ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void closeBitalino(BITalino b) {
        try {
            //close bluetooth connection
            if (b != null) {
                b.close();
            }
        } catch (BITalinoException ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

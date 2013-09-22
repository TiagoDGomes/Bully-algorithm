
/*
 * Copyright (C) 2013 Tiago Donizetti Gomes
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 *
 * @author Tiago
 */
package algoritmoeleicao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class SimpleSocket {

    int UDP_PORT;
    int DATA_LENGTH_BYTES = 4;
    private DatagramSocket serverSocket;

    public abstract void comandoRecebido(Serializable dados);

    public SimpleSocket(int UDP_PORT) {
        this.UDP_PORT = UDP_PORT;
        new Receptor();
    }


    public void enviar(Serializable data) {
        //<editor-fold defaultstate="collapsed" desc="Enviar">
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(data);
            oos.flush();
            // get the byte array of the object
            byte[] buffer = baos.toByteArray();
            byte[] dadosEnviados = transformar(buffer);
            DatagramSocket socket = new DatagramSocket();
            InetAddress client = InetAddress.getByName("255.255.255.255");
            DatagramPacket packet;
            packet = new DatagramPacket(dadosEnviados, DATA_LENGTH_BYTES, client, UDP_PORT);
            socket.send(packet);
            packet = new DatagramPacket(buffer, buffer.length, client, UDP_PORT);
            socket.send(packet);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //</editor-fold>
    }


    class Receptor implements Runnable {
        //<editor-fold defaultstate="collapsed" desc="Receber">
        public Receptor() {
            try {
                InetAddress inetListen = InetAddress.getByName("0.0.0.0");
                serverSocket = new DatagramSocket(UDP_PORT, inetListen);
                Thread escuta = new Thread(this);
                escuta.start();
            } catch (UnknownHostException ex) {
                Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        @Override
        public void run() {
            try {
                while (true) {
                    DatagramSocket socket = serverSocket;
                    byte[] data = new byte[DATA_LENGTH_BYTES];
                    DatagramPacket packet = new DatagramPacket(data, data.length);
                    socket.receive(packet);
                    int len = getTamanhoBuffer(data);
                    byte[] buffer = new byte[len];
                    packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    ByteArrayInputStream baos = new ByteArrayInputStream(buffer);
                    ObjectInputStream oos = new ObjectInputStream(baos);
                    comandoRecebido((Serializable) oos.readObject());
                    
                }
            } catch (Exception e) {
            }
        }
        //</editor-fold>
    }

    //<editor-fold defaultstate="collapsed" desc="Funções de tamanho de buffer">
    private byte[] transformar(byte[] buffer) {
        int number = buffer.length;
        byte[] data = new byte[DATA_LENGTH_BYTES];
        
        // int -> byte[]
        for (int i = 0; i < DATA_LENGTH_BYTES; ++i) {
            int shift = i << 3; // i * 8
            data[3 - i] = (byte) ((number & (0xff << shift)) >>> shift);
        }
        return data;
    }
    
    private int getTamanhoBuffer(byte[] data) {
        int len = 0;
        for (int i = 0; i < DATA_LENGTH_BYTES; ++i) {
            len |= (data[3 - i] & 0xff) << (i << 3);
        }
        return len;
        
    }
    //</editor-fold>

}
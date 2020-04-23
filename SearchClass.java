package com.example.mptremote2;

import android.widget.Toast;

import androidx.core.content.res.TypedArrayUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import static com.example.mptremote2.ConfigFragment.IP;
import static com.example.mptremote2.ConfigFragment.port;
import static com.example.mptremote2.NetworkClass.parent;
import static java.lang.Thread.interrupted;

public class SearchClass extends DatagramSocket {

    public SearchClass() throws SocketException {

    }

    public void sendDatagramMessages(String IP2, int port) throws IOException {
        int MessageAttr = 0;
        DatagramSocket datagramSocket = null;
        String msg = "MPT LUA Engine UDP - broadcast rq";
        byte[] msgs1 = msg.getBytes();
        try {
            datagramSocket = new DatagramSocket();
            DatagramPacket bundle = new java.net.DatagramPacket(msgs1, msgs1.length, java.net.InetAddress.getByName(IP2), port);
            datagramSocket.send(bundle);
            //datagramSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
            //datagramSocket.close();

        }
    }

    public void receiveDatagramMessages(String IP2, int port) {
    DatagramSocket socket = null;
    byte[] msg = new byte[1024];
    try{
        socket = new DatagramSocket();
        socket.connect(InetAddress.getByName("10.255.255.255"), 50001);
        DatagramPacket packet;
            System.out.println("Received" + socket.getInetAddress() + socket.getPort() + socket.isBound() + socket.isConnected());
            //packet = new DatagramPacket(msg, msg.length);

            packet = new DatagramPacket(msg, msg.length, socket.getInetAddress(), port );
            socket.setSoTimeout(5000);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println(received);

        socket.close();

    } catch (IOException e) {
        e.printStackTrace();
        socket.close();
    }

    }
}

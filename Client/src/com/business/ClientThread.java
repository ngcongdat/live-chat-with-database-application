/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.entity.MessageDetail;
import com.entity.Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author tiny
 */
public class ClientThread implements Runnable, Serializable {

    //for I/O
    private Socket socket;
    private Server server;
    private JTextArea txtContent;

    // Use to read and write data to/from server
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public ClientThread(Server server, JTextArea txtContent) {
        try {
            this.txtContent = txtContent;
            this.server = server;
            socket = new Socket(server.getHost(), server.getPort());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            oos = new ObjectOutputStream(dos);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            ois = new ObjectInputStream(dis);
            // Receive message from server
            while (true) {
                Object obj = ois.readObject();
                if (obj instanceof MessageDetail) {
                    // Output receive message
                    MessageDetail m = (MessageDetail) obj;
                    txtContent.append("\n" + m.getFromUser() + ":" + m.getContent());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //send message to client
    public void send(Object line) throws Exception {
        oos.writeObject(line);
        if (line instanceof MessageDetail) {
            MessageDetail m = (MessageDetail) line;
            txtContent.append("\nMe:" + m.getContent());
        }
    }

}

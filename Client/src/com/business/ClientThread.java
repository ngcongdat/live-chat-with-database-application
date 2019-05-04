/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.entity.Server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
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
        /*insert code for opening a connection to server here*/
        throw new UnsupportedOperationException("Remove this line and implement your code here!");
    }

    @Override
    public void run() {
        /*insert code for receiving and output a message from server here*/
        throw new UnsupportedOperationException("Remove this line and implement your code here!");

    }

    //send message to client
    public void send(Object line) throws Exception {
        /*insert code for sending a message to client here*/
        throw new UnsupportedOperationException("Remove this line and implement your code here!");
    }
    
}

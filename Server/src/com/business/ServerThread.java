/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.dal.MessageDAO;
import com.dal.UserDAO;
import com.entity.Client;
import com.entity.Server;
import com.entity.Users;
import com.business.ClientHandler;
import com.ui.ServerBox;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiny
 */
public class ServerThread implements Runnable {

    private ServerSocket server;
    private Server chatServer;
    private Socket socket;

    public ServerThread(Server chatServer) {
        this.chatServer = chatServer;
        try {
            server = new ServerSocket(chatServer.getPort());
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        try {
            while (true) {
                // Accept a connection from clients
                socket = server.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                ObjectInputStream ois = new ObjectInputStream(dis);
                // Read information of logined user
                Object obj = ois.readObject();
                if (obj instanceof Users) {
                    // Save connected user to database and save to database if needed
                    Users m = (Users) obj;
                    UserDAO u = new UserDAO();
                    u.addUser(m);
                    MessageDAO d = new MessageDAO();
                    Client c = new Client();
                    // Setting message for ClientThread
                    // For each connection for client, create a Thread-ClientHandler to hanle the connection
                    c.setUsername(m.getUsername());
                    c.setSocket(socket);
                    ServerBox.clients.addElement(c);
                    System.out.println("Welcome " + m.getUsername());
                    ClientHandler ch = new ClientHandler(socket, c, ois);
                    // Save to list of ClientHandler
                    clients.put(c.getUsername(), ch);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static HashMap<String, ClientHandler> clients = new HashMap<>();

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.dal.MessageDAO;
import com.entity.Client;
import com.entity.MessageDetail;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author tiny
 */
public class ClientHandler implements Runnable {

  // Declare instance
  private ObjectInputStream ois;
  private ObjectOutputStream oos;
  private Socket socket;
  private Client client;
  private JTextArea txtContent;
  private MessageDAO md;

  // Contructors
  public ClientHandler(Socket socket) {
    this.socket = socket;
  }

  public ClientHandler(Socket socket, Client client) {
    this.socket = socket;
    this.client = client;
  }

  public ClientHandler(Socket socket, Client client, ObjectInputStream ois) {
    this.socket = socket;
    this.client = client;
    this.ois = ois;
  }

  public ClientHandler(Socket socket, Client client, JTextArea txtContent) {
    this.socket = socket;
    this.client = client;
    this.txtContent = txtContent;
  }

  // Setter - Getter
  public Socket getSocket() {
    return socket;
  }

  public void setSocket(Socket socket) {
    this.socket = socket;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public JTextArea getTxtContent() {
    return txtContent;
  }

  public void setTxtContent(JTextArea txtContent) {
    this.txtContent = txtContent;
  }

  @Override
  public void run() {
    try {
      md = new MessageDAO();
      oos = new ObjectOutputStream(socket.getOutputStream());
      // Receive data from client
      while (true) {
        Object line = ois.readObject();
        if (line instanceof MessageDetail) {
          // Output content of message to textContent and save to database
          MessageDetail m = (MessageDetail) line;
          txtContent.append("\n" + m.getFromUser() + ": " + m.getContent());
          md.addMessageDetail(m);
        }
      }
    } catch (IOException ex) {
      Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  // Send message to client
  public void send(Object line) throws Exception {
    if (line instanceof MessageDetail) {
      // Output content of message to textContent and save to database
      MessageDetail m = (MessageDetail) line;
      txtContent.append("\nMe: " + m.getContent());
      oos.writeObject(line);
      md.addMessageDetail(m);
    }
  }

}

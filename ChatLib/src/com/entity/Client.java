/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author tiny
 */
public class Client implements Serializable {

  private String username, password;
  private Socket socket;

  @Override
  public String toString() {
    return username; //To change body of generated methods, choose Tools | Templates.
  }

  public Socket getSocket() {
    return socket;
  }

  public void setSocket(Socket socket) {
    this.socket = socket;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getType() {
    return password;
  }

  public void setType(String password) {
    this.password = password;
  }

  public Client() {
  }

  public Client(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Client(String username, String password, Socket socket) {
    this.username = username;
    this.password = password;
    this.socket = socket;
  }

}

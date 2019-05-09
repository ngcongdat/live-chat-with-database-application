/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author tiny
 */
public class MessageDetail implements Serializable {

  private int messageID;
  private String fromUser, toUser;
  private Date dateCreated;
  private String content;
  private MessageType messageType;

  public MessageDetail() {
  }

  public MessageDetail(String fromUser, String toUser, Date dateCreated, String content, MessageType messageType) {
    this.fromUser = fromUser;
    this.dateCreated = dateCreated;
    this.content = content;
    this.messageType = messageType;
    this.toUser = toUser;
  }

  public int getMessageID() {
    return messageID;
  }

  public void setMessageID(int messageID) {
    this.messageID = messageID;
  }

  public String getFromUser() {
    return fromUser;
  }

  public void setFromUser(String fromUser) {
    this.fromUser = fromUser;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public MessageType getMessageType() {
    return messageType;
  }

  public void setMessageType(MessageType messageType) {
    this.messageType = messageType;
  }

  public void setToUser(String toUser) {
    this.toUser = toUser;
  }

  public String getToUser() {
    return toUser;
  }

}

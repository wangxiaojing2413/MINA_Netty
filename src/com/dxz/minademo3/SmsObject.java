package com.dxz.minademo3;

public class SmsObject {
    private String sender;// ���ŷ�����
    private String receiver;// ���Ž�����
    private String message;// ��������

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
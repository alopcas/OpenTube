/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codejumble.opentube;

import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author lope115
 */
public class EventHandler {

    public EventHandler() {
    }

    public void createErrorDialog(JFrame frame, String content, String name) {
        JOptionPane.showMessageDialog(frame,
                content,
                name,
                JOptionPane.ERROR_MESSAGE);
    }

    public boolean checkInternetConnection() {
        boolean status = false;
        Socket sock = new Socket();
        InetSocketAddress address = new InetSocketAddress("www.google.com", 80);
        try {
            sock.connect(address, 3000);
            if (sock.isConnected()) {
                status = true;
            }
        } catch (Exception e) {

        } finally {
            try {
                sock.close();
            } catch (Exception e) {

            }
        }

        return status;
    }
}

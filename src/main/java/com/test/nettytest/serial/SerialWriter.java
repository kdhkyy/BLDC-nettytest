package com.test.nettytest.serial;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class SerialWriter{
    OutputStream out;
    String command;

    public SerialWriter(OutputStream out, String command) throws IOException {
        this.out = out;
        this.command = command;
    }

    public void sendCommand(){
        try {
            command = '\u0002' + command + '\u0003';
            this.out.write(command.getBytes(StandardCharsets.US_ASCII));
        } catch (IOException e) {
            System.out.println("error send command");
            e.printStackTrace();
        }
    }
}
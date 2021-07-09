package com.test.nettytest.serial;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SerialClient implements AutoCloseable{

    CommPortIdentifier portIdentifier;
    SerialPort serialPort;
    InputStream in;
    OutputStream out;
    public void connect(String portName) throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if (portIdentifier.isCurrentlyOwned()) {
            System.out.println("Error: Port is currently in use");
        } else {
            CommPort commPort = portIdentifier.open(this.getClass().getName(), 3000);

            if (commPort instanceof SerialPort) {

                serialPort = (SerialPort) commPort;

                serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);

                in = serialPort.getInputStream();
                out = serialPort.getOutputStream();

                (new Thread(new SerialReader(in))).start();

            } else {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }
    }

    public void sendCommand(String command) throws IOException {
        new SerialWriter(out, command);
    }

    @Override
    public void close() throws Exception {
        serialPort.close();
    }
}

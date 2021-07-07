package com.test.nettytest.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class SendDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String STX = "\u0002";
    private String ETX = "\u0003";
    private String command;

    @Override
    public String toString(){
        return STX + command + ETX;
    }
}

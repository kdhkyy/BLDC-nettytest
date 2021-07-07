package com.test.nettytest.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClientDto implements Serializable {
    private String host;
    private String port;
}

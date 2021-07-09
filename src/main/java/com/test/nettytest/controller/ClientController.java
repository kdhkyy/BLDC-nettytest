package com.test.nettytest.controller;

import com.test.nettytest.Client.Client;
import com.test.nettytest.domain.ClientDto;
import com.test.nettytest.domain.RespDto;
import com.test.nettytest.serial.SerialClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Slf4j
@RestController
public class ClientController {

    private Client client;
    /**
     * 클라이언트 커넥트 & 커맨드 전송
     *
     * @parm request
     *       command
     *
     * @return
     *       
     */

    @RequestMapping(value = "/sendCommand/{command}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public HashMap<String, RespDto> sendCommand(@RequestBody ClientDto clientDto,
                              @PathVariable("command") String command, HttpServletRequest request){
        HashMap res = new HashMap();
        try{
            client = new Client(clientDto.getHost(), Integer.parseInt(clientDto.getPort()));
            client.connect();
            client.sendCommand(command);
            res.put("resp", new RespDto(RespDto.CODE.SUCCESS, "완료"));
        } catch (InterruptedException e) {
            res.put("resp", new RespDto(RespDto.CODE.ERROR, "error"));
            e.printStackTrace();
        }finally {
            client.close();
        }

        return res;
    }

    @RequestMapping(value = "/serialSend/{command}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public HashMap<String, RespDto> serialSend(@RequestBody ClientDto clientDto,
                                                @PathVariable("command") String command, HttpServletRequest request) throws Exception {
        HashMap res = new HashMap();

        try(SerialClient client1 = new SerialClient();
            SerialClient client2 = new SerialClient();){
            client1.connect("COM10");
            client2.connect("COM11");

            client1.sendCommand(command);
            client2.sendCommand(command);

            res.put("resp", new RespDto(RespDto.CODE.SUCCESS, "완료"));
        }catch (Exception e){
            res.put("resp", new RespDto(RespDto.CODE.ERROR, "error"));
        }
        return res;
    }

}

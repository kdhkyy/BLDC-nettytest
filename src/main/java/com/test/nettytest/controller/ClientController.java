package com.test.nettytest.controller;

import com.test.nettytest.Client.Client;
import com.test.nettytest.domain.ClientDto;
import com.test.nettytest.domain.RespDto;
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

}

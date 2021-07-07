package com.test.nettytest.plc;

import java.io.ByteArrayOutputStream;

public enum PLC_ACTION {
    // 게이트 열기
    SET_GATE_UP("GATE UP"),
    // 게이트 닫기
    SET_GATE_DOWN("GATE DOWN"),
    // 게이트 락
    SET_GATE_UPLOCK("GATE UPLOCK"),
    // 게이트 언락
    SET_GATE_UNLOCK("GATE UNLOCK"),
    // 경고 초기화
    SET_DETECTOR_CLEAR("DETECTOR CLEAR"),
    // 상태값
    GET_STATUS("STATUS"),
    // 리셋
    SET_SYSTEM_RESET("SYSTEM RESET"),

    RES_SYSTEM_INIT("SYSTEM INIT"),
    // 게이트 열리는중
    RES_GATE_OPENNING("GATE UP ACTION"),
    // 게이트 닫히는중
    RES_GATE_CLOSING("GATE DOWN ACTION"),
    // 게이트 에러
    RES_GATE_ERROR("GATE ERROR"),
    // 열기 완료
    RES_GATE_UP_OK("GATE UP OK"),
    // 닫기 완료
    RES_GATE_DOWN_OK("GATE DOWN OK"),
    // GATE=UPLOCK,DETECTOR=ERROR =DOWN ACTION,DETECTOR=OFF
    RES_GATE_STATUS("")
    ;

    String text;

    PLC_ACTION(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static PLC_ACTION getFromString(String string) {
        for(PLC_ACTION state : values()) {
            if(state.getText().equals(string)) {
                return state;
            }
        }
        return null;
    }

    public byte[] getByte() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            outputStream.write(0x02);
            outputStream.write(text.getBytes("US-ASCII"));
            outputStream.write(0x03);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}

package com.test.nettytest.domain;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

import java.io.Serializable;

@Data
public class RespDto implements Serializable {

    private String msg;
    private CODE code = CODE.SUCCESS;
    private String field;

    public RespDto() {
        this.setResult(CODE.SUCCESS, "성공적으로 수행하였습니다.", null);
    }

    public RespDto(CODE code, String msg) {
        this.setResult(code, msg, null);
    }

    public RespDto(CODE code, String msg, String field) {
        this.setResult(code, msg, field);
    }


    public RespDto setResult(CODE code, String msg) {
        return setResult(code, msg, null);
    }

    public RespDto setResult(CODE code, String msg, String field) {
        this.setCode(code);
        this.setMsg(msg);
        this.setField(field);
        return this;
    }


    public boolean isSuccess() {
        return code == CODE.SUCCESS;
    }

    public enum CODE {
        // 성공
        SUCCESS(200),
        // 데이터 없음
        NOT_FOUND(404),
        // 중복
        CONFLICT(409),
        // 에러
        ERROR(500),
        // 권한없음
        UNAUTHORIZED(401),
        // 접근 거부 (세션없음)
        FORBIDDEN(403),
        // 비밀번호 5번 틀림
        INVALID_PASS_5TIMES(901);


        private int result;

        CODE(int code) {
            this.result = code;
        }

        public int getCode() {
            return result;
        }

        @JsonCreator
        public static CODE create(int val) {
            CODE[] codes = CODE.values();
            for (CODE code : codes) {
                if (code.getCode() == val) {
                    return code;
                }
            }
            return ERROR;
        }
    }

}

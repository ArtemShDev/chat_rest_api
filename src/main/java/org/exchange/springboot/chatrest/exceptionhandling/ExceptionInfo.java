package org.exchange.springboot.chatrest.exceptionhandling;

import java.io.Serializable;

public class ExceptionInfo implements Serializable {

    private String info;

    public ExceptionInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

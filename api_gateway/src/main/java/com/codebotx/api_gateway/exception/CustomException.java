
package com.codebotx.api_gateway.exception;


public class CustomException {

    String message;
    int status;

    
    public CustomException(String message, int status) {
        this.message = message;
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }


}

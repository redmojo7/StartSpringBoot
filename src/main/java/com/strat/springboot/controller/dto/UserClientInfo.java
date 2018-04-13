package com.strat.springboot.controller.dto;


import java.io.Serializable;

/**
 * @author : Donald
 * @date : 2018/1/31 18:24.
 * @description :
 */
public class UserClientInfo implements Serializable {
    
    
    private static final long serialVersionUID = -802238379788308637L;
    
    private String clientId;
    
    private String login;
    
    public UserClientInfo(String clientId, String login) {
        this.clientId = clientId;
        this.login = login;
    }
    
    public String getClientId() {
        return clientId;
    }
    
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserClientInfo)) {
            return false;
        }
        
        UserClientInfo that = (UserClientInfo) o;
        
        if (!clientId.equals(that.clientId)) {
            return false;
        }
        if (!login.equals(that.login)) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() {
        return this.clientId + "_" + this.login;
    }
}

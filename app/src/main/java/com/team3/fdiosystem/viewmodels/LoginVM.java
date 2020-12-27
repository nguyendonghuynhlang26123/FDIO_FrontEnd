package com.team3.fdiosystem.viewmodels;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.team3.fdiosystem.BR;

public class LoginVM extends BaseObservable {
    private String username;
    private String password;
    private String notify;
    private boolean loginSpinner;

    public LoginVM(){
        username="";
        password="";
        notify="";
        setLoginSpinner(false);
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }


    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
        notifyPropertyChanged(BR.notify);
    }

    @Bindable
    public boolean isLoginSpinner() {
        return loginSpinner;
    }

    public void setLoginSpinner(boolean loginSpinner) {
        this.loginSpinner = loginSpinner;
        notifyPropertyChanged(BR.loginSpinner);
    }
}

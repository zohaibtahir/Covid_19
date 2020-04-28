package com.zeetech.covid19.Models;

import com.google.gson.annotations.SerializedName;

public class CountryDetail {

    @SerializedName("flag")
    private String _flag;

    public String get_flag() {
        return _flag;
    }

    public void set_flag(String _flag) {
        this._flag = _flag;
    }
}

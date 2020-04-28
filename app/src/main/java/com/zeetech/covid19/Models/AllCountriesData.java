package com.zeetech.covid19.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AllCountriesData {
    @SerializedName("country")
    private String _country;
    @SerializedName("countryInfo")
    private CountryDetail _countryInfo;
    @SerializedName("cases")
    private int _cases;
    @SerializedName("todayCases")
    private int _todayCases;
    @SerializedName("deaths")
    private int _deaths;
    @SerializedName("todayDeaths")
    private int _todayDeaths;
    @SerializedName("recovered")
    private int _recovered;
    @SerializedName("active")
    private int _active;
    @SerializedName("critical")
    private int _critical;
    @SerializedName("casesPerOneMillion")
    private int _casesPerOneMillion;
    @SerializedName("deathsPerOneMillion")
    private int _deathsPerOneMillion;

    public String get_country() {
        return _country;
    }

    public void set_country(String _country) {
        this._country = _country;
    }

    public CountryDetail get_countryInfo() {
        return _countryInfo;
    }

    public void set_countryInfo(CountryDetail _countryInfo) {
        this._countryInfo = _countryInfo;
    }

    public int get_cases() {
        return _cases;
    }

    public void set_cases(int _cases) {
        this._cases = _cases;
    }

    public int get_todayCases() {
        return _todayCases;
    }

    public void set_todayCases(int _todayCases) {
        this._todayCases = _todayCases;
    }

    public int get_deaths() {
        return _deaths;
    }

    public void set_deaths(int _deaths) {
        this._deaths = _deaths;
    }

    public int get_todayDeaths() {
        return _todayDeaths;
    }

    public void set_todayDeaths(int _todayDeaths) {
        this._todayDeaths = _todayDeaths;
    }

    public int get_recovered() {
        return _recovered;
    }

    public void set_recovered(int _recovered) {
        this._recovered = _recovered;
    }

    public int get_active() {
        return _active;
    }

    public void set_active(int _active) {
        this._active = _active;
    }

    public int get_critical() {
        return _critical;
    }

    public void set_critical(int _critical) {
        this._critical = _critical;
    }

    public int get_casesPerOneMillion() {
        return _casesPerOneMillion;
    }

    public void set_casesPerOneMillion(int _casesPerOneMillion) {
        this._casesPerOneMillion = _casesPerOneMillion;
    }

    public int get_deathsPerOneMillion() {
        return _deathsPerOneMillion;
    }

    public void set_deathsPerOneMillion(int _deathsPerOneMillion) {
        this._deathsPerOneMillion = _deathsPerOneMillion;
    }
}

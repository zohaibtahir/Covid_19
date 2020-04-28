package com.zeetech.covid19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zeetech.covid19.Models.AllCountriesData;
import com.zeetech.covid19.Models.CountryDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class CountriesListviewAdapter extends BaseAdapter {
    private Context mContext;
    private List<AllCountriesData> _mResponseData;
    private ArrayList<AllCountriesData> arraylist=null;
    public CountriesListviewAdapter(Context mContext,List<AllCountriesData> _mResponseData) {
        this.mContext = mContext;
        this._mResponseData = _mResponseData;
        this.arraylist = new ArrayList<AllCountriesData>();
        this.arraylist.addAll(_mResponseData);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return _mResponseData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CircleImageView _flagImage;
        TextView _countrytxt,_casestxt,_todayCasestxt,_deathstxt,_todayDeathstxt,
                _recoveredtxt,_activetxt,_criticaltxt,_casesPerMilliontxt,
                _deathsPerMillion;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_row,null);
        _flagImage = v.findViewById(R.id.countryFlad_id);
        _countrytxt = v.findViewById(R.id.countryName_id);
        _casestxt = v.findViewById(R.id.cases_id);
        _todayCasestxt = v.findViewById(R.id.todayCases_id);
        _deathstxt = v.findViewById(R.id.deaths_id);
        _todayDeathstxt = v.findViewById(R.id.todayDeaths_id);
        _recoveredtxt = v.findViewById(R.id.recovered_id);
        _activetxt = v.findViewById(R.id.active_id);
        _criticaltxt = v.findViewById(R.id.critical_id);
        _casesPerMilliontxt = v.findViewById(R.id.casesPerMillion_id);
        _deathsPerMillion = v.findViewById(R.id.deathsPerMillion_id);

        CountryDetail getFlag =  _mResponseData.get(position).get_countryInfo();
        Glide.with(mContext).load(getFlag.get_flag()).into(_flagImage);
        _countrytxt.setText(_mResponseData.get(position).get_country());
        _casestxt.setText("Cases: "+_mResponseData.get(position).get_cases());
        _todayCasestxt.setText("Today Cases: "+_mResponseData.get(position).get_todayCases());
        _deathstxt.setText("Deaths: "+_mResponseData.get(position).get_deaths());
        _todayDeathstxt.setText("Today Deaths: "+_mResponseData.get(position).get_todayDeaths());
        _recoveredtxt.setText("Recovered: "+_mResponseData.get(position).get_recovered());
        _activetxt.setText("Active: "+_mResponseData.get(position).get_active());
        _criticaltxt.setText("Critical: "+_mResponseData.get(position).get_critical());
        _casesPerMilliontxt.setText("Cases/Million: "+_mResponseData.get(position).get_casesPerOneMillion());
        _deathsPerMillion.setText("Deaths/Million: "+_mResponseData.get(position).get_deathsPerOneMillion());


        return v;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        _mResponseData.clear();
        if (charText.length() == 0) {
            _mResponseData.addAll(arraylist);
        }
        else
        {
            for (AllCountriesData wp : arraylist) {
                if (wp.get_country().toLowerCase(Locale.getDefault()).contains(charText)) {
                    _mResponseData.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}

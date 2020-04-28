package com.zeetech.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.zeetech.covid19.Models.AllCountriesData;

import org.json.JSONStringer;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesData extends AppCompatActivity {
    private ListView _listView;
    public List<AllCountriesData> _responseData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_data);

        _listView = findViewById(R.id.listView_id);


        FetchAllCountriesData();

    }

    public void FetchAllCountriesData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://corona.lmao.ninja/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NovelCovid19Api novelCovid19Api = retrofit.create(NovelCovid19Api.class);
        Call<List<AllCountriesData>> call = novelCovid19Api.getCountriesData();
        call.enqueue(new Callback<List<AllCountriesData>>() {
            @Override
            public void onResponse(Call<List<AllCountriesData>> call, Response<List<AllCountriesData>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(CountriesData.this, "No Data", Toast.LENGTH_SHORT).show();
                    return;
                }
                try{
                    _responseData = response.body();
                    CountriesListviewAdapter adapter = new CountriesListviewAdapter(CountriesData.this,_responseData);
                    _listView.setAdapter(adapter);

                }catch (Exception e){
                    Toast.makeText(CountriesData.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AllCountriesData>> call, Throwable t) {
                Toast.makeText(CountriesData.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

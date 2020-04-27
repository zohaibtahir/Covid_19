package com.zeetech.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zeetech.covid19.Models.WholeWorldData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView _worldDataTextView;
    private ProgressBar _progrssBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _worldDataTextView = findViewById(R.id.worldData_tv);
        _progrssBar = findViewById(R.id.progressCricle_id);
        _progrssBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://corona.lmao.ninja/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NovelCovid19Api novelCovid19Api = retrofit.create(NovelCovid19Api.class);
        Call<WholeWorldData> call = novelCovid19Api.getAllData();
        call.enqueue(new Callback<WholeWorldData>() {
            @Override
            public void onResponse(Call<WholeWorldData> call, Response<WholeWorldData> response) {
                if(!response.isSuccessful()){
                    _worldDataTextView.setText("No Data: "+ response.code());
                    return;
                }
                WholeWorldData _data = response.body();
                String _content="";
                _content += "Cases: "+ _data.get_cases()+"\n";
                _content += "Today Cases: " +_data.get_toDayCases() +"\n";
                _content += "Deaths: "+_data.get_deaths()+"\n";
                _content += "Today Deaths: "+_data.get_todayDeaths()+"\n";
                _content += "Recovered: "+_data.get_recovered()+"\n";
                _content += "Active: "+_data.get_active()+"\n";
                _content += "Critical: "+_data.get_critical()+"\n";
                _content += "Cases Per Million: "+_data.get_casesPerOneMillion()+"\n";
                _content += "Deaths Per Million: "+_data.get_deathsPerOneMillion()+"\n";
                _content += "Tests: " + _data.get_tests()+"\n";
                _content += "Tests Per Million: "+_data.get_testsPerOneMillion()+"\n";
                _content += "Affected Countrie: "+_data.get_affectedCountries()+"\n\n";

                _worldDataTextView.append(_content);
                _progrssBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<WholeWorldData> call, Throwable t) {
                _worldDataTextView.setText("Error: "+t.toString());
            }
        });
    }
}

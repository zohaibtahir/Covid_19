package com.zeetech.covid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.selection.OperationMonitor;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesData extends AppCompatActivity {
    private ListView _listView;
    private EditText _search;
    private SwipeRefreshLayout _swipe;
    CountriesListviewAdapter adapter;
    public List<AllCountriesData> _responseData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_data);

        _listView = findViewById(R.id.listView_id);
        _search = findViewById(R.id.searchBox_id);
        _swipe = findViewById(R.id.swipe_id);

        FetchAllCountriesData();

        _swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FetchAllCountriesData();
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        _swipe.setRefreshing(false);
                    }
                }, 4000);
            }
        });
        _swipe.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

        _search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = _search.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
                    adapter = new CountriesListviewAdapter(CountriesData.this,_responseData);
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
    //For hiding keyboard
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

}

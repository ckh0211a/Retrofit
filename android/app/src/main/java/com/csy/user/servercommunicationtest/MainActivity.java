package com.csy.user.servercommunicationtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.csy.user.servercommunicationtest.connection.Model;
import com.csy.user.servercommunicationtest.connection.RemoteService;
import com.csy.user.servercommunicationtest.connection.RequestResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RemoteService remoteService;
    EditText menu, category, restaurant;
    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);

        menu = findViewById(R.id.menu);
        category = findViewById(R.id.category);
        restaurant = findViewById(R.id.restaurant);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        //************************************************************//
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RemoteService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        remoteService = retrofit.create(RemoteService.class);
        //************************************************************//

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:

                model = new Model(menu.getText().toString(), category.getText().toString(), restaurant.getText().toString());

                Call<RequestResult> call1 = remoteService.insertData(model);
                call1.enqueue(new Callback<RequestResult>() {
                    @Override
                    public void onResponse(Call<RequestResult> call, Response<RequestResult> response) {
                        RequestResult requestResult = response.body();

                        switch (requestResult.getCode()) {
                            case 200:
                                Toast.makeText(getApplicationContext(), requestResult.getMessage(), Toast.LENGTH_SHORT).show();
                                break;
                            case 401:
                                Toast.makeText(getApplicationContext(), requestResult.getMessage(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                    @Override
                    public void onFailure(Call<RequestResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                        Toast.makeText(getApplicationContext(), throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case R.id.button2:

                Call<RequestResult> call2 = remoteService.getData(menu.getText().toString());
                call2.enqueue(new Callback<RequestResult>() {
                    @Override
                    public void onResponse(Call<RequestResult> call, Response<RequestResult> response) {
                        RequestResult requestResult = response.body();
                        menu.setText(requestResult.getData().getMenu());
                        category.setText(requestResult.getData().getCategory());
                        restaurant.setText(requestResult.getData().getRestaurant());

                        switch (requestResult.getCode()) {
                            case 200:
                                Toast.makeText(getApplicationContext(), requestResult.getMessage(), Toast.LENGTH_SHORT).show();
                                break;
                            case 401:
                                Toast.makeText(getApplicationContext(), requestResult.getMessage(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<RequestResult> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case R.id.button3:

                model = new Model(menu.getText().toString(), category.getText().toString(), restaurant.getText().toString());

                Call<RequestResult> call3 = remoteService.changeData(model);
                call3.enqueue(new Callback<RequestResult>() {
                    @Override
                    public void onResponse(Call<RequestResult> call, Response<RequestResult> response) {
                        RequestResult requestResult = response.body();

                        switch (requestResult.getCode()) {
                            case 200:
                                Toast.makeText(getApplicationContext(), requestResult.getMessage(), Toast.LENGTH_SHORT).show();
                                break;
                            case 401:
                                Toast.makeText(getApplicationContext(), requestResult.getMessage(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                    @Override
                    public void onFailure(Call<RequestResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                        Toast.makeText(getApplicationContext(), throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case R.id.button4:

                Call<RequestResult> call4 = remoteService.deleteData(menu.getText().toString());
                call4.enqueue(new Callback<RequestResult>() {
                    @Override
                    public void onResponse(Call<RequestResult> call, Response<RequestResult> response) {
                        RequestResult requestResult = response.body();

                        switch (requestResult.getCode()) {
                            case 200:
                                Toast.makeText(getApplicationContext(), requestResult.getMessage(), Toast.LENGTH_SHORT).show();
                                break;
                            case 401:
                                Toast.makeText(getApplicationContext(), requestResult.getMessage(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<RequestResult> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}

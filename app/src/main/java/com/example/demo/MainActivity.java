package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.example.demo.databinding.ActivityMainBinding;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    String[] drinkList = {"Milk", "Coffee", "Orange juice", "Cola", "Red tea"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnGetAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textResult.setVisibility(View.INVISIBLE);
                suggestDrink();
            }
        });
    }
    private void suggestDrink() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        final String[] drinkName = {""};
        binding.progressBar.setVisibility(View.VISIBLE);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                drinkName[0] = drinkList[new Random().nextInt(drinkList.length)];

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.textResult.setVisibility(View.VISIBLE);
                        binding.textResult.setText(drinkName[0]);
                        binding.progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }
}
package com.example.mydraganddeaw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

abstract public class SingleFragmentActivity extends AppCompatActivity {

    abstract public Fragment creatFragment();
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        fm=getSupportFragmentManager();

        fm.beginTransaction()
                .add(R.id.fragmentContaier,creatFragment())
                .commit();
    }
}

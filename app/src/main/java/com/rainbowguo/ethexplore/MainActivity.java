package com.rainbowguo.ethexplore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import com.rainbowguo.ethexplore.databinding.ActivityMainBinding;
import com.rainbowguo.ethexplore.fragments.*;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "mainActivity";
    private ActivityMainBinding bind;
    private FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        setSupportActionBar(bind.toolbar);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragmentBox,new HomeFragment()).commit();
        bind.searchButton.setOnClickListener(v->new mDialogFragment().show(fragmentManager,null));
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_appbar_item,menu);
        //Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        Log.i(TAG, "content: "+ getBaseContext());
        return true;
    }

    public void addFragment(Fragment fragment){
        fragmentManager.beginTransaction()
                .add(R.id.fragmentBox,fragment).addToBackStack(null).commit();
    }


}
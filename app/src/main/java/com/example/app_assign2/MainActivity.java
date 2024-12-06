package com.example.app_assign2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout = findViewById(R.id.tablayout);
        frameLayout = findViewById(R.id.frameLayout);


        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new profileView())
                .addToBackStack(null)
                .commit();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Fragment fragment = null;

                switch (tab.getPosition())
                {
                    case 0:
                        fragment = new profileView();
                        break;

                    case 1:
                        fragment = new userSettings();
                        break;


                }


                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        changeTheme();
    }


    public void changeTheme(){
        Window window = getWindow();
        SharedPreferences pf = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        String color = pf.getString("themeColor", "Green");

        switch (color) {
            case "Dark":
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBlack));
                tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlack));
                break;
            case "Red":
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorRed));
                tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
                break;
            case "Blue":
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBlue));
                tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
                break;
            case "Green":
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorGreen));
                tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
                break;
            default:
                break;
        }
    }
}
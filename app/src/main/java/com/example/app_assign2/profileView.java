package com.example.app_assign2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class profileView extends Fragment {


    private TextView txtUsername, txtEmail, txtNotifications, txtThemeColor, emptyTxt;
    private Button btnReset;

    public profileView() {

    }





   private String themeColor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_profile_view, container, false);

        txtUsername = view.findViewById(R.id.txtUsername);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtNotifications = view.findViewById(R.id.txtNotifications);
        txtThemeColor = view.findViewById(R.id.txtThemeColor);
        btnReset = view.findViewById(R.id.btnReset);
        emptyTxt = view.findViewById(R.id.empty);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "No username");

         String email = sharedPreferences.getString("email", "No email");
        boolean notifications = sharedPreferences.getBoolean("notifications", false);
        themeColor = sharedPreferences.getString("themeColor", "Green");




        txtUsername.setText("Username:  "+ username);
        txtEmail.setText("email:  "+email);
        txtNotifications.setText("Notification:  "+(notifications ? "Enabled" : "Disabled"));
        txtThemeColor.setText("Theme Color:  "+themeColor);


        if (username.equals("No username") || email.equals("No email") || themeColor.equals("Green"))
         {
             setVisibility();
         }

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor  = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Toast.makeText(getActivity(), "Successfully Cleared.", Toast.LENGTH_SHORT).show();

                txtEmail.setText("");
                txtUsername.setText("");
                txtNotifications.setText("");
                txtThemeColor.setText("");


                btnReset.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorBlack));
               setVisibility();




              changeTheme();
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).changeTheme();
                }
            }

        });





    changeTheme();

        return view;
    }




    public  void setVisibility(){
        txtNotifications.setVisibility(View.GONE);
        txtEmail.setVisibility(View.GONE);
        txtThemeColor.setVisibility(View.GONE);
        txtUsername.setVisibility(View.GONE);
        emptyTxt.setText("There is no user Information");
        emptyTxt.setVisibility(View.VISIBLE);

        btnReset.setEnabled(false);
    }

    public void changeTheme(){
        int color;
        switch (themeColor) {
            case "Dark":
                color = getResources().getColor(R.color.colorBlack);
                btnReset.setBackgroundColor(color);

                break;

            case "Red":
                color = getResources().getColor(R.color.colorRed);
                btnReset.setBackgroundColor(color);
                break;

            case "Blue":
                color = getResources().getColor(R.color.colorBlue);
                btnReset.setBackgroundColor(color);
                break;
            case "Green":
                color = getResources().getColor(R.color.colorGreen);
                btnReset.setBackgroundColor(color);
                break;

            default:

        }
    }
}
package com.example.app_assign2;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class userSettings extends Fragment {

    private EditText edtUsername, edtEmail, edtPassword;
    private RadioGroup radioGroupNotifications;
    private Spinner spinnerThemeColor;
    Button btnSavePreferences;
    RadioButton radioOn , radioOff;
    public userSettings() {

    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_settings, container, false);

        edtUsername = view.findViewById(R.id.edtUsername);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
        radioGroupNotifications = view.findViewById(R.id.radioGroupNotifications);
        spinnerThemeColor = view.findViewById(R.id.spinnerThemeColor);
        btnSavePreferences = view.findViewById(R.id.btnSavePreferences);

        radioOn = view.findViewById(R.id.radioOn);
        radioOff = view.findViewById(R.id.radioOff);





        btnSavePreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String username = edtUsername.getText().toString();
                    String email = edtEmail.getText().toString();
                    String password = edtPassword.getText().toString();
                    boolean notifications = radioGroupNotifications.getCheckedRadioButtonId() == R.id.radioOn;


                    String themeColor = spinnerThemeColor.getSelectedItem().toString();

                    if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.putBoolean("notifications", notifications);
                    editor.putString("themeColor", themeColor);
                    editor.apply();

                    Toast.makeText(getActivity(), "Preferences Saved", Toast.LENGTH_SHORT).show();

                    changeTheme();
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).changeTheme();
                }

            }
        });

        changeTheme();
        return view;
    }

   public void changeTheme(){

       int color;
       SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
       String thColor = sharedPreferences.getString("themeColor","Green");

       switch (thColor) {
           case "Dark":
               color = getResources().getColor(R.color.colorBlack);
               changeTheColors(color);
               break;

           case "Red":
               color = getResources().getColor(R.color.colorRed);
              changeTheColors(color);
               break;

           case "Blue":
               color = getResources().getColor(R.color.colorBlue);
               changeTheColors(color);
               break;
           case "Green":
               color = getResources().getColor(R.color.colorGreen);
               changeTheColors(color);
               break;

           default:

       }

   }



   public  void changeTheColors(int color){


       btnSavePreferences.setBackgroundColor(color);
       radioOn.setButtonTintList(ColorStateList.valueOf(color));
       radioOff.setButtonTintList(ColorStateList.valueOf(color));


       GradientDrawable drawable;
       drawable = (GradientDrawable) edtUsername.getBackground();
       drawable.setStroke(1,color);
       drawable = (GradientDrawable) edtEmail.getBackground();
       drawable.setStroke(1,color);
       drawable = (GradientDrawable) edtPassword.getBackground();
       drawable.setStroke(1,color);
   }
}
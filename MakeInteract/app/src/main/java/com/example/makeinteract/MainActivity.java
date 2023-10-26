package com.example.makeinteract;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.makeinteract.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'makeinteract' library on application startup.
    static {
        System.loadLibrary("makeinteract");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());
//        TextView tv = findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());

        binding.sampleText2.setText(process());

        TextView tv2 = binding.textView;
        Button btn = binding.button;
        EditText edt = binding.editTextNumber;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = 0;
                try {
                    a = Integer.parseInt(edt.getText().toString());
                    tv2.setText(printnumber(a));
                }catch (NumberFormatException e){
                    tv2.setText("Invalid value");
                }

            }
        });

    }

     public String processInJava(){
        return "Processed in Java";
     }

     public String res(int num){
        return "Value is " + num;
     }

    /**
     * A native method that is implemented by the 'makeinteract' native library,
     * which is packaged with this application.
     */
    public native String process();
    public native String stringFromJNI();

    public native String printnumber(int num);
}
package com.sommayah.writewithmeinarabic2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DesignActivity extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText editText1;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.editText4)
    EditText editText4;

    private String[] result;
    public static final String RESULTBACK = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        ButterKnife.bind(this);
        result = new String[4];
    }

    public void onDoneClicked(View view) {
        Intent returnIntent = new Intent();
        result[0] = editText1.getText().toString();
        result[1] = editText2.getText().toString();
        result[2] = editText3.getText().toString();
        result[3] = editText4.getText().toString();
        returnIntent.putExtra(RESULTBACK,result);
        setResult(RESULT_OK,returnIntent);
        finish();
    }

    public void onDeleteClicked(View view){
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
    }
}

package com.example.easyteaching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SwitchActivity extends AppCompatActivity {
    private int testID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        // Create Bundle
        Bundle bundle = getIntent().getExtras();
        testID = bundle.getInt("testID");

        //Toast.makeText(this, "Hey / " + testID, Toast.LENGTH_SHORT).show();
    }


    public void onClickGoToModify(View view) {
        Intent intent = new Intent(this, ModifyTest.class);
        intent.putExtra("testID", testID);
        startActivity(intent);
    }

    public void onClickGoToEditQuestions(View view) {
        Intent intent = new Intent(this, EditTestQuestionsActivity.class);
        intent.putExtra("testID", testID);
        startActivity(intent);
    }

}
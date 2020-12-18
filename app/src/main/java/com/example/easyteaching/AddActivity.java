package com.example.easyteaching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.Cours;
import model.ProfesseursComplet;
import model.Tests;
import repository.CoursRepository;
import repository.ProfesseursCompletRepository;
import repository.TestsRepository;

public class AddActivity extends AppCompatActivity {
    private List<Cours> cours;
    private TextView testName;
    private Spinner spinner;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        testName = (TextView) findViewById(R.id.textName_tv);
        spinner = (Spinner) findViewById(R.id.spinnerCours);
        SharedPreferences prefs = getSharedPreferences("myapp", MODE_PRIVATE);
        userID = prefs.getInt("userID", 1);

        final ProfesseursCompletRepository professeursCompletRepository = new ProfesseursCompletRepository();
        professeursCompletRepository.get(userID).observe(this, new Observer<ProfesseursComplet>() {
            @Override
            public void onChanged(ProfesseursComplet professeursComplet) {
                Log.i("Professeurs Fetch", professeursComplet.getProfesseur().toString());
                Log.i("Professeurs Fetch", "");

                cours = new ArrayList<>(professeursComplet.getCours());
                initSpinner();
            }
        });
    }

    private void initSpinner() {
        List<String> nomCours = new ArrayList<>();
        for (Cours coursSingle: cours) {
            nomCours.add(coursSingle.getNom_cours());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomCours);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    public void onAddTest(View view) {
        // Build Tests
        if (testName.getText().equals("")){
            Toast.makeText(this, "Please fill in the test name", Toast.LENGTH_SHORT).show();
            return;
        }

        Tests tests = buildTests();

        final TestsRepository testsRepository = new TestsRepository();
        //Toast.makeText(this, "coursID " + tests.getCours(), Toast.LENGTH_SHORT).show();
        testsRepository.post(tests).observe(this, new Observer<Tests>() {
            @Override
            public void onChanged(Tests tests) {
                Log.i("Add Activity", tests.toString());
                Toast.makeText(getApplicationContext(), "Test has been created !", Toast.LENGTH_SHORT).show();
                testName.setText("");
            }
        });
    }

    private Tests buildTests() {
        int coursID = getCurrentCoursID();
        final Tests tests = new Tests(-1, testName.getText().toString(), userID, coursID);

        return tests;
    }

    private int getCurrentCoursID() {
        int coursID = 0;
        for (Cours coursSingle: cours) {
            if (coursSingle.getNom_cours().equals(spinner.getSelectedItem().toString())){
                coursID = coursSingle.getId();
            }
        }
        return coursID;
    }
}
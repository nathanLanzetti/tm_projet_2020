package com.example.easyteaching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
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
import repository.ProfesseursCompletRepository;
import repository.TestsRepository;

public class ModifyTest extends AppCompatActivity {

    private List<Cours> cours;
    private TextView testNameTV;
    private Spinner spinner;
    private Tests test;
    private int testID;
    private int coursID;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_test);

        Bundle bundle = getIntent().getExtras();
        testID = bundle.getInt("testID", testID);

        testNameTV = (TextView) findViewById(R.id.textName_tv);
        spinner = (Spinner) findViewById(R.id.spinnerCours);
        SharedPreferences prefs = getSharedPreferences("myapp", MODE_PRIVATE);
        userID = prefs.getInt("userID", 1);

        final TestsRepository testsRepository = new TestsRepository();
        LifecycleOwner context = this;
        testsRepository.get(testID).observe(this, new Observer<Tests>() {
            @Override
            public void onChanged(Tests tests) {
                test = tests;
                testNameTV.setText(tests.getName());
                coursID = tests.getCours();

                final ProfesseursCompletRepository professeursCompletRepository = new ProfesseursCompletRepository();
                professeursCompletRepository.get(userID).observe(context, new Observer<ProfesseursComplet>() {
                    @Override
                    public void onChanged(ProfesseursComplet professeursComplet) {
                        Log.i("Professeurs Fetch", professeursComplet.getProfesseur().toString());
                        Log.i("Professeurs Fetch", "");

                        cours = new ArrayList<>(professeursComplet.getCours());
                        initSpinner();
                    }
                });
            }
        });


    }

    private void initSpinner() {
        List<String> nomCours = new ArrayList<>();
        List<Integer> idCours = new ArrayList<>();
        int i = 0;
        int posID = 0;
        for (Cours coursSingle: cours) {
            nomCours.add(coursSingle.getNom_cours());
            if (coursSingle.getId() == coursID) posID = i;
            i++;
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomCours);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(posID);
    }

    public void onUpdateTest(View view) {
        // Build Tests
        if (testNameTV.getText().equals("")){
            Toast.makeText(this, "Please fill in the test name", Toast.LENGTH_SHORT).show();
            return;
        }

        buildTests();

        final TestsRepository testsRepository = new TestsRepository();
        testsRepository.put(test);
        Toast.makeText(getApplicationContext(), "Test has been updated !", Toast.LENGTH_SHORT).show();

        /*
        final TestsRepository testsRepository = new TestsRepository();
        Toast.makeText(this, "coursID " + tests.getCours(), Toast.LENGTH_SHORT).show();
        testsRepository.post(tests).observe(this, new Observer<Tests>() {
            @Override
            public void onChanged(Tests tests) {
                Log.i("Add Activity", tests.toString());
            }
        });

         */
    }

    private void buildTests() {
        int coursID = getCurrentCoursID();
        test.setName(testNameTV.getText() + "");
        test.setCours(coursID);
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
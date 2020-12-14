package com.example.easyteaching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import adapters.TestsCompletAdapter;
import api.AuthenticateService;
import model.Cours;
import model.Professeurs;
import model.TestsComplet;
import repository.AuthenticationRepository;
import repository.CoursRepository;
import repository.ProfesseursCompletRepository;
import repository.ProfesseursRepository;
import repository.TestsCompletRepository;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TestsCompletAdapter testsCompletAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_tests_complets);

        final LifecycleOwner context = this;
        recyclerView = findViewById(R.id.recyclerViewTestsComplet);

        final ProfesseursCompletRepository professeursCompletRepository = new ProfesseursCompletRepository();
        professeursCompletRepository.query().observe(this, new Observer<List<Professeurs>>() {
            @Override
            public void onChanged(List<Professeurs> professeurs) {
                Log.i("Professeurs Fetch", professeurs.toString());
                Log.i("Professeurs Fetch", "");
            }
        });

        // Declare Repos // Tests | Prof | Cours
        TestsCompletRepository testsCompletRepository = new TestsCompletRepository();
        final ProfesseursRepository professeursRepository = new ProfesseursRepository();
        final CoursRepository coursRepository = new CoursRepository();

        // Fetch Tests | Prof | Cours
        testsCompletRepository.query().observe(this, new Observer<List<TestsComplet>>() {
            @Override
            public void onChanged(final List<TestsComplet> testsComplets) {
                Log.i("Main activity TESTS", testsComplets.toString());
                Log.i("Main activit test Q", testsComplets.get(0).getQuestionsComplets().toString());

                // fetch profs
                professeursRepository.query().observe(context, new Observer<List<Professeurs>>() {
                    @Override
                    public void onChanged(final List<Professeurs> professeurs) {
                        Log.i("Main activity PROFS", professeurs.toString());
                        // fecth cours
                        coursRepository.query().observe(context, new Observer<List<Cours>>() {
                            @Override
                            public void onChanged(List<Cours> cours) {
                                Log.i("Main activity COURS", cours.toString());
                                // SetUp Recycler View
                                setUpRecyclerView(testsComplets, professeurs, cours);
                            }
                        });
                    }
                });

            }
        });


        /*
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        authenticationRepository.login("godefroid.laurent@helha.be", "gogogo").observe(this, new Observer<Professeurs>() {
            @Override
            public void onChanged(Professeurs professeurs) {
                Log.i("Authenticate", professeurs.getToken());
            }
        });
        */
    }

    private void setUpRecyclerView(List<TestsComplet> testsComplets, List<Professeurs> professeurs, List<Cours> cours){

        Log.i("Recycler", recyclerView.toString());
        LinkedList<TestsComplet> linkedList = new LinkedList<>(testsComplets);
        Log.i("Main Acti LinkedList", linkedList.get(0).getTest().getName());
        testsCompletAdapter = new TestsCompletAdapter(this, linkedList, professeurs, cours);
        recyclerView.setAdapter(testsCompletAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

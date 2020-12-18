package com.example.easyteaching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.LinkedList;
import java.util.List;

import adapters.TestsCompletAdapter;
import adapters.TestsPageAdapter;
import api.AuthenticateService;
import model.Cours;
import model.Professeurs;
import model.ProfesseursComplet;
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

        SharedPreferences prefs = getSharedPreferences("myapp", MODE_PRIVATE);
        final int userID = prefs.getInt("userID", 1);
        Log.i("USER ID", userID + "");

        final ProfesseursCompletRepository professeursCompletRepository = new ProfesseursCompletRepository();
        professeursCompletRepository.get(userID).observe(this, new Observer<ProfesseursComplet>() {
            @Override
            public void onChanged(ProfesseursComplet professeursComplet) {
                Log.i("Professeurs Fetch", professeursComplet.getProfesseur().toString());
                Log.i("Professeurs Fetch", "");

                TabLayout tabLayout = findViewById(R.id.tabLayoutTests);
                // Set the text for each tab.

                // Set the tabs to fill the entire layout.
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                final ViewPager2 viewPager = findViewById(R.id.testsCompletsPager);

                final TestsPageAdapter adapter = new TestsPageAdapter(getSupportFragmentManager(), getLifecycle(),professeursComplet.getCours());
                viewPager.setAdapter(adapter);

                new TabLayoutMediator(tabLayout, viewPager,
                        (tab, position) -> {
                            tab.setText(professeursComplet.getCours().get(position).getNom_cours());
                        }
                ).attach();


                viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        Log.wtf("HEYO PAGE", "Position : "+ position);
                        //super.onPageSelected(position);
                        viewPager.setCurrentItem(position);
                        Log.i("CURRENT PAGE ", viewPager.getCurrentItem() + "");
                        // Toast.makeText(getApplicationContext(), "Selected : " + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onFABAddTest(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    /*
    private void setUpRecyclerView(List<TestsComplet> testsComplets, List<Professeurs> professeurs, List<Cours> cours){

        Log.i("Recycler", recyclerView.toString());
        LinkedList<TestsComplet> linkedList = new LinkedList<>(testsComplets);
        Log.i("Main Acti LinkedList", linkedList.get(0).getTest().getName());
        testsCompletAdapter = new TestsCompletAdapter(this, linkedList, professeurs, cours);
        recyclerView.setAdapter(testsCompletAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    */
}

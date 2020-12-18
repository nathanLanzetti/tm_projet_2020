package com.example.easyteaching;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import adapters.TestsCompletAdapter;
import model.Cours;
import model.Professeurs;
import model.Tests;
import model.TestsComplet;
import repository.ProfesseursCompletRepository;
import repository.TestsCompletRepository;
import repository.TestsRepository;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestsTabFragment} factory method to
 * create an instance of this fragment.
 */
public class TestsTabFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private Cours cours;
    private RecyclerView recyclerView;
    private TestsCompletAdapter testsCompletAdapter;
    private LifecycleOwner mContext;
    private List<TestsComplet> testsComplets;
    private LinkedList<TestsComplet> linkedList;

    public TestsTabFragment() {
        // Required empty public constructor
    }

    public TestsTabFragment(Cours cours) {
        // Required empty public constructor
        this.cours = cours;
    }

    // TODO: Rename and change types and number of parameters
    /*
    public static TestsTabFragment newInstance(String param1, String param2) {
        TestsTabFragment fragment = new TestsTabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    */

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = (LifecycleOwner) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //testsCompletAdapter;
        /*
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        */

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tests_tab, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewTestsComplet);

        final ProfesseursCompletRepository professeursCompletRepository = new ProfesseursCompletRepository();
        // Fetch Tests | Prof
        final TestsCompletRepository testsCompletRepository = new TestsCompletRepository();
        testsCompletRepository.query().observe(mContext, new Observer<List<TestsComplet>>() {
            @Override
            public void onChanged(final List<TestsComplet> testsComplets) {
                Log.i("Main activity TESTS", testsComplets.toString());
                Log.i("Main activit test Q", testsComplets.get(0).getQuestionsComplets().toString());
                setTestsComplets(testsComplets);


                // Fetch profs
                professeursCompletRepository.query().observe(mContext, new Observer<List<Professeurs>>() {
                    @Override
                    public void onChanged(List<Professeurs> professeurs) {
                    Log.i("Professeurs Fetch", professeurs.toString());
                    Log.i("Professeurs Fetch", "");

                    setUpRecyclerView(testsComplets, professeurs,cours);
                    }
                });
            }
        });

        return v;
    }

    private void setUpRecyclerView(List<TestsComplet> testsComplets, List<Professeurs> professeurs,Cours cours) {
        this.linkedList = new LinkedList<>();
        for (TestsComplet testsComplet: testsComplets) {
            Log.i("Cours in FRAGMENT", cours.getNom_cours() + " " + cours.getId());
            if (testsComplet.getTest().getCours() == cours.getId()){
                linkedList.add(testsComplet);
            }
        }
        Log.i("Fragment LinkedList", linkedList.size() + "");
        Log.i("Fragment Cours", cours.getNom_cours());
        testsCompletAdapter = new TestsCompletAdapter(this.getContext(), linkedList, professeurs, cours);
        recyclerView.setAdapter(testsCompletAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        //set up ItemTouch for swiping
        setUpSwipeFunctionality(recyclerView);
    }

    private void setUpSwipeFunctionality(final RecyclerView recyclerView) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Log.i("SWIPIN", "Position : " +viewHolder.getAdapterPosition());
                int idTest = linkedList.get(viewHolder.getAdapterPosition()).getTest().getId();
                /*
                linkedList.remove(viewHolder.getAdapterPosition());
                testsCompletAdapter.setTestsComplets(linkedList);
                testsCompletAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                */
                // Delete Tests
                onSwipeDeleteTest(idTest);
                linkedList.remove(viewHolder.getAdapterPosition());
                testsCompletAdapter.setTestsComplets(linkedList);
                testsCompletAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                final ColorDrawable background = new ColorDrawable(getResources().getColor(R.color.default_red));
                background.setBounds(0, viewHolder.itemView.getTop(),viewHolder.itemView.getLeft() + Math.round(dX), viewHolder.itemView.getBottom());

                Drawable icon = ContextCompat.getDrawable(getContext(), R.drawable.ic_trash_24);
                // compute top and left margin to the view bounds
                icon.setBounds(0,0, 0, 0);
                if (isCurrentlyActive) {
                    icon.draw(c);
                    background.draw(c);
                }
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void onSwipeDeleteTest(int id) {
        Log.i("POSITION", id + "");
        final TestsRepository testsRepository = new TestsRepository();
        Log.i("LINKED LIST", linkedList.toString());
        for (TestsComplet testsComplet: linkedList) {
            if (testsComplet.getTest().getId() == id){
                Log.i("Heyoooo", "heyaaaaaaaaaaa");
                testsRepository.delete(id);
            }
        }
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public List<TestsComplet> getTestsComplets() {
        return testsComplets;
    }

    public void setTestsComplets(List<TestsComplet> testsComplets) {
        this.testsComplets = testsComplets;
    }
}
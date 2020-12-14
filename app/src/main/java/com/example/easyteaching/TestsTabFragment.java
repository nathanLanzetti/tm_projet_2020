package com.example.easyteaching;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import model.Cours;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestsTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestsTabFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private Cours cours;

    public TestsTabFragment() {
        // Required empty public constructor
    }

    public TestsTabFragment(Cours cours) {
        // Required empty public constructor

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        return inflater.inflate(R.layout.fragment_tests_tab, container, false);
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }
}
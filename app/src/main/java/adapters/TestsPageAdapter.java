package adapters;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.easyteaching.TestsTabFragment;

import java.util.ArrayList;
import java.util.List;

import model.Cours;

public class TestsPageAdapter extends FragmentStateAdapter {
    private List<Cours> cours;

    public TestsPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<Cours> cours) {
        super(fragmentManager, lifecycle);
        this.cours = new ArrayList<>(cours);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.wtf("GETITEM AGAIN", position + "");
        Log.wtf("COURS GETITEM", cours.get(position).getNom_cours());
        TestsTabFragment testsTabFragment = new TestsTabFragment();
        testsTabFragment.setCours(cours.get(position));
        return testsTabFragment;
    }

    @Override
    public int getItemCount() {
        return cours.size();
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }
}

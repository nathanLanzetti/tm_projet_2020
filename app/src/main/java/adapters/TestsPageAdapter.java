package adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.easyteaching.TestsTabFragment;

import java.util.ArrayList;
import java.util.List;

import model.Cours;

public class TestsPageAdapter extends FragmentStatePagerAdapter {
    private List<Cours> cours;

    public TestsPageAdapter(@NonNull FragmentManager fm, int behavior, List<Cours> cours) {
        super(fm, behavior);
        //this.numOfTabs = numOfTabs;
        this.cours = new ArrayList<>(cours);
        Log.i("PAGE ADPATER COURS", cours.toString());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.wtf("GETITEM AGAIN", position + "");
        Log.wtf("COURS GETITEM", cours.get(position).getNom_cours());
        return new TestsTabFragment(cours.get(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return cours.get(position).getNom_cours();
    }

    @Override
    public int getCount() {
        return cours.size();
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }
}

package adapters;

import androidx.annotation.NonNull;
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
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        for (Cours coursSingle: cours) {
            return new TestsTabFragment(coursSingle);
        }
    }

    @Override
    public int getCount() {
        return cours.size();
    }
}

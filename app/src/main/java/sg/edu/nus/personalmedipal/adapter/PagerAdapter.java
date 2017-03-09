package sg.edu.nus.personalmedipal.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import sg.edu.nus.personalmedipal.fragment.ConsumptionListFragment;
import sg.edu.nus.personalmedipal.fragment.MedicineListFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numTabs;

    public PagerAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MedicineListFragment medicineListFragment = new MedicineListFragment();
                return medicineListFragment;
            case 1:
                ConsumptionListFragment consumptionListFragment = new ConsumptionListFragment();
                return consumptionListFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}

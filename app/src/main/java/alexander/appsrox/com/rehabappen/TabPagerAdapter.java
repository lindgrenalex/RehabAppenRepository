package alexander.appsrox.com.rehabappen;

/**
 * Created by Alexander on 2015-11-27.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        Bundle bundle = new Bundle();
        String tab = "";
        int colorResId = 0;
        switch (index) {
            case 0:
                tab = "List of Missed Calls";
                colorResId = R.color.colorAccent;
                break;
            case 1:
                tab = "List of Dialled Calls";
                colorResId = R.color.colorPrimary;
                break;
            case 2:
                tab = "List of Received Calls";
                colorResId = R.color.colorPrimaryDark;
                break;
        }
        bundle.putString("tab",tab);
        bundle.putInt("color", colorResId);
        SwipeTabFragment swipeTabFragment = new SwipeTabFragment();
        swipeTabFragment.setArguments(bundle);
        return swipeTabFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

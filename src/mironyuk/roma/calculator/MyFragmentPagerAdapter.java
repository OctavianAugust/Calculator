package mironyuk.roma.calculator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class MyFragmentPagerAdapter extends FragmentPagerAdapter {

	public MyFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			return new MainFragment();
		case 1:
			return new CustomFragment();
		default:
			return null;
		}
	}
	

	@Override
	public int getCount() {
		return 2;
	}

}
package com.ben.colorpicker.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.ben.colorpicker.R;
import com.ben.colorpicker.ui.picker.PickerActivity;
import com.ben.colorpicker.ui.common.SelectPhotoActivity;
import com.ben.colorpicker.ui.fragment.SampleFragmentPagerAdapter;

import java.io.FileNotFoundException;

public class MainActivity extends SelectPhotoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void setBitmapUri(Uri uri) throws FileNotFoundException {
        Intent intent = new Intent(this,PickerActivity.class);
        intent.putExtra(SelectPhotoActivity.KEY_IMAGE_URI,uri);
        startActivity(intent);
    }
}

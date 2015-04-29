package com.lfrj.diancan.view;

import butterknife.ButterKnife;

import com.lfrj.diancan.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends BaseToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ButterKnife.inject(this);
		initToolSuperBar();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	protected void initToolSuperBar() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <T> void onFailure(int requestCode, int resultCode, T parameter) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <T> void onSuccess(int requestCode, int resultCode, T parameter) {
		// TODO Auto-generated method stub
		
	}
}

package cn.dormitorymanage;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import cn.dormitorymanage.fragment.HomeFragment;
import cn.dormitorymanage.fragment.MyFragment;
import cn.dormitorymanage.fragment.TxlFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentList.add(new HomeFragment());
        fragmentList.add(new TxlFragment());
        fragmentList.add(new MyFragment());
        loadFragment(0);
        findViewById(R.id.mainActivity_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(0);
            }
        });
        findViewById(R.id.mainActivity_txl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(1);
            }
        });
        findViewById(R.id.mainActivity_my).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(2);
            }
        });
    }

    private Fragment mFrag;
    private List<Fragment> fragmentList = new ArrayList<>();
    private void loadFragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentList.get(position);
        if(mFrag != null) {
            transaction.hide(mFrag);
        }
        if(!fragment.isAdded()) {
            transaction.add(R.id.mainActivity_fragment, fragment);
        } else {
            transaction.show(fragment);
        }
        mFrag = fragment;
        transaction.commit();
    }

}

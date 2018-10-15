package id.hadist.icreativelabs.latihanfragments;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    protected Toolbar toolbar;
    protected TabLayout tabLayout;
    protected ViewPager viewPager;
    protected ViewPagerAdapter adapter;
    protected Menu menu;

    private void initComponent(){
        toolbar     = (Toolbar) findViewById(R.id.toolbar);
        tabLayout   = (TabLayout) findViewById(R.id.tab_layout);
        viewPager   = (ViewPager) findViewById(R.id.ViewPager);
        adapter     = new ViewPagerAdapter(getSupportFragmentManager());
    }

    private void setIcon(){
        int icons[] = {
                R.drawable.ic_people_white_24dp,
                R.drawable.ic_chat_white_24dp,
                R.drawable.ic_timeline
        };

        for (int x = 0 ; x < tabLayout.getTabCount() ; x++){
            tabLayout.getTabAt(x).setIcon(icons[x]);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Fragments");

        adapter.setFragmentList(new FirstFragment());
        adapter.setFragmentList(new SecondFragment());
        adapter.setFragmentList(new ThirdFragment());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(this);

        setIcon();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        menu.findItem(R.id.search).setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.people){
            Toast.makeText(getApplicationContext(), "People", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.search){
            Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.list){
            Toast.makeText(getApplicationContext(), "Exit", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        MenuItem people = menu.findItem(R.id.people);
        MenuItem search = menu.findItem(R.id.search);
        MenuItem timeline = menu.findItem(R.id.list);

        if (tab.getPosition() == 0 ){
            getSupportActionBar().setTitle("Friends");
            search.setVisible(true);
            people.setVisible(false);
            timeline.setVisible(false);
        }

        if (tab.getPosition() == 1 ){
            getSupportActionBar().setTitle("Chats");
            search.setVisible(false);
            people.setVisible(true);
            timeline.setVisible(false);
        }

        if (tab.getPosition() == 2){
            getSupportActionBar().setTitle("Timeline");
            search.setVisible(false);
            people.setVisible(false);
            timeline.setVisible(true);
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }
}

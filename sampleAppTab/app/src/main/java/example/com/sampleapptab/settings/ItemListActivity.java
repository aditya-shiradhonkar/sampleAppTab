
package example.com.sampleapptab.settings;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.com.sampleapptab.R;
import example.com.sampleapptab.demochannel.DemoChannelFragment;

public class ItemListActivity extends AppCompatActivity {

    private ArrayList<MenuItem> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        View recyclerView = findViewById(R.id.item_list);

        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(1, "Settings"));
        menuItems.add(new MenuItem(2, "Select Profile"));
        menuItems.add(new MenuItem(3, "About"));
        menuItems.add(new MenuItem(4, "Exit"));

        if (recyclerView != null) {
            setupRecyclerView((RecyclerView) recyclerView, menuItems);
        }
    }

    private void setupRecyclerView(RecyclerView recyclerView, ArrayList<MenuItem> menuItems) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(menuItems));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private ArrayList<MenuItem> mValues = new ArrayList<>();
        // private final List<DummyContent.DummyItem> mValues;

        public SimpleItemRecyclerViewAdapter(ArrayList<MenuItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            // holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).getMenuName());
            holder.mItem = mValues.get(position);
            // holder.mContentView.setText(mValues.get(position).content);*/

            /*
             * menuItems.add("Settings"); menuItems.add("Select Profile"); menuItems.add("About");
             * menuItems.add("Demo Channel"); menuItems.add("Exit");
             */

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (holder.mItem.getMenuId()) {
                        case 1:
                            SettingsFragment settingsFragment = new SettingsFragment();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.item_detail_container, settingsFragment)
                                    .commit();
                            break;
                        case 2:
                            ProfilesFragment profilesFragment = new ProfilesFragment();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.item_detail_container, profilesFragment)
                                    .commit();
                            break;
                        case 3:
                        case 4:
                            Bundle arguments = new Bundle();
                            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.getMenuName());
                            ItemDetailFragment fragment = new ItemDetailFragment();
                            fragment.setArguments(arguments);
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.item_detail_container, fragment)
                                    .commit();
                            break;

                        default:
                            break;
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            // public final TextView mContentView;
            // public DummyContent.DummyItem mItem;

            public MenuItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                // mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}

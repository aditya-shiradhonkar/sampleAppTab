
package example.com.sampleapptab;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.com.sampleapptab.demochannel.DemoChannelFragment;

public class ItemListActivity extends AppCompatActivity {

    private ArrayList<String> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;

        menuItems = new ArrayList<>();
        menuItems.add("Settings");
        menuItems.add("Select Profile");
        menuItems.add("About");
        menuItems.add("Demo Channel");
        menuItems.add("Exit");

        setupRecyclerView((RecyclerView) recyclerView, menuItems);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, ArrayList<String> menuItems) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(menuItems));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private ArrayList<String> mValues = new ArrayList<>();
        //private final List<DummyContent.DummyItem> mValues;

        public SimpleItemRecyclerViewAdapter(ArrayList<String> items) {
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
            //holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position));
            holder.mItem = mValues.get(position);
            //holder.mContentView.setText(mValues.get(position).content);*/

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.mItem.equals("Demo Channel")) {
                        DemoChannelFragment fragment = new DemoChannelFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                        Bundle arguments = new Bundle();
                        arguments.putString(ItemDetailFragment.ARG_ITEM_ID, holder.mItem);
                        ItemDetailFragment fragment = new ItemDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
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
            public final TextView mContentView;
            //public DummyContent.DummyItem mItem;

            public String mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}

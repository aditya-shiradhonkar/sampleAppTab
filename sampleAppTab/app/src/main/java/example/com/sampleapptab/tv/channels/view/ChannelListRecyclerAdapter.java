package example.com.sampleapptab.tv.channels.view;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import example.com.sampleapptab.R;
import example.com.sampleapptab.appframework.ui.BaseFragment;

/**
 * Created by aniruddhatr on 12/29/2016.
 */
public class ChannelListRecyclerAdapter  extends RecyclerView.Adapter<ChannelListRecyclerAdapter.ViewHolder> implements View.OnClickListener {

    private BaseFragment mUiFragment;
    private List<Channel> mChannelList;

    public ChannelListRecyclerAdapter(BaseFragment uiFragment, List<Channel> channelArrayList) {
        this.mUiFragment = uiFragment;
        this.mChannelList = channelArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View productViewHolder = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.channel_item_layout, viewGroup, false);
        return new ViewHolder(productViewHolder);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Channel channel = mChannelList.get(position);
        holder.mChannelNumberTV.setText(channel.mChannelNumber);
        holder.mChannelNameTV.setText(channel.mChannelName);
        holder.mChannelItemLL.setOnClickListener(this);
        holder.mChannelItemLL.setTag(channel);
    }

    @Override
    public int getItemCount() {
        return mChannelList.size();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.channelItemLL:
                Channel channel = (Channel)view.getTag();
                ((ChannelsFragment)mUiFragment).playChannel(channel.getUrl());
                break;
            default:
                break;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView mChannelNumberTV;
        final TextView mChannelNameTV;
        final LinearLayout mChannelItemLL;

        ViewHolder(View itemView) {
            super(itemView);
            mChannelItemLL = (LinearLayout) itemView.findViewById(R.id.channelItemLL);
            mChannelNumberTV = (TextView) itemView.findViewById(R.id.channelNumberTV);
            mChannelNameTV = (TextView) itemView.findViewById(R.id.channelNameTV);
        }
    }
}

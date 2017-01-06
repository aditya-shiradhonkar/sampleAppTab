package example.com.sampleapptab.tv.channels.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import example.com.sampleapptab.R;
import example.com.sampleapptab.appframework.ui.BaseFragment;
import framework.global.Utils;
import framework.network.communication.NetworkCommunication;

/**
 * Created by aniruddhatr on 12/29/2016.
 */
public class ChannelListRecyclerAdapter extends RecyclerView.Adapter<ChannelListRecyclerAdapter.ViewHolder> implements View.OnClickListener {

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

        if (channel.getLogoUrl() != null && !channel.getLogoUrl().isEmpty()) {
            holder.mChannelLogoIV.setVisibility(View.VISIBLE);
            Utils.loadImage(mUiFragment, channel.getLogoUrl(), R.drawable.white_drawable, holder.mChannelLogoIV, NetworkCommunication.getInstance(mUiFragment));
        }

        if(channel.isPlaying()) {
            holder.mChannelPlayingIV.setVisibility(View.VISIBLE);
        } else {
            holder.mChannelPlayingIV.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mChannelList.size();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.channelItemLL:
                Channel channel = (Channel) view.getTag();
                ((ChannelsFragment) mUiFragment).playChannel(channel.getUrl());
                view.findViewById(R.id.channelPlayingIV).setVisibility(View.VISIBLE);
                for (Channel otherChannel : mChannelList) {
                    otherChannel.setPlaying(false);
                }
                channel.setPlaying(true);
                notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView mChannelNumberTV;
        final TextView mChannelNameTV;
        final LinearLayout mChannelItemLL;
        final ImageView mChannelLogoIV;
        final ImageView mChannelPlayingIV;
        final ImageView mDummyClockIV;
        final ImageView mChannelDrawableLeftIV;

        ViewHolder(View itemView) {
            super(itemView);
            mChannelItemLL = (LinearLayout) itemView.findViewById(R.id.channelItemLL);
            mChannelNumberTV = (TextView) itemView.findViewById(R.id.channelNumberTV);
            mChannelNameTV = (TextView) itemView.findViewById(R.id.channelNameTV);
            mChannelLogoIV = (ImageView) itemView.findViewById(R.id.channelLogoIV);
            mChannelPlayingIV = (ImageView) itemView.findViewById(R.id.channelPlayingIV);
            mDummyClockIV = (ImageView) itemView.findViewById(R.id.dummyClockIV);
            mChannelDrawableLeftIV = (ImageView) itemView.findViewById(R.id.channelDrawableLeftIV);
        }
    }
}

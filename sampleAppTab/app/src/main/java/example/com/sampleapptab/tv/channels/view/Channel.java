package example.com.sampleapptab.tv.channels.view;

/**
 * Created by aniruddhatr on 12/29/2016.
 */
public class Channel {
    String mChannelNumber;
    String mChannelName;
    String mUrl = "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8";

    public String getChannelNumber() {
        return mChannelNumber;
    }

    public void setChannelNumber(String channelNumber) {
        this.mChannelNumber = channelNumber;
    }

    public String getChannelName() {
        return mChannelName;
    }

    public void setChannelName(String channelName) {
        this.mChannelName = channelName;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }
}

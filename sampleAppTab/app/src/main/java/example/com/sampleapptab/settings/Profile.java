package example.com.sampleapptab.settings;

/**
 * Created by aniruddhatr on 1/10/2017.
 */

public class Profile {
    private String profileName;
    private String macAddress;
    private String url;

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileName='" + profileName + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

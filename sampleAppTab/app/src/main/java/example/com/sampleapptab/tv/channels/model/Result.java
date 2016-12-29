
package example.com.sampleapptab.tv.channels.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("censored")
    @Expose
    private Integer censored;
    @SerializedName("genre_id")
    @Expose
    private String genreId;
    @SerializedName("favorite")
    @Expose
    private Integer favorite;
    @SerializedName("archive")
    @Expose
    private Integer archive;
    @SerializedName("archive_range")
    @Expose
    private Integer archiveRange;
    @SerializedName("pvr")
    @Expose
    private Integer pvr;
    @SerializedName("monitoring_status")
    @Expose
    private Integer monitoringStatus;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("url")
    @Expose
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCensored() {
        return censored;
    }

    public void setCensored(Integer censored) {
        this.censored = censored;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public Integer getFavorite() {
        return favorite;
    }

    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    public Integer getArchive() {
        return archive;
    }

    public void setArchive(Integer archive) {
        this.archive = archive;
    }

    public Integer getArchiveRange() {
        return archiveRange;
    }

    public void setArchiveRange(Integer archiveRange) {
        this.archiveRange = archiveRange;
    }

    public Integer getPvr() {
        return pvr;
    }

    public void setPvr(Integer pvr) {
        this.pvr = pvr;
    }

    public Integer getMonitoringStatus() {
        return monitoringStatus;
    }

    public void setMonitoringStatus(Integer monitoringStatus) {
        this.monitoringStatus = monitoringStatus;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", censored=" + censored +
                ", genreId='" + genreId + '\'' +
                ", favorite=" + favorite +
                ", archive=" + archive +
                ", archiveRange=" + archiveRange +
                ", pvr=" + pvr +
                ", monitoringStatus=" + monitoringStatus +
                ", logo='" + logo + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

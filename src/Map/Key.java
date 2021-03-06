package map;

/**
 * Created by ���� on 23.04.2016.
 */
public class Key implements Comparable<Key> {
    private long day;
    private String user;
    private String url;

    public Key(long timeStart, String user, String url){
        this.day = Go.dayOf(timeStart);
        this.user = user;
        this.url = url;
    }

    public void setDay(long day) {
        this.day = Go.dayOf(day);
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getDay() {
        return day;
    }

    public String getUser() {
        return user;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object b) {
        if (b != null && b instanceof Key) {

            return (b != null) &&  ((Key) b).day ==(this.day)
                    && (((Key) b).url != null && ((Key) b).url.equals(this.url)) &&
                    (((Key) b).user != null && ((Key) b).user.equals(this.user));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (((url.hashCode()) * 19 + Long.hashCode(this.day)  + user.hashCode()));

    }




    @Override
    public int compareTo(Key c) {
        if (c != null && c instanceof Key) {
            Key  b = (Key)c;
            //b<this - -1
            if(this.day >  b.day) return 1;
            else if (this.day < b.day) return -1;
            else {
                if (this.user.compareTo(b.user) != 0 ){
                    return this.user.compareTo(b.user);
                }
                else {
                    return this.url.compareTo(b.url);
                }
            }
        }
        return 0;
    }
}

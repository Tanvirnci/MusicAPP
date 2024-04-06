package musicapp;

import java.util.Objects;

/**
 *
 * @author tanvi
 */
public class Songs {
    
    private final String title;
    private final String artist;

    public Songs(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    // Getters for title and artist
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    //method to compares title and artist attributes for equality using Objects.equals()
    public boolean equals(Songs obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Songs other = (Songs) obj;
        return Objects.equals(this.title, other.title) && Objects.equals(this.artist, other.artist);
    }

    @Override
    //emproving the preformance (big 0 notation)
    //I created this method to enhance the search calculating big (O) notation 
    public int hashCode() {
        return Objects.hash(title, artist);
    }
    
    @Override
    public String toString() {
        return "{" + "title= " + title + ", artist= " + artist + '}';
    }
    
}
//this is mine 

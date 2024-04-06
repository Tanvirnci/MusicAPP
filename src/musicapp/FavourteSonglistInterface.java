
package musicapp;

/**
 *
 * @author tanvi
 */
public interface FavourteSonglistInterface {
    
void addSong();
    void deleteSong();
    void printPlaylist();
    int countSongs();
//    void setRepeat(boolean repeat);
    void searchSong(String title, String artist);
    void setRepeat();
    void setNotRepeat();
    void addLastSongToGenre();
}

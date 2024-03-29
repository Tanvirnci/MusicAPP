
package musicapp;

/**
 *
 * @author tanvi
 */
public interface GenrePlaySongInterface {
    void addCountrymusic Song(Song song);
    void addSoulmusicSong(Song song);
    void deleteCountrymusicSong(Song song);
    void deleteSoulmusicSong(Song song);
    void printCountrymusicPlaylist();
    void printSoulmusicPlaylist();
    int countCountrymusicSongs();
    int countSoulmusicSongs();
    void setRepeat(boolean repeat);
    void searchCountrymusicSong(String title, String artist);
    void searchSoulmusicSong(String title, String artist);
}


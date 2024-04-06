
package musicapp;

/**
 *
 * @author tanvi
 */
public interface GenrePlaySongInterface {
    void addCountrymusic(Songs song);
    void addSoulmusicSong(Songs song);
    void deleteCountrymusicSong(Songs song);
    void deleteSoulmusicSong(Songs song);
    void printCountrymusicPlaylist();
    void printSoulmusicPlaylist();
    int countCountrymusicSongs();
    int countSoulmusicSongs();
    void setRepeat(boolean repeat);
    void searchCountrymusicSong(String title, String artist);
    void searchSoulmusicSong(String title, String artist);
}


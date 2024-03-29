package musicapp;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
/**
 *
 * @author tanvi
 */


class GenrePlaylist implements GenrePlaylist Interface {

    //two arraylist for the both genres 
    private final ArrayList<Node> CountrymusicPlaylist;
    private final ArrayList<Node> SoulmusicPlaylist;
    //variable to keep track of repeat 
    private boolean repeat;

    private static GenrePlaylist instance;

    // Private constructor to prevent instantiation
    private GenrePlaylist() {
        this.CountrymusicPlaylist = new ArrayList<>();
        this.SoulmusicPlaylist = new ArrayList<>();
        this.repeat = false;
    }

    // Method to get the singleton instance
    public static GenrePlaylist getInstance() {
        if (instance == null) {
            instance = new GenrePlaylist();
        }
        return instance;
    }
    
    // Add song to Countrymusic the playlist
    @Override
    public void addCountrymusicPlaylist(Song song) {
        //Node class instance 
        Node newNode = new Node(song);
        //adding the new song
        CountrymusicPlaylist.add(newNode);
        JOptionPane.showMessageDialog(null, "Adding song to Countrymusic playlist was successful.");
        //if it is repeat
        if (repeat && CountrymusicPlaylist.size() > 1) {
            // Link the last song to the first song to make it circular
            CountrymusicPlaylist.get(CountrymusicPlaylist.size() - 2).setNext(newNode);
            newNode.setNext(CountrymusicPlaylist.get(0));
        }
    }

    // Add song to the Soulmusic playlist
    @Override
    public void add(song song) {
        //Node class instance 
        Node newNode = new Node(song);
        //adding the new song
        SoulmusicPlaylist.add(newNode);
        JOptionPane.showMessageDialog(null, "Adding song to Soulmusic playlist was successful.");
        //if it is repeat
        if (repeat && SoulmusicPlaylist.size() > 1) {
            // Link the last song to the first song to make it circular
            SoulmusicPlaylist.get(SoulmusicPlaylist.size() - 2).setNext(newNode);
            newNode.setNext(SoulmusicPlaylist.get(0));
        }
    }

    // Delete song from the Countrymusic playlist
    @Override
    public void deleteCountrymusic(song songToDelete) {
        // Check if the list is empty
        if (CountrymusicPlaylist.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Countrymusic playlist is empty. No song to delete.");
            return;
        }

        // variable to check if the song is found 
        boolean songFound = false;

        // Iterate through the playlist to find and delete the song
        Iterator<Node> iterator = CountrymusicPlaylist.iterator();
        while (iterator.hasNext()) {
            Node currentNode = iterator.next();
            if (currentNode.getSong().equals(songToDelete)) {
                iterator.remove(); // Remove the song from the playlist
                songFound = true;
                break; // Exit the loop once the song is deleted
            }
        }

        // If the song was found and deleted, notify the user
        if (songFound) {
            JOptionPane.showMessageDialog(null, "Song deleted from Countrymusic playlist.");
        } else {
            JOptionPane.showMessageDialog(null, "Song not found in Countrymusic playlist.");
        }
    }
    
    // Delete song from the Soulmusic playlist
    @Override
    public void deleteSoulmusicSong(Song songToDelete) {
        // Check if the list is empty
        if (SoulmusicPlaylist.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Soulmusicplaylist is empty. No song to delete.");
            return;
        }

        // variable to check if the song is found 
        boolean songFound = false;

        // Iterate through the playlist to find and delete the song
        Iterator<Node> iterator = SoulmusicPlaylist.iterator();
        while (iterator.hasNext()) {
            Node currentNode = iterator.next();
            if (currentNode.getSong().equals(songToDelete)) {
                iterator.remove(); // Remove the song from the playlist
                songFound = true;
                break; // Exit the loop once the song is deleted
            }
        }

        // If the song was found and deleted, notify the user
        if (songFound) {
            JOptionPane.showMessageDialog(null, "Song deleted from Soulmusicplaylist.");
        } else {
            JOptionPane.showMessageDialog(null, "Song not found in Soulmusicplaylist.");
        }
    }

    // Print the Countrymusic playlist
    @Override
    public void printCountrymusicPlaylist() {
        StringBuilder playlistContent = new StringBuilder("Countrymusic Playlist:\n");

        // Check if the playlist is empty
        if (CountrymusicPlaylist.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The Countrymusic playlist is empty.");
            return;
        }

        // Iterate over each Node in the playlist and append the song information to the StringBuilder
        for (Node node : CountrymusicPlaylist) {
            // Retrieve the Song object associated with the current Node
            Song song = node.getSong();
            // Append the title and artist of the song to the StringBuilder
            playlistContent.append(song.getTitle()).append(" - ").append(song.getArtist()).append("\n");
        }

        // Show the playlist content in a JOptionPane
        JOptionPane.showMessageDialog(null, playlistContent.toString());
    }

    
    // Print the Soulmusic playlist
    @Override
    public void printSoulmusicPlaylist() {
        StringBuilder playlistContent = new StringBuilder("Soulmusic Playlist:\n");

        // Check if the playlist is empty
        if (SoulmusicPlaylist.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The Soulmusicplaylist is empty.");
            return;
        }

        // Iterate over each Node in the playlist and append the song information to the StringBuilder
        for (Node node : SoulmusicPlaylist) {
            // Retrieve the Song object associated with the current Node
            Song song = node.getSong();
            // Append the title and artist of the song to the StringBuilder
            playlistContent.append(song.getTitle()).append(" - ").append(song.getArtist()).append("\n");
        }

        // Show the playlist content using JOptionPane
        JOptionPane.showMessageDialog(null, playlistContent.toString());
    }


    // Count total songs in Countrymusic
    @Override
    public int countCountrymusicSongs() {
        
        JOptionPane.showMessageDialog(null , "The size is: " + CountrymusicPlaylist.size() );
        return CountrymusicPlaylist.size();
        
    }

    // Count total songs in Soulmusic
    @Override
    public int countSoulmusicSongs() {
        
        JOptionPane.showMessageDialog(null , "The size is: " + SoulmusicPlaylist.size() );
        return SoulmusicPlaylist.size();
    }

    // Set repeat mode for Countrymusic
    @Override
    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
        
        if (repeat) {
            // Ensure the last song is linked to the first song for both playlists
            if (!CountrymusicPlaylist.isEmpty()) {
                Node lastHipHopNode = CountrymusicPlaylist.get(CountrymusicPlaylist.size() - 1);
                lastHipHopNode.setNext(CountrymusicPlaylist.get(0));
            }
            if (!SoulmusicPlaylist.isEmpty()) {
                Node lastPianoNode = SoulmusicPlaylist.get(SoulmusicPlaylist.size() - 1);
                lastPianoNode.setNext(SoulmusicPlaylist.get(0));
            }
        }
    }
    
    // Search for a song in the Countrymusic playlist by title or artist
    @Override
    public void searchCountrymusicSong(String title, String artist) {
        if (CountrymusicPlaylist.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Countrymusic playlist is empty. No songs to search.");
            return;
        }

        boolean songFound = false;

        // Iterate through the playlist to find the song matching the search term
        for (Node currentNode : CountrymusicPlaylist) {
            Song currentSong = currentNode.getSong();
            if (currentSong.getTitle().equalsIgnoreCase(title) || currentSong.getArtist().equalsIgnoreCase(artist)) {
                JOptionPane.showMessageDialog(null, "Song found in Countrymusic playlist: " + currentSong.getTitle() + " - " + currentSong.getArtist());
                songFound = true;
                break;
            }
        }

        if (!songFound) {
            JOptionPane.showMessageDialog(null, "Song not found in Countrymusic playlist.");
        }
    }

    // Search for a song in the Soulmusic playlist by title or artist
    @Override
    public void searchSoulmusicSong(String title, String artist) {
        if (SoulmusicPlaylist.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Soulmusicplaylist is empty. No songs to search.");
            return;
        }

        boolean songFound = false;

        // Iterate through the playlist to find the song matching the search term
        for (Node currentNode : SoulmusicPlaylist) {
            Song currentSong = currentNode.getSong();
            if (currentSong.getTitle().equalsIgnoreCase(title) || currentSong.getArtist().equalsIgnoreCase(artist)) {
                JOptionPane.showMessageDialog(null, "Song found in Soulmusic playlist: " + currentSong.getTitle() + " - " + currentSong.getArtist());
                songFound = true;
                break;
            }
        }

        if (!songFound) {
            JOptionPane.showMessageDialog(null, "Song not found in Soulmusic playlist.");
        }
    }
}

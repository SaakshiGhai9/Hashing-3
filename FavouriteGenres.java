// time Complexity : O(Genres * Songs + Users * N) where N is the average number of songs per user
// Space Complexity: O(Genre * Songs) and O(Users * Genres) for worst case scenario
import java.util.*;

public class FavouriteGenres {
    public Map<String, List<String>> favoriteGenres(Map<String, List<String>> userSongs,
                                                    Map<String, List<String>> songGenres) {
        Map<String, List<String>> result = new HashMap<>();

        // Step 1: Create a song-to-genre map
        Map<String, String> songToGenre = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : songGenres.entrySet()) {
            String genre = entry.getKey();
            for (String song : entry.getValue()) {
                songToGenre.put(song, genre);
            }
        }

        // Step 2: Count genre frequencies for each user
        for (Map.Entry<String, List<String>> entry : userSongs.entrySet()) {
            String user = entry.getKey();
            List<String> songs = entry.getValue();
            Map<String, Integer> genreCount = new HashMap<>();

            // Count genres for the user's songs
            for (String song : songs) {
                String genre = songToGenre.getOrDefault(song, null);
                if (genre != null) {
                    genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
                }
            }

            // Step 3: Identify favorite genres
            int maxCount = 0;
            List<String> favoriteGenres = new ArrayList<>();
            for (Map.Entry<String, Integer> countEntry : genreCount.entrySet()) {
                int count = countEntry.getValue();
                if (count > maxCount) {
                    maxCount = count;
                    favoriteGenres.clear();
                    favoriteGenres.add(countEntry.getKey());
                } else if (count == maxCount) {
                    favoriteGenres.add(countEntry.getKey());
                }
            }

            result.put(user, favoriteGenres);
        }

        return result;
    }

    public static void main(String[] args) {
        FavouriteGenres fg = new FavouriteGenres();

        Map<String, List<String>> userSongs = new HashMap<>();
        userSongs.put("Alice", Arrays.asList("song1", "song2", "song3", "song4", "song8"));
        userSongs.put("Bob", Arrays.asList("song5", "song6", "song7"));

        Map<String, List<String>> songGenres = new HashMap<>();
        songGenres.put("Rock", Arrays.asList("song1", "song3"));
        songGenres.put("Pop", Arrays.asList("song7", "song8"));
        songGenres.put("Jazz", Arrays.asList("song2", "song4", "song5", "song6"));

        System.out.println(fg.favoriteGenres(userSongs, songGenres));
    }
}

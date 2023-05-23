package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnimeService {
    private static final String FILE_PATH = "D:/LR2/list.txt";

    public List<AnimeModel> getAllAnime() throws IOException {
        List<AnimeModel> library = loadLibrary();

        if (library == null) {
            library = new ArrayList<>();
        }

        return library;
    }

    public void addAnime(AnimeModel anime) throws IOException {
        List<AnimeModel> library = loadLibrary();
        library.add(anime);
        saveLibrary(library);
    }

    private List<AnimeModel> loadLibrary() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        List<AnimeModel> library = new Gson().fromJson(reader, new TypeToken<List<AnimeModel>>(){}.getType());
        reader.close();

        return library;
    }

    private void saveLibrary(List<AnimeModel> library) throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH);
        new Gson().toJson(library, writer);
        writer.close();
    }
}

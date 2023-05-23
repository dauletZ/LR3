package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AnimeServlet", value = "/anime")
public class AnimeController extends HttpServlet {
    private AnimeService animeService;

    @Override
    public void init() throws ServletException {
        animeService = new AnimeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<AnimeModel> library = animeService.getAllAnime();
        String json = new Gson().toJson(library);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String format = request.getParameter("format");
        String releaseDate = request.getParameter("releaseDate");
        String genre = request.getParameter("genre");
        String status = request.getParameter("status");

        AnimeModel newAnime = new AnimeModel(title, format, releaseDate, genre, status);

        animeService.addAnime(newAnime);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
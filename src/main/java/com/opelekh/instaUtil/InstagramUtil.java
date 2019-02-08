package com.opelekh.instaUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class InstagramUtil {

    public List<String> getImagesUrls(String postUrl) {
        if (postUrl == null || postUrl.length() < 21)
            return null;

        List<String> urlsList = new ArrayList<>();
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(readJson(postUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject obj = jsonObject.getJSONObject("graphql").getJSONObject("shortcode_media");

        if (obj.has("edge_sidecar_to_children")) {      // if the post has several photos
            JSONArray jsonArray = obj.getJSONObject("edge_sidecar_to_children").getJSONArray("edges");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject imgJson = (JSONObject) jsonArray.get(i);
                urlsList.add(imgJson
                        .getJSONObject("node")
                        .getString("display_url"));
            }
        } else {
            urlsList.add(jsonObject
                    .getJSONObject("graphql")
                    .getJSONObject("shortcode_media")
                    .getString("display_url"));
        }

        return urlsList;
    }

    public String readJson(String postUrl) throws IOException {
        // Convert post link to json link
        String jsonPostUrl = postUrl.replaceAll("\\?(.*)","?__a=1");

        URLConnection connection = new URL(jsonPostUrl).openConnection();
        StringBuilder response = new StringBuilder();

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {

            String inputLine;

            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
        }

        return response.toString();
    }
}

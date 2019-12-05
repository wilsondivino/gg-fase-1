package br.com.wilsondivino;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * ListarTopicosReddit
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<String> titles = new ArrayList<String>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.reddit.com/r/programming.json")).build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(response -> {

                    JSONObject object = new JSONObject(response);

                    JSONArray lista = object.getJSONObject("data").getJSONArray("children");

                    for(int i = 0; i < lista.length(); i++) {

                        String title = lista.getJSONObject(i).getJSONObject("data").getString("title");

                        titles.add(title);

                    }

                    titles.sort(String::compareToIgnoreCase);

                    System.out.println(titles);

                })
                .join();
    }
}

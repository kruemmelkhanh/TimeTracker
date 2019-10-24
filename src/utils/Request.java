package utils;

import com.sun.istack.internal.NotNull;

import java.io.*;
import java.net.*;
import java.util.*;

public class Request  {

    private String requestUrl;
    private String userName;
    private String password;

    public Request(String requestUrl, String userName, String password) {
        this.requestUrl = requestUrl;
        this.userName = userName;
        this.password = password;
    }
//response convert to string
    public String convertToString(InputStream in) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            reader.close();

            return out.toString();
        } catch(Exception e) {
            return "Failed!";
        }
    }
//http url verbindung
    public String getResponse() {

        String username = this.userName;
        String password = this.password;
        String userpass = username + ":" + password;
        String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());

        URL url;

        try {
            url = new URL(requestUrl + "?" + getParameters());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Authorization", basicAuth);
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return convertToString(conn.getInputStream());
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }
//häng die Parameter an die URL
    private String getParameters() {
        ArrayList<Pair> params = new ArrayList<>();
        params.add(new Pair("fields", "labels"));
        params.add(new Pair("fields", "timetracking"));
        params.add(new Pair("fields", "subtasks"));

        StringBuilder postData = new StringBuilder();

        try {
            for (Iterator<Pair> param = params.iterator(); param.hasNext(); ) {
                Pair curPair = param.next();

                if (postData.length() != 0) postData.append('&');

                postData.append(URLEncoder.encode(curPair.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(curPair.getValue(), "UTF-8"));
            }

            return postData.toString();
        } catch(Exception e) {
            return "";
        }
    }
}

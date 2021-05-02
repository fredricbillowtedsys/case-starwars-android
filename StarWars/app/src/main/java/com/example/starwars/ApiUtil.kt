package com.example.starwars

import com.example.starwars.model.Person
import com.example.starwars.model.PersonsResponse
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection


object ApiUtil {
    const val PEOPLE_ENDPOINT = "https://swapi.dev/api/people/"

    private fun fetchJson(urlStr: String): JSONObject? {
        var jsonArray: JSONObject? = null

        var unsecureUrl = URL(urlStr)
        val url = URL("https", unsecureUrl.host, unsecureUrl.port, unsecureUrl.file)

        var con: HttpURLConnection = url.openConnection() as HttpsURLConnection
        con.requestMethod = "GET"
        con.setRequestProperty("Accept", "application/json");
        var jsonString: String

        try {
            val bufferedReader = BufferedReader(InputStreamReader(con.inputStream))
            val stringBuilder = StringBuilder()
            var line: String?

            do {
                line = bufferedReader.readLine()
                if (line != null) {
                    stringBuilder.append(line + '\n')
                }
            } while (line != null)

            jsonString = stringBuilder.toString()
        } finally {
            con.disconnect()
        }
        try {
            if (jsonString != null) {
                jsonArray = JSONObject(jsonString)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonArray
    }

    fun <T> fetchPersons(url: String): PersonsResponse? {
            val obj = fetchJson(url)
            return personsResponseParser(obj)
    }

    private fun personsResponseParser(json: JSONObject?): PersonsResponse {
        val count = json?.getInt("count")
        val nextUrl = json?.getString("next")
        val personsJson = json?.get("results") as JSONArray
        val persons = personParser(personsJson)
        return PersonsResponse(count, nextUrl, persons)
    }

    private fun personParser(json: JSONArray?): ArrayList<Person> {
        val arr = ArrayList<Person>()
        if (json != null) {
            for (i in 0 until json.length()) {
                val o = json[i] as JSONObject
                val person = Person(
                    o.getString("name"),
                    o.getString("height"),
                    o.getString("mass"),
                    o.getString("hair_color"),
                    o.getString("skin_color"),
                    o.getString("eye_color"),
                    o.getString("birth_year"),
                    o.getString("gender"),
                    o.getString("created"),
                    o.getString("edited"),
                    o.getString("url")
                )
                arr.add(person)
            }
        }
        return arr;
    }
}
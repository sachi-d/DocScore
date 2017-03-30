package com.cfscr;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


/**
 * Created by Sachi on 3/29/2017.
 */
public class Document {

    public static final String StudentID = "{\n" +
            "  \"documentName\": \"studentID\",\n" +
            "  \"university\": \"University of Moratuwa\",\n" +
            "  \"faculty\": \"Engineering\",\n" +
            "  \"degree\": \"BSc Eng Hons\",\n" +
            "  \"name\": {\n" +
            "    \"surname\": \"Dangalla\",\n" +
            "    \"initials\": \"DADJS\"\n" +
            "  },\n" +
            "  \"indexNo\": \"130094R\",\n" +
            "  \"address\": {\n" +
            "    \"line1\": \"9A, De Mel Watta Road, Koswatta\",\n" +
            "    \"line2\": \"Nawala\"\n" +
            "  },\n" +
            "  \"nic\": \"937741180V\",\n" +
            "  \"validUntil\": \"Dec 2018\",\n" +
            "  \"cardId\": \"STU-1-41022060-317-130094R\"\n" +
            "}";
    public static final String Passport = "{\n" +
            "  \"documentName\": \"Passport\",\n" +
            "  \"passportNo\": \"N6663355\",\n" +
            "  \"surname\": \"Lokupothagamage Don\",\n" +
            "  \"otherNames\": \"Chanaka Lakmal\",\n" +
            "  \"nationalStatus\": \"Sri Lankan\",\n" +
            "  \"profession\": \"University Student\",\n" +
            "  \"dateOfBirth\": \"18/11/1992\",\n" +
            "  \"idNo\": \"923231196V\",\n" +
            "  \"sex\": \"M\",\n" +
            "  \"placeOfBirth\": \"Kurunegala\",\n" +
            "  \"dateOfIssue\": \"25/04/2016\",\n" +
            "  \"dateOfExpiry\": \"25/04/2026\",\n" +
            "  \"photo\": \"/img/n6663355.jpg\"\n" +
            "}";
    public static final String NIC = "{\n" +
            "  \"documentName\": \"NIC\",\n" +
            "  \"nic\": \"923231126V\",\n" +
            "  \"photograph\": \"img/user123/nic.png\",\n" +
            "  \"date\": \"2009/12/12\",\n" +
            "  \"fullname\": \"Lokupothagamage Don Chanak Lakmal\",\n" +
            "  \"otherNames\": \"\",\n" +
            "  \"gender\": \"male\",\n" +
            "  \"birthDate\": \"1992/11/18\",\n" +
            "  \"birthPlace\": \"Kurunegala\",\n" +
            "  \"career\": \"\",\n" +
            "  \"address\": {\n" +
            "    \"line1\": \"9A, De Mel Watta Road, Koswatta\",\n" +
            "    \"line2\": \"Nawala\"\n" +
            "  },\n" +
            "  \"cardId\": \"123A-654B-987D\"\n" +
            "}";

    String[] labels;
    String[] values;

    public void getDummyDocument(String jsonString) {
        TreeMap<String, String> doc = new TreeMap<String, String>();
        iterateJsonObject(jsonString, doc);
        for (String k : doc.keySet()) {
            System.out.println("Key: " + k);
            System.out.println("Val: " + doc.get(k));
        }
    }

    public void iterateJsonObject(String objString, TreeMap<String, String> result) {
        JsonObject obj = new JsonParser().parse(objString).getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entrySet = obj.entrySet();
        for (Map.Entry<String, JsonElement> entry : entrySet) {

            if (entry.getValue().isJsonObject()) {
                iterateJsonObject(entry.getValue().toString(), result);
            } else {
                result.put(entry.getKey(), entry.getValue().toString());
            }
        }
    }

    public static void main(String[] args) {
        Document doc = new Document();
        doc.getDummyDocument(Document.StudentID);
    }

}

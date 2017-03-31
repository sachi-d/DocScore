package com.cfscr;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.util.*;


/**
 * Created by Sachi on 3/29/2017.
 */
public class Document {

    public static final String StudentID = "{\n" +
            "  \"documentName\": \"Student ID Card\",\n" +
            "  \"university\": \"University of Moratuwa\",\n" +
            "  \"faculty\": \"Engineering\",\n" +
            "  \"degree\": \"BSc Eng Hons\",\n" +
            "  \"name\": {\n" +
            "    \"surname\": \"Dangalla\",\n" +
            "    \"initials\": \"D.A.D.J.S\"\n" +
            "  },\n" +
            "  \"studentIDNo\": \"130094R\",\n" +
            "  \"address\": {\n" +
            "    \"line1\": \"9A, De Mel Watta Road, Koswatta\",\n" +
            "    \"line2\": \"Nawala\"\n" +
            "  },\n" +
            "  \"nic\": \"937741180V\",\n" +
            "  \"studentIDValidUntil\": \"Dec 2018\",\n" +
            "  \"studentIDCardID\": \"STU-1-41022060-317-130094R\"\n" +
            "}";
    public static final String Passport = "{\n" +
            "  \"documentName\": \"Passport\",\n" +
            "  \"passportNo\": \"N6663355\",\n" +
            "  \"surname\": \"Lokupothagamage Don\",\n" +
            "  \"otherNames\": \"Chanaka Lakmal\",\n" +
            "  \"nationality\": \"Sri Lankan\",\n" +
            "  \"profession\": \"University Student\",\n" +
            "  \"birthDate\": \"18/11/1992\",\n" +
            "  \"nic\": \"923231196V\",\n" +
            "  \"sex\": \"M\",\n" +
            "  \"birthPlace\": \"Kurunegala\",\n" +
            "  \"passportIssueDate\": \"25/04/2016\",\n" +
            "  \"passportExpiryDate\": \"25/04/2026\",\n" +
            "  \"photo\": \"/img/n6663355.jpg\"\n" +
            "}";
    public static final String NIC = "{\n" +
            "  \"documentName\": \"NIC\",\n" +
            "  \"nic\": \"923231126V\",\n" +
            "  \"photo\": \"img/user123/nic.png\",\n" +
            "  \"nicIssueDate\": \"2009/12/12\",\n" +
            "  \"fullname\": \"Lokupothagamage Don Chanak Lakmal\",\n" +
            "  \"otherNames\": \"\",\n" +
            "  \"sex\": \"male\",\n" +
            "  \"birthDate\": \"1992/11/18\",\n" +
            "  \"birthPlace\": \"Kurunegala\",\n" +
            "  \"profession\": \"\",\n" +
            "  \"address\": {\n" +
            "    \"line1\": \"9A, De Mel Watta Road, Koswatta\",\n" +
            "    \"line2\": \"Nawala\"\n" +
            "  },\n" +
            "  \"NICCardId\": \"123A-654B-987D\"\n" +
            "}";

    private TreeMap<String, String> documentElements;
    private String jsonString;

    public Document(String jsonString) {
        this.jsonString = jsonString;
        this.documentElements = getDummyDocument(jsonString);
    }

    private TreeMap<String, String> getDummyDocument(String jsonString) {
        TreeMap<String, String> doc = new TreeMap<String, String>();
        iterateJsonObject(jsonString, doc);
        for (String k : doc.keySet()) {
            System.out.println(k);
//            System.out.println("Key: " + k + " and value: " + doc.get(k));
        }
        return doc;
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
        return;
    }

    public double calculateScore(ArrayList<Document> docList) {

        return 0;
    }

    public static void main(String[] args) {
        Document studentID = new Document(Document.StudentID);
        Document passport = new Document(Document.Passport);
        Document nic = new Document(Document.NIC);


        ArrayList<Document> userDocuments = new ArrayList<Document>(Arrays.asList(studentID,nic,passport));
        double score=studentID.calculateScore(userDocuments);
        System.out.println(score);
    }

}

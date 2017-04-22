package com.cfscr;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import info.debatty.java.stringsimilarity.*;

import java.util.*;


/**
 * Created by Sachi on 3/29/2017.
 */
public class Document {

    private TreeMap<String, String> documentElements;

    public Document(String jsonString) {
        this.documentElements = parseDocumentJson(jsonString);
        //TODO determine document's unique identifier - "documentName" assumed
    }

    private TreeMap<String, String> parseDocumentJson(String jsonString) {
        TreeMap<String, String> doc = new TreeMap<>();
        iterateJsonObject(jsonString, doc);
        return doc;
    }

    private void iterateJsonObject(String objString, TreeMap<String, String> result) {
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

    public double calculateScore(ArrayList<Document> docList) {

        docList.remove(this);//remove document currently calculating

        TreeMap<String, String> compareLevels = Label.getLabelCompLevels();
        double score = 0;
        //TODO use threads for each label
        for (String label : this.documentElements.keySet()) {
            String compareLevel = compareLevels.get(label);

            String value = this.documentElements.get(label);
            double labelScore;

            ArrayList<String> benches = new ArrayList<>();
            for (Document d : docList) {
                String tempVal = d.getDocumentElements().get(label);
                if (null != tempVal) {
                    benches.add(tempVal);
                }
            }

            if (null != compareLevel) { //null for documentName
                switch (compareLevel) {
                    case "exact":
                        labelScore = getExactScore(value, benches);
                        //TODO determine whether to get average or weighted scores
                        score += labelScore;
                        break;
                    case "partial":
                        labelScore = getPartialScore(value, benches);
                        score += labelScore;
                        break;
                    default:
                        break;
                }
            }
        }

        return score;
    }

    private double getExactScore(String value, ArrayList<String> benches) {
        //TODO set method body
        return 0;
    }

    private double getPartialScore(String value, ArrayList<String> benches) {
        //TODO set method body
        return 0;
    }


    public TreeMap<String, String> getDocumentElements() {
        return documentElements;
    }

    public String getDocumentContentAsString() {
        StringJoiner sb = new StringJoiner(" ");
        for (String lab : this.documentElements.keySet()) {
            sb.add(this.documentElements.get(lab).replaceAll("\"", ""));
//            System.out.println(this.documentElements.get(lab).replaceAll("\"", ""));
        }
        return sb.toString();
    }

    public static double calculateJaroWinkler(Document[] docs) {
        int n = docs.length;
        int pairCount = n * (n - 1) / 2;

        String[] contentStrings = new String[n];
        for (int i = 0; i < n; i++) {
            contentStrings[i] = docs[i].getDocumentContentAsString();
            System.out.println(docs[i].getDocumentContentAsString());
        }

//        ArrayList<Double> pairScores=new ArrayList<>();
        JaroWinkler jw = new JaroWinkler();
        double totScore = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double pairScore = jw.similarity(contentStrings[i], contentStrings[j]);
//                pairScores.add(pairScore);
                totScore += pairScore;
            }
        }
        double avgScore = totScore / pairCount;
        return avgScore;
    }

    public static void main(String[] args) {
        String studentIDString = "{ documentName\": \"Student ID Card\"," +
                "  \"university\": \"University of Moratuwa\"," +
                "  \"faculty\": \"Engineering\"," +
                "  \"degree\": \"BSc Eng Hons\"," +
                "  \"name\": {" +
                "    \"surname\": \"Lokupothagamage Don\"," +
                "    \"initials\": \"Chanaka Lakmal\"" +
                "  }," +
                "  \"studentIDNo\": \"130094R\"," +
                "  \"address\": {" +
                "    \"line1\": \"9A, De Mel Watta Road, Koswatta\"," +
                "    \"line2\": \"Kurunegala\"" +
                "  }," +
                "  \"nic\": \"923231196V\"," +
                "  \"studentIDValidUntil\": \"Dec 2018\"," +
                "  \"studentIDCardID\": \"STU-1-41022060-317-130094R\"" +
                "}";
        String passportString = "{" +
                "  \"documentName\": \"Passport\"," +
                "  \"passportNo\": \"N6663355\"," +
                "  \"surname\": \"Lokupothagamage Don\"," +
                "  \"otherNames\": \"Chanaka Lakmal\"," +
                "  \"nationality\": \"Sri Lankan\"," +
                "  \"profession\": \"University Student\"," +
                "  \"birthDate\": \"18/11/1992\"," +
                "  \"nic\": \"923231196V\"," +
                "  \"sex\": \"M\"," +
                "  \"birthPlace\": \"Kurunegala\"," +
                "  \"passportIssueDate\": \"25/04/2016\"," +
                "  \"passportExpiryDate\": \"25/04/2026\"," +
                "  \"photo\": \"/img/n6663355.jpg\"" +
                "}";
        String nicString = "{" +
                "  \"documentName\": \"NIC\"," +
                "  \"nic\": \"923231126V\"," +
                "  \"photo\": \"img/user123/nic.png\"," +
                "  \"nicIssueDate\": \"2009/12/12\"," +
                "  \"fullname\": \"Lokupothagamage Don Chanaka Lakmal\"," +
                "  \"otherNames\": \"\"," +
                "  \"sex\": \"male\"," +
                "  \"birthDate\": \"1992/11/18\"," +
                "  \"birthPlace\": \"Kurunegala\"," +
                "  \"profession\": \"\"," +
                "  \"address\": {" +
                "    \"line1\": \"9A, De Mel Watta Road, Koswatta\"," +
                "    \"line2\": \"Kurunegala\"" +
                "  }," +
                "  \"NICCardId\": \"123A-654B-987D\"" +
                "}";


        Document studentID = new Document(studentIDString);
        Document passport = new Document(passportString);
        Document nic = new Document(nicString);


        //RAW STRING CALCULATION

        Document[] docStrings = {studentID, passport, nic};
        double score = Document.calculateJaroWinkler(docStrings);
        System.out.println(score);

        JaroWinkler j = new JaroWinkler();
        System.out.println(j.similarity("ww", "ww"));
    }

}

package com.cfscr;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.util.*;


/**
 * Created by Sachi on 3/29/2017.
 */
public class Document {

    private TreeMap<String, String> documentElements;

    public Document(String jsonString) {
        this.documentElements = parseDocumentJon(jsonString);
        //TODO determine document's unique identifier - "documentName" assumed
    }

    private TreeMap<String, String> parseDocumentJon(String jsonString) {
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


    public static void main(String[] args) {
        String StudentID = "{ documentName\": \"Student ID Card\"," +
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
                "    \"line2\": \"Nawala\"" +
                "  }," +
                "  \"nic\": \"923231196V\"," +
                "  \"studentIDValidUntil\": \"Dec 2018\"," +
                "  \"studentIDCardID\": \"STU-1-41022060-317-130094R\"" +
                "}";
        String Passport = "{" +
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
        String NIC = "{" +
                "  \"documentName\": \"NIC\"," +
                "  \"nic\": \"923231126V\"," +
                "  \"photo\": \"img/user123/nic.png\"," +
                "  \"nicIssueDate\": \"2009/12/12\"," +
                "  \"fullname\": \"Lokupothagamage Don Chanak Lakmal\"," +
                "  \"otherNames\": \"\"," +
                "  \"sex\": \"male\"," +
                "  \"birthDate\": \"1992/11/18\"," +
                "  \"birthPlace\": \"Kurunegala\"," +
                "  \"profession\": \"\"," +
                "  \"address\": {" +
                "    \"line1\": \"9A, De Mel Watta Road, Koswatta\"," +
                "    \"line2\": \"Nawala\"" +
                "  }," +
                "  \"NICCardId\": \"123A-654B-987D\"" +
                "}";


        Document studentID = new Document(StudentID);
        Document passport = new Document(Passport);
        Document nic = new Document(NIC);


        ArrayList<Document> userDocuments = new ArrayList<>(Arrays.asList(studentID, nic, passport));
        double score = studentID.calculateScore(userDocuments);
        System.out.println(score);
    }

}

package dto.game;

public class Game {
    String game = "{\n" +
            "  \"company\": \"string\",\n" +
            "  \"description\": \"string\",\n" +
            "  \"dlcs\": [\n" +
            "    {\n" +
            "      \"description\": \"string\",\n" +
            "      \"dlcName\": \"string\",\n" +
            "      \"isDlcFree\": true,\n" +
            "      \"price\": 0,\n" +
            "      \"rating\": 0,\n" +
            "      \"similarDlc\": {\n" +
            "        \"dlcNameFromAnotherGame\": \"string\",\n" +
            "        \"isFree\": true\n" +
            "      }\n" +
            "    }\n" +
            "  ],\n" +
            "  \"gameId\": 228,\n" +
            "  \"genre\": \"string\",\n" +
            "  \"isFree\": true,\n" +
            "  \"price\": 0,\n" +
            "  \"publish_date\": \"2024-02-12T07:29:40.919Z\",\n" +
            "  \"rating\": 0,\n" +
            "  \"requiredAge\": true,\n" +
            "  \"requirements\": {\n" +
            "    \"hardDrive\": 0,\n" +
            "    \"osName\": \"string\",\n" +
            "    \"ramGb\": 0,\n" +
            "    \"videoCard\": \"string\"\n" +
            "  },\n" +
            "  \"tags\": [\n" +
            "    \"string\"\n" +
            "  ],\n" +
            "  \"title\": \"string\"\n" +
            "}";

    public String getGame() {
        return game;
    }
}

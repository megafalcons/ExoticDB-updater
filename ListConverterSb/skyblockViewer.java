import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.*;

public class skyblockViewer {

    public static void main(String[] args) {
        //LinkedList<String> playerNames = new LinkedList<String>();
        String playerName = "coldsoldier";
        boolean onlineStatus = Snipable(playerName, "FERMENTO_CHESTPLATE", "58890c");

        System.out.println(playerName + " is snipeable: " + onlineStatus);
    }
    
    public static boolean Snipable(String uuid, String piece, String hex) {
        String baseUrl = "https://sky.shiiyu.moe/api/v2/";
        String updateUrl = "https://sky.shiiyu.moe/stats/";
        try {
            // Get player UUID
            //String uuidUrl = baseUrl + "player?key=" + apiKey + "&name=" + playerName;
            //String uuidResponse = sendHttpRequest(uuidUrl);

            // Check if the player was found
                //System.out.println(uuidResponse);
                System.out.println("akdjflkahsdfkljahfla ");
                //String playerUuid = parseUuid(uuidResponse);
                //playerUuid = playerName;
                //System.out.println(playerUuid);
                // Check player's online status
                String statusUrl = baseUrl + "profile/" + uuid;
                //System.out.println(statusUrl);
                //System.out.println("is it working2");
                String updateUrl2 = updateUrl + uuid;
                sendHttpRequest(updateUrl2);
                System.out.println("is it working");
                String statusResponse = sendHttpRequest(statusUrl);
                //System.out.println(statusResponse);
                // Check if the player is online
                if(hasItem(piece, hex, statusResponse)){
                    if(networth(statusResponse) > 400000000 ){
                        //System.out.println("it works!!");
                        return false;
                    }
                    if(skyblockLevel(statusResponse) < 120){
                        return true;
                    }
                    return false;
                }
                else{
                    return false;
                }
                
            /*else {
                System.out.println("why");
                return false;  // Player not found
            }*/
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean hasItem(String piece, String hex, String statusResponse){
        int loc = 0;
        int found = 0;
        int hexFLoc = 0;
        int hexLoc = 0;
        String foundHex;
        int commaLoc = 0;
        while(loc != -1){
            loc = statusResponse.indexOf(piece, loc + 1);
            if(loc == - 1){
                break;
            }
            hexFLoc = statusResponse.indexOf("texture_path", loc);
            hexLoc = statusResponse.indexOf(hex, hexFLoc);
            commaLoc = statusResponse.indexOf(",", hexFLoc);
            if(hexLoc < commaLoc && hexLoc != -1){
                //System.out.println("i have the item");
                return true;
            }
        }

        return false;
    }
    public static double networth(String statusResponse){
        int loc = 0;
        int locOfL = 0;
        double nw = 0;
        int locOfE = 0;
        int locOfE2 = 0;
        double temp = 0;
        while(loc != -1){
            loc = statusResponse.indexOf("unsoulboundNetworth", loc + 1);
            if(loc == - 1){
                break;
            }
            locOfL = statusResponse.indexOf(":", loc);
            locOfE = statusResponse.indexOf(",", locOfL);
            locOfE2 = statusResponse.indexOf(".", locOfL);
            if(locOfE2 < locOfE && locOfE2 != -1){
                temp = Double.parseDouble(statusResponse.substring(locOfL + 1, locOfE2));
            }
            else{
                temp = Double.parseDouble(statusResponse.substring(locOfL + 1, locOfE));
            }   
            nw = Math.max(temp, nw);
        }
        //System.out.println(nw);
        return nw;
    }
    public static int skyblockLevel(String statusResponse){
        int loc = 0;
        int locOfL = 0;
        int level = 0;
        int locOfE = 0;
        int temp = 0;
        while(loc != -1){
            loc = statusResponse.indexOf("skyblock_level", loc + 1);
            if(loc == - 1){
                break;
            }
            locOfL = statusResponse.indexOf(",\"level", loc);
            locOfE = statusResponse.indexOf(",\"m", locOfL);
            temp = Integer.parseInt(statusResponse.substring(locOfL + 9, locOfE));
            level = Math.max(temp, level);
        }
        //System.out.println(level);
        return level;
    }

    private static String sendHttpRequest(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder response = new StringBuilder();

        while (scanner.hasNextLine()) {
            response.append(scanner.nextLine());
        }

        scanner.close();
        return response.toString();
    }

    private static String parseUuid(String response) {
        // Implement your logic to parse the UUID from the JSON response
        // For simplicity, you may use a JSON parsing library like Jackson or Gson
        // This example assumes a simple string manipulation for demonstration purposes
        // Replace this with a proper implementation based on your chosen library
        return response.split("\"uuid\":\"")[1].split("\"")[0];
    }
}
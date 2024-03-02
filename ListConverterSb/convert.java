import java.util.*;
import java.io.*;
public class convert {
    public static void main(String args[]) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("message.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("chestplates.in"));
        String a;
        int x = 7;
        String hex;
        String item;
        String uuid;
        skyblockViewer sb = new skyblockViewer();
        int end = 0;
        ArrayList<String> dupl = new ArrayList<String>();
        while(true){
            a = r.readLine();
            if(a.equals("****")){
                pw.println(a);
                break;
            }
            /*for(int i = a.length() - 2; i >= 0; i--){
                if(a.charAt(i) == '-'){
                    x = i + 1;
                    break;
                }
            }*/
            else{
            if(a.length() == 0){
                continue;
            }
            for(int i = 7; i < a.length(); i++){
                if(a.charAt(i) == ' '){
                    end = i;
                    break;
                }
            }
            hex = a.substring(0, 6);
            hex = hex.toLowerCase();
            item = a.substring(7, end);
            uuid = a.substring(end + 1);
            if(dupl.contains(uuid)){
                
            }
            else{
                dupl.add(uuid);
                if(sb.Snipable(uuid, item, hex)){
                    System.out.println(hex + " " + item +" "+uuid);
                    pw.println(item + " "  + uuid);
                }
                /*try{
                Thread.sleep(1000);
                }
                catch(InterruptedException e){

                }*/
            }
            //else if(a.contains("superior")){
            //    dupl.add(t);
            //    pw.println(a.substring(x, a.length() - 1));
            //}
            }

        }
        r.close();
        pw.close();
    }
    
}
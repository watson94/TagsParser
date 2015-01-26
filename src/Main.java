import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by watson on 24.01.15.
 */
public class Main {
    public Map<String, Object> getTags(String text) {
        Map <String, Object> ans = new HashMap<String, Object>();
        String[] lines = text.split("\\r?\\n");;
        int i = 0;
        System.out.println(lines.length);
        ArrayList<String> hashTags = new ArrayList<String>();
        while ((i < lines.length) && (lines[i].matches("#.+"))) {
            hashTags.add(lines[i]);
            i++;
        }
        ans.put("tags", hashTags.toArray());
        String description = "";
        while ((i < lines.length) && (lines[i].isEmpty())) {
            i++;
        }
        while ( (i < lines.length) && (!lines[i].isEmpty()) ) {
            description += lines[i] + '\n';
            i++;
        }
        ans.put("header", description);
        String article = "";
        while (!lines[i].matches("(http|https):\\/\\/(.)+\\.(.)+")) {
            article += lines[i] + '\n';
            i++;
        }
        ans.put("article", article);
        String url = "";
        while (i < lines.length) {
            url += lines[i] + '\n';
            i++;
        }
        ans.put("url", url);
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String s;
        char[] buf = new char [1024 * 1024];
        int cur = 0;
        int len = 0;
        while ((len = br.read(buf, cur, 1024)) >= 0) {
            cur += len;
        }
        String text = new String(buf);
        System.out.println(new Main().getTags(text));
    }
}

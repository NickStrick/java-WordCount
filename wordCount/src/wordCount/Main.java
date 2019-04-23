package wordCount;

import org.w3c.dom.Text;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static HashMap.Entry<String, Integer> findLargest(HashMap<String, Integer> map) {
        HashMap.Entry<String, Integer> maxEntry = null;
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            if ((maxEntry == null) || maxEntry.getValue() < entry.getValue()) {
                maxEntry = entry;
            }
        }

        return maxEntry;
    }

    public static void main(String[] args) {
        String unText = new TextData().getText();

        // remove puctuation marks;
        //regular expression
        unText = unText.replaceAll("[[\\.\\?\\!\\,\\;\\:\\{\\}\\(\\)\\']]", "");

        String[] words = unText.split(" +");
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for(String aWord:words){
            if(!map.containsKey(aWord)){
                map.put(aWord,1);
            }else{
                map.put(aWord, map.get(aWord) + 1);
            }
        }


        //sort ======
        ArrayList<HashMap.Entry<String, Integer>> sortedMap = new ArrayList<HashMap.Entry<String, Integer>>();
        sortedMap.addAll(map.entrySet());

        Collections.sort(sortedMap, new Comparator<Map.Entry<String, Integer> >(){
            public int compare(HashMap.Entry<String, Integer> o1, HashMap.Entry<String, Integer> o2){
                return o2.getValue() - o1.getValue();
            }
        });

        ArrayList<HashMap.Entry<String, Integer>> alphaSortedMap = new ArrayList<HashMap.Entry<String, Integer>>();
        System.out.println("\nTop 50 words\n");
        for(int i = 0;  i < 50 ;  i++){
            System.out.println("Common Word <" + sortedMap.get(i).getKey() + "> occurs " + sortedMap.get(i).getValue() + " times");
            alphaSortedMap.add(sortedMap.get(i));
        }
    }
}
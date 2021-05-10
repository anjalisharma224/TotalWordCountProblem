import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TotalWordCounts {

   // Give a total word count

    public static HashMap<String, Integer> wordCounts(String file) throws Exception {
        String s;
        FileReader fileReader= new FileReader(file);
        BufferedReader bufferedReader=new BufferedReader(fileReader);

        HashMap<String, Integer> hashMap = new HashMap<>();

        while((s = bufferedReader.readLine())!=null) {
            s = s.replaceAll("[^a-zA-Z0-9]", " ").replaceAll("  "," ").toLowerCase();
            String arr[] = s.toLowerCase().split(" ");
            for(String word: arr)
            {
                if(hashMap.containsKey(word))
                {
                    int count=hashMap.get(word);
                    hashMap.put(word,count+1);
                }
                else
                {
                    hashMap.put(word,1);
                }
            }
        }


        for(Map.Entry<String, Integer> entry:hashMap.entrySet())
        {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        return hashMap;
    }

   // Identify the top 10 words used and display them in sorted order

    public static  void topWordCounts(List<Map.Entry<String, Integer>> collect)
    {
        TreeMap<String , Integer> treeMap = new TreeMap<>();

        List<String> words=new ArrayList<>();

        for(Map.Entry<String,Integer> entry: collect)
        {
            treeMap.put(entry.getKey(), entry.getValue());
            words.add(entry.getKey());
        }

        System.out.println("top 10 used words in alphabetical order: \n "+treeMap.entrySet());

    }


    public static String lastSentence(String word, String file) throws Exception
    {
        String lastsentence="";
        String s="";
        FileReader fileReader= new FileReader(file);
        BufferedReader br=new BufferedReader(fileReader);


        while((s = br.readLine())!=null) {
            s = s.replaceAll("[^a-zA-Z0-9.?]", " ").replaceAll("  "," ").toLowerCase();

            String arr[] = s.split("(?<=[.?])\\s*");

            for(String s1:arr) {
                boolean flag=true;
                if (!s1.contains(word)) {
                        flag = false;
                }
                if(flag)
                    lastsentence=s1;
            }
        }
        return lastsentence;
    }


    public static void main(String[] args) throws Exception {
        String filename = "src/passage.txt";

        HashMap<String, Integer> hashMap = wordCounts(filename);

        List<Map.Entry<String, Integer>> collect = hashMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .limit(10)
                .collect(Collectors.toList());

        System.out.println();

        topWordCounts(collect);

        System.out.println();

        String lastsentence=lastSentence(collect.get(0).getKey(),filename);


        if(lastsentence.length()>1)
            System.out.println("last sentence that contains all top words are: \n"+lastsentence);
        else
            System.out.println("No such sentence found");
    }
}

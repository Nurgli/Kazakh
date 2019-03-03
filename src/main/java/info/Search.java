package info;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Search {
    private List<String> listWord=new ArrayList<>();
    private List<String> listMorph  =new ArrayList<>();
    private List<String>morph=new ArrayList<>();

    public Search() throws IOException {
//        listWord.add("арға");listMorph.add("зе");
//        listWord.add("қарға");listMorph.add("зе");
//        listWord.add("қарға");listMorph.add("ет");
//        listWord.add("қар");listMorph.add("зе");
//        listWord.add("қарға");listMorph.add("се");
//        listWord.add("қызыл");listMorph.add("се");
//        listWord.add("қар");listMorph.add("ет");
        Set<String> set=new TreeSet<>();
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\nurga\\Desktop\\word\\example.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.length()>1)
                set.add(line.replaceAll(" ",""));
            else System.out.println(reader.readLine());
        }
        reader.close();
        for(String str:set){
            String []s=str.split("=");
            if(s.length==2){
                listWord.add(s[0]);
                listMorph.add(s[1]);
            }
        }
    }
    public void clearMorph(){
        morph.clear();
    }

    int search(String s){
    morph.clear();
        int position=-1;
        for(int i=0;i<listWord.size();i++)
            if (listWord.get(i).equals(s)){
                morph.add(listMorph.get(i));
                position=i;}
        return position;
    }

    public List<String> getMorph() {
        return morph;
    }

    public String getWord(int index){
        return listWord.get(index);
    }
}

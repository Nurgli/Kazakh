package info;

import java.util.ArrayList;
import java.util.List;

public class FullInfo {


    private String word;
    private String lem;
    private List<Affix>affix=new ArrayList<Affix>();
    private List<String>morph=new ArrayList<String>();

    public FullInfo() {
    }

    public FullInfo(FullInfo fullInfo) {
        word = fullInfo.word;
        lem=fullInfo.lem;
        affix=fullInfo.getAffix();
       // morph=fullInfo.getMorph();
    }

    public String getWord() {
        return word;
    }
    public void clearMoph(){
    morph.clear();
}
    public void setWord(String word) {
        this.word = word;
    }
    public void setLem(String lem) {
        this.lem = lem;
    }

    public void addAff(Affix i){
        affix.add(i);
    }

    public void setMorph(List<String> morph) {
        this.morph = morph;
    }

    public String getLem() {
        return lem;
    }

    public void clearMorph(){
        this.morph.clear();
}

    public void setAffix(List<Affix> affix) {
        this.affix = affix;
    }

    public List<Affix> getAffix() {
        return affix;
    }

    public List<String> getMorph() {
        return morph;
    }

    @Override
    public String toString() {
        return "FullInfo{" +
                "word='" + word + '\'' +
                ", lem='" + lem + '\'' +
                ", affix=" + affix +
                ", morph=" + morph +
                '}';
    }
}

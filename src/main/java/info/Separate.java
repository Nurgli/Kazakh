package info;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Separate {
    private String[] dep = {"ыңыз", "іміз", "ымыз", "ыңыз", "іңіз", "ңыз", "ңіз", "міз", "мыз", "сын", "ын", "ым", "ім", "ың", "ің", "сі", "сы", "м", "ң", "ы", "і"};
    private Search search;

    private final FullInfo fullInfo= new FullInfo(); ;

    public List<FullInfo> getVersion() {
        return version;
    }

    private List<FullInfo> version = new ArrayList<>();

    public Separate() throws IOException {
    }


    void caseStemming(String s) {
        for (int i = 0; i < 19; i++) {
            subCaseTest(StemmingMatrix.getCase(i), s);
        }
    }

    void subCaseTest(String[] reg, String s) {
        int lenWord = s.length();
        int lenAff = reg[0].length();
        String endWord = s.substring(lenWord - lenAff);
        if (endWord.equals(reg[0]) || endWord.equals(reg[1])) {
            String endLetter = "" + s.charAt(lenWord - lenAff - 1);
            if (reg[2].contains(endLetter)) {
                fullInfo.addAff(new Affix(endWord, reg[3]));
                fullInfo.setLem(s.substring(0, lenWord - lenAff));
            }
        }
    }

    void depenStemming(String s) {
        int lenWord = s.length();
        for (String affix : dep) {
            int lenaff = affix.length();
            if (lenWord > lenaff) {
                String endWord = s.substring(lenWord - lenaff);
                if (endWord.equals(affix)) {
                    fullInfo.addAff(new Affix(endWord, "dep"));
                    fullInfo.setLem(s.substring(0, lenWord - lenaff));
                }
            }
        }
    }

//    void pluralStemming(String s) {
//        for (int i = 0; i < 3; i++) {
//            subPluralTest(StemmingMatrix.getPlural(i), s);
//        }
//    }

//    void subPluralTest(String[] reg, String s) {
//        int len = s.length();
//        if (len >3) {
//            String aff = s.substring(len - 3);
//            if (aff.equals(reg[0]) || aff.equals(reg[1])) {
//                char c = s.charAt(len - 4);
//                if (reg[2].contains("" + c)) {
//                    fullInfo.addAff(new Affix(aff, "plural"));
//                    fullInfo.setLem(s.substring(0, len - 3));
//                }
//            }
//        }
//    }

    void cutter(String [][]regMatrix, String s,String type){
        int len=s.length();
        for(String []af:regMatrix){
            if(af[0].length()<len){
                String aff=s.substring(len-af[0].length());
                if(aff.equals(af[0])||aff.equals(af[1])){
                    char c=s.charAt(len-af[0].length()-1);
                    if(af[2].contains(""+c)){
                        fullInfo.addAff(new Affix(aff,type));
                        fullInfo.setLem(s.substring(0,len-aff.length()));
                    }
                }
            }
        }
    }

//    void persStemming(String s) {
//        int lenWord = s.length();
//        for (int i = 0; i < 6; i++) {
//            if (lenWord > 3) {
//                String endWord = s.substring(lenWord - 3);
//                String[] reg = StemmingMatrix.getPers(i);
//                if (endWord.equals(reg[0]) || endWord.equals(reg[1])) {
//                    String endLetter = s.charAt(lenWord - 4) + "";
//                    if (reg[2].contains(endLetter)) {
//                        fullInfo.addAff(new Affix(endWord, reg[3]));
//                        fullInfo.setLem(s.substring(0, lenWord - 3));
//                    }
//                }
//            }
//        }
//    }

    public void getAnalysis(String word) throws IOException {

        fullInfo.setWord(word);
        search = new Search();
        fullInfo.setLem(word);

        FullInfo finfo = new FullInfo();
        finfo.setWord(word);

        int index = search.search(word);

        if (index != -1){
            finfo.setLem(word);
            finfo.setMorph(search.getMorph());
            addVersion(finfo);
        }

        cutter(StemmingMatrix.regEsimshe,fullInfo.getLem(),"Esimsh");
        addVersion();
        cutter(StemmingMatrix.regKosem,fullInfo.getLem(),"Kosemsh");
        addVersion();

        //persStemming(fullInfo.getLem());
        cutter(StemmingMatrix.regularPers,fullInfo.getLem(),"Pers");
        addVersion();

         caseStemming(fullInfo.getLem());
        addVersion();

        depenStemming(fullInfo.getLem());
        addVersion();

        //pluralStemming(fullInfo.getLem());
        cutter(StemmingMatrix.regularPlural,fullInfo.getLem(),"Plural");
        addVersion();
        System.out.println(fullInfo);
    }
    void addVersion(FullInfo info ) {
        if (!isVersionContain(info.getLem())) {
            if (search.search(info.getLem()) != -1){
                version.add(info);
            }
        }
    }

    void addVersion() throws IOException {
        Search search=new Search();
        FullInfo info=new FullInfo(fullInfo);
        if (!isVersionContain(info.getLem())) {
            if (search.search(info.getLem()) != -1){
                info.setMorph(search.getMorph());
                version.add(info);
            }
        }
    }



    boolean isVersionContain(String word) {
        if (!version.isEmpty())
            return version.get(version.size() - 1).getLem().equals(word);
        return false;
    }
}

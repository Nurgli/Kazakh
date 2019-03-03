import info.FullInfo;
import info.Separate;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Separate separate=new Separate();
        separate.getAnalysis("қарға");
        for (FullInfo fullInfo : separate.getVersion()) {
            System.out.println(fullInfo);
        }
    }
}

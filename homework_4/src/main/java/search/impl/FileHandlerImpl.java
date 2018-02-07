package search.impl;

import search.FileHandler;
import vo.Program;

import java.io.*;
import java.util.List;

public class FileHandlerImpl implements FileHandler {

    @Override
    public int program2File(List<Program> programList) {
        File f=new File("programs.txt");
        int n=0;
        try{
            FileWriter fw=new FileWriter(f.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for(Program p:programList){
                bw.write(p.getUniversity()+"\t"+p.getCountry()+"\t"+p.getProgramName()+"\t"+
                        p.getSchool()+"\t"+p.getDegree()+"\t"+p.getEmail()+"\t"+p.getPhoneNumber()+"\t"+p.getLocation()+"\t"
                        +p.getDeadlineWithAid()+"\t"+p.getDeadlineWithoutAid()+"\t"+p.getHomepage()+"\n");
                n++;
            }
            bw.close();fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return n;
    }
}

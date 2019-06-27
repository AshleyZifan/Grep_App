package ca.jrvs.apps.grep;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class JavaGrepImp implements JavaGrep{

    private String rootPath;
    private String regex;
    private String outFile;

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: regex rootPath outFile");
        }
        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);
        try {
            javaGrepImp.process();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void process() throws IOException {
        List<String> matchedLines = new ArrayList<String>();
        for (File file : listFiles(this.rootPath)){
           for (String line : readLines(file)){
               if (containsPattern(line)){
                   matchedLines.add(line);
                   writeToFile(matchedLines);
               }
           }
        }
    }

    @Override
    public List<File> listFiles(String rootDir) {
        List<File> result = new ArrayList<File>();
        try {
            result = Files.walk(Paths.get(rootPath)).filter(Files::isRegularFile).map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> result = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean containsPattern(String line) {
        Pattern p = Pattern.compile(this.regex);   // the pattern to search for
        Matcher m = p.matcher(line);
        // now try to find at least one match
        if (m.find())
            return true;
        else
            return false;
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.outFile));
        for (String str : lines) {
            writer.write(str + " ");
        }
        writer.close();
    }

    @Override
    public String getRootPath() {
        return this.rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getRegex() {
        return this.regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getOutFile() {
        return this.outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }
}

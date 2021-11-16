import com.sun.jdi.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.Key;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class tempestDemo {

    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/tempest.txt"));

            String input;
            StringBuilder sb = new StringBuilder();
            while ((input = br.readLine()) != null) {
                sb.append(" ");
                sb.append(input);
            }
            String singleString = sb.toString();
            Stream<String> inputStream = Stream.of(singleString.split(" "));
            Map<String, Long> wordMap = inputStream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            wordMap.entrySet().stream()
                    .sorted((v1,v2)->(v1.getValue()<v2.getValue()?1:(v1.getValue()>v2.getValue()?-1:0))).limit(10)
                    .forEach(entrySet -> System.out.println(entrySet.getKey() + "(" + entrySet.getValue() +")"));

            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Greeting {

    public static String greet(Object o) {
        if (o == null) {
            return "Hello, my friend";
        } else if (o instanceof String) {
            String name = (String) o;
            if (name.equals(name.toUpperCase())) {
                return "HELLO, " + name;
            } else {
                return "Hello, " + name;
            }
        } else if (o instanceof String[]) {
            String[] names = (String[]) o;

//            String allNamesExceptLast = Arrays.stream(names, 0, names.length - 1)
//                    .collect(Collectors.joining(", "));
//            String lastName = names[names.length - 1];
//            return "Hello, " + allNamesExceptLast + ", and " + lastName;


            List<String> allNames = Arrays.stream(names)
                    .flatMap(name -> Stream.of(name.split(", ")))
                    .toList();

            List<String> saidNames= allNames.stream()
                    .filter(name-> !name.equals(name.toUpperCase()))
                    .collect(Collectors.toList());

            List<String> shoutedNames= allNames.stream()
                    .filter(name-> name.equals(name.toUpperCase()))
                    .collect(Collectors.toList());


            String said= "";
            if (!saidNames.isEmpty()){
                String saidNamesExceptLast = saidNames.size()> 1? String
                        .join(", ", saidNames.subList(0, saidNames.size() - 1)) : "";
                String saidLastName = saidNames.get(saidNames.size() - 1);
                said= "Hello " + (saidNames.size()>1 ? saidNamesExceptLast + ", and ": "") + saidLastName+ ".";
            }

            String shouted = "";
            if (!shoutedNames.isEmpty()){
                String shoutedNamesExceptLast = shoutedNames.size()> 1? String
                        .join(", ", shoutedNames.subList(0, shoutedNames.size() - 1)) : "";
                String shoutedLastName = shoutedNames.get(shoutedNames.size()-1).toUpperCase();
                 shouted= ("and hello " +
                        (shoutedNames.size()>1 ? shoutedNamesExceptLast + ", and " :"")+
                        shoutedLastName).toUpperCase();
            }


            return said +" "+ shouted;

        } else {
            return "Hello, ";
        }
    }
}

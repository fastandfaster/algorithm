package algorithm_2019_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindDupInFileSys {

    public static void main(String[] args) {
//        String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};
        String[] paths = {"root/a 1.txt(abcd) 2.txt(efsfgh)","root/c 3.txt(abdfcd)","root/c/d 4.txt(efggdfh)"};
        System.out.println(findDuplicate(paths));
    }

    public static List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> result = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String path : paths
        ) {
            String[] parts = path.split("\\s");
            String dir = parts[0];
            for (int i = 1; i < parts.length; i++) {
                String fileContent = parts[i];
                //Get the file content
                String content = fileContent.substring(fileContent.indexOf("(") + 1, fileContent.indexOf(")"));
//                System.out.println(content);
                String fileName = fileContent.substring(0, fileContent.indexOf("("));
                List<String> list = new ArrayList<>();
                if (map.containsKey(content)) {
                    list = map.get(content);
                    //Get the dir and file name together
                    list.add(dir + "/" + fileName);
                } else {
                    list = new ArrayList<>();
                    list.add(dir + "/" + fileName);
                    map.put(content, list);
                }
            }
        }
//        System.out.println(map);
        for (String content : map.keySet()
        ) {
            List<String> vals = map.get(content);
            if (vals.size() > 1)
                result.add(map.get(content));
        }
        return result;
    }
}

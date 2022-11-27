import java.util.HashMap;

public class Test {

    public static void main(String[] args) {

        HashMap<Departement, Integer> map = new HashMap<>();
        map.put(new Departement("LA","44"),3);

        Departement dep1 = new Departement("LA","44");
        Departement dep2 = new Departement("PA","64");

        map.merge(dep2, 5, (a,b) -> a+b);
        System.out.println(map);



//        System.out.println(map.containsKey(test) ? "ca marche " : " ca marche vraiment pas");
//
//        System.out.println(map.get(test));
//        map.merge(test, 6, (a,b) -> a+b);
//        System.out.println(map.get(test));
//        String test = "DEP-16";
//        System.out.println(test.substring(4));
//        System.out.println(Integer.valueOf("0"));
    }
}

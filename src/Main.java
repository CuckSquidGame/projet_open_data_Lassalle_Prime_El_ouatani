import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Main {

    final static String COVID_SEPARATOR = ",";
    final static String SOCIAL_SEPARATOR = ";";
    final static String FILE_COVID = "covid.csv";
    final static String FILE_SOCIAL = "social.csv";

    public static void main(String[] args) {

        HashMap<Departement, HashMap<String, Integer>> map = new HashMap<>();

        FileWriter file_Result;

        try {

            file_Result = new FileWriter("Covidv2.csv");

            writeCSV(file_Result, "Departement","code", "Décès", "Hospitalisations", "Guérisons");

            FileReader frPop = new FileReader(FILE_COVID);
            BufferedReader brPop = new BufferedReader(frPop);

            for (String line = brPop.readLine(); line != null; line = brPop.readLine()) {
                line.split(COVID_SEPARATOR);
                String[] data = line.split(COVID_SEPARATOR);

                Departement dep = new Departement(data[3], data[2].substring(4));
                map.putIfAbsent(dep,new HashMap<String, Integer>());
                addData(map, dep, data);

            }
            brPop.close();
            frPop.close();

            map.forEach((departement, données) -> {
                writeCSV(file_Result, departement.nom, departement.code, String.valueOf(données.get("Décès")), String.valueOf(données.get("Hospitalisations")),String.valueOf(données.get("Guérisons")));
            });

            file_Result.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // ajoute les données ou les additionne
    private static void addData(HashMap<Departement, HashMap<String, Integer>> map, Departement dep, String[] data) {
        map.get(dep).merge("Décès", getValue(data,8) , (a,b) -> a+b);
        map.get(dep).merge("Hospitalisations", getValue(data, 11), (a,b) -> a+b);
        map.get(dep).merge("Guérisons", getValue(data,14) , (a,b) -> a+b);
    }

    private static Integer getValue(String[] data, int i) {
        return "".equals(data[i]) ? 0 : Integer.valueOf(data[i]);
    }


    // écrit les libelles des colonnes
    private static void writeCSV(FileWriter file_Result, String departement, String code, String décès, String hospitalisations, String guérisons) {
        try {
            file_Result.append(departement).append(COVID_SEPARATOR).append(code).append(COVID_SEPARATOR).append(décès).append(COVID_SEPARATOR).append(hospitalisations).append(COVID_SEPARATOR).append(guérisons).append("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

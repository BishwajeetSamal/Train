import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Geektrust {
    public static void main(String []args) throws FileNotFoundException,NoSuchElementException,CloneNotSupportedException{
        try {
            HashMap<String,Integer> train1 = new HashMap<>();
            train1.put("SLM",350);
            train1.put("CHN",0);
            train1.put("BLR",550);
            train1.put("KRN",900);
            train1.put("HYB",1200);
            train1.put("NGP",1600);
            train1.put("ITJ",1900);
            train1.put("BPL",2000);
            train1.put("AGA",2500);
            train1.put("NDL",2700);

            HashMap<String,Integer> train2 = new HashMap<>();
            train2.put("TVC",0);
            train2.put("SRR",300);
            train2.put("MAQ",600);
            train2.put("MAO",1000);
            train2.put("PNE",1400);
            train2.put("HYB",2000);
            train2.put("NGP",2400);
            train2.put("ITJ",2700);
            train2.put("BPL",2800);
            train2.put("PTA",3800);
            train2.put("NJP",4200);
            train2.put("GHY",4700);

            File file = new File(args[0]); 
        Scanner sc = new Scanner(file);
        //fetching the first Line
        String train1_input = sc.nextLine();
        //fetching the next line
        String train2_input = sc.nextLine();
        //closing the scanner object
        sc.close();
        String[] newTrain_1_stringArr = train1_input.split("\\s+");
        ArrayList<String> train1_stationList = new ArrayList<>(Arrays.asList(newTrain_1_stringArr)); 

        for (String newTrain1_each_station : newTrain_1_stringArr) {
            if(train1.containsKey(newTrain1_each_station) && train1.get(newTrain1_each_station)<train1.get("HYB")){
                train1_stationList.remove(newTrain1_each_station);
            } 
        }

        String[] newTrain_2_stringArr = train2_input.split("\\s+");
        ArrayList<String> train2_stationList = new ArrayList<>(Arrays.asList(newTrain_2_stringArr));  
        for (String newTrain2_each_station : newTrain_2_stringArr) {
            if(train2.containsKey(newTrain2_each_station) && train2.get(newTrain2_each_station)<train2.get("HYB")){
                train2_stationList.remove(newTrain2_each_station);
            } 
        }

        //Combine train1 and train2 in HYB before Departure
        HashMap<String,Integer> trainAB = new HashMap<>();
        ArrayList<Integer> getTrainDistList = new ArrayList<>();
        for(String station : train1_stationList){
            if(train1.containsKey(station)){
                Integer val = train1.get(station)-train1.get("HYB");
                trainAB.put(station,val);
                getTrainDistList.add(val);
            }else if(train2.containsKey(station)) {
                Integer val = train2.get(station)-train2.get("HYB");
                trainAB.put(station,val);
                getTrainDistList.add(val);
            }
        }

        for(String station : train2_stationList){
            if(train2.containsKey(station)){
                Integer val = train2.get(station)-train2.get("HYB");
                trainAB.put(station,val);
                getTrainDistList.add(val);
            }else if(train1.containsKey(station)) {
                Integer val = train1.get(station)-train1.get("HYB");
                trainAB.put(station,val);
                getTrainDistList.add(val);
            }
        }

        ArrayList<String> finalTrainABList = new ArrayList<>();
        String stationName="";
        Collections.sort(getTrainDistList, Collections.reverseOrder());
        for(Integer getDistance :getTrainDistList){
            if(getTrainDistList.isEmpty()){
                System.out.println("JOURNEY_ENDED");
                break;
            }
            for(Map.Entry<String, Integer> entry: trainAB.entrySet()) {
                if(entry.getValue().equals(getDistance)) {
                stationName = entry.getKey();
                }
              }
              finalTrainABList.add(stationName);
        }
        finalTrainABList.add(0,"DEPARTURE");
        finalTrainABList.add(1,"TRAIN_AB");
        finalTrainABList.add(2,"ENGINE");
        finalTrainABList.add(3,"ENGINE");
        finalTrainABList.remove("HYB");
        train1_stationList.add(0,"ARRIVAL");
        train2_stationList.add(0,"ARRIVAL");
        String finalTrain_A_list = String.join(" ", train1_stationList);
        String finalTrain_B_list = String.join(" ", train2_stationList);
        String finalTrain_AB_list = String.join(" ", finalTrainABList);
        System.out.println(finalTrain_A_list);
        System.out.println(finalTrain_B_list);
        System.out.println(finalTrain_AB_list);
        } catch (NoSuchElementException e) {
            System.out.println("No lines found below");
        }catch(Exception e){

            e.printStackTrace();
        }
    }
}

package smp;

import java.util.*;

public class Marrige {
    public static void main(String[] args) {

        int[][] priorities = {
                {1,0,2,3,4},
                {4,1,2,3,0},
                {2,3,1,0,4},
                {1,4,3,2,0},
                {2,0,4,1,3}
        };

        int[][] estimations = {
                {12,8,3,5,10},//{3,4,2,5,1}
                {11,13,7,9,10},
                {14,10,9,5,8},
                {13,14,10,8,11},
                {11,13,14,9,15}
        };

        Set<Integer> availableMen = new HashSet<Integer>();
        for (int i=0; i<priorities.length; i++) {
            availableMen.add(i);
        }

        Map<Integer, Integer> availableWomen = new HashMap<Integer, Integer>();
        for (int i=0; i<estimations.length; i++) {
            availableWomen.put(i, null);
        }

        int size = availableMen.size();
        while (size > 0) {
            int currentBachelor = availableMen.iterator().next();
            System.out.println ("\nC " + currentBachelor + " :");
            for (int w : priorities[currentBachelor]){

                Integer fiance = availableWomen.get(w);

                if (fiance == null){

                    availableWomen.put(w, currentBachelor);
                    availableMen.remove(currentBachelor);
                    System.out.println ("C-" + currentBachelor + " contract with T-" + w);
                    break;
                }
                else{

                    int prefForFiance = -1;
                    int prefForCurrentBachelor = -1;
                    for (int k=0; k<estimations[w].length; k++){
                        if (k == fiance) {
                            prefForFiance = estimations[w][k];
                        }

                        if (k == currentBachelor) {
                            prefForCurrentBachelor = estimations[w][k];
                        }
                    }

                    if (prefForCurrentBachelor < prefForFiance){

                        availableWomen.put (w, currentBachelor);
                        availableMen.remove(currentBachelor);
                        availableMen.add(fiance);
                        System.out.println ("C-" + fiance + " is dumped by T-" + w);
                        System.out.println ("C-" + currentBachelor + " takes T-" + w);
                        break;
                    }
                }
            }
            size = availableMen.size();
        }

        int totalEfficiency=0;
        System.out.println();
        Iterator<Map.Entry<Integer, Integer>> itr = availableWomen.entrySet().iterator();
        while (itr.hasNext()){
            Map.Entry<Integer, Integer> entry = itr.next();
            System.out.println ("C-" + entry.getValue() + " does T-" + entry.getKey());
            totalEfficiency+=estimations[entry.getKey()][entry.getValue()];
        }
        System.out.println("Total efficiency: " + totalEfficiency);


        Set<Integer> availableTasks = new HashSet <Integer> ();
        for (int i=0; i<estimations.length; i++) {
            availableTasks.add(i);
        }

        Map<Integer, Integer> availableCoders = new HashMap <Integer, Integer> ();
        for (int i=0; i<priorities.length; i++) {
            availableCoders.put(i, null);
        }

        size = availableTasks.size();
        while (size > 0) {
            int currentTask = availableTasks.iterator().next();

            SortedMap<Integer, Integer> sortedMap = new TreeMap<Integer, Integer>();

            for (int i = 0; i<estimations[currentTask].length; i++) {
                sortedMap.put(estimations[currentTask][i],i);
            }
            System.out.println ("\nT " + currentTask + " :");
            for(Map.Entry<Integer,Integer> entry : sortedMap.entrySet()) {
                Integer fiance = availableCoders.get(entry.getValue());
                if (fiance == null){

                    availableCoders.put(entry.getValue(), currentTask);
                    availableTasks.remove(currentTask);
                    System.out.println ("T-" + currentTask + " contract with C-" + entry.getValue());
                    break;
                }
                else{

                    int prefForFiance = -1;
                    int prefForCurrentBachelor = -1;
                    for (int k=0; k<priorities[entry.getValue()].length; k++){
                        if (priorities[entry.getValue()][k] == fiance) {
                            prefForFiance = k;
                        }

                        if (priorities[entry.getValue()][k] == currentTask) {
                            prefForCurrentBachelor = k;
                        }
                    }

                    if (prefForCurrentBachelor < prefForFiance){

                        availableCoders.put (entry.getValue(), currentTask);
                        availableTasks.remove(currentTask);
                        availableTasks.add(fiance);
                        System.out.println ("T-" + fiance + " is dumped by C-" + entry.getValue());
                        System.out.println ("T-" + currentTask + " contract with C-" + entry.getValue());
                        break;
                    }
                }
            }
            size = availableTasks.size();
        }

        totalEfficiency=0;
        System.out.println();
        itr = availableCoders.entrySet().iterator();
        while (itr.hasNext()){
            Map.Entry<Integer, Integer> entry = itr.next();
            System.out.println ("T-" + entry.getValue() + " Taken by C-" + entry.getKey());
            totalEfficiency+=estimations[entry.getValue()][entry.getKey()];
        }
        System.out.println("Total efficiency: " + totalEfficiency);

    }

}

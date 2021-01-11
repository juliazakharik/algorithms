import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Fit {

    public static LinkedList<Double> weights = new LinkedList<>();
    public static int NUM_BAGS = 1000;
    public static double[] bags = new double[NUM_BAGS];
    public static ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>();
    public static int[][] res;
    public static int nextFit(){
        double[] lefts = bags.clone();
        LinkedList<Double> weights1 = new LinkedList<>(weights);
        int res = 0;
        for(int i = 0;!weights1.isEmpty();i++){
            if(lefts[i]>weights1.getFirst()){
                lefts[i]=BigDecimal.valueOf(lefts[i]-weights1.getFirst()).setScale(2, RoundingMode.UP).doubleValue();
                result.get(i).add(weights1.getFirst());
                weights1.remove(weights1.getFirst());
                i--;
            }
            res=i;
        }
        return res;
    }
    public static int firstFit(){
        double[] lefts = bags.clone();
        LinkedList<Double> weights1 = new LinkedList<>(weights);
        int res = 0;
        for(int i = 0;!weights1.isEmpty();i++){
            for(int j = 0;!weights1.isEmpty();j++){
                if(lefts[j]>=weights1.getFirst()){
                    lefts[j]=BigDecimal.valueOf(lefts[j]-weights1.getFirst()).setScale(2, RoundingMode.UP).doubleValue();
                    result.get(j).add(weights1.getFirst());
                    weights1.remove(weights1.getFirst());
                    res = Math.max(res,j);
                    j=-1;
                }
            }
        }
        return res;
    }
    public static int firstFitSort(){
        LinkedList<Double> weights1 = new LinkedList<>(weights);
        Collections.sort(weights1);
        Collections.reverse(weights1);
        double[] lefts = bags.clone();
        int res = 0;
        for(int i = 0;!weights1.isEmpty();i++){
            for(int j = 0;!weights1.isEmpty();j++){
                if(lefts[j]>=weights1.getFirst()){
                    lefts[j]=BigDecimal.valueOf(lefts[j]-weights1.getFirst()).setScale(2, RoundingMode.UP).doubleValue();
                    result.get(j).add(weights1.getFirst());
                    weights1.remove(weights1.getFirst());
                    res = Math.max(res,j);
                    j=-1;
                }
            }
        }
        return res;
    }
    public static int minLeft(double[]lefts, double left){
        double min = Double.POSITIVE_INFINITY;

        int minInd = lefts.length;
        for(int i = 0;i<lefts.length;i++){
            if(lefts[i]<min&&left<=lefts[i]){
                min = lefts[i];
                minInd = i;
            }
        }
        return minInd;
    }
    public static int bestFit(){
        double[] lefts = bags.clone();
        LinkedList<Double> weights1 = new LinkedList<>(weights);
        int res = 0;
        while(true){
            int minInd = minLeft(lefts, weights1.getFirst());
                if(!weights1.isEmpty()&&lefts[minInd]>=weights1.getFirst()){
                    lefts[minInd]=BigDecimal.valueOf(lefts[minInd]-weights1.getFirst()).setScale(2, RoundingMode.UP).doubleValue();
                    result.get(minInd).add(weights1.getFirst());
                    weights1.remove(weights1.getFirst());
                }
                res = Math.max(minInd, res);
                if(weights1.isEmpty()){
                    break;
                }
            }
        return res;

    }
    public static void printresult(){
        for(int i = 0;i<bags.length;i++){
            System.out.println(result.get(i).toString());
        }
    }

    public static void main(String[]args){
        for(int i  = 0;i<NUM_BAGS;i++){
            bags[i] = 1;
        }

        for(int i = 0;i<NUM_BAGS;i++){
            weights.add(Math.random());
        }
        for(int i = 0;i<NUM_BAGS;i++){
            result.add(new ArrayList<Double>());
        }
        System.out.println("FIRST FIT: "+firstFit());
        System.out.println("FIRST FIT SORT: "+firstFitSort());
        System.out.println("NEXT FIT: "+nextFit());
        System.out.println("BEST FIT: "+bestFit());
    }
}

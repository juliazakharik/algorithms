import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Gene {
    public static int[]eq;
    public static int res;
    public static Random rand = new Random();
    public static final double MUTATION_PERC = 0.03;
    public static final int RES_LENGTH = 5;
    public static final int FIRST_POPULATION = 100;
    public static final int LEAST_SUITABLE_CHILD = 80;
    public static final int MOST_SUITABLE_CHILD = 80;
    public static final int SELECTION = 85;
    public static final int CROSSBREEDING = 140;
    public static int min = Integer.MAX_VALUE;

    //отбор
    public static int[][] selection(int[][]generation){
        ArrayList<int[]> newGeneration = new ArrayList<>();
        for(int i = 0;i<SELECTION;i++){
            int sel = (int)(Math.random()*100);
            if(sel<generation.length){
                newGeneration.add(generation[sel]);
            }
        }
        int[][] newGen = new int[newGeneration.size()][generation[0].length];
        for (int i = 0;i<newGeneration.size();i++){
            newGen[i] = newGeneration.get(i);
        }
        return newGen;
    }
    public static int[][] mutation(int[][]generation){
        int[]leastSuitable = leastSuitableChildren(generation);
        for(int i = 0;i<leastSuitable.length;i++) {
            for (int j = 0; j < generation[0].length; j++) {
                if (Math.random() < MUTATION_PERC) {
                    generation[leastSuitable[i]][j] += rand.nextInt(100) - 50;
                }
            }
        }
        return generation;
    }

    public static int[][] firstPopulation(){
        int[][] firstPopulation = new int[FIRST_POPULATION][RES_LENGTH];
        for(int i = 0;i<FIRST_POPULATION;i++){
            for(int j = 0;j<RES_LENGTH;j++){
                firstPopulation[i][j] = rand.nextInt(200)-100;
            }
        }
        return firstPopulation;
    }
    public static int solveEq(int[]gene){
        long resEq = 0;
        long resMult = 1;
        for(int i = 0;i<RES_LENGTH*5;i+=5){
            resMult = 1;
            for(int j = 0;j<5;j++){
                resMult*=Math.pow(gene[j], eq[i+j]);
            }
            resEq+=resMult;
        }
        return (int)resEq;
    }
    public static int isSolved(int[][]generation){
        for(int i = 0;i<generation.length;i++){
            if(solveEq(generation[i])==res){
                return i;
            }
        }
        return -1;
    }
    public static int[] leastSuitableChildren(int[][]generation){
        ArrayList<Integer> difference = new ArrayList<>();
        ArrayList<Integer> originalDiff = difference;
        int[]leastSuitableChildren = new int[LEAST_SUITABLE_CHILD];
        for(int i = 0;i<generation.length;i++){
            difference.add(Math.abs(solveEq(generation[i]) - res));
        }
        originalDiff = difference;
        difference.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        for(int i = 0;i<LEAST_SUITABLE_CHILD;i++){
            for(int j = 0;j<originalDiff.size();j++){
                if(difference.get(i)==originalDiff.get(j)){
                    leastSuitableChildren[i] = j;
                }
            }
        }
        return leastSuitableChildren;
    }
    public static int[] mostSuitableChildren(int[][]generation){
        ArrayList<Integer> difference = new ArrayList<>();
        ArrayList<Integer> originalDiff = difference;
        int[]mostSuitableChildren = new int[MOST_SUITABLE_CHILD];
        for(int i = 0;i<generation.length;i++){
            difference.add(Math.abs(solveEq(generation[i]) - res));
        }

        originalDiff = difference;
        difference.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        if(difference.get(0)<min){
            min = difference.get(0);

            System.out.println("BEST: "+min);
        }
        for(int i = 0;i<MOST_SUITABLE_CHILD;i++){
            for(int j = 0;j<originalDiff.size();j++){
                if(difference.get(i)==originalDiff.get(j)){
                    mostSuitableChildren[i] = j;
                }
            }
        }
        return mostSuitableChildren;
    }
    //скрещивание
    public static int[][]crossbreeding(int[][]generation){
        int[][] newGeneration = new int[CROSSBREEDING][generation[0].length];
        for (int i = 0;i<CROSSBREEDING;i++){
            int parent1 = rand.nextInt(generation.length);
            int parent2 = rand.nextInt(generation.length);
            int[] child = new int[generation[parent1].length];
            for(int j = 0;j<generation[parent1].length;j++){
                if(Math.random()<0.5){
                    child[j] = generation[parent1][j];
                }else{
                    child[j] = generation[parent2][j];
                }

            }
            newGeneration[i] = child;
        }
        return newGeneration;
    }

    //замещениеё
    public static int[][] substitution(int[][]originalPopulation, int[][]newGeneration){
        int[]leastSuitableChildren = leastSuitableChildren(originalPopulation);
        int[]mostSuitableChildren = mostSuitableChildren(newGeneration);
        for(int i = 0;i<LEAST_SUITABLE_CHILD;i++){
            originalPopulation[leastSuitableChildren[i]]=newGeneration[mostSuitableChildren[i]];
        }
        return originalPopulation;
    }

    public static int[] genetic(){
        int[][]firstPopulation = firstPopulation();
        int[][]generationInProcess = firstPopulation;
        int[][] originalPopulation = generationInProcess;
        int i = 0;
        while(true){
            int[][] selectionPopulation = selection(generationInProcess);
            int isSolved = isSolved(selectionPopulation);
            if(isSolved!=-1){
                System.out.println("Generation "+i);
                return selectionPopulation[isSolved];
            }
            int[][]crossBreedPopulation = crossbreeding(selectionPopulation);
            isSolved = isSolved(crossBreedPopulation);
            if(isSolved!=-1){
                System.out.println("Generation "+i);
                return crossBreedPopulation[isSolved];
            }
            int[][]mutatedPopulation = mutation(crossBreedPopulation);
            isSolved = isSolved(mutatedPopulation);
            if(isSolved!=-1){
                System.out.println("Generation "+i);
                return mutatedPopulation[isSolved];
            }
            int[][]finalPopulation = substitution(generationInProcess, mutatedPopulation);
            isSolved = isSolved(finalPopulation);
            if(isSolved!=-1){
                System.out.println("Generation "+i);
                return finalPopulation[isSolved];
            }
            generationInProcess = finalPopulation;
            i++;

        }
    }
    public static void main(String[]args){
        eq = new int[]{1,1,1,1,2,0,0,0,0,1,0,0,2,2,0,0,1,0,2,0,1,0,0,2,2};
        res = -50;
        System.out.println("-------------FIRST EQ------------");
        long t11 = System.currentTimeMillis();
        System.out.println(Arrays.toString(genetic()));
        long t12 = System.currentTimeMillis();
        min = Integer.MAX_VALUE;
        eq = new int[]{1,0,0,2,1,1,0,0,0,1,2,2,0,1,0,1,1,1,2,2,0,0,1,0,0};
        res = 13;
        System.out.println("-------------SECOND EQ------------");
        long t21 = System.currentTimeMillis();
        System.out.println(Arrays.toString(genetic()));
        long t22 = System.currentTimeMillis();
        min = Integer.MAX_VALUE;
        eq = new int[]{2,1,0,1,0,1,2,0,1,0,2,2,0,2,1,1,2,0,1,1,0,0,0,0,1};
        res = 13;
        System.out.println("-------------THIRD EQ------------");
        long t31 = System.currentTimeMillis();
        System.out.println(Arrays.toString(genetic()));
        long t32 = System.currentTimeMillis();

        System.out.println("MED TIME: "+((t12-t11)+(t22-t21 )+(t32-t31))/3);
//        eq = new int[]{1,1,1,1,2};

//        int[] r = new int[]{25, -45, 56, -55, -55, 59, 85, 37, -59, 79, 29, 65, -25, 0, 14, 11, -2, 90, -8, -54, -68, 47, 3, -89, -77};
//        solveEq(r);


    }
}

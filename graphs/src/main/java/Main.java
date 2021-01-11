//import action.DFS;
//import action.Search;
//import entity.Graph;
//
//import java.util.Random;
//
//public class Main {
//
//    public static void main (String[] args){
//        System.out.println("Hello");
//        int a1 = 548709284;//целочисл
//        long a2 = 328943293;
//        double a3 = 2.412323;
//        int a4;
//        a4 = 12;
//
//        int a5 = a4 +3;
//        int a6;
//        a6 = a5-a1;
//        int a7 = a5/a4;//15/12=1
//        int a8 = a5%a4;//15%12=3
//        int a9 = a5*a4;
//
//        a4 = a4 - 2;
//        a5 = a5 + 10;
//        a5+=10;
//        a5++;
//        a5--;
//
//        int b = -10;
//
//        if(b>2){
//            System.out.println("b>2");
//        }
//        else{
//            b++;
//        }
//        //Задача. В переменной min лежит число от 0 до 59. Определите в какую четверть часа попадает это число (в первую, вторую, третью или четвертую).
//        int min = (int)(Math.random()*59);
//        System.out.println(min);
//        if(min>=0&&min<15){
//            System.out.println("1 ch");
//        }
//        if(min>=15&&min<30){
//            System.out.println("2 ch");
//        }
//        if(min>=30&&min<45){
//            System.out.println("3 ch");
//        }
//        if(min>=45&&min<60){
//            System.out.println("4ch");
//        }
//
//        //Дана строка, состоящая из символов, например, 'abcde'. Проверьте, что первым символом этой строки является буква 'a'. Если это так - выведите 'да', в противном случае выведите 'нет'.
//        String text = "abcde";
//        char ch = 'q';
//        char chText = text.charAt(0);
//        if(chText=='a')
//        {
//            System.out.println("yes");
//            System.out.println("sas");
//            a5++;
//        }
//        else {
//            System.out.println("no");
//        }
//
//        //Дана строка из 3-х цифр. Найдите сумму этих цифр. То есть сложите как числа первый символ строки, второй и третий.
//        String t = "534";
//        char num1 = t.charAt(0);
//        char num2 = t.charAt(1);
//        char num3 = t.charAt(2);
//
//        int nums = (int)num1+(int)num2+(int)num3;
//        String t1 = "qwe";
//        String t2 = t+t1;//543qwe так делать низя, почему выясним позже
//
//        for(int i = 0; i<5;i++){
//            System.out.println(i);
//        }
//
//        int fact = factorial(4);
//        System.out.println(fact);
//        int sum = plus(5,3);//int sum = 8;
//        func(1, 6);
//        System.out.println(pow(2,3));
//
//        System.out.println(perimetr(3,4,6));
//
//        //использование break
//        while(true){
//            Random rand = new Random();
//            int r = rand.nextInt(20);
//            if(r==15){
//                System.out.println("r=15!!!!");
//                break;
//            }
//        }
//    }
//    //
//
//
//    //написать функцию степени
//    static int pow(int base, int pow){
//        int res = 1;
//        for(int i = 0;i<pow;i++){
//            res *= base;
//        }
//        return res;
//    }
//
//    static int powWhile(int base, int pow){
//        int res = 1;
//        int i =0;
//        while(i<pow){
//            res*=base;
//            i++;
//        }
//        return res;
//    }
//    //переопределение функции
//    //Процедура нахождения по заданным  сторонам треугольника, найти периметр
//    static int perimetr(int a, int b, int c){
//        return a+b+c;
//    }//треуг
////    static double perimetr(int a, int b, int c){ //так делать НИЗЯ!!!!!!!
////        return a+b+c;
////    }
//    static int perimetr(int a, int b, int c, int d){
//        return a+b+c+d;
//    }//4xугольник
//    static int perimetr(int a, int b, int c, int d, int e){
//        return a+b+c+d+e;
//    }//5тиугольник
//    // x y
//    //0 1
//    //3 5
//
//    //Вычислить значения нижеприведенной функции в диапазоне значений x от -10 до 10 включительно с шагом, равным 1.
//    //y = x^2 при -5 <= x <= 5;
//    //y = 2*|x|-1 при x < -5;
//    //y = 2x при x > 5.
//    //Вычисление значения функции оформить в виде программной функции, которая принимает значение x, а возвращает полученное значение функции (y).
//    static void  func(int xmin, int xmax){
//        int y=0;
//        for(int x = xmin;x<xmax;x++){
//            y = x*x;
//            System.out.println(x+ " "+y);
//        }
//    }
//
//
//
//    static int factorial(int n){
//        int fact = 1;//n=5
//        for(int i = 1; i<=n;i++){
//            fact*=i;
//        }
//        return fact;
//    }
//
//    static int plus(int a, int b){
//        int c = a+b;
//        return c;
//    }
//}

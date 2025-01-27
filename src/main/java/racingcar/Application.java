package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    static int dashStandard=4; //전진 기준
    static int nameStandard=5;
    public static void main(String[] args) {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String car= Console.readLine();
        String[] cars=car.split(",");
        for(int i=0;i< cars.length;i++){
            if(cars[i].length()>nameStandard)
                throw new IllegalArgumentException("다섯 글자 초과");
        }
        int[] dash=new int[cars.length];
        for(int i=0;i<dash.length;i++){
            dash[i]=0;
        }

        System.out.println("시도할 회수는 몇회인가요?");
        int round=Integer.parseInt(Console.readLine());

        System.out.println("실행 결과");
        for(int i=0;i<round;i++){
            carRacing(cars,dash);
        }
        printWinners(whoWins(dash),cars);
    }

    private static void carRacing(String[] cars, int[] dash) {
        for(int i=0;i<cars.length;i++){
            int dice= Randoms.pickNumberInRange(0,9);
            if(dice>=dashStandard){
                dash[i]++;
            }
        }
       for(int i=0;i<cars.length;i++){
           System.out.println(cars[i]+" : "+"-".repeat(dash[i]));
       }
    }

    private static List<Integer> whoWins(int[] dash){
        int max=0;
        List<Integer> winners=new ArrayList<>();
        for(int i=0;i<dash.length;i++){
            if(dash[i]>dash[max]){
                max=i;
            }
        }

        for(int i=0;i<dash.length;i++){
            if(dash[i]==dash[max]){
                winners.add(i);
            }
        }
        return winners;
    }

    private static void printWinners(List<Integer> winners,String[] cars){
        String str="최종 우승자 : "+cars[winners.get(0)];
        for(int i=1;i<winners.size();i++){
            str+=", "+cars[winners.get(i)];
        }
        System.out.println(str);
    }
}

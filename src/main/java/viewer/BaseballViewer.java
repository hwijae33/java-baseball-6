package viewer;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class BaseballViewer {
    private String message;
    //자릿수
    private static int digit = 3;
    //제어
    boolean game = true;
    //유저번호
    static List<Integer>user = new ArrayList<>();
    //야구번호
    static List<Integer> baseball = new ArrayList<>();
    //스트라이크
    private static int strike;
    // 볼
    private static int ball ;


    public void start(){
        while (game){
            System.out.println("숫자 야구 게임을 시작합니다.");
            gamestart();
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            int try_again = Integer.parseInt(Console.readLine());
            if(try_again==1){
                baseball = new ArrayList<>();
                game = true;
            }else if (try_again==2){
                game = false;
                break;
            }
        }
    }

    public static void gamestart(){
        baseball = baseballNum();
        System.out.println(baseball);
        while (true){
            System.out.print("숫자를 입력해주세요.");
            String input = Console.readLine();

            // 검증 완료한 값을 user 리스트에 넣어준다
            user = userNum(input);

            //값 비교
            compare();

            //출력
            if(print()){
               break;
            }
        }
    }

    // 야구 랜덤 숫자 받기
    public static List<Integer> baseballNum(){
        while (baseball.size() < digit) {
            // 1~9자리 랜덤숫자
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            //똑같은 수가 아닌 대상만 baseball 더해준다
            if (!baseball.contains(randomNumber)) {
                baseball.add(randomNumber);
            }
        }
        return baseball;
    }

    // 유저번호
    public static List<Integer> userNum(String input){
        //  1~9에 숫자이며 3자릿수 입력 제한
        if (!isValid(input)) {
            throw new IllegalArgumentException();
        }
        String[] user = input.split("");
        List<Integer>userNum = new ArrayList<>();
        //String[] user = new String[]{input};
        for(String u : user){
            userNum.add(Integer.parseInt(u));
        }
//        for(int i =0; i< user.length; i++){
//            userNum.add(Integer.parseInt(user[i]));
//        }
        return userNum;
    }

    // 유저 입력 검증
    public static boolean isValid(String input){
        Set<Character> seen = new HashSet<>();
        for(int i =0; i < input.length(); i++){
            char num = input.charAt(i);
            if(Character.isDigit(num)){
                int digit = Character.getNumericValue(num);
                if (digit <1 || digit > 9 || seen.contains(num)){
                    return false;
                }
                seen.add(num);
            } else {
                return false;
            }
        }
        return seen.size() ==  digit;
    }

    public static void compare(){
        strike = 0;
        ball = 0;
        for(int i = 0; i < user.size(); i++){
            if(baseball.get(i).equals(user.get(i))){
                strike ++;
            }
            else if(baseball.contains(user.get(i))){
                 ball++;
            }
        }
    }

    public static boolean print(){

        if(strike == 0 && ball == 0){
            System.out.print("낫싱");
        }
        if (ball > 0) {
            System.out.print(ball + "볼 ");
        }
        if (strike > 0 ) {
            System.out.print(strike + "스트라이크");
        }
        System.out.println();
        if (strike == 3) {
            return   true;
        }
        return false;
    }

}

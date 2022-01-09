package HJproject.Hellospring.domain.Practice;

class Latte extends Cafe_B {



    void drink() {
        System.out.println("Cafe_B 나는 라떼를 마셔요");
    }

}

class Moca extends Cafe_B{


    void drink() {
        System.out.println("Cafe_B 나는 모카를 마셔요");
    }
}

public class Cafe_B{

    void where(){
        System.out.println("여기는 Cafe_B");
    }

    void drink(){
        System.out.println("나는 Cafe_B");
    }


}


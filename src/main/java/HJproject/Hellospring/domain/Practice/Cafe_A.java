package HJproject.Hellospring.domain.Practice;

public class Cafe_A {

    public void drink(){
        System.out.println("커피를 마셔요");
    }

    public void where(){
        System.out.println("여기는 CAFE_A");
    }
}


class Capuuccino extends Cafe_A {  // 카페 A 안에 있는 카푸치노



    public void drink(){
        System.out.println("Cafe_A 나는 카푸치노를 마셔요");
    }

}

class Americano extends Cafe_A { // 카페 A 안에 있는 아메리카노


    public void drink(){
        System.out.println("Cafe_A 나는 아메리카노를 마셔요");
    }


}

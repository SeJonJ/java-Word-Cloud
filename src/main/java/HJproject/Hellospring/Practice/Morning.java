package HJproject.Hellospring.Practice;

public class Morning {
    private Cafe_A cafe_a;
    private Cafe_B cafe_b;

    public static void main(String args[]){

//        Morning morning = new Morning();
//
//        morning.cafe_a.where();
//        morning.cafe_a.drink();

        /////////////////////////////////////

        Latte latte = new Latte();
        Morning morning = new Morning(latte);
        morning.cafe_b.where();
        morning.cafe_b.drink();

        Moca moca = new Moca();
        Morning morning2 = new Morning(moca);
        morning2.cafe_b.where();
        morning2.cafe_b.drink();

    }

//    public Morning() {
//        this.cafe_a = new Capuuccino(); // 주인장 왈 : 오늘은 아메리카노만 팔아요 딴 거 마시려면 객체 바꿔주세요
//
//    }

    
    public Morning(Cafe_B cafe_b) {
        this.cafe_b = cafe_b;
        // 주인장 왈 : 객체 만들때 알아서 결정하쇼
    }
}

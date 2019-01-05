package 泛型;

class Base{}
class Dervice extends Base{}
interface  Orgetter{
    Base get();
}

interface Dergetter extends Orgetter{
    Dervice get();
}
/**
 * 方法参数类型会随着子类而变化
 * */
public class 协变参数类型 {
    void test(Dergetter d){
        Dervice dervice = d.get();
    }
}

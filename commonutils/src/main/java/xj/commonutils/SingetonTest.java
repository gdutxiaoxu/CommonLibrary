package xj.commonutils;

/**
 * @author meitu.xujun  on 2017/9/13
 * @version 0.1
 */
public class SingetonTest implements Singleton {

    public  static void  getInstance(){

    }

    @Override
    public void init() {
         new SingetonTest();
    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public Object getLock() {
        return null;
    }
}

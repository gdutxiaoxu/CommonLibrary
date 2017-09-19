package xj.commonutils;

/**
 * @author meitu.xujun  on 2017/9/13
 * @version 0.1
 */
public class SingletonUtil {

    public static void getInstance(Singleton singleton) {
        if (!singleton.isInitialized()) {
            synchronized (singleton.getLock()) {
                if (!singleton.isInitialized()){
                    singleton.init();
                }


            }

        }
    }
}

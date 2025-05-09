package fun.acowbo.simpleaccounting.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public class Assert {

    public static boolean isEmpty(CharSequence s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        for (int i = 0; i < s.length(); ++i) {
            if (' ' != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(Collection<?> obj) {
        return obj == null || obj.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> obj) {
        return obj == null || obj.isEmpty();
    }

    public static boolean isEmpty(Object[] obj) {
        return obj == null || obj.length == 0;
    }

    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    public static boolean isEmpty(List<?> obj) {
        return obj == null || obj.size() == 0;
    }

}

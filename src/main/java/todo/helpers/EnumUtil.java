package todo.helpers;

import java.util.Arrays;

public class EnumUtil {
    public static <T extends Enum<T>> String[] getEnumNames(Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(Enum::name)
                .toArray(String[]::new);
    }
}

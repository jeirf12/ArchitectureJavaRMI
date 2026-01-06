package common.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 *
 * @author jhonfer
 */
public class Console {
    
    private static Map<String, Function<String, Optional<?>>> dataTypeMaps = Map.of(
        "String", value -> !isNumber(value) ? Optional.of(value.trim()) : Optional.empty(),
        "Double", value -> isNumber(value) && value.contains(".") ? Optional.of(Double.valueOf(value.trim())) : Optional.empty(),
        "Float", value -> isNumber(value) && value.contains(".") ? Optional.of(Float.valueOf(value.trim())) : Optional.empty(),
        "Integer", value -> isNumber(value) ? Optional.of(Integer.valueOf(value.trim())) : Optional.empty()
    );

    public static <T> boolean writeJumpLine(T tagMessage, boolean isMessage) {
        try {
            System.out.println(tagMessage);
            if (isMessage) {
                System.out.println("Se escribio correctamente!");
            }
            return true;
        } catch (Exception e) {
            if (isMessage) {
                System.out.println("No se pudo escribir correctamente, debibo a " + e.getMessage());
            }
            return false;
        }
    }

    public static <T> boolean writeJumpLine(T[] list, boolean isMessage) {
        for (int index = 0; index < list.length; index++) {
            if (!writeJumpLine((index + 1) + "." + list[index], isMessage)) {
                writeJumpLine("Existe un dato incorrecto del vector que no se puede mostrar", false);
                writeJumpLine(index + "Posicion del vector con el error", false);
            }
        }
        return true;
    }

    public static <T> boolean write(T tagMessage, boolean isMessage) {
        try {
            System.out.print(tagMessage);
            if (isMessage) {
                writeJumpLine("Se escribio correctamente!", false);
            }
            return true;
        } catch (Exception e) {
            if (isMessage) {
                writeJumpLine("No se pudo escribir correctamente, debibo a " + e.getMessage(), false);
            }
            return false;
        }
    }
    
    @SuppressWarnings("unchecked")
    private static <T> T read(T chainInput, boolean isMessage) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String value = "", option = "";
        T backupChainInput = chainInput;
        try {
            value = input.readLine();
            option = ((Object) chainInput).getClass().getSimpleName();
            chainInput = dataTypeMaps.get(option)
                            .apply(value)
                            .map(v -> (T) v)
                            .orElse(backupChainInput);
            
            if (isMessage) {
                writeJumpLine("Se ha leido correctamente!", false);
            }
        } catch (IOException | NumberFormatException e) {
            if (isMessage) {
                writeJumpLine("No se pudo leer debido al error: " + e.getMessage(), false);
            }
        }
        if (backupChainInput.equals(chainInput)) {
            writeJumpLine("El dato ingresado no es valido", isMessage);
        }
        return chainInput;
    }

    public static <T> T read(String tagMessage, T chainInput, boolean isMessage) {
        T result;
        do {
            writeJumpLine(tagMessage, isMessage);
            result = read(chainInput, isMessage);
        } while (chainInput.equals(result));
        return result;
    }

    public static boolean isNumber(String chain) {
        return chain.matches("[+-]?\\d*(\\.\\d+)?");
    }
}
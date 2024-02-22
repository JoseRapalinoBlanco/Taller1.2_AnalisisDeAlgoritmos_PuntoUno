/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package actividad_analisisdealgoritmos;

public class Actividad_AnalisisDeAlgoritmos {

    //tabla de valores hexadecimales
    private static char[] caracteresValidos_HEX = {'0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    //tabla de valores binarios de los hexadecimales
    private static String[] valoresBinarios = {"0000", "0001", "0010", "0011", "0100", "0101",
        "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"
    };

    //array A y B de ejemplo, puede colocar cualquier valor hex. valido
    private static char[] A = {'A', '4', 'F'};
    private static char[] B = {'1', '0', 'C'};

    //main del programa
    public static void main(String[] args) {

        //SumaHex retorna el array C en hexadecimal
        char[] C = SumaHex(A, B);

        //se convierte C hex. a bin.
        char[] C_Binario = HexadecimalToBinario(C);

        System.out.println("array C binario: " + new String(C_Binario));

    }

    //funcion que recibe los dos arreglos de numeros Hex. A y B y retorna C en Hex.
    private static char[] SumaHex(char[] A, char[] B) {

        /* este es un validador para evitar errores de indice, ejemplo:
        
        hexadecimal 1 = A4F
        hexadecimal 2 = C
        
        si se tratara de sumar posicion por posicion daria error en indice del arreglo 2,
        asi que se iguala el tamaño de los arreglos para poder realizar la suma correctamente
        se rellena con un dato sin valor para no alterar el resultado
   
        quedaria:
        
        hexadecimal 1 = A4F
        hexadecimal 2 = XXC
         */
        while (A.length != B.length) {

            if (A.length > B.length) {

                B = rellenarArreglo(B, 'X');

            } else if (B.length > A.length) {

                A = rellenarArreglo(A, 'X');
            }
        }

        // este entero sera el iterador de cuantas veces sumaremos los digitos de los arreglos
        int NumeroRepeticiones = A.length;

        /* se crea un array C donde se almacenaran los digitos sumados
        normalmente los hexadecimales heredan el numero de digitos del sumando de mayor digitos
        pero si la ultima suma a realizar lleva acarreo el array C crecera + 1 elemento
        el tamaño de C sera modificado mas adelante
         */
        char[] C = new char[NumeroRepeticiones];

        int carreo = 0;

        //se recorrera de derecha a izquierda el arreglo
        for (int i = NumeroRepeticiones - 1; i >= 0; i--) {

            //este entero corresponde al valor decimal de los digitos hexadecimales + acarreo posible
            int valorHex = HexadecimalToDecimal(A[i]) + HexadecimalToDecimal(B[i]) + carreo;

            carreo = 0;

            //while de las restas consecutivas hasta hacer el valor valido en hexadecimal
            while (valorHex > 15) {

                valorHex -= 16;

                carreo++;
            }

            //se le asigna el digito hexadecimal de derecha a izquierda a C
            C[i] = DecimalToHexadecimal(valorHex);

            /* si al terminar todas las sumas con los digitos principales, todavia hay acarreo
            se le asigna el valor del acarreo en hexadecimal al principio del array + los digitos ya trabajados
            
             */
            if (i == 0 && carreo != 0) {
                C = rellenarArreglo(C, DecimalToHexadecimal(carreo));
            }

        }

        System.out.println("array A: " + new String(A));
        System.out.println("array B: " + new String(B));

        System.out.println("array C: " + new String(C));

        return C;
    }

    //funcion que rellena un arreglo de char en la primera posicion y reescribe su info
    private static char[] rellenarArreglo(char N[], char relleno) {

        char[] nuevoArray = new char[N.length + 1];

        nuevoArray[0] = relleno;

        for (int i = 0; i < N.length; i++) {
            nuevoArray[i + 1] = N[i];
        }

        return nuevoArray;

    }

    //funcion que compara el digito hexadecimal "n" con la tabla hex. retorna la posicion = valor
    private static int HexadecimalToDecimal(char n) {

        for (int i = 0; i < caracteresValidos_HEX.length; i++) {
            if (n == caracteresValidos_HEX[i]) {
                return i;
            }
        }

        return 0;

    }

    //como el valor = posicion, despues de las restas consecutivas el valor resultante es devuelto en hex.
    private static char DecimalToHexadecimal(int n) {

        return caracteresValidos_HEX[n];

    }

    //funcion que lee C hex. y retorna C en binario gracias a la tabla predeterminada de valores bin-hex.
    private static char[] HexadecimalToBinario(char[] C) {

        String aux_Cbin = ""; //string donde sera concatenado los valores binarios de C

        for (int i = 0; i < C.length; i++) {
            aux_Cbin += valoresBinarios[HexadecimalToDecimal(C[i])];
        }

        return aux_Cbin.toCharArray();
    }

}

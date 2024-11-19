package mag.ej01.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CalculosService {

    public boolean esPrimo(Integer numero) {

        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false; // Si es divisible por algún número distinto de 1 y sí mismo, no es primo
            }
        }

        return true; // Si no se encontró ningún divisor, es primo
    }

    public double calcularHipotenusa(double x, double y) {
        
        double hipotenusa = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        
        return Math.round(hipotenusa * 100.0) / 100.0; // Redondeo a 2 decimales
     
    }

    public List<Integer> calcularDivisores(int divNum) {
        List<Integer> divisores = new ArrayList<>();
        for (int i = 1; i <= divNum; i++) {
            if (divNum % i == 0) {
                divisores.add(i);
            }
        }
        return divisores;
    }
}
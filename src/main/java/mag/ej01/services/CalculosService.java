package mag.ej01.services;
import org.springframework.stereotype.Service;

@Service
public class CalculosService {
    public Double calcularHipotenusa (Double cat1, Double cat2) throws RuntimeException {
 
        //Condicional excepción catetos menores que cero o mayores que 1000
        if (cat1 <= 0 || cat2 <= 0)
        throw new RuntimeException("Algún cateto menor que cero");
        if (cat1 > 1000 || cat2 > 1000)
        throw new RuntimeException("Algún cateto mayor que 1000");
        return Math.hypot(cat1, cat2);
        }
}
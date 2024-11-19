package mag.ej01.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import mag.ej01.services.CalculosService;

@Controller
public class CalculosController {

    @Autowired(required = true)
    private CalculosService calculosService;

    @GetMapping("/")
    public String showIndex() {
        return "indexView";

    }

    @GetMapping("/calculos/primo/")
    public String showPrimo(@RequestParam(required = false) Integer numero, Model model) {

        // Clausula seguridad, número obligatorio y mayor que cero
        if (numero == null || numero <= 0) {
            model.addAttribute("error", "El parámetro 'numero' es obligatorio y debe ser mayor que cero.");
            return "errorView";
        }

        try {
            // Lógica principal en el @Service
            boolean numeroEsPrimo = calculosService.esPrimo(numero);
            model.addAttribute("numeroAComprobar", numero);
            model.addAttribute("textoPrimo", numeroEsPrimo ? " es primo" : " no es primo");
            return "indexView";
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al procesar la solicitud.");
            return "errorView";
        }
    }

    @GetMapping("/calculos/hipotenusa/{x}/{y}")
    public String showHipotenusa(@PathVariable double x,
            @PathVariable(required = false) double y,
            Model model) {

        // Clausula seguridad, los catetos deben ser mayor que cero
        if (x <= 0 || y <= 0) {
            model.addAttribute("error", "Los catetos deben ser mayores que cero.");
            return "errorView";
        }

        try {
            // Cálculo de la hipotenusa
            double hipotenusa = calculosService.calcularHipotenusa(x, y);
            model.addAttribute("catetoX", x);
            model.addAttribute("catetoY", y);
            model.addAttribute("hipotenusa", hipotenusa);
            return "hipotenusaView";
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al calcular la hipotenusa.");
            return "errorView";
        }
    }

    @GetMapping("/calculos/divisores/{x}")
    public String mostrarDivisores(@PathVariable int x, Model model) {

        // Clausula seguridad, x debe ser mayor que cero
        if (x <= 0) {
            model.addAttribute("error", "el número para saber sus divisores debe ser mayor que cero");
            return "errorView";
        }

        try {
            // Creamos el array de Integer con los divisores
            List<Integer> divisores = calculosService.calcularDivisores(x);

            model.addAttribute("numero", x);
            model.addAttribute("divisores", divisores);

            return "divisoresView";
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al calcular los divisores.");
            return "errorView";
        }

    }

}

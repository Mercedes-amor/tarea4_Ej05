package mag.ej01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import mag.ej01.services.CalculosService;

@Controller
public class CalculosController {

    @Autowired(required = true)
    private CalculosService calculosService;

    private String txtStatus = null;

    @GetMapping("/")
    public String showInit(Model model) {
        if (txtStatus != null) {
            model.addAttribute("txtStatus", txtStatus);
            txtStatus = null; // vacía la variable para poder usarla de nuevo
        }
        return "indexView";
    }

    @GetMapping("/calcularHipotenusa/{cat1}/{cat2}")
    public String showHipot(@PathVariable String cat1, @PathVariable String cat2,
            Model model) {
        Double cateto1, cateto2;
        try {
            cateto1 = Double.parseDouble(cat1); // puede lanzar excepción
            cateto2 = Double.parseDouble(cat2);
            model.addAttribute("resultado",
                    calculosService.calcularHipotenusa(cateto1, cateto2)); // otra excepción
            return "resultadoView";
        } catch (NumberFormatException ex) { 
            //Creamos mensaje personalizado para la excepción NumberFormatException (cuando no es un número)
            txtStatus = "Algún cateto no numérico";
        } catch (RuntimeException ex) {
            txtStatus = ex.getMessage();
        }
        return "redirect:/";
    }
}

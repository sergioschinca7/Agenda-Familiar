package com.agendaFamiliar.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorsController implements ErrorController {

    @RequestMapping(value = "error", method = {RequestMethod.GET, RequestMethod.POST})
    public String mensajesDeError(Model model, HttpServletRequest httpServletRequest) {

        String mensajeError = "";

        int codigoError = (int) httpServletRequest.getAttribute("javax.servlet.error.status_code");

        switch (codigoError) {
            case 400:
                mensajeError = "pagina no encontrada, el servidor no es capaz de entender la  \n petición del navegador porque su sintaxis no es correcta";
                break;
            case 401:
                mensajeError = "No esta Autorizado";
                break;
            case 403:
                mensajeError = "la petición del navegador es correcta, pero el servidor no puede responder con el recurso \n"
                        + "	solicitado porque se ha denegado el acceso";
                break;
            case 404:
                mensajeError = " el servidor no puede encontrar el recurso solicitado por el navegador y no es posible determinar\n"
                        + "	 si esta ausencia es temporal o permanente.";
                break;
            case 405:
                mensajeError = "el navegador ha utilizado un método (GET, POST, etc.) no permitido por el servidor \n"
                        + "	para obtener ese recurso";
                break;
            case 500:
                mensajeError = "la solicitud del navegador no se ha podido completar porque se ha producido un \n"
                        + "	error inesperado en el servidor";
                break;
            default:
        }
        model.addAttribute("codigo", codigoError);
        model.addAttribute("mensaje", mensajeError);
        return "error";
    }

}

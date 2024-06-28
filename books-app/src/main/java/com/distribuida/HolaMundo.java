package com.distribuida;

import java.time.LocalDateTime;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/hola")
public class HolaMundo {
   
    @Inject
    @ConfigProperty(name="app.books.msg",defaultValue = "XX")
    private String message;

    @GET
    public String hola(){
         // API, utilizar esto si nuestro servicio, si tenemos un componente que no sea CDI:
    // Config config = ConfigProvider.getConfig();
    // //  listar las fuentes: ver en el output, cuando se compila si aparece algo diferente de 100
    // // 200, 300, 400, significa que es una configuracion particular
    // config.getConfigSources().forEach(t->(
    //             System.out.printlnf("%d : %s\n", t.getOrdinal(), t.getName());
    //         ));

    //var msg = config.getValue("app.books.msg", String.class);

     //System.out.println(msg);
        return message + LocalDateTime.now();
    }

}
package com.example.hazelcastprueba;

import com.hazelcast.core.*;
import com.hazelcast.map.IMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HazelCastController {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @GetMapping("/hazelcast-example")
    public String hazelcastExample() {
        // Obtener o crear un IMap llamado "miMapa"
        IMap<String, String> miMapa = hazelcastInstance.getMap("miMapa");

        // Añadir datos al mapa
        miMapa.put("clave1", "valor1");
        miMapa.put("clave2", "valor2");

        // Obtener datos del mapa
        StringBuilder result = new StringBuilder();
        result.append("Valor para clave1: ").append(miMapa.get("clave1")).append("\n");
        result.append("Valor para clave2: ").append(miMapa.get("clave2")).append("\n");

        // Realizar una operación en todos los elementos del mapa
        miMapa.replaceAll((key, value) -> value.toUpperCase());

        // Imprimir los valores transformados
        result.append("Valor para clave1 después de la transformación: ").append(miMapa.get("clave1")).append("\n");
        result.append("Valor para clave2 después de la transformación: ").append(miMapa.get("clave2"));

        return result.toString();
    }
}

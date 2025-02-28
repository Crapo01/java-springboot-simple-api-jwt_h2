package com.example.demo.util;

import com.example.demo.model.Response;

public class ResponseUtil {

    public static Response createResponse(String message, Object data) {
        return new Response(message, data);
    }

    // Méthode pour créer une réponse de succès
    public static Response success(String message, Object data) {
        return new Response(message, data);
    }

    // Méthode pour créer une réponse d'erreur
    public static Response error(String message) {
        return new Response(message, null);
    }

}

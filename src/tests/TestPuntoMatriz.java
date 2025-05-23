package tests;

import consola.puntoMatriz;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase puntoMatriz
 * Verifica los métodos específicos de manejo de coordenadas
 */
public class TestPuntoMatriz {

    private puntoMatriz punto;

    @BeforeEach
    void setUp() {
        punto = new puntoMatriz(3, 5);
    }

    @Test
    void testGetX() {
        assertEquals(3, punto.getX(),
                "getX() debe devolver la coordenada X correcta");

        // Probar con diferentes valores
        puntoMatriz punto2 = new puntoMatriz(0, 8);
        assertEquals(0, punto2.getX(),
                "getX() debe funcionar con valor 0");

        puntoMatriz punto3 = new puntoMatriz(8, 0);
        assertEquals(8, punto3.getX(),
                "getX() debe funcionar con valor máximo del tablero");


    }

    @Test
    void testGetY() {
        assertEquals(5, punto.getY(),
                "getY() debe devolver la coordenada Y correcta");

        // Probar con diferentes valores
        puntoMatriz punto2 = new puntoMatriz(8, 0);
        assertEquals(0, punto2.getY(),
                "getY() debe funcionar con valor 0");

        puntoMatriz punto3 = new puntoMatriz(0, 8);
        assertEquals(8, punto3.getY(),
                "getY() debe funcionar con valor máximo del tablero");
    }
}
package tests;

import logique.ResultatExperience;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Christophe Dussault
 * Ordre de conseption : 1e
 */
public class TestsResultatExperience {
    ResultatExperience resultatExperience = null;

    @BeforeEach
    public void ResultatExperiance_initialiser() {
        resultatExperience = new ResultatExperience();
    }

    @Test
    public void ResultatExperiance_setExiste_correct() {
        boolean existInit = resultatExperience.getExiste();

        resultatExperience.setExiste(true);

        assertNotEquals(existInit, resultatExperience.getExiste());
    }

    @Test
    public void ResultatExperiance_setSuccess_correct() {
        boolean succtInit = resultatExperience.getSuccess();

        resultatExperience.setSuccess(true);

        assertNotEquals(succtInit, resultatExperience.getSuccess());
    }
}

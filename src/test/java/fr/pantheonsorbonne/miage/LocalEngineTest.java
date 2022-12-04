package fr.pantheonsorbonne.miage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LocalEngineTest {

    @Test
    public void testChangeRotation() {
        Local local = new Local();
        boolean clockwise = local.getClockwise();
        assertTrue(clockwise);
        local.changeRotation();
        clockwise = local.getClockwise();
        assertTrue(!clockwise);
        local.changeRotation();
        clockwise = local.getClockwise();
        assertTrue(clockwise);
    }

    @Test
    public void testNextTurnIndex() {
        Local local = new Local();
        assertEquals(0, local.getNextPlayer());
        local.nextTurnIndex();
        assertEquals(1, local.getNextPlayer());
        local.changeRotation();
        local.nextTurnIndex();
        assertEquals(0, local.getNextPlayer());
        local.nextTurnIndex();
        assertEquals(local.getInitialPlayers().length - 1, local.getNextPlayer());
        local.changeRotation();
        local.nextTurnIndex();
        assertEquals(0, local.getNextPlayer());
    }

}

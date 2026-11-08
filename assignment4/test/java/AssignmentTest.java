import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class AssignmentTest {

    @Test
    void testEditDistanceReferences() {
        EditDistance ed = new EditDistance();
        assertEquals(3, ed.distanceTable("kitten", "sitting"));
        assertEquals(2, ed.distanceTable("flaw", "lawn"));
        assertEquals(5, ed.distanceTable("intention", "execution"));
        assertEquals(3, ed.distanceTable("", "abc"));
        assertEquals(0, ed.distanceTable("abc", "abc"));
    }

    @Test
    void testEditDistanceMethodsAgreement() {
        EditDistance ed = new EditDistance();
        String a = "algorithm";
        String b = "logarithm";
        int resRec = ed.distanceRecursive(a, b);
        int resTab = ed.distanceTable(a, b);
        int resRows = ed.distanceTwoRows(a, b);
        assertEquals(resRec, resTab);
        assertEquals(resTab, resRows);
    }

    @Test
    void testLpsReferences() {
        StringSearch ss = new StringSearch();
        assertArrayEquals(new int[]{0, 1, 2, 0, 1, 2, 3, 3}, ss.lps("AAACAAAA"));
        assertArrayEquals(new int[]{0, 0, 1, 2, 0, 1, 2, 3, 4}, ss.lps("ABABCABAB"));
    }

    @Test
    void testSearchReferences() {
        StringSearch ss = new StringSearch();
        assertEquals(List.of(10), ss.kmpSearch("ABABDABACDABABCABAB", "ABABCABAB"));
        assertEquals(List.of(0, 1, 2, 3), ss.kmpSearch("AAAAA", "AA"));
        assertEquals(List.of(0, 2, 4), ss.kmpSearch("ABABABA", "ABA"));
    }

    @Test
    void testSearchMethodsAgreement() {
        StringSearch ss = new StringSearch();
        String text = "ababcababababcab";
        String pattern = "ab";
        assertEquals(ss.naiveSearch(text, pattern), ss.kmpSearch(text, pattern));
    }

    @Test
    void testEdgeCases() {
        StringSearch ss = new StringSearch();
        assertThrows(IllegalArgumentException.class, () -> ss.naiveSearch("text", ""));
        assertThrows(IllegalArgumentException.class, () -> ss.kmpSearch("text", null));
        assertTrue(ss.kmpSearch("abc", "abcd").isEmpty());
    }
}
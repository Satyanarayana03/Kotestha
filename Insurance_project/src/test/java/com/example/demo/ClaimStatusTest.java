package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.enumerate.ClaimStatus;
import org.junit.jupiter.api.Test;

public class ClaimStatusTest {

    @Test
    public void testClaimStatusValues() {
        ClaimStatus[] statuses = ClaimStatus.values();
        assertNotNull(statuses);
        assertEquals(5, statuses.length);
        assertEquals(ClaimStatus.FILED, statuses[0]);
        assertEquals(ClaimStatus.REJECTED, statuses[1]);
        assertEquals(ClaimStatus.APPROVED, statuses[2]);
        assertEquals(ClaimStatus.UNDER_REVIEW, statuses[3]);
        assertEquals(ClaimStatus.PENDING, statuses[4]);
    }

    @Test
    public void testClaimStatusValueOf() {
        assertEquals(ClaimStatus.FILED, ClaimStatus.valueOf("FILED"));
        assertEquals(ClaimStatus.REJECTED, ClaimStatus.valueOf("REJECTED"));
        assertEquals(ClaimStatus.APPROVED, ClaimStatus.valueOf("APPROVED"));
        assertEquals(ClaimStatus.UNDER_REVIEW, ClaimStatus.valueOf("UNDER_REVIEW"));
        assertEquals(ClaimStatus.PENDING, ClaimStatus.valueOf("PENDING"));
    }
}

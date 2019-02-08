package com.opelekh;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void main() {
        try {
            App.main(null);
        } catch (Exception e){
            assertNull(e);
        }
    }
}
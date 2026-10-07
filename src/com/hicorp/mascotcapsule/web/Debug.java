package com.hicorp.mascotcapsule.web;

final class Debug {
   private static final boolean DEBUG = true;

   static void fail() {
      throw new RuntimeException("Assertion failed.");
   }

   static void assertTrue(boolean condition) {
      if (!condition) {
         fail();
      }
   }

   static void log(String message) {
      System.out.println(message);
   }
}

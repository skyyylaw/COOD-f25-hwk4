import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrimesTest {

  // ---- isPrime() coverage ----

  @Test
  void isPrime_handlesSmallNonPrimes() {
    assertFalse(Primes.isPrime(-5));
    assertFalse(Primes.isPrime(0));
    assertFalse(Primes.isPrime(1));
  }

  @Test
  void isPrime_smallPrimesAndComposites_viaTrialDivision() {
    assertTrue(Primes.isPrime(2));     // divisible by a prime in PRIMES, equals that prime
    assertTrue(Primes.isPrime(3));
    assertFalse(Primes.isPrime(4));    // divisible by 2 -> false
    assertFalse(Primes.isPrime(21));   // divisible by 3 -> false
  }

  @Test
  void isPrime_largePrime_triggersMillerRabin_t2_t3_t4Assignments() {
    // 2^31 - 1 (2147483647) is prime; exceeds all thresholds, executing t=2, t=3, then t=4
    assertTrue(Primes.isPrime(2147483647));
  }

  @Test
  void isPrime_largeSemiPrime_notCaughtBySmallPrimes_failsMillerRabin() {
    // 10007 and 10009 are primes > 3671; product bypasses the small-prime loop
    int semi = 10007 * 10009; // 100160063
    assertFalse(Primes.isPrime(semi));
  }

  // ---- nextPrime() coverage ----

  @Test
  void nextPrime_negativeReturnsZero() {
    assertEquals(0, Primes.nextPrime(-7)); // note: impl returns 0 (javadoc says -1)
  }

  @Test
  void nextPrime_zeroBecomesOneDueToOr_andReturnsOne() {
    // n|=1 makes 0 -> 1; code explicitly returns 1
    assertEquals(1, Primes.nextPrime(0));
  }

  @Test
  void nextPrime_exactTwoReturnsTwo() {
    assertEquals(2, Primes.nextPrime(2));
  }

  @Test
  void nextPrime_primeOddReturnsItself() {
    assertEquals(3, Primes.nextPrime(3));
    assertEquals(13, Primes.nextPrime(13));
  }

  @Test
  void nextPrime_remEquals0Path_hitsFirstLoopCheck() {
    // 9 % 3 == 0 -> n += 2 -> 11; then first isPrime in while returns
    assertEquals(11, Primes.nextPrime(9));
  }

  @Test
  void nextPrime_remEquals1Path_preBumpBy4_thenReturns() {
    // 25 % 3 == 1 -> n += 4 -> 29; then first isPrime in while returns
    assertEquals(29, Primes.nextPrime(25));
  }

  @Test
  void nextPrime_remEquals2Path_returnsOnSecondCheckAfterPlus2() {
    // 35 % 3 == 2; first while check fails (35), then n += 2 -> 37 prime, second check returns
    assertEquals(37, Primes.nextPrime(35));
  }

  @Test
  void nextPrime_exercisesInLoopPlus4AndMultipleIterations() {
    // 143 % 3 == 2; 143 (fail), +2 -> 145 (fail), +4 -> 149 (next loop: +0 then +2 -> 149 prime)
    assertEquals(149, Primes.nextPrime(143));
  }

  @Test
  void isPrime_compositeEntersInnerLoop_hitsReturnFalseInsideWhile() {
    // Both factors > 3671, product ≡ 1 (mod 4) so (n-1) has at least two trailing zeros -> while runs.
    // 10037 and 10061 are primes; their product is 100_982_257.
    int n = 10037 * 10061; // 100_982_257
    assertFalse(Primes.isPrime(n));
  }
}


package org.system;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class Application {
  public static void main(String[] args) throws NoSuchAlgorithmException {

    HashRing ring = new HashRing(MessageDigest.getInstance("MD5"));

    ring.addNode("A", 5000);
    ring.addNode("B", 5000);
    ring.addNode("C", 5000);
    ring.continuum();

    for(int i = 0; i < 1000; i++) {
      String element = "ABC" + LocalDateTime.now();
      ring.get(element).store.add(element);
      element = "DEF" + LocalDateTime.now();
      ring.get(element).store.add(element);
      element = "GHI" + LocalDateTime.now();
      ring.get(element).store.add(element);
      element = "JKL" + LocalDateTime.now();
      ring.get(element).store.add(element);
      element = "MNO" + LocalDateTime.now();
      ring.get(element).store.add(element);
    }

    for(Node node: ring.nodes) {
      System.out.println(node.store.size());
    }
  }
}

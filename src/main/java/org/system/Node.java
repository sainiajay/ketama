package org.system;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private final String host;
    private final int port;
    private final int weight;
    public List<String> store = new ArrayList<>();

    public Node(String host, int port, int weight) {
        this.host = host;
        this.port = port;
        this.weight = weight;
    }

    public int weight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", weight=" + weight +
                '}';
    }
}

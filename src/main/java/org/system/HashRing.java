package org.system;

import java.nio.ByteBuffer;
import java.util.*;

import java.security.MessageDigest;


public class HashRing {
    public static final int DEFAULT_WEIGHT = 100;
    public static final int DEFAULT_REPLICATION_FACTOR = 3;

    private final MessageDigest md;
    private final TreeMap<Long, Node> ring = new TreeMap<>();
    public final List<Node> nodes = new ArrayList<>();

    public HashRing(MessageDigest mdInstance) {
        this.md = mdInstance;
    }

    public Node get(String key) {
        Long hash = hash(key);
        Map.Entry<Long, Node> entry = this.ring.ceilingEntry(hash);
        if(entry == null) {
            return this.ring.firstEntry().getValue();
        }
        return entry.getValue();
    }

    public void addNode(String host, int port) {
        nodes.add(new Node(host, port, DEFAULT_WEIGHT));
    }

    public void removeNode() {

    }

    public void reset() {

    }

    public void end() {

    }

    public void swapNode() {

    }

    public void range() {

    }

    public void continuum() {
        for(Node node: this.nodes) {
            for (int index = 0; index < node.weight(); index++) {
                String key = String.format("%s:%d", node, index);
                byte[] hash = md.digest(key.getBytes());
                ByteBuffer buffer = ByteBuffer.wrap(hash);
                for(int replica = 0; replica < DEFAULT_REPLICATION_FACTOR; replica++) {
                    int signedVNode = buffer.getInt();
                    ring.put(getUnsigned(signedVNode), node);
                }
            }
        }
        System.out.println("ring size:" + this.ring.size());
    }

    private long getUnsigned(int signed) {
        return signed >= 0 ? signed : 2 * (long) Integer.MAX_VALUE + 2 + signed;
    }

    private Long hash(String key) {
        byte[] digest = md.digest(key.getBytes());
        ByteBuffer buffer = ByteBuffer.wrap(digest);
        return getUnsigned(buffer.getInt());
    }
}

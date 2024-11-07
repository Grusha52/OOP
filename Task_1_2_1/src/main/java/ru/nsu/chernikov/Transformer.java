package ru.nsu.chernikov;

/**
 * Transformer.
 *
 * @param <T> parameter of Edge or Vertex.
 */
public interface Transformer<T> {
    T transform(String str);
}

package utils;

public class Nodo<T> {
    private T value;
    private Nodo<T> left, right; // para poder comunicarme con elementos a la izquierda y derecha.

    public Nodo(T value) { // un nodo que no eta enlazado a ningun lado
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Nodo() {
        this((T) null); // cuando genere un nodo con este, no tiene valor ni apunta a ningun lado, solo asignamos memoria.
    }

    public Nodo(Nodo<T> node) {
        this.value = node.value;
        this.left = node.left;
        this.right = node.right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Nodo<T> getLeft() {
        return left;
    }

    public void setLeft(Nodo<T> left) {
        this.left = left;
    }

    public Nodo<T> getRight() {
        return right;
    }

    public void setRight(Nodo<T> right) {
        this.right = right;
    }
}
